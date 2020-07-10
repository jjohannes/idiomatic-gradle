package buildlogic.convention

plugins {
    `java-library`
    id("buildlogic.libraries")
}

val serverRuntimePath = jvm.createResolvableConfiguration("serverRuntimePath") {
    usingDependencyBucket("serverRuntime")
    requiresAttributes {
        runtimeUsage()
    }
}

// SourceSet for test sources
val end2endTest: SourceSet by sourceSets.creating

// Task to package tests as Jar
val end2endTestTestJarTask = tasks.register<Jar>(end2endTest.jarTaskName) {
    archiveClassifier.set("end2end-tests")
    from(end2endTest.output)
}

// Task to run the tests with JUnit5
val end2endTestTask = tasks.register<Test>("end2endTest") {
    description = "Runs end2end tests."
    group = "verification"

    useJUnitPlatform()

    testClassesDirs = end2endTest.output.classesDirs
    classpath = configurations[end2endTest.runtimeClasspathConfigurationName] + files(end2endTestTestJarTask)

    jvmArgumentProviders.add(ServerJarArgumentProvider(project.objects, serverRuntimePath))
}

class ServerJarArgumentProvider(objects: ObjectFactory, path: Configuration) : CommandLineArgumentProvider {
    @get:Classpath
    val serverJarPath: ConfigurableFileCollection = objects.fileCollection().from(path)

    override fun asArguments() = listOf("-DserverJar=${serverJarPath.singleFile}")
}

// JUnit5 dependencies
dependencies {
    findProject(":platform")?.let { "end2endTestImplementation"(platform(it)) }
    "end2endTestImplementation"(libs.junitApi)
    "end2endTestRuntimeOnly"(libs.junitEngine)
}

// Run end2end tests as part of the 'check' lifecycle phase
tasks.check.configure {
    dependsOn(end2endTestTask)
}
