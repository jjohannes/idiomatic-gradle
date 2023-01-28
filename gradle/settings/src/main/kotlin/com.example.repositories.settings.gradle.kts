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
}
