package buildlogic

// A plugin building on top of 'buildlogic.packaging' to aggregate a JaCoCo report for the tests of all things included in the executable Jar
plugins {
    id("buildlogic.packaging")
    jacoco
}

// These are defined in 'packaging.gradle.kts'
val packaging by configurations.getting
val packagingPath by configurations.getting

java {
    modeling {
        createResolvableGraph("sourcesPath") {
            extendsFrom(packaging)
            attributes {
                runtimeUsage()
                documentation("source-folders")
            }
        }
        createResolvableGraph("coverageDataPath") {
            extendsFrom(packaging)
            attributes {
                runtimeUsage()
                documentation("jacoco-coverage-data")
            }
        }
    }
}

val sourcesPath by configurations.getting
val coverageDataPath by configurations.getting

// Register a code coverage report task to generate the aggregated report
val codeCoverageReport by tasks.registering(JacocoReport::class) {
    additionalClassDirs(packagingPath)
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