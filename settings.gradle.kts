rootProject.name = "idiomatic-gradle"

// Convenience to provide constants for library GA coordinates
includeBuild("libraries")

// Platform for dependency versions shared by 'main build' and 'build-src'
includeBuild("platform")

// Not traditional 'buildSrc', but 'build-src' as a normal included build
includeBuild("build-src")

// The actual product. This could be split up into multiple builds, if the product consists of multiple components.
includeBuild("product")

// Build responsible for assembling the final delivery or producing reports (does not contain production code)
includeBuild("aggregation")
