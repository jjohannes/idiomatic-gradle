plugins {
    id("buildlogic.packaging")
    id("buildlogic.jacoco-aggregation")
}

dependencies {
    packaging("com.example.idiomatic.gradle:ig-server")
}
