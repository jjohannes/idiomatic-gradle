plugins {
    id("java-library")
}

tasks.test {
    useJUnitPlatform() // Use JUnit5
}

dependencies {
    testImplementation("org.junit.jupiter:junit-jupiter-api")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine")
}
