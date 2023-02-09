plugins {
    id("java-platform")
}

// Allow dependencies for dependencies to other platforms (BOMs)
javaPlatform.allowDependencies()

group = "com.example.idiomatic.gradle"

dependencies {
    api(platform(libs.jetty.bom)) {
        (this as ExternalDependency).version { prefer("9.4.30.v20200611") }
    }
    constraints {
        api(libs.httpclient) {
            version { prefer("4.5.12") }
        }
        api(libs.guava) {
            version { prefer("29.0-jre") }
        }
        api(libs.junit.api) {
            version { prefer("5.6.2") }
        }
        api(libs.gradlex.java.ecosystem.capabilities) {
            version { prefer("1.1") }
        }
    }
}
