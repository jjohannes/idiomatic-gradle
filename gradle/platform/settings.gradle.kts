dependencyResolutionManagement {
    includeBuild("../catalog")
    versionCatalogs.create("libs") {
        from("com.example.idiomatic.gradle:catalog")
    }
}
