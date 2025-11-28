// Configure publishing of a Catalog and a Platform (BOM) to a Maven repository.
plugins {
    id("java-platform")
    id("maven-publish")
}

// Allow dependencies for dependencies to other platforms (BOMs).
javaPlatform {
    allowDependencies()
}

publishing.publications.register<MavenPublication>("maven") {
    from(components["javaPlatform"])
}

publishing.repositories.maven("https://repo.onepiece.software/releases") {
    credentials(PasswordCredentials::class)
}
