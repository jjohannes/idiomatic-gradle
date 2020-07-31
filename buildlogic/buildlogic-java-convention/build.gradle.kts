plugins {
    `kotlin-dsl`
    id("buildlogic.libraries")
}

group = "buildlogic"

dependencies {
    implementation(project(":buildlogic-versioning"))
    implementation("buildlogic:libraries")

    implementation(platform("com.example.idiomatic.gradle:platform"))
    implementation(libs.guava)
}

repositories {
    mavenCentral()
}