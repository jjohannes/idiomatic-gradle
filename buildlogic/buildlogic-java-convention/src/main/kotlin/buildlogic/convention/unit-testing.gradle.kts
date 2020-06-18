package buildlogic.convention

plugins {
    `java-library`
}

// Use JUnit5
tasks.test.configure {
    useJUnitPlatform()
}
dependencies {
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.6.2")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine")
}
