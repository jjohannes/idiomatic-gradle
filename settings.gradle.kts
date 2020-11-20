rootProject.name = "idiomatic-gradle"

// Convenience to provide constants for library GA coordinates
includeBuild("libraries")

// Platform for dependency versions shared by 'main build' and 'build-logic'
includeBuild("platform")

// Not traditional 'buildSrc', but 'build-logic' as a normal included build
includeBuild("build-logic")

// The actual product split into multiple components.
includeBuild("product/ig-api")
includeBuild("product/ig-data")
includeBuild("product/ig-server")

// Build responsible for assembling the final delivery or producing reports (does not contain production code)
includeBuild("aggregation")
