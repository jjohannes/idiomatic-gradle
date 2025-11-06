plugins {
    id("java-library")
}

tasks.test {
    useJUnitPlatform() // Use JUnit5
}

val libs = the<VersionCatalogsExtension>().named("libs")
dependencies {
    testImplementation(libs.findLibrary("junit.api").get())
    testRuntimeOnly(libs.findLibrary("junit.engine").get())
    testRuntimeOnly(libs.findLibrary("junit.launcher").get())
}
