plugins {
    id("com.example.idiomatic.gradle.build.module.versions")
}

dependencies {
    api(platform(libs.jetty.bom))
}
