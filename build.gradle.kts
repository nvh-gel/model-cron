import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import java.io.FileInputStream
import java.util.*

plugins {
    id("org.springframework.boot") version "3.0.5"
    id("io.spring.dependency-management") version "1.1.0"
    kotlin("jvm") version "1.7.22"
    kotlin("plugin.spring") version "1.7.22"
    id("com.google.cloud.tools.jib") version "3.3.1"
}

group = "com.eden.gallery"
java.sourceCompatibility = JavaVersion.VERSION_17

configurations {
    compileOnly {
        extendsFrom(configurations.annotationProcessor.get())
    }
}

var versions = Properties()
FileInputStream(file("versions.properties")).use {
    versions.load(it)
}

repositories {
    mavenCentral()
}

val version: String = "${versions["app"]}"

jib {
    from {
        image = "openjdk:17-slim"
    }
    to {
        image = "nvhien2703/gallery-cron"
        tags = mutableSetOf<String>(version, "latest")
        auth {
            username = System.getenv("DOCKER_USERNAME")
            password = System.getenv("DOCKER_PASSWORD")
        }
    }
    container {
        mainClass = "${group}.modelcron.ModelCronApplicationKt"
        ports = mutableListOf<String>("8080")
        environment = mapOf(
            "VERSION" to version,
            "APPLICATION_PORT" to "8080",
            "DEVELOPMENT_MODE" to "false"
        )
    }
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-data-mongodb")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jsoup:jsoup:${versions["jsoup"]}")
    implementation("org.apache.logging.log4j:log4j-api:${versions["log4j"]}")
    implementation("org.apache.logging.log4j:log4j-api-kotlin:${versions["log4jKotlin"]}")

    testImplementation("org.springframework.boot:spring-boot-starter-test")
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "17"
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}
