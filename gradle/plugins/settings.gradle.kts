dependencyResolutionManagement {
    versionCatalogs.register("libs") {
        from(files("../libs.versions.toml"))
    }
}
