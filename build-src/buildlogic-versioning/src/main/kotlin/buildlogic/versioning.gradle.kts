package buildlogic

repositories {
    mavenCentral()
}

// A common group for all projects
group = "com.example.idiomatic.gradle"
// The version is read from the 'version.txt' file in the root folder
version = rootProject.layout.projectDirectory.file("version.txt").asFile.let {
    // Note: it is important to handle the file-not-exists case as this code is executed during script compilation (on an artificial project)
    if (it.exists()) it.readLines().first().trim() else "unknown"
}