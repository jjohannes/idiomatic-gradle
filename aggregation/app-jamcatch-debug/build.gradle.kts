plugins {
    id("com.example.idiomatic.gradle.build.module.app")
}

application { mainClass = "com.example.idiomatic.gradle.javarca.engine.Engine" }

dependencies {
    runtimeOnly(libs.javarca.engine)
    runtimeOnly(libs.renderer.lwjgl)
    implementation(libs.jamcatch.actors)
    implementation(libs.jamcatch.stage)
    runtimeOnly(libs.slf4j.jul)
}
