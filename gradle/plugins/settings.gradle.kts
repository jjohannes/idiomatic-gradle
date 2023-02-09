dependencyResolutionManagement {
    repositories.mavenCentral()
    repositories.gradlePluginPortal()
    includeBuild("../platform")

    versionCatalogs.create("libs") {
        from("com.example.idiomatic.gradle:catalog")
    }
}

include("java-convention")
include("lifecycle")
include("packaging")
include("publication")
include("versioning")
