package buildlogic

repositories {
    mavenCentral()
}

group = "com.example.idiomatic.gradle"
version = rootProject.layout.projectDirectory.file("version.txt").asFile.let {
    if (it.exists()) it.readLines().first().trim() else "unknown"
}