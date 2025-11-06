pluginManagement {
    includeBuild("../gradle/plugins")
}

dependencyResolutionManagement {
    repositories.mavenCentral()

    includeBuild("../platform")

    includeBuild("../product/ig-server")

    include("publish-api")
    include("package-server")
}