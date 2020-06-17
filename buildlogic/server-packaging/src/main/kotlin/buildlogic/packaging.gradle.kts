package buildlogic

import java.nio.file.Files
import java.util.jar.Attributes
import java.util.zip.ZipFile

plugins {
    id("buildlogic.versioning")
    `java-base`
}

val artifactType = Attribute.of("artifactType", String::class.java)

val packaging: Configuration by configurations.creating {
    isVisible = false
    isCanBeResolved = false
    isCanBeConsumed = false
}
val packagingClasspath: Configuration by configurations.creating {
    isVisible = false
    isCanBeResolved = true
    isCanBeConsumed = false
    extendsFrom(packaging)
    attributes {
        attribute(Usage.USAGE_ATTRIBUTE, objects.named(Usage.JAVA_RUNTIME))
        attribute(Category.CATEGORY_ATTRIBUTE, objects.named(Category.LIBRARY))
        attribute(LibraryElements.LIBRARY_ELEMENTS_ATTRIBUTE, objects.named(LibraryElements.CLASSES))
        attribute(Bundling.BUNDLING_ATTRIBUTE, objects.named(Bundling.EXTERNAL))
    }
}

val fatJar by tasks.registering(Jar::class) {
    from(packagingClasspath.incoming.artifactView {
        attributes.attribute(artifactType, LibraryElements.CLASSES)
    }.files)
    duplicatesStrategy = DuplicatesStrategy.EXCLUDE
    manifest.attributes(mapOf(
            Attributes.Name.MAIN_CLASS.toString() to "com.example.idiomatic.gradle.server.IgServer"))
}
tasks.assemble.configure {
    dependsOn(fatJar)
}
val runtimeElements: Configuration by configurations.creating {
    isVisible = false
    isCanBeResolved = false
    isCanBeConsumed = true
    outgoing.artifact(fatJar)

    attributes {
        attribute(Usage.USAGE_ATTRIBUTE, objects.named(Usage.JAVA_RUNTIME))
        attribute(Category.CATEGORY_ATTRIBUTE, objects.named(Category.LIBRARY))
        attribute(LibraryElements.LIBRARY_ELEMENTS_ATTRIBUTE, objects.named(LibraryElements.JAR))
        attribute(Bundling.BUNDLING_ATTRIBUTE, objects.named(Bundling.EMBEDDED))
    }
}


dependencies {
    registerTransform(ClassesExtraction::class.java) {
        from.attribute(artifactType, LibraryElements.JAR)
        to.attribute(artifactType, LibraryElements.CLASSES)
    }
}

abstract class ClassesExtraction : TransformAction<TransformParameters.None> {

    @get:InputArtifact
    abstract val inputArtifact: Provider<FileSystemLocation>

    override fun transform(outputs: TransformOutputs) {
        val input = inputArtifact.get().asFile
        val output = outputs.dir("classes")
        extractJar(input, output)
    }
    private fun extractJar(input: File, output: File) {
        ZipFile(input).use { jarFile ->
            jarFile.entries().asIterator().forEach { zipEntry ->
                jarFile.getInputStream(zipEntry).use { file ->
                    if (!zipEntry.isDirectory) {
                        val path = output.toPath().resolve(zipEntry.name)
                        Files.createDirectories(path.parent)
                        Files.copy(file, path)
                    }
                }
            }
        }
    }
}