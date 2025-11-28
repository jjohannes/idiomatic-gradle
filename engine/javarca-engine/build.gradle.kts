plugins {
    id("com.example.idiomatic.gradle.build.module.lib")
    id("com.example.idiomatic.gradle.build.feature.test-fixtures")
}

dependencies {
    api("com.example.idiomatic.gradle:javarca-model")
    implementation("org.slf4j:slf4j-api")

    testImplementation("org.junit.jupiter:junit-jupiter-api")

    testFixturesImplementation("org.slf4j:slf4j-api")
    testFixturesRuntimeOnly("com.example.idiomatic.gradle:renderer-lwjgl")
    testFixturesRuntimeOnly("org.slf4j:slf4j-simple")
}
