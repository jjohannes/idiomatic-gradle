// Configure unit testing.
plugins {
    id("java")
}

val junitVersion = the<VersionCatalogsExtension>().named("libs").findLibrary("junit.api").get().get().version!!

@Suppress("UnstableApiUsage")
testing.suites.named<JvmTestSuite>("test") {
    useJUnitJupiter(junitVersion)
}
