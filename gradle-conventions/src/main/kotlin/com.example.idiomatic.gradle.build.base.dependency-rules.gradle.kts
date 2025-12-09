plugins {
    id("org.gradlex.jvm-dependency-conflict-resolution")
    id("io.mvnpm.gradle.plugin.native-java-plugin")
}

// Versions of 3rd party libraries are provided through a catalog.
jvmDependencyConflicts {
    // Set up consistent version management through a platform (BOM)
    val catalogVersion = the<CatalogVersion>().version.getOrElse("")
    consistentResolution {
        platform("com.example.idiomatic.gradle:dependency-versions:$catalogVersion")
    }

    // Add missing information to metadata of 3rd-party libraries.
    // ...
}
