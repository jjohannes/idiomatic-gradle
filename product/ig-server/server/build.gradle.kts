plugins {
    id("com.example.java")
}

dependencies {
    implementation("com.example.idiomatic.gradle:data")

    implementation(libs.jettyServer)
    implementation(libs.jettyServlet)

    testImplementation(libs.httpclient)
}
