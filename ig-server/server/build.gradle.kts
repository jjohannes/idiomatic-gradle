plugins {
    id("com.example.idiomatic.gradle.build.module.lib")
}

dependencies {
    implementation(libs.ig.data)

    implementation(libs.jetty.server)
    implementation(libs.jetty.servlet)

    testImplementation(libs.httpclient)
}
