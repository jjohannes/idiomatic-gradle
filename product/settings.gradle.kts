includeBuild("../libraries")
includeBuild("../platform")
includeBuild("../build-src")

// For end2end testing
includeBuild("../aggregation") { name = "aggregation-for-en2end-testing"}

include("ig-data")
include("ig-server")
include("ig-api")