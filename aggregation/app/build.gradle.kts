plugins {
    id("com.example.idiomatic.gradle.build.module.app")
}

dependencies {
    api(libs.ig.data)
    implementation(libs.httpclient)
}
