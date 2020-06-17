plugins {
    id("buildlogic.packaging")
    id("buildlogic.jacoco-aggregation")
}

dependencies {
    packaging(project(":ig-server"))
}
