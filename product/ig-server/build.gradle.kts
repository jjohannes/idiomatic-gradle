plugins {
    id("buildlogic.convention.java")
}

dependencies {
    implementation(project(":ig-data"))

    implementation(libs.jettyServer)
    implementation(libs.jettyServlet)

    testImplementation(libs.httpclient)
}
