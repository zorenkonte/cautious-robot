plugins {
    kotlin("jvm") version "1.9.21"
}

group = "com.github.zorenkonte"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.slf4j:slf4j-simple:2.0.7")
    implementation("io.javalin:javalin:5.6.3")
    implementation("com.fasterxml.jackson.core:jackson-databind:2.15.0")
    testImplementation("org.jetbrains.kotlin:kotlin-test")
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(21)
}