pluginManagement {
    includeBuild("../libraries")
}
dependencyResolutionManagement {
    repositories.mavenCentral()
    includeBuild("../libraries")
    includeBuild("../platform")
}

include("java-convention")
include("lifecycle")
include("packaging")
include("publication")
include("versioning")
