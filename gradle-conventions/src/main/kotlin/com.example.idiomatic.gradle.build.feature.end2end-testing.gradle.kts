// Configure a 'end2endTest' test suite.
plugins {
    id("java-library")
}

val junitVersion = the<VersionCatalogsExtension>().named("libs").findLibrary("junit.api").get().get().version

@Suppress("UnstableApiUsage")
testing.suites.register<JvmTestSuite>("end2endTest") {
    useJUnitJupiter(junitVersion)
}
