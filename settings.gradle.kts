pluginManagement {
    includeBuild("gradle-conventions")
}

rootProject.name = "idiomatic-gradle"

dependencyResolutionManagement {
    // Version management of all external libraries
    includeBuild("dependency-versions")

    // Build responsible for assembling the final delivery or producing reports (does not contain production code)
    includeBuild("aggregation")

    // The actual product split into multiple components.
    includeBuild("ig-api")
    includeBuild("ig-data")
    includeBuild("ig-server")
}
