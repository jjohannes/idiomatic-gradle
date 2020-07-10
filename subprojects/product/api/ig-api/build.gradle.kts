plugins {
    id("buildlogic.convention.java")
}

dependencies {
    api(project(":ig-data"))
    implementation(libs.httpclient)

    testFixturesImplementation("com.google.guava:guava:29.0-jre")

    end2endTestImplementation(libs.guava)
    end2endTestCompileOnly(project(":ig-api"))
    end2endTestRuntimeOnly(project(":publish-api"))

    serverRuntime(project(":package-server"))
}
