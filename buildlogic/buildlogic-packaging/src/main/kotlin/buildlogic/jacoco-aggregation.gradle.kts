package buildlogic

// A plugin building on top of 'buildlogic.packaging' to aggregate a JaCoCo report for the tests of all things included in the executable Jar
plugins {
    id("buildlogic.packaging")
    jacoco
}

// This is defined in 'packaging.gradle.kts'
val packagingPath by configurations.getting

val sourcesPath = jvm.createResolvableConfiguration("sourcesPath") {
    usingDependencyBucket("packaging")
    requiresAttributes {
        documentation("source-folders")
    }
}

val coverageDataPath = jvm.createResolvableConfiguration("coverageDataPath") {
    usingDependencyBucket("packaging")
    requiresAttributes {
        documentation("jacoco-coverage-data")
    }
}

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