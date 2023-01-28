plugins {
    id("java-platform")
}

// Allow dependencies for dependencies to other platforms (BOMs)
javaPlatform.allowDependencies()

group = "com.example.idiomatic.gradle"

dependencies {
    api(platform("org.eclipse.jetty:jetty-bom:9.4.30.v20200611"))

    constraints {
        api("org.apache.httpcomponents:httpclient:4.5.12")
        api("com.google.guava:guava:29.0-jre")
        api("org.junit.jupiter:junit-jupiter-api:5.6.2")
    }
}