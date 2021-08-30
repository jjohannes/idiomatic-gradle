rootProject.name = "build-logic"

pluginManagement {
    includeBuild("../libraries")
}
dependencyResolutionManagement {
    repositories.mavenCentral()
}

include("java-convention")
include("lifecycle")
include("packaging")
include("publication")
include("versioning")
