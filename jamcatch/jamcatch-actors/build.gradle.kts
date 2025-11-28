plugins {
    id("com.example.idiomatic.gradle.build.module.lib")
    id("com.example.idiomatic.gradle.build.feature.end2end-testing")
}

dependencies {
    api("com.example.idiomatic.gradle:javarca-model")
    implementation("org.apache.commons:commons-csv")

    testImplementation("org.junit.jupiter:junit-jupiter-api")

    testEnd2endImplementation(testFixtures("com.example.idiomatic.gradle:javarca-engine"))
}
