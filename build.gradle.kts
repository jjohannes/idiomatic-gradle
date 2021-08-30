// This file is ONLY used to define global lifecycle tasks
val taskGroup = "IG build"
tasks.named<TaskReportTask>("tasks") {
    displayGroup = taskGroup
    doLast {
        println("\nTo work with selected components, run ':<component>:tasks' (e.g. ':ig-data:tasks') for more information")
    }
}

// Register lifecycle tasks in this umbrella build.
// A user/CI usually only needs these.
val checkAll = tasks.register("checkAll") {
    group = taskGroup
    description = "Run all tests and create code coverage report"
    dependsOn(gradle.includedBuilds.filter { it.name.startsWith("ig-") }.map { it.task(":checkAll") })
    dependsOn(gradle.includedBuild("aggregation").task(":package-server:codeCoverageReport"))
}
val buildServer = tasks.register("buildServer") {
    group = taskGroup
    description = "Build the Server (executable Jar)"
    dependsOn(gradle.includedBuild("aggregation").task(":package-server:assemble"))
}
val buildClientApi = tasks.register("buildClientApi") {
    group = taskGroup
    description = "Build the Client API"
    dependsOn(gradle.includedBuild("aggregation").task(":publish-api:assemble"))
}
tasks.register("publishClientApi") {
    group = taskGroup
    description = "Publish the Client API"
    dependsOn(gradle.includedBuild("aggregation").task(":publish-api:publish"))
}
tasks.register("build") {
    group = taskGroup
    description = "Run all tests, build Server and Client API (without publishing)"
    dependsOn(checkAll, buildServer, buildClientApi)
}