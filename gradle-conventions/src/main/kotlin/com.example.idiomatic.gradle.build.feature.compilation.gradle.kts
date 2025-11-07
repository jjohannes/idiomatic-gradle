// configure aspects of Java compilation.
plugins {
    id("java")
    id("org.gradlex.reproducible-builds")
}

java {
    toolchain.languageVersion = JavaLanguageVersion.of(21)
}

tasks.withType<JavaCompile>().configureEach {
    options.compilerArgs.add("-Xlint:all,-serial")
    options.compilerArgs.add("-Werror")
}

tasks.withType<Javadoc>().configureEach {
    options {
        this as StandardJavadocDocletOptions
        addStringOption("Xdoclint:all,-missing", "-Xwerror")
    }
}
