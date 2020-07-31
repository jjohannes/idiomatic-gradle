plugins {
    `java-platform`
    id("buildlogic.libraries")
}

// Allow dependencies for dependencies to other platforms (BOMs)
javaPlatform.allowDependencies()

group = "com.example.idiomatic.gradle"

dependencies {
    api(platform(libs.jettyBom)) {
        (this as ExternalDependency).version { prefer("9.4.30.v20200611") }
    }
    constraints {
        api(libs.httpclient) {
            version { prefer("4.5.12") }
        }
        api(libs.guava) {
            version { prefer("29.0-jre") }
        }
        api(libs.junitApi) {
            version { prefer("5.6.2") }
        }
    }
}