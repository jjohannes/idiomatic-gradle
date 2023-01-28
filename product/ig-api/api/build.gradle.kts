plugins {
    id("com.example.java")
}

dependencies {
    api("com.example.idiomatic.gradle:data")
    implementation("org.apache.httpcomponents:httpclient")

    testFixturesImplementation("com.google.guava:guava:29.0-jre")

    end2endTestImplementation("com.google.guava:guava")
    end2endTestCompileOnly("com.example.idiomatic.gradle:api")
    end2endTestRuntimeOnly("com.example.idiomatic.gradle:publish-api")

    serverRuntime("com.example.idiomatic.gradle:package-server")
}
