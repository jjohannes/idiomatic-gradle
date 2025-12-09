plugins {
    id("com.example.idiomatic.gradle.build.module.lib")
}

dependencies {
    api(libs.javarca.engine)
    implementation(libs.lwjgl)
    implementation(libs.lwjgl.glfw)
    implementation(libs.lwjgl.opengl)
    implementation(libs.lwjgl.stb)
    implementation(libs.slf4j.api)

    testImplementation(libs.junit.api)
}
