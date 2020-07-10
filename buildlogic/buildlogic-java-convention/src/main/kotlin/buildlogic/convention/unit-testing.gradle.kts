package buildlogic.convention

plugins {
    `java-library`
    id("buildlogic.libraries")
}

// Use JUnit5
tasks.test.configure {
    useJUnitPlatform()
}

dependencies {
    testImplementation(libs.junitApi)
    testRuntimeOnly(libs.junitEngine)
}
