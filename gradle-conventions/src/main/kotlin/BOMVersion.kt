import org.gradle.api.provider.Property

interface BOMVersion {
    val version: Property<String>
}