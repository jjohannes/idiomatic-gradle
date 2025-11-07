plugins {
    id("java")
    id("jacoco-report-aggregation")
    id("com.example.idiomatic.gradle.build.base.versioning")
    id("com.example.idiomatic.gradle.build.base.dependency-rules")
    id("com.example.idiomatic.gradle.build.feature.compilation")
}

// Make JaCoCo report generation part of the 'check' lifecycle phase
tasks.check { dependsOn(tasks.testCodeCoverageReport) }
// nothing is published
tasks.register("publish")
// A 'report' project is not supposed to have source code. The 'java' plugin is applied only
// for its dependency management features. Therefore, the following tasks which are part of
// 'assemble' are disabled.
tasks.compileJava { enabled = false }
tasks.processResources { enabled = false }
tasks.jar { enabled = false }
