plugins {
    id("com.example.idiomatic.gradle.build.module.lib")
}

dependencies {
    implementation(libs.javarca.model)
    implementation(libs.commons.io)

    testImplementation(libs.junit.api)
}
