plugins {
    id("com.example.packaging")
    id("com.example.jacoco-aggregation")
}

dependencies {
    packaging("com.example.idiomatic.gradle:server")
}
