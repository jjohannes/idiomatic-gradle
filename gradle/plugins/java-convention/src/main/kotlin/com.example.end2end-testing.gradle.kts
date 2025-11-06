plugins {
    id("java-library")
}

val serverRuntime: Configuration by configurations.creating {
    isCanBeConsumed = false
    isCanBeResolved = false
}
val serverRuntimePath: Configuration by configurations.creating {
    isCanBeConsumed = false
    isCanBeResolved = true
    extendsFrom(serverRuntime)
    attributes {
        attribute(Usage.USAGE_ATTRIBUTE, objects.named(Usage.JAVA_RUNTIME))
        attribute(Category.CATEGORY_ATTRIBUTE, objects.named(Category.LIBRARY))
        attribute(LibraryElements.LIBRARY_ELEMENTS_ATTRIBUTE, objects.named(LibraryElements.JAR))
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
val libs = the<VersionCatalogsExtension>().named("libs")
dependencies {
    "end2endTestImplementation"(platform("com.example.idiomatic.gradle:platform"))
    "end2endTestImplementation"(libs.findLibrary("junit.api").get())
    "end2endTestRuntimeOnly"(libs.findLibrary("junit.engine").get())
    "end2endTestRuntimeOnly"(libs.findLibrary("junit.launcher").get())
}

// Run end2end tests as part of the 'check' lifecycle phase
tasks.check {
    dependsOn(end2endTestTask)
}
