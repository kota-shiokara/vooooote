plugins {
    kotlin("multiplatform")
}

group = "jp.ikanoshiokara.vooooote"
version = "0.0.1"

repositories {
    mavenCentral()
}

kotlin {
    jvm()
    js(IR) {
        browser()
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(kotlin("stdlib-common"))
            }
        }
    }
}