plugins {
    kotlin("jvm") version "2.2.0"
    `java-library`
}

repositories {
    // Use Maven Central for resolving dependencies.
    mavenCentral()
}

dependencies {
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit5")
    testImplementation(libs.junit.jupiter.engine)
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
    testImplementation("io.kotest:kotest-property:6.0.0.M4")
    testImplementation("io.kotest:kotest-assertions-core:6.0.0.M4")
    testImplementation("io.kotest:kotest-runner-junit5:6.0.0.M4")
}

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(21)
    }
}

tasks.named<Test>("test") {
    useJUnitPlatform()
}
