package buildlogic.convention

plugins {
    `java-library`
}

java {
    configure {
        createResolvableGraph("serverRuntime") {
            attributes {
                runtimeUsage()
            }
        }
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

    jvmArgumentProviders.add(ServerJarArgumentProvider(project.objects, configurations.getByName("serverRuntimeResolution")))
}
class ServerJarArgumentProvider(objects: ObjectFactory, path: Configuration) : CommandLineArgumentProvider {
    @get:Classpath
    val serverJarPath: ConfigurableFileCollection = objects.fileCollection().from(path)

    override fun asArguments() = listOf("-DserverJar=${serverJarPath.singleFile}")
}

// JUnit5 dependencies
dependencies {
    "end2endTestImplementation"("org.junit.jupiter:junit-jupiter-api:5.6.2")
    "end2endTestRuntimeOnly"("org.junit.jupiter:junit-jupiter-engine")
}

// Run end2end tests as part of the 'check' lifecycle phase
tasks.check.configure {
    dependsOn(end2endTestTask)
}
