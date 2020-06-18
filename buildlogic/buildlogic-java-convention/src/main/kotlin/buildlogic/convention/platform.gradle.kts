package buildlogic.convention

plugins {
    id("buildlogic.versioning")
    `java-platform`
}

// Allow dependencies for dependencies to other platforms (BOMs)
javaPlatform.allowDependencies()
