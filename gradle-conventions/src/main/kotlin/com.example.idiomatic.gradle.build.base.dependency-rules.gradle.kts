import org.gradle.kotlin.dsl.the

plugins {
    id("org.gradlex.jvm-dependency-conflict-resolution")
}

// Versions of 3rd party libraries are provided through a catalog.
jvmDependencyConflicts {
    val catalogVersion = the<CatalogVersion>().version.getOrElse("")
    consistentResolution {
        platform("com.example.idiomatic.gradle:dependency-versions:$catalogVersion")
    }
}

