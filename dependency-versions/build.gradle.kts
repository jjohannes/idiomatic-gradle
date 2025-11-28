plugins {
    id("com.example.idiomatic.gradle.build.module.versions")
}

val javarca = "1.0"
val renderer = "1.1"
val jamcatch = "1.6"

dependencies.constraints {
    api("com.example.idiomatic.gradle:javarca-model:$javarca")
    api("com.example.idiomatic.gradle:javarca-engine:$javarca")
    api("com.example.idiomatic.gradle:renderer-lwjgl:$renderer")
    api("com.example.idiomatic.gradle:jamcatch-assets:$jamcatch")
    api("com.example.idiomatic.gradle:jamcatch-actors:$jamcatch")
    api("com.example.idiomatic.gradle:jamcatch-stage:$jamcatch")

    // Logging
    api("org.slf4j:slf4j-api:2.0.17")
    api("org.slf4j:slf4j-simple")
    api("org.slf4j:slf4j-jdk14")

    // Utilities: Apache Commons
    api("org.apache.commons:commons-csv:1.14.1")
    api("commons-io:commons-io:2.21.0")

    // Graphics: LWJGL
    api("org.lwjgl:lwjgl:3.3.6")
    api("org.lwjgl:lwjgl-glfw")
    api("org.lwjgl:lwjgl-opengl")
    api("org.lwjgl:lwjgl-stb")

    // Testing: JUnit
    api("org.junit.jupiter:junit-jupiter-api:6.0.1")
    api("org.junit.jupiter:junit-jupiter-engine")
    api("org.junit.platform:junit-platform-launcher")
}
