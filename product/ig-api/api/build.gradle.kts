plugins {
    id("com.example.java")
}

dependencies {
    api("com.example.idiomatic.gradle:data")
    implementation(libs.httpclient)

    testFixturesImplementation("com.google.guava:guava:29.0-jre")

    end2endTestImplementation(libs.guava)
    end2endTestCompileOnly("com.example.idiomatic.gradle:api")
    end2endTestRuntimeOnly("com.example.idiomatic.gradle:publish-api")

    serverRuntime("com.example.idiomatic.gradle:package-server")
}
