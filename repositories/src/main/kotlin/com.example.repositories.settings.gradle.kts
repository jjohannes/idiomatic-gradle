pluginManagement {
    // Convenience to provide constants for library GA coordinates
    includeBuild("../../libraries")
    // Not traditional 'buildSrc', but 'build-logic' as a normal included build
    includeBuild("../../build-logic")
}

dependencyResolutionManagement {
    repositories.mavenCentral()
    // Platform for dependency versions shared by 'main build' and 'build-logic'
    includeBuild("../../platform")
    // For end2end testing
    includeBuild("../../aggregation")
}
