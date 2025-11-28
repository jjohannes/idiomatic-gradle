plugins {
    id("com.example.idiomatic.gradle.build.module.lib")
    id("com.example.idiomatic.gradle.build.feature.end2end-testing")
}

dependencies {
    api("com.example.idiomatic.gradle:javarca-model")
    implementation("commons-io:commons-io")

    testImplementation("org.junit.jupiter:junit-jupiter-api:junit-api")

    testEnd2endImplementation(testFixtures("com.example.idiomatic.gradle:javarca-engine"))
}
