plugins {
    `kotlin-dsl`
}

repositories {
    gradlePluginPortal()
}

dependencies {
    // example of using a Gradle community plugin
    implementation(libs.gradlex.jvm.dependency.conflict.resolution)

    // example of using a Java library in your plugins
    // NOTE: only do this, if you write your own tasks with advanced functionality that need such a library
    implementation(libs.guava)
}
