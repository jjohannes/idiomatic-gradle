pluginManagement {
    includeBuild("../../gradle/plugins")
}

plugins {
    id("com.example.repositories")
}

dependencyResolutionManagement {
    includeBuild("../ig-data")
}

include("api")