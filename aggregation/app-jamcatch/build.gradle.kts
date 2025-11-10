plugins {
    id("com.example.idiomatic.gradle.build.module.app")
}

application { mainClass = "com.example.idiomatic.gradle.javarca.engine.Engine" }

dependencies {
    runtimeOnly(libs.jamcatch.actors)
    runtimeOnly(libs.jamcatch.assets)
    runtimeOnly(libs.jamcatch.stage)
    runtimeOnly(libs.javarca.engine)
    runtimeOnly(libs.renderer.lwjgl)
    runtimeOnly(libs.slf4j.simple)
}
