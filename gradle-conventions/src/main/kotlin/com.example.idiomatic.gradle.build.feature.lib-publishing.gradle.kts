// Configure publishing to a Maven repository.
plugins {
    id("maven-publish")
    id("java-library")
}

java {
    withJavadocJar()
    withSourcesJar()
}

publishing.publications.register<MavenPublication>("maven") {
    from(components["java"])
    suppressAllPomMetadataWarnings()
    versionMapping {
        allVariants { fromResolutionOf(configurations.runtimeClasspath.get()) }
    }
}

publishing.repositories.maven("https://repo.onepiece.software/releases") {
    credentials(PasswordCredentials::class)
}
