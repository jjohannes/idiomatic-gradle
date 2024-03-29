pluginManagement {
    // Not traditional 'buildSrc', but 'gradle/plugins' as a normal included build
    includeBuild("../../gradle/plugins")
}

dependencyResolutionManagement {
    repositories.mavenCentral()
    // Platform for dependency versions shared by 'main build' and 'build-logic'
    includeBuild("../../gradle/platform")
    // For end2end testing
    includeBuild("../../aggregation")

    // For the version catalog 'libs.versions.toml' shared by all builds/components in the repository
    versionCatalogs.create("libs") {
        from(files("../../gradle/libs.versions.toml"))
    }
}
