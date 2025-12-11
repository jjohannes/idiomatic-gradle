pluginManagement {
    if (gradle.parent != null) { includeBuild("../idiomatic-gradle/gradle-conventions") }
    repositories.maven("https://repo.onepiece.software/releases")
    repositories.gradlePluginPortal()
}

plugins {
    id("com.example.idiomatic.gradle.build") version "0.5"
}

catalogVersion.version = "1.2"
