package buildlogic

// A plugin building on top of 'buildlogic.packaging' to aggregate a JaCoCo report for the tests of all things included in the executable Jar
plugins {
    id("buildlogic.packaging")
    jacoco
}

// These are defined in 'packaging.gradle.kts'
val packaging: Configuration by configurations.getting
val packagingClasspath: Configuration by configurations.getting

// A resolvable configuration to collect source code
val sourcesPath: Configuration by configurations.creating {
    isVisible = false
    isCanBeResolved = true
    isCanBeConsumed = false
    extendsFrom(packaging)
}
java {
    configure {
        configureAttributes(sourcesPath) {
            runtimeUsage()
            documentation("source-folders")
        }
    }
}

// A resolvable configuration to collect JaCoCo coverage data
val coverageDataPath: Configuration by configurations.creating {
    isVisible = false
    isCanBeResolved = true
    isCanBeConsumed = false
    extendsFrom(packaging)
}
java {
    configure {
        configureAttributes(coverageDataPath) {
            runtimeUsage()
            documentation("jacoco-coverage-data")
        }
    }
}

// Register a code coverage report task to generate the aggregated report
val codeCoverageReport by tasks.registering(JacocoReport::class) {
    additionalClassDirs(packagingClasspath)
    additionalSourceDirs(sourcesPath.incoming.artifactView { lenient(true) }.files)
    executionData(coverageDataPath.incoming.artifactView { lenient(true) }.files)

    reports {
        html.isEnabled = true
        xml.isEnabled = true
    }
}

// Make JaCoCo report generation part of the 'check' lifecycle phase
tasks.check.configure {
    dependsOn(codeCoverageReport)
}