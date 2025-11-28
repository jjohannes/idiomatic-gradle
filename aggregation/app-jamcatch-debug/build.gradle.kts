plugins {
    id("com.example.idiomatic.gradle.build.module.app")
}

application { mainClass = "com.example.idiomatic.gradle.javarca.engine.Engine" }

dependencies {
    runtimeOnly("com.example.idiomatic.gradle:jamcatch-actors")
    runtimeOnly("com.example.idiomatic.gradle:jamcatch-stage")
    runtimeOnly("com.example.idiomatic.gradle:javarca-engine")
    runtimeOnly("com.example.idiomatic.gradle:renderer-lwjgl")
    runtimeOnly("org.slf4j:slf4j-simple")
}
