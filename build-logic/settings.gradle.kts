rootProject.name = "build-logic"

pluginManagement {
    includeBuild("../libraries")
}
dependencyResolutionManagement {
    repositories.mavenCentral()
}

include("java-convention")
include("packaging")
include("publication")
include("versioning")
