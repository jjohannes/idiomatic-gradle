// The version of the produc itself is read from the 'version.txt' file in the root of a product.
@Suppress("UnstableApiUsage")
version = File(rootDir, "version.txt").readText().trim()
