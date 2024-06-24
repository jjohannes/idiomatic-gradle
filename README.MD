# Example of how to idiomatically structure a large build with Gradle 7.2+

> [!IMPORTANT]
> This project shows how to use Gradle in a structured way not only for simple, but also rather complex project setups.
> In this context, it is rather "academic" and contains quite some "Gradle boilerplate".
> If you want to understand Gradle on a deeper level, it is a great resource.
> And if you are looking for more explanation on the different features used here, you can finde that in my
> 👉 [**Understanding Gradle**](https://github.com/jjohannes/understanding-gradle#readme) video series.
>
> If you are looking for a (more or less) standard setup for a project, I would recommend looking at my other repository
> 👉 [**Gradle Project Setup HowTo**](https://github.com/jjohannes/gradle-project-setup-howto) first or instead.

There is also
[documentation](https://docs.gradle.org/release-nightly/userguide/structuring_software_products.html)
and another
[sample](https://docs.gradle.org/release-nightly/samples/sample_structuring_software_projects.html)
on this topic in the Gradle User Manual.

## Example

This example is a software product called _Idiomatic Gradle (IG)_.

To build the example, clone this repository and run the following for more information:
- `./gradlew :tasks`
- `./gradlew :projects`

This example consists of:

### Production code

Inside the 'product' folder, there are three components/builds:

- `product/ig-server`: A server providing some services
- `product/ig-api`: A client API Jar that clients (e.g. Android Apps) can integrate directly
- `product/ig-common`: Some common code used by both Server and API Jar

For the sake of the sample each of these folders only contain one subproject.
In a real-world application, this can be structured into many, many more Gradle subprojects.

### Packaging and Publishing

Both the server and the client API Jar require some special packaging to be published/distributed.
Hence, this is configured in a separate component/build only responsible for aggregating build results:
- `aggregation/package-server`: Package the complete server and its dependencies into one fat jar that can run without other dependencies
- `aggregation/publish-api`: Package the client into one Jar that is published to a Maven repository

### Testing

Each project contains unit tests using Gradle's default setup for Java projects with the `src/test/java` folder.
Furthermore, some projects contain end2end tests testing with the real (packaged) server and the real client API Jar.
For this, the packaging/publishing projects _provide_ their results in the build for other subprojects to consume.

## Idiomatic Build Logic Structure

The build contains some standard configuration for Java compilation and testing.
It contains more involved configuration code to configure the packaging/publishing and the end2end test setup.
There are multiple ways to do all this in Gradle today.
This sample employs the following good patterns which result in a good build structure (easy to maintain and fast for Gradle to execute) as [described here](https://docs.gradle.org/release-nightly/userguide/structuring_software_products.html).

With this, the following outdated practices are avoided:
- No direct dependencies between tasks declared (except for extending lifecycle tasks like `assemble` or `check`)
- No direct dependencies between tasks from different subprojects are declared
- No cross-project configuration (_subproject_ / _allprojects_) is performed
- Each build script of a subproject is simpler to read as all relationships to other projects are expressed in terms of dependencies
- ...

## Alternative structuring options (on branches)

- Alternative setups to use or not use Gradle's [Version Catalog](https://docs.gradle.org/current/userguide/platforms.html#sub:version-catalog) concept (see [#4](https://github.com/jjohannes/idiomatic-gradle/issues/4)).
    - [Do not use the Catalog](https://github.com/jjohannes/idiomatic-gradle/commit/no_version_catalog) - Removes the `libs.versions.toml` catalog and instead uses GA coordinates directly (versions are managed in platform project)
    - [Use only the Catalog (remove Platform)](https://github.com/jjohannes/idiomatic-gradle/commit/version_catalog_without_platform) - Remove the `gradle/platform` project and put the versions directly into the catalog instead.

## Discussions and alternative structuring options

- [Using or not using a Version Catalog](https://github.com/jjohannes/idiomatic-gradle/issues/4)
- [How to use an external plugin in a local plugin and where to put its version](https://github.com/jjohannes/idiomatic-gradle/issues/14)
- [Name of 'Settings Plugin' project](https://github.com/jjohannes/idiomatic-gradle/issues/8)
- [Using JVM packages for precompiled script plugins](https://github.com/jjohannes/idiomatic-gradle/issues/9)
- [Where to put the Gradle Wrapper](https://github.com/jjohannes/idiomatic-gradle/issues/12)

More questions or points you would like to discuss? Please [open an issue](https://github.com/jjohannes/idiomatic-gradle/issues/new).

## FAQ

- [Android Studio: 'gradle/plugins' project clean uses different Gradle version](https://github.com/jjohannes/idiomatic-gradle/issues/5)

More questions or points you would like to discuss? Please [open an issue](https://github.com/jjohannes/idiomatic-gradle/issues/new).
