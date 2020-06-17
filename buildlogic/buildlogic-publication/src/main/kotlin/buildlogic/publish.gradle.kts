package buildlogic

plugins {
    id("buildlogic.versioning")
    `java-library`
    `maven-publish`
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

val localRepository = layout.buildDirectory.dir("repo")

tasks.jar.configure {
    from(configurations.runtimeClasspath.get().incoming.artifactView {
        attributes.attribute(LibraryElements.LIBRARY_ELEMENTS_ATTRIBUTE, objects.named(LibraryElements.CLASSES))
    }.files.filter { it.isDirectory })
}

tasks.javadoc.configure {
    classpath = configurations.runtimeClasspath.get()
    source(sourcesPath.incoming.artifactView { lenient(true) }.files)
}

tasks.named<Jar>("sourcesJar").configure {
    from(sourcesPath.incoming.artifactView { lenient(true) }.files)
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
