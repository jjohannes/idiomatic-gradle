plugins {
    id("com.example.idiomatic.gradle.build.module.lib")
}

dependencies {
    api(libs.javarca.model)
    implementation(libs.slf4j.api)

    testImplementation(libs.junit.api)
}
