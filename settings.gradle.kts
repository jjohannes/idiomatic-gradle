rootProject.name = "idiomatic-gradle"

dependencyResolutionManagement {
    // The actual product split into multiple components.
    includeBuild("product/ig-api")
    includeBuild("product/ig-data")
    includeBuild("product/ig-server")

    // Build responsible for assembling the final delivery or producing reports (does not contain production code)
    includeBuild("aggregation")
}

// Turned off by default for now - still causes deprecations in Gradle 8.0 when used with `kotlin-dsl`
// enableFeaturePreview("STABLE_CONFIGURATION_CACHE")
