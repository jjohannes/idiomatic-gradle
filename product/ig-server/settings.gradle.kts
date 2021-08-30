pluginManagement {
    includeBuild("../../libraries")
    includeBuild("../../build-logic")
}

dependencyResolutionManagement {
    repositories.mavenCentral()
    includeBuild("../../platform")

    // For end2end testing
    includeBuild("../../aggregation")

    includeBuild("../ig-data")
}

include("server")