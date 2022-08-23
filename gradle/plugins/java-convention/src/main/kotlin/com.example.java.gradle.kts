plugins {
    id("com.example.versioning")
    id("java-library")
    id("java-test-fixtures")
    id("com.example.unit-testing")
    id("com.example.end2end-testing")
    id("com.example.jacoco")
}

dependencies {
    implementation(platform("com.example.idiomatic.gradle:platform"))
}

// configure details of java compilation
tasks.withType<JavaCompile>().configureEach {
    options.headerOutputDirectory.convention(null) // currently, need to clear convention to remove
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
    sourceSets.main.get().java.srcDirs.forEach { outgoing.artifact(it) }
}
