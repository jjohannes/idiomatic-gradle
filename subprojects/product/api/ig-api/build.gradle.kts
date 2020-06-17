plugins {
    id("buildlogic.convention.java")
}

dependencies {
    implementation(platform(project(":platform")))

    api(project(":ig-data"))
    implementation(JavaModules.httpclient)

    testFixturesImplementation("com.google.guava:guava:29.0-jre")

    end2endTestImplementation(platform(project(":platform")))
    end2endTestImplementation(JavaModules.guava)
    end2endTestCompileOnly(project(":ig-api"))
    end2endTestRuntimeOnly(project(":publish-api"))

    serverRuntime(project(":package-server"))
}
