import org.gradle.api.provider.Property

interface CatalogVersion {
    val version: Property<String>
}