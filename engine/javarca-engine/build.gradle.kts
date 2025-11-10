plugins {
    id("com.example.idiomatic.gradle.build.module.lib")
    id("com.example.idiomatic.gradle.build.feature.test-fixtures")
}

dependencies {
    api(libs.javarca.model)
    implementation(libs.slf4j.api)

    testImplementation(libs.junit.api)

    testFixturesImplementation(libs.slf4j.api)
    testFixturesRuntimeOnly(libs.renderer.lwjgl)
    testFixturesRuntimeOnly(libs.slf4j.simple)
}
