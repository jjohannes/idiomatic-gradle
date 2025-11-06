plugins {
    id("java-platform")
}

// Allow dependencies for dependencies to other platforms (BOMs)
javaPlatform.allowDependencies()

group = "com.example.idiomatic.gradle"

dependencies {
    api(platform(libs.jetty.bom))
}

// Allow upgrading (transitive) versions via catalog by adding constraints
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
