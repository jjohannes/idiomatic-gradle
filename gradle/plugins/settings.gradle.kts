dependencyResolutionManagement {
    repositories.mavenCentral()
    includeBuild("../platform")

    versionCatalogs.create("libs") {
        from(files("../libs.versions.toml"))
    }
}

include("java-convention")
include("lifecycle")
include("packaging")
include("publication")
include("versioning")
