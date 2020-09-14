rootProject.name = "idiomatic-gradle"

// Convenience to provide constants for library GA coordinates
includeBuild("libraries")

// Platform for dependency versions shared by 'main build' and 'build-src'
includeBuild("platform")

// Not traditional 'buildSrc', but 'build-src' as a normal included build
includeBuild("build-src")

subproject("product/common", "ig-data")
subproject("product/server", "ig-server")
subproject("product/api", "ig-api")

subproject("aggregation", "publish-api")
subproject("aggregation", "package-server")

fun subproject(category: String, name: String) {
    include(name)
    rootProject.children.first { it.name == name }.projectDir = file("subprojects/$category/$name")
}
