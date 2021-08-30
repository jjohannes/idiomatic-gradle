pluginManagement {
    includeBuild("../../repositories")
}

plugins {
    id("com.example.repositories")
}

dependencyResolutionManagement {
    includeBuild("../ig-data")
}

include("server")