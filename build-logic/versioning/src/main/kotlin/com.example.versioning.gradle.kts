// A common group for all projects
group = "com.example.idiomatic.gradle"
// The version is read from the 'version.txt' file in the root folder
version = layout.projectDirectory.parentOrRoot().file("version.txt").asFile.let {
    // Note: it is important to handle the file-not-exists case as this code is executed during script compilation (on an artificial project)
    if (it.exists()) it.readLines().first().trim() else "unknown"
}

fun Directory.parentOrRoot(): Directory = if (this.file("version.txt").asFile.exists()) {
    this
} else {
    val parent = dir("..")
    when {
        parent.file("version.txt").asFile.exists() -> parent
        this == parent -> parent
        else -> parent.parentOrRoot()
    }
}