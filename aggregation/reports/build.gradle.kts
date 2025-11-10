plugins {
    id("com.example.idiomatic.gradle.build.module.report")
}

dependencies {
    implementation(libs.jamcatch.actors)
    implementation(libs.jamcatch.assets)
    implementation(libs.jamcatch.stage)
}
