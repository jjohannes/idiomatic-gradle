plugins {
    `kotlin-dsl`
}

group = "com.example.buildlogic"

dependencies {
    implementation(project(":versioning"))

    implementation(platform("com.example.idiomatic.gradle:platform"))
    implementation(libs.guava) // example of using a standard Java library in your plugins
    implementation(libs.gradlex.java.ecosystem.capabilities) // example of using a Gradle community plugin
}
