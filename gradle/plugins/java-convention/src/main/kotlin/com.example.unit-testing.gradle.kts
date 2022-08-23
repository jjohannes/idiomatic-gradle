plugins {
    id("java-library")
    id("com.example.libraries")
}

tasks.test {
    useJUnitPlatform() // Use JUnit5
}

dependencies {
    testImplementation(libs.junitApi)
    testRuntimeOnly(libs.junitEngine)
}
