plugins {
    id("com.example.java")
}

dependencies {
    implementation("com.example.idiomatic.gradle:data")

    implementation("org.eclipse.jetty:jetty-server")
    implementation("org.eclipse.jetty:jetty-servlet")

    testImplementation("org.apache.httpcomponents:httpclient")
}
