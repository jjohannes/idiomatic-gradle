// A common group for all modules
group = "com.example.idiomatic.gradle"
// The version is read from the 'version.txt' file in the root of a product
@Suppress("UnstableApiUsage")
version = providers.fileContents(layout.settingsDirectory.file("version.txt")).asText.get()
