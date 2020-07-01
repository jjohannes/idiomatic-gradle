plugins {
    `kotlin-dsl`
    id("buildlogic.convention.java")
}

group = "buildlogic"

dependencies {
    implementation("buildlogic:versioning")
}

repositories {
    mavenCentral()
}