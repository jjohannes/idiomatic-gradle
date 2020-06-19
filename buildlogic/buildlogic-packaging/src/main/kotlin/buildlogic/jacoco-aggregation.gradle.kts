package buildlogic

// A plugin building on top of 'buildlogic.packaging' to aggregate a JaCoCo report for the tests of all things included in the executable Jar
plugins {
    id("buildlogic.packaging")
    jacoco
}

// This is defined in 'packaging.gradle.kts'
val packagingPath by configurations.getting // <-- java.modeling.resolvableGraphs.getting ???

val sourcesPath = java.modeling.createResolvableGraph("sourcesPath") {
    usingDependencyBucket("packaging")
    attributes {
        runtimeUsage()
        documentation("source-folders")
    }
}
val coverageDataPath = java.modeling.createResolvableGraph("coverageDataPath") {
    usingDependencyBucket("packaging")
    attributes {
        runtimeUsage()
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