pluginManagement {
    includeBuild("../../gradle/settings")
}

plugins {
    id("com.example.repositories")
}

dependencyResolutionManagement {
    includeBuild("../ig-data")
}

include("server")