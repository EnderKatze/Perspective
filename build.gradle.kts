plugins {
    kotlin("jvm") version "2.0.20"
}

group = "de.enderkatze"
version = "1.0-SNAPSHOT"

repositories {
    mavenLocal()
    maven {
        url = uri("https://hub.spigotmc.org/nexus/content/repositories/snapshots/")
    }

    maven {
        url = uri("https://oss.sonatype.org/content/groups/public/")
    }
}

dependencies {
    implementation(kotlin("stdlib"))
    compileOnly(libs.org.spigotmc.spigot.api)
}

tasks.test {
    useJUnitPlatform()
}