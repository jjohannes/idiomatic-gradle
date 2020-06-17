plugins {
    id("buildlogic.convention.platform")
}

dependencies {
    api(platform(JavaModules.jettyBom)) {
        (this as ExternalDependency).version { prefer("9.4.30.v20200611") }
    }
    constraints {
        api(JavaModules.httpclient) {
            version { prefer("4.5.12") }
        }
        api(JavaModules.guava) {
            version { prefer("29.0-jre") }
        }
    }
}