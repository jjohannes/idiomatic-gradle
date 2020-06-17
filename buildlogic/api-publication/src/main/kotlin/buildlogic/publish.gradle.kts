package buildlogic

plugins {
    id("buildlogic.versioning")
    `java-library`
    `maven-publish`
}

java {
    // TODO assemble fat Jars for these
    withJavadocJar()
    withSourcesJar()
}

val localRepository = layout.buildDirectory.dir("repo")

tasks.jar.configure {
    from(configurations.runtimeClasspath.get().incoming.artifactView {
        attributes.attribute(LibraryElements.LIBRARY_ELEMENTS_ATTRIBUTE, objects.named(LibraryElements.CLASSES))
    }.files.filter { it.isDirectory })
}

publishing {
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
