package buildlogic.convention

plugins {
    `java-library`
    jacoco
}

tasks.jacocoTestReport.configure {
    enabled = false
}

configurations.create("coverageDataElements") {
    isVisible = false
    isCanBeResolved = false
    isCanBeConsumed = true
    extendsFrom(configurations.implementation.get())
    attributes {
        attribute(Usage.USAGE_ATTRIBUTE, objects.named(Usage.JAVA_RUNTIME))
        attribute(Category.CATEGORY_ATTRIBUTE, objects.named(Category.DOCUMENTATION))
        attribute(DocsType.DOCS_TYPE_ATTRIBUTE, objects.named("jacoco-coverage-data"))
    }
    outgoing.artifact(tasks.test.flatMap { task ->
        provider { task.extensions.getByType<JacocoTaskExtension>().destinationFile }
    }) {
        builtBy(tasks.test)
    }
}