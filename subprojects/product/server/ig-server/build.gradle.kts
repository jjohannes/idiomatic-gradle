plugins {
    id("buildlogic.convention.java")
}

dependencies {
    api(platform(project(":platform")))

    implementation(JavaModules.jettyServer)
    implementation(JavaModules.jettyServlet)

    testImplementation(JavaModules.httpclient)
}
