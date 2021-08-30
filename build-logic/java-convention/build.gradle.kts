plugins {
    `kotlin-dsl`
    id("com.example.libraries")
}

group = "com.example.buildlogic"

dependencies {
    implementation(project(":versioning"))
    implementation("com.example.buildlogic:libraries")

    implementation(platform("com.example.idiomatic.gradle:platform"))
    implementation(libs.guava)
}
