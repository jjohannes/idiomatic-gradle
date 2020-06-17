rootProject.name = "idiomatic-gradle"

includeBuild("buildlogic/buildlogic-versioning")
includeBuild("buildlogic/buildlogic-java-convention")
includeBuild("buildlogic/buildlogic-packaging")
includeBuild("buildlogic/buildlogic-publication")

subproject("product", "platform")
subproject("product/common", "ig-data")
subproject("product/server", "ig-server")
subproject("product/api", "ig-api")

subproject("aggregation", "publish-api")
subproject("aggregation", "package-server")

fun subproject(category: String, name: String) {
    include(name)
    rootProject.children.first { it.name == name }.projectDir = file("subprojects/$category/$name")
}
