// Configure unit testing.
plugins {
    id("java")
}

@Suppress("UnstableApiUsage")
testing.suites.named<JvmTestSuite>("test") {
    useJUnitJupiter()
}
