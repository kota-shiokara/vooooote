plugins {
    kotlin("jvm")
    application
}

group = "jp.ikanoshiokara.vooooote"
version = "0.0.1"

application {
    mainClass.set("io.ktor.server.netty.EngineMain")
}

repositories {
    mavenCentral()
    maven("https://kotlin.bintray.com/ktor")
}

dependencies {
    implementation(project(":common"))
    implementation(kotlin("stdlib"))

    // ktor
    val ktor_version = "2.1.3"
    implementation("io.ktor:ktor-server-core-jvm:$ktor_version")
    implementation("io.ktor:ktor-server-netty-jvm:$ktor_version")
    implementation("io.ktor:ktor-server-status-pages-jvm:$ktor_version")
    implementation("io.ktor:ktor-server-default-headers-jvm:$ktor_version")

    // logging
    val logback_version = "1.4.4"
    implementation("ch.qos.logback:logback-classic:$logback_version")
    implementation("io.ktor:ktor-server-call-logging:$ktor_version")
}
