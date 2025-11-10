plugins {
    id("base")
}

// Configure lifecycle tasks in this umbrella build to enable running full builds with one command
tasks.assemble {
    dependsOn(gradle.includedBuilds.filter { it.name != "gradle-conventions" }.map {
        it.task(":assemble")
    })
}
tasks.check {
    dependsOn(gradle.includedBuilds.filter { it.name != "gradle-conventions" }.map {
        it.task(":check")
    })
}
tasks.build {
    dependsOn(gradle.includedBuilds.filter { it.name != "gradle-conventions" }.map {
        it.task(":build")
    })
}
tasks.clean {
    dependsOn(gradle.includedBuilds.filter { it.name != "gradle-conventions" }.map {
        it.task(":clean")
    })
}
tasks.register("publish") {
    dependsOn(gradle.includedBuilds.map {
        it.task(":publish")
    })
}
