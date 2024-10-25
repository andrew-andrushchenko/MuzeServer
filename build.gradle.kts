plugins {
    alias(libs.plugins.kotlin.jvm)
    alias(libs.plugins.ktor)
    alias(libs.plugins.kotlin.serialization)
}

group = "com.andrii_a.muze"
version = "0.0.1"

application {
    mainClass.set("io.ktor.server.netty.EngineMain")

    val isDevelopment: Boolean = project.ext.has("development")
    applicationDefaultJvmArgs = listOf("-Dio.ktor.development=$isDevelopment")
}

kotlin {
    jvmToolchain(18)
}

repositories {
    mavenCentral()
}

dependencies {
    implementation(libs.logback.classic)

    implementation(libs.exposed.core)
    implementation(libs.exposed.dao)
    implementation(libs.exposed.jdbc)
    implementation(libs.exposed.java.time)
    implementation(libs.postgresql)
    implementation(libs.hikari)

    implementation(libs.koin.ktor)
    implementation(libs.koin.logger.slf4j)
    implementation(libs.ktor.server.core.jvm)
    implementation(libs.ktor.server.contentnegotiation.jvm)
    implementation(libs.ktor.server.calllogging.jvm)
    implementation(libs.ktor.serialization.kotlinx.json.jvm)
    implementation(libs.ktor.server.host.common.jvm)
    implementation(libs.ktor.server.netty.jvm)

    testImplementation(libs.kotlin.test.junit)
    testImplementation(libs.ktor.server.tests.jvm)
}

tasks.wrapper {
    gradleVersion = "8.9"
    distributionType = Wrapper.DistributionType.ALL
}