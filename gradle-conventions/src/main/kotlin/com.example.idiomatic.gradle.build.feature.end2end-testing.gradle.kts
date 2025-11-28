// Configure a 'end2endTest' test suite.
plugins {
    id("java-library")
}

val junitVersion = the<VersionCatalogsExtension>().named("libs").findLibrary("junit.api").get().get().version

@Suppress("UnstableApiUsage")
testing.suites.register<JvmTestSuite>("testEnd2end") {
    useJUnitJupiter(junitVersion)
    targets.configureEach {
        testTask {
            // testing needs to be performed with a renderer implementation that uses this env setting.
            environment("PRESENTATION_FOLDER", project.layout.buildDirectory.dir("test-screenshot").get().asFile)
        }
    }
}
