plugins {
    id("com.example.idiomatic.gradle.build.module.lib")
    id("com.example.idiomatic.gradle.build.feature.end2end-testing")
}

dependencies {
    api("com.example.idiomatic.gradle:javarca-model")
    implementation("org.slf4j:slf4j-api")

    testImplementation("org.junit.jupiter:junit-jupiter-api")

    testEnd2endImplementation(testFixtures("com.example.idiomatic.gradle:javarca-engine"))
}
