plugins {
    id("com.example.idiomatic.gradle.build.module.lib")
    id("com.example.idiomatic.gradle.build.feature.end2end-testing")
}

dependencies {
    implementation(libs.javarca.model)
    implementation(libs.commons.csv)

    testImplementation(libs.junit.api)

    end2endTestImplementation(testFixtures(libs.javarca.engine))
}
