plugins {
    `kotlin-dsl`
}

group = "buildlogic"

dependencies {
    implementation(project(":buildlogic-versioning"))
}

repositories {
    mavenCentral()
}