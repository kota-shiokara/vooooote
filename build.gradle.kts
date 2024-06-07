val ktor_version: String by project
val kotlin_version: String by project
val logback_version: String by project

plugins {
    val kotlinVersion = "1.9.23"

    kotlin("jvm") version kotlinVersion
    id("io.ktor.plugin") version "2.3.10"
    id("org.jetbrains.kotlin.plugin.serialization") version kotlinVersion
}

group = "jp.ikanoshiokara"
version = "0.0.3"

application {
    mainClass.set("jp.ikanoshiokara.ApplicationKt")

    val isDevelopment: Boolean = project.ext.has("development")
    applicationDefaultJvmArgs = listOf("-Dio.ktor.development=$isDevelopment")
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("io.ktor:ktor-server-content-negotiation-jvm")
    implementation("io.ktor:ktor-server-core-jvm")
    implementation("io.ktor:ktor-serialization-kotlinx-json-jvm")
    implementation("io.ktor:ktor-server-cors-jvm")
    implementation("io.ktor:ktor-server-netty-jvm")
    implementation("ch.qos.logback:logback-classic:$logback_version")
    testImplementation("io.ktor:ktor-server-tests-jvm")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit:$kotlin_version")

    // Swagger UI
    implementation("io.github.smiley4:ktor-swagger-ui:2.10.0")
}
