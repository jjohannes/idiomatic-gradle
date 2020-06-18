package buildlogic

import java.nio.file.Files
import java.util.jar.Attributes
import java.util.zip.ZipFile

// This plugin collects artifacts from the dependent projects to publish everything as a stand-alone executable Jar with a 'IgServer' class as entry point
plugins {
    id("buildlogic.versioning")
    `java-base` // Note: because many things from 'java-library' are not needed (e.g. projects applying this plugin have no src code), we only apply 'java-base'.
}

java {
    configure {
        createResolvableGraph("packaging") {
            requiresJavaLibrariesRuntime()
            attributes {
                library(LibraryElements.CLASSES)
            }
        }
    }
}

// A Jar task that collects all classes from dependent projects - to obtain the classes of external dependencies, a artifact transform is registered to extract Jars
val artifactType = Attribute.of("artifactType", String::class.java)
val executableFatJar by tasks.registering(Jar::class) {
    from(configurations.getByName("packagingResolution").incoming.artifactView {
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
java {
    configure {
        createOutgoingElements("runtimeElements") {
            providesRuntime()
            addArtifact(executableFatJar)
        }
    }
}

// Make building the executable jar part of the 'assemble' lifecycle phase
tasks.assemble.configure {
    dependsOn(executableFatJar)
}
