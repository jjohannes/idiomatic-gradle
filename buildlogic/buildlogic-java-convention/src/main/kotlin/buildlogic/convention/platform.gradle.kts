package buildlogic.convention

plugins {
    id("buildlogic.versioning")
    `java-platform`
    id("buildlogic.libraries")
}

// Allow dependencies for dependencies to other platforms (BOMs)
javaPlatform.allowDependencies()
