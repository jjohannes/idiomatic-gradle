// This plugin collects artifacts from the dependent projects to publish everything in a fat Jar
plugins {
    id("com.example.versioning")
    id("java-library")
    id("maven-publish")
}

java {
    withJavadocJar()
    withSourcesJar()
}

val sourcesPath: Configuration by configurations.creating {
    isVisible = false
    isCanBeResolved = true
    isCanBeConsumed = false
    extendsFrom(configurations.implementation.get())
    attributes {
        attribute(Usage.USAGE_ATTRIBUTE, objects.named(Usage.JAVA_RUNTIME))
        attribute(Category.CATEGORY_ATTRIBUTE, objects.named(Category.DOCUMENTATION))
        attribute(DocsType.DOCS_TYPE_ATTRIBUTE, objects.named("source-folders"))
    }
}

// Configure the 'jar', 'javadoc' and 'sourcesJar' tasks to use the classes/sources of all dependencies as input
tasks.jar {
    from(configurations.runtimeClasspath.get().incoming.artifactView {
        attributes.attribute(LibraryElements.LIBRARY_ELEMENTS_ATTRIBUTE, objects.named(LibraryElements.CLASSES))
    }.files.filter { it.isDirectory })
}
tasks.javadoc {
    classpath = configurations.runtimeClasspath.get()
    // Be lenient as third party dependencies to not offer their source code in a folder (and we do now want to include these in our Javadoc)
    source(sourcesPath.incoming.artifactView { lenient(true) }.files)
}
tasks.named<Jar>("sourcesJar") {
    // Be lenient as third party dependencies to not offer their source code in a folder (and we do not want to package it)
    from(sourcesPath.incoming.artifactView { lenient(true) }.files)
}

publishing {
    // A local repository for testing
    val localRepository = layout.buildDirectory.dir("repo")
    repositories {
        maven {
            name = "local"
            url = uri(localRepository)
        }
        maven {
            name = "remote"
            url = uri("https://exmaple.com/repo")
            credentials {
                username = "..."
                password = "..."
            }
        }
    }
    publications {
        create<MavenPublication>("api") {
            from(components["java"])
            versionMapping {
                allVariants {
                    fromResolutionResult()
                }
            }
        }
    }
}

