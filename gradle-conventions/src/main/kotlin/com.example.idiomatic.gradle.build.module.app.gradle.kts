plugins {
    id("application")
    id("jacoco")
    id("com.example.idiomatic.gradle.build.base.versioning")
    id("com.example.idiomatic.gradle.build.base.dependency-rules")
    id("com.example.idiomatic.gradle.build.feature.compilation")
    id("com.example.idiomatic.gradle.build.feature.unit-testing")
    id("com.example.idiomatic.gradle.build.feature.end2end-testing")
    id("org.gradlex.jvm-dependency-conflict-resolution")
}

tasks.jar { enabled = false } // no own source code or Jar

tasks.register("publish") // nothing is published
