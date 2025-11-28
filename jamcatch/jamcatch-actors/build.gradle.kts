plugins {
    id("com.example.idiomatic.gradle.build.module.lib")
    id("com.example.idiomatic.gradle.build.feature.end2end-testing")
}

dependencies {
    api(libs.javarca.model)
    implementation(libs.commons.csv)

    testImplementation(libs.junit.api)

    testEnd2endImplementation(testFixtures(libs.javarca.engine))
}
