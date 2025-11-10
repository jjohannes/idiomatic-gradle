plugins {
    id("com.example.idiomatic.gradle.build.module.lib")
}

@Suppress("UnstableApiUsage")
dependencies {
    api(libs.javarca.engine)
    implementation(libs.lwjgl)
    implementation(libs.lwjgl.glfw)
    implementation(libs.lwjgl.opengl)
    implementation(libs.lwjgl.stb)
    implementation(libs.slf4j.api)
    runtimeOnly(libs.lwjgl) { capabilities { requireFeature("natives") } }
    runtimeOnly(libs.lwjgl.glfw) { capabilities { requireFeature("natives") } }
    runtimeOnly(libs.lwjgl.opengl) { capabilities { requireFeature("natives") } }
    runtimeOnly(libs.lwjgl.stb) { capabilities { requireFeature("natives") } }

    testImplementation(libs.junit.api)
}
