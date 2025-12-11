pluginManagement {
    includeBuild("gradle-conventions")
}

rootProject.name = "idiomatic-gradle"

File(rootDir, "jamcatch").copyRecursively(File(rootDir, "../jamcatch"))

dependencyResolutionManagement {
    // Version management of all external libraries
    includeBuild("dependency-versions")

    // Build responsible for assembling the final delivery or producing reports (does not contain production code)
    includeBuild("aggregation")

    // The actual product split into multiple components.
    includeBuild("engine")
    includeBuild("../jamcatch")
    includeBuild("renderer")
}
