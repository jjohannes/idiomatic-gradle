plugins {
    id("version-catalog")
}

group = "com.example.idiomatic.gradle"

configurations.versionCatalogElements {
    outgoing.artifacts.clear()
    outgoing.artifact(layout.projectDirectory.dir("libs.versions.toml"))
}
