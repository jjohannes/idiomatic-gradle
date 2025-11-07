plugins {
    id("com.example.idiomatic.gradle.build.module.lib")
}

dependencies {
    api(libs.ig.data)
    implementation(libs.httpclient)

    testFixturesImplementation(libs.guava)

    end2endTestImplementation(libs.guava)
    end2endTestCompileOnly(libs.ig.api)
}
