plugins {
    id("java-library")
    id("com.example.libraries")
}

// Use JUnit5
tasks.test.configure {
    useJUnitPlatform()
}

dependencies {
    testImplementation(libs.junitApi)
    testRuntimeOnly(libs.junitEngine)
}
