plugins {
    `kotlin-dsl`
}

group = "buildlogic"

dependencies {
    implementation("buildlogic:versioning")
    implementation("buildlogic:libraries")
}

repositories {
    mavenCentral()
}