package buildlogic

import java.nio.file.Files
import java.util.jar.Attributes
import java.util.zip.ZipFile

// This plugin collects artifacts from the dependent projects to publish everything as a stand-alone executable Jar with a 'IgServer' class as entry point
plugins {
    id("buildlogic.versioning")
    `java-base` // Note: because many things from 'java-library' are not needed (e.g. projects applying this plugin have no src code), we only apply 'java-base'.
}

// Configurations to declare dependencies
val packaging: Configuration by configurations.creating {
    isVisible = false
    isCanBeResolved = false
    isCanBeConsumed = false
}

// Resolvable configuration to resolve the classes of all dependencies
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

// A Jar task that collects all classes from dependent projects - to obtain the classes of external dependencies, a artifact transform is registered to extract Jars
val artifactType = Attribute.of("artifactType", String::class.java)
val executableFatJar by tasks.registering(Jar::class) {
    from(packagingClasspath.incoming.artifactView {
        attributes.attribute(artifactType, LibraryElements.CLASSES)
    }.files)
    duplicatesStrategy = DuplicatesStrategy.EXCLUDE
    manifest.attributes(mapOf(
            Attributes.Name.MAIN_CLASS.toString() to "com.example.idiomatic.gradle.server.IgServer"))
}
dependencies.registerTransform(ClassesExtraction::class) {
    from.attribute(artifactType, LibraryElements.JAR)
    to.attribute(artifactType, LibraryElements.CLASSES)
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

// Consumable configuration such that other projects can consume the executable Jar for end2end testing
configurations.create("runtimeElements") {
    isVisible = false
    isCanBeResolved = false
    isCanBeConsumed = true
    outgoing.artifact(executableFatJar)

    attributes {
        attribute(Usage.USAGE_ATTRIBUTE, objects.named(Usage.JAVA_RUNTIME))
        attribute(Category.CATEGORY_ATTRIBUTE, objects.named(Category.LIBRARY))
        attribute(LibraryElements.LIBRARY_ELEMENTS_ATTRIBUTE, objects.named(LibraryElements.JAR))
        attribute(Bundling.BUNDLING_ATTRIBUTE, objects.named(Bundling.EMBEDDED))
    }
}

// Make building the executable jar part of the 'assemble' lifecycle phase
tasks.assemble.configure {
    dependsOn(executableFatJar)
}
