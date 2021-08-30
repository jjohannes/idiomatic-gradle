pluginManagement {
    includeBuild("../libraries")
    includeBuild("../build-logic")
}

dependencyResolutionManagement {
    repositories.mavenCentral()

    includeBuild("../platform")

    includeBuild("../product/ig-server")

    include("publish-api")
    include("package-server")
}