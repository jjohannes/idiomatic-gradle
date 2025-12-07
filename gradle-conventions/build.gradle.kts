plugins {
    `kotlin-dsl`
    id("maven-publish")
}

group = "com.example.idiomatic.gradle"
version = "0.4"

dependencies {
    // examples of using a Gradle community plugin
    implementation("org.gradlex:jvm-dependency-conflict-resolution:2.5")
    implementation("org.gradlex:reproducible-builds:1.1")
    implementation("io.mvnpm.gradle.plugin:native-java-plugin:1.0.0")
}

publishing.repositories.maven("https://repo.onepiece.software/releases") {
    credentials(PasswordCredentials::class)
}
