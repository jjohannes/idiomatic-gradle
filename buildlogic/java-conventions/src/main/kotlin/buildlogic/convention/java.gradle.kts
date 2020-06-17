package buildlogic.convention

plugins {
    id("buildlogic.versioning")
    `java-library`
    `java-test-fixtures`
}

tasks.withType<Test>().configureEach {
    useJUnitPlatform()
}

val serverRuntime: Configuration by configurations.creating {
    isVisible = false
    isCanBeConsumed = false
    isCanBeResolved = false
}
val serverRuntimePath: Configuration by configurations.creating {
    isVisible = false
    isCanBeConsumed = false
    isCanBeResolved = true
    extendsFrom(serverRuntime)
}

val end2endTest: SourceSet by sourceSets.creating

val end2endTestTestJarTask = tasks.register<Jar>(end2endTest.jarTaskName) {
    archiveClassifier.set("end2end-tests")
    from(end2endTest.output)
}

val end2endTestTask = tasks.register<Test>("end2endTest") {
    description = "Runs end2end tests."
    group = "verification"

    testClassesDirs = end2endTest.output.classesDirs
    classpath = configurations[end2endTest.runtimeClasspathConfigurationName] + files(end2endTestTestJarTask)

    jvmArgumentProviders.add(ServerJarArgumentProvider(project.objects, serverRuntimePath))
}
tasks.check.configure {
    dependsOn(end2endTestTask)
}

dependencies {
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.6.2")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.6.2")
    "end2endTestImplementation"("org.junit.jupiter:junit-jupiter-api:5.6.2")
    "end2endTestRuntimeOnly"("org.junit.jupiter:junit-jupiter-engine:5.6.2")
}

class ServerJarArgumentProvider(objects: ObjectFactory, path: Configuration) : CommandLineArgumentProvider {

    @get:Classpath
    val serverJarPath: ConfigurableFileCollection = objects.fileCollection().from(path)

    override fun asArguments() = listOf("-DserverJar=${serverJarPath.singleFile}")
}