# How to idiomatically structure a large Gradle build

This project shows how to use Gradle in a structured way not only for simple, but also complex project setups.

> [!TIP]
> Learn more about all the Gradle features used in this project:
> - 🎞️ [**Understanding Gradle**](https://youtube.com/@jjohannes) video series
>
> There are two sister repositories that share the same example with a different focus in the build setup:
> - 🧶 [**gradle-project-setup-howto**](https://github.com/jjohannes/gradle-project-setup-howto) Focus on a full mono-repo setup
> - 🕹️ [**javarcade**](https://github.com/jjohannes/javarcade) Focus on structured dependency management with the JavaRCA recipe
> 
> This project uses Gradle's Kotlin DSL for configuration files. However, you can use any JVM language (including pure Java!)
> to configure Gradle. More details on that can be found here:
> - 🔌 [**gradle-plugins-howto**](https://github.com/jjohannes/gradle-plugins-howto) Details on the plugin concept alternative implementation solutions
>
> This example uses the `build.gradle.kts` files as dependency definition files. An alternative approach is to use 
> `module-info.java` files, as part of the Java Module System (JPMS), to define dependencies directly in Java instead
> of Gradle-specific Kotlin notation. More details on that:
> - 🧩 [**javarca.de**](https://javarca.de) Java Recipe for Carefree dependency Administration
> 
## Example

This example is a software composed of three _products_:
1. A game [engine](engine)
2. A graphics [renderer](renderer) to plug into the engine
3. A game called [jamcatch](jamcatch) based on the engine

Each _product_ consists of one or multiple _modules_.

To explore and build the example, clone this repository and open the root folder in IntelliJ IDEA.
You can also build from the command line by running _gradlew_.

```shell
./gradlew
```

All three _products_ are located in this Git repository (monorepo approach).
They could also be moved into separate Git repositories (multirepo approach) with the same build setup.
To demonstrate that, the _products_ are also published to a
[Maven repository](https://repo.onepiece.software/#/releases/com/example/idiomatic/gradle).
Then, each _product_ can also be built on its own by consuming other published _products_ from the Maven repository.
You can explore that by opening one of the individual _product_ folders
([engine](engine), [renderer](renderer), [jamcatch](jamcatch)) in IntelliJ IDEA.

### Individual Modules - Production Code and Tests

Inside the three _product_ folders, there are multiple _module_ folders:

```shell
├── engine
│   ├── javarca-engine
│   ├── javarca-model
├── renderer
│   └── renderer-lwjgl
└── jamcatch
    ├── jamcatch-actors
    ├── jamcatch-assets
    └── jamcatch-stage
```

Each _module_ folder contains:
- Production code and tests (Java, independent of Gradle)
- A [build.gradle.kts](jamcatch/jamcatch-actors/build.gradle.kts) file that **only** contains
  a `plugins` and `dependencies` block. 
  These files are used to clearly define the dependencies of that module to other modules of the same (or other)
  products and to third-party open source modules. They are explicitly **not** used for further build configuration
  (see [JavaRCA recipe](https://javarca.de) for more details on that).

```shell
└── jamcatch
    └── jamcatch-actors
        ├── src
        │   ├── main/java
        │   ├── test/java
        │   └── testEnd2end/java
        └── build.gradle.kts
```

Each module contains unit tests using Gradle's default setup for Java projects with the `src/test/java` folder.
Furthermore, the modules of the [jamcatch](jamcatch) product also have end2end tests that run the module in the
context of a complete application. This demonstrates the flexible testing and dependency management capabilities
of Gradle that allow you to define custom tests sets with individual dependencies.

Technically, each _product_ is a **Gradle build**
(has its own [settings.gradle.kts](jamcatch/settings.gradle.kts) file)
and each _module_ is a **Gradle subproject**
(has its own [build.gradle.kts](jamcatch/jamcatch-actors/build.gradle.kts) file for dependency declaration).
All _modules_ are organized as independent, first-class entities under a shared _product_ directory.
Inside a _product_, shared code should live in a clearly
defined common _module_ (like [engine/javarca-model](engine/javarca-model) in the example).

> [!WARNING]
> Putting comment code into a parent folder is an anti-pattern!
> 
> ```
> └── engine/
>     ├── src/... // common code (do not do this!)
>     ├── javarca-engine
>     │   └── src/..
>     └── javarca-feature-b
>         └── src/...
> ```

### Composing Applications and Reports

There are separate Gradle subproject for composing individual applications of reports from the _modules_.
These are so-called _aggregation_ subproject that do not contain any code themselves.
They only have a [build.gradle.kts](aggregation/app-jamcatch/build.gradle.kts) file to define the dependencies
to be aggregated.

```shell
└── aggregation
    ├── app-jamcatch
    │   └── build.gradle.kts
    ├── app-jamcatch-debug
    │   └── build.gradle.kts
    └── reports
        └── build.gradle.kts
```

To run an application:

```
./gradlew :aggregation:app-jamcatch:run
```

You can modify the dependencies in [build.gradle.kts](aggregation/app-jamcatch/build.gradle.kts) and run again
to explore the aggregation topic.

## Idiomatic Build Logic Structure

All individual Gradle build configurations are located in [gradle-conventions](gradle-conventions/src/main/kotlin).
It contains some standard configuration for Java compilation and testing
and more individual configurations for publishing and the end2end testing setup.
To organize this configuration into multiple files, Gradle provides the concept of
[Convention Plugins](https://docs.gradle.org/current/userguide/implementing_gradle_plugins_convention.html).
Here, individual `.gradle.kts` files for different configuration aspects can be written and then treated as plugins.
This allows reusing and composing the configuration aspects in a flexible way.

> [!Caution]
> With this, the following outdated practices are avoided:
> - No direct dependencies between tasks declared (except for extending lifecycle tasks like `assemble` or `check`)
> - No direct dependencies between tasks from different subprojects are declared
> - No cross-project configuration (_subproject_ / _allprojects_) is performed
> - Each build script of a subproject is simpler to read as all relationships to other projects are expressed in terms of dependencies

Treating all configurations as plugins, also allows publishing them to a
[Maven repository](https://repo.onepiece.software/#/releases/com/example/idiomatic/gradle/build). 
Technically, [gradle-conventions](gradle-conventions) is also a **Gradle build** just as our _products_ are.
In this case, though, it is a build producing Gradle plugins, rather than modules of our software.

## Discussions and alternative structuring options

- [Using or not using a Version Catalog](https://github.com/jjohannes/idiomatic-gradle/issues/4)
- [Folder name/location for convention plugins](https://github.com/jjohannes/idiomatic-gradle/issues/11)
- [Name of Version Catalog TOML file](https://github.com/jjohannes/idiomatic-gradle/issues/15)
- [Name of 'Settings Plugin' project](https://github.com/jjohannes/idiomatic-gradle/issues/8)
- [Using JVM packages for precompiled script plugins](https://github.com/jjohannes/idiomatic-gradle/issues/9)

More questions or points you would like to discuss? Please [open an issue](https://github.com/jjohannes/idiomatic-gradle/issues/new).

## FAQ

- [buildSrc vs. Composite Build for convention plugins](https://github.com/jjohannes/idiomatic-gradle/issues/16)
- [Where to put the Gradle Wrapper](https://github.com/jjohannes/idiomatic-gradle/issues/12)
- [How to use an external plugin in a local plugin and where to put its version](https://github.com/jjohannes/idiomatic-gradle/issues/14)
- [Android Studio: 'gradle/plugins' project clean uses different Gradle version](https://github.com/jjohannes/idiomatic-gradle/issues/5)

More questions or points you would like to discuss? Please [open an issue](https://github.com/jjohannes/idiomatic-gradle/issues/new).
