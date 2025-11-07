plugins {
    `kotlin-dsl`
    id("maven-publish")
}

group = "com.example.idiomatic.gradle"
version = "0.3"

dependencies {
    // examples of using a Gradle community plugin
    implementation("org.gradlex:jvm-dependency-conflict-resolution:2.4")
    implementation("org.gradlex:reproducible-builds:1.1")
}

publishing.repositories.maven("https://repo.onepiece.software/releases") {
    credentials(PasswordCredentials::class)
}
