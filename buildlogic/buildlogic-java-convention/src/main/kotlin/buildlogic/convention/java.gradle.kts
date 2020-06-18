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
configurations.create("transitiveSourcesElements") {
    isVisible = false
    isCanBeResolved = false
    isCanBeConsumed = true
    extendsFrom(configurations.implementation.get())
    attributes {
        attribute(Usage.USAGE_ATTRIBUTE, objects.named(Usage.JAVA_RUNTIME))
        attribute(Category.CATEGORY_ATTRIBUTE, objects.named(Category.DOCUMENTATION))
        attribute(DocsType.DOCS_TYPE_ATTRIBUTE, objects.named("source-folders"))
    }
    sourceSets.main.get().java.srcDirs.forEach {
        outgoing.artifact(it)
    }
}
