import org.gradle.nativeplatform.MachineArchitecture.*
import org.gradle.nativeplatform.OperatingSystemFamily.*

plugins {
    id("org.gradlex.jvm-dependency-conflict-resolution")
    id("io.mvnpm.gradle.plugin.native-java-plugin")
}

// Versions of 3rd party libraries are provided through a catalog.
jvmDependencyConflicts {
    // Set up consistent version management through a platform (BOM)
    val bomVersion = the<BOMVersion>().version.getOrElse("")
    consistentResolution {
        platform("com.example.idiomatic.gradle:dependency-versions:$bomVersion")
    }

    // Add missing information to metadata of 3rd-party libraries.
    patch {
        val lwjglModules = listOf("", "-glfw", "-opengl", "-stb")
        // LWJGL - https://github.com/LWJGL/lwjgl3/pull/1081
        lwjglModules.forEach { module ->
            @Suppress("UnstableApiUsage")
            module("org.lwjgl:lwjgl$module") {
                addTargetPlatformVariant("natives", "natives-linux", LINUX, X86_64)
                addTargetPlatformVariant("natives", "natives-linux-arm64", LINUX, ARM64)
                addTargetPlatformVariant("natives", "natives-macos", MACOS, X86_64)
                addTargetPlatformVariant("natives", "natives-macos-arm64", MACOS, ARM64)
                addTargetPlatformVariant("natives", "natives-windows", WINDOWS, X86_64)
                addTargetPlatformVariant("natives", "natives-windows-arm64", WINDOWS, ARM64)
            }
        }
        // LWJGL - https://github.com/gradlex-org/jvm-dependency-conflict-resolution/issues/328
        alignWithBom("org.lwjgl:lwjgl-bom", *lwjglModules.map { "org.lwjgl:lwjgl$it" }.toTypedArray())
    }
}
