// Configure publishing of a Catalog and a Platform (BOM) to a Maven repository.
plugins {
    id("java-platform")
    id("version-catalog")
    id("maven-publish")
}

// Allow dependencies for dependencies to other platforms (BOMs).
javaPlatform {
    allowDependencies()
}

// Add all entiers from the Catalog also to the BOM.
dependencies.constraints {
    val libs = versionCatalogs.named("libs")
    val catalogEntries = libs.libraryAliases.map { libs.findLibrary(it).get().get() }
    catalogEntries.forEach { entry ->
        val version = entry.version
        if (version != null) {
            api(entry) { version { require(version) } }
        }
    }
}

// Catalog is defined in a TOML file.
configurations.versionCatalogElements {
    outgoing.artifacts.clear()
    outgoing.artifact(layout.projectDirectory.file("libs.versions.toml"))
}

// The Catalog is added as an additional variant to the 'javaPlatform' component that is published.
val javaPlatform = components["javaPlatform"] as AdhocComponentWithVariants
javaPlatform.addVariantsFromConfiguration(configurations.versionCatalogElements.get()) { }

publishing.publications.register<MavenPublication>("maven") {
    from(components["javaPlatform"])
    pom.packaging = "pom" // ensure this is 'pom', not 'toml', to conform to Maven BOM standards
}

publishing.repositories.maven("https://repo.onepiece.software/releases") {
    credentials(PasswordCredentials::class)
}
