package buildlogic.convention

plugins {
    `java-library`
    jacoco
}

// Do not generate reports for individual projects
tasks.jacocoTestReport.configure {
    enabled = false
}

// Share the coverage data to be aggregated for the whole product
jvm {
    createOutgoingElements("coverageDataElements") {
        providesAttributes { documentation("jacoco-coverage-data") }
        extendsFrom(configurations.implementation)
        artifact(tasks.test.map { task ->
            task.extensions.getByType<JacocoTaskExtension>().destinationFile!!
        })
    }
}
