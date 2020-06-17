# Example of how to idiomatically structure a large build with Gradle 6

## Example

The example is a software product called _Idiomatic Gradle (IG)_ which consists of:

### Production code

- `subprojects/product/server`: A server providing some services
- `subprojects/product/api`: A client API Jar that clients (e.g. Android Apps) can integrate directly
- `subprojects/product/common`: Some common code used by both Server and API Jar

For the sake of the sample each of these folders only contain one subproject. 
In a real-world application, this can be structured into many, many more Gradle subprojects.

### Packaging and Publishing

Both the server and the client API Jar require some special packaging to be published/distributed.
Hence this is configured in separate subprojects:
- `subprojects/aggregation/package-server`: Package the complete server and its dependencies into one fat jar that can run without other dependencies
- `subprojects/aggregation/publish-api`: Package the client into one Jar that is published to a Maven repository

### Testing

Each project contains unit tests using Gradle's default setup for Java projects with the `src/test/java` folder.
Furthermore, some projects contain end2end tests testing with the real (packaged) server and the real client API Jar.
For this, the packaging/publishing projects _provide_ their results in the build for other subprojects to consume. 

## Idiomatic Build Logic Structure

The build contains some standard configuration for Java compilation and testing.
It contains more involved configuration code to configure the packaging/publishing and the end2end test setup.
There are multiple ways to do all this in Gradle today.
This sample employs the following good patterns which result in a good build structure (easy to maintain and fast for Gradle to execute):

- Empty root build script.
- Project structure defined a 'projectDir' for each subproject in settings Gradle.
- Subproject build scripts only contain plugin applications (ideally only custom convention plugins) and dependency declarations without versions. But no additional custom build logic.
- Convention plugins are defined in (multiple) included builds.
- The changing version of the product is defined in 'version.txt' and not in the build script.
- A platform project (subprojects/product/platform) is used to define dependency versions, which also utilises published platforms (BOMs).
- API/Implementation separation is used to declare dependencies.
- Custom build logic always provides custom _variants_ to share artifacts (like the packaged server Jar) between projects.

With this, the following outdated practices are avoided:
- No direct dependencies between tasks declared (except for extending lifecycle tasks like `assemble` or `check`)
- No direct dependencies between tasks from different subprojects are declared
- No cross-project configuration performed (_subproject_ / _allprojects_) is performed
- Each build script of a subproject is simpler to read as all relationships to other projects are expressed in terms of dependencies
- ...