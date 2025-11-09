// Connect all root project tasks to corresponding subproject lifecycle tasks.
// This allows to build a complete project in a composite setup.
plugins {
    id("base")
}

if (!subprojects.isEmpty()) {
    tasks.assemble {
        dependsOn(subprojects.map { ":${it.name}:assemble" })
    }
    tasks.check {
        dependsOn(subprojects.map { ":${it.name}:check" })
    }
    tasks.build {
        dependsOn(subprojects.map { ":${it.name}:build" })
    }
    tasks.clean {
        dependsOn(subprojects.map { ":${it.name}:clean" })
    }
    tasks.register("publish") {
        dependsOn(subprojects.map { ":${it.name}:publish" })
    }
}
