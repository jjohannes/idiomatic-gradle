dependencyResolutionManagement {
    @Suppress("UnstableApiUsage")
    repositories {
        maven("https://repo.onepiece.software/releases") {
            mavenContent { includeGroup("com.example.idiomatic.gradle") }
        }
        mavenCentral()
    }

    // https://docs.gradle.org/current/userguide/composite_builds.html#included_build_declaring_substitutions
    includeBuild(".")

    // custom extension to define the version of the catalog (and the platform) to use
    val catalogVersion = extensions.create<CatalogVersion>("catalogVersion")

    // for the version catalog 'libs.versions.toml' shared by all builds/components in the repository
    versionCatalogs.register("libs") {
        if (catalogVersion.version.isPresent) {
            from("com.example.idiomatic.gradle:dependency-versions:${catalogVersion.version.get()}")
        } else {
            from(files("libs.versions.toml"))
        }
    }

    // global build plugins that need to be applied to the 'root project' rather than 'settings'
    @Suppress("UnstableApiUsage")
    gradle.lifecycle.beforeProject {
        extensions.add("catalogVersion", catalogVersion)
        // A common group for all modules
        group = "com.example.idiomatic.gradle"
        if (parent == null) {
            apply(plugin = "com.example.idiomatic.gradle.build.feature.lifecycle.root")
        }
    }
}

// all directories with a 'build.gradle.kts' are modules (subprojects) of the build
rootDir.listFiles()?.filter { folder -> File(folder, "build.gradle.kts").exists() }?.forEach {
    include(it.name)
}
