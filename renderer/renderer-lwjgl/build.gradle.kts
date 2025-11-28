plugins {
    id("com.example.idiomatic.gradle.build.module.lib")
}

dependencies {
    api("com.example.idiomatic.gradle:javarca-engine")
    implementation("org.lwjgl:lwjgl")
    implementation("org.lwjgl:lwjgl-glfw")
    implementation("org.lwjgl:lwjgl-opengl")
    implementation("org.lwjgl:lwjgl-stb")
    implementation("org.slf4j:slf4j-api")

    testImplementation("org.junit.jupiter:junit-jupiter-api")
}
