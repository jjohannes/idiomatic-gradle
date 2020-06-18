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
            extendsFrom(configurations.implementation)
            sourceSets.main.get().java.srcDirs.forEach(::addArtifact)
        }
    }
}
