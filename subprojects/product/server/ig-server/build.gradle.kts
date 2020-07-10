plugins {
    id("buildlogic.convention.java")
}

dependencies {
    api(platform(project(":platform")))

    implementation(project(":ig-data"))

    implementation(libs.jettyServer)
    implementation(libs.jettyServlet)

    testImplementation(libs.httpclient)
}
