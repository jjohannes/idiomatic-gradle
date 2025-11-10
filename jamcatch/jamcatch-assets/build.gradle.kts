plugins {
    id("com.example.idiomatic.gradle.build.module.lib")
    id("com.example.idiomatic.gradle.build.feature.end2end-testing")
}

dependencies {
    api(libs.javarca.model)
    implementation(libs.commons.io)

    testImplementation(libs.junit.api)

    end2endTestImplementation(testFixtures(libs.javarca.engine))
}
