package buildlogic

// This plugin collects artifacts from the dependent projects to publish everything in a fat Jar
plugins {
    id("buildlogic.versioning")
    `java-library`
    `maven-publish`
}

java {
    withJavadocJar()
    withSourcesJar()
}

java {
    configure {
        createResolvableGraph("sources") {
            extendsFrom(configurations.implementation)
            attributes {
                runtimeUsage()
                documentation("source-folders")
            }
        }
    }
}

val sourcesResolution by configurations.getting

// Configure the 'jar', 'javadoc' and 'sourcesJar' tasks to use the classes/sources of all dependencies as input
tasks {
    jar.configure {
        from(configurations.runtimeClasspath.get().incoming.artifactView {
            attributes.attribute(LibraryElements.LIBRARY_ELEMENTS_ATTRIBUTE, objects.named(LibraryElements.CLASSES))
        }.files.filter { it.isDirectory })
    }
    javadoc.configure {
        classpath = configurations.runtimeClasspath.get()
        // Be lenient as third party dependencies to not offer their source code in a folder (and we do now want to include these in our Javadoc)
        source(sourcesResolution.incoming.artifactView { lenient(true) }.files)
    }
    named<Jar>("sourcesJar").configure {
        // Be lenient as third party dependencies to not offer their source code in a folder (and we do not want to package it)
        from(sourcesResolution.incoming.artifactView { lenient(true) }.files)
    }
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
        }
    }
}

