plugins {
    id("com.example.java")
}

dependencies {
    implementation("com.example.idiomatic.gradle:data")

    implementation(libs.jetty.server)
    implementation(libs.jetty.servlet)

    testImplementation(libs.httpclient)
}
