plugins {
    id("com.example.idiomatic.gradle.build.module.lib")
}

@Suppress("UnstableApiUsage")
dependencies {
    api("com.example.idiomatic.gradle:javarca-engine")
    implementation("org.lwjgl:lwjgl")
    implementation("org.lwjgl:lwjgl-glfw")
    implementation("org.lwjgl:lwjgl-opengl")
    implementation("org.lwjgl:lwjgl-stb")
    implementation("org.slf4j:slf4j-api")
    runtimeOnly("org.lwjgl:lwjgl") { capabilities { requireFeature("natives") } }
    runtimeOnly("org.lwjgl:lwjgl-glfw") { capabilities { requireFeature("natives") } }
    runtimeOnly("org.lwjgl:lwjgl-opengl") { capabilities { requireFeature("natives") } }
    runtimeOnly("org.lwjgl:lwjgl-stb") { capabilities { requireFeature("natives") } }

    testImplementation("org.junit.jupiter:junit-jupiter-api")
}
