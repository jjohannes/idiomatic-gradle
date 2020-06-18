package buildlogic.convention

plugins {
    id("buildlogic.versioning")
    `java-library`
    `java-test-fixtures`
    id("buildlogic.convention.unit-testing")
    id("buildlogic.convention.end2end-testing")
    id("buildlogic.convention.jacoco")
}

// Share sources folder with other projects for aggregated Javadoc and JaCoCo reports
java {
    configure {
        createOutgoingElements("transitiveSourcesElements") {
            attributes { documentation("source-folders") } // <- this could maybe be outside the attributes block - like 'providesDocumentation(...)' (maybe there is also a better word than 'documentation'?)
            extendsFrom(configurations.implementation.get())
            sourceSets.main.get().java.srcDirs.forEach {
                // TODO This should work -> addArtifact(it)
                // Or even: addArtifacts(sourceSets.main.get().java.srcDirs)
            }
        }
    }
}

// TODO needed because addArtifact() above does not work
configurations.named("transitiveSourcesElements") {
    sourceSets.main.get().java.srcDirs.forEach {
        outgoing.artifact(it)
    }
}
