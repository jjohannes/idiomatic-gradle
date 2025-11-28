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
    val bomVersion = extensions.create<BOMVersion>("bomVersion")

    // global build plugins that need to be applied to the 'root project' rather than 'settings'
    @Suppress("UnstableApiUsage")
    gradle.lifecycle.beforeProject {
        extensions.add("bomVersion", bomVersion)
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
