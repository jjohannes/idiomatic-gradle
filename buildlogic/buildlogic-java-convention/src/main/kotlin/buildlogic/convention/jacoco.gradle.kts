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
java {
    configure {
        createOutgoingElements("coverageDataElements") {
            attributes { documentation("jacoco-coverage-data") }
            extendsFrom(configurations.implementation.get())
            //sourceSets.main.get().java.srcDirs.forEach {
                // TODO Something like this should work -> addArtifact(tasks.test.flatMap { task -> provider { task.extensions.getByType<JacocoTaskExtension>().destinationFile } })
            //}
        }
    }
}

// TODO needed because addArtifact() above does not work
configurations.named("coverageDataElements") {
    outgoing.artifact(tasks.test.flatMap { task ->
        provider { task.extensions.getByType<JacocoTaskExtension>().destinationFile }
    }) {
        builtBy(tasks.test)
    }
}