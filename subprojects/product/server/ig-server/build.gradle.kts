plugins {
    id("buildlogic.convention.java")
}

dependencies {
    api(platform(project(":platform")))

    implementation(project(":ig-data"))

    implementation(JavaModules.jettyServer)
    implementation(JavaModules.jettyServlet)

    testImplementation(JavaModules.httpclient)
}
