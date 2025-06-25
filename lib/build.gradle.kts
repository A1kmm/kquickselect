import com.vanniktech.maven.publish.SonatypeHost

plugins {
    kotlin("jvm") version "2.2.0"
    `java-library`
    id("com.vanniktech.maven.publish") version "0.33.0"
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

mavenPublishing {
  coordinates("com.amxl", "kquickselect", "1.0.0")
  publishToMavenCentral()

  signAllPublications()
  pom {
    name.set("kquickselect")
    description.set("Provides quickselect for Kotlin.")
    inceptionYear.set("2025")
    url.set("https://github.com/A1kmm/kquickselect/")
    licenses {
      license {
        name.set("The Apache License, Version 2.0")
        url.set("http://www.apache.org/licenses/LICENSE-2.0.txt")
        distribution.set("http://www.apache.org/licenses/LICENSE-2.0.txt")
      }
    }
    developers {
      developer {
        id.set("A1kmm")
        name.set("Andrew Miller")
        url.set("https://github.com/A1kmm/")
      }
    }
    scm {
      url.set("https://github.com/A1kmm/kquickselect/")
      connection.set("scm:git:git://github.com/A1kmm/kquickselect.git")
      developerConnection.set("scm:git:ssh://git@github.com/A1kmm/kquickselect.git")
    }
  }
}
