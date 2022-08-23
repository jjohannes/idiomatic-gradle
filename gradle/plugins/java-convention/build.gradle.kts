plugins {
    `kotlin-dsl`
}

group = "com.example.buildlogic"

dependencies {
    implementation(project(":versioning"))

    implementation(platform("com.example.idiomatic.gradle:platform"))
    implementation(libs.guava)
}
