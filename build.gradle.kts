plugins {
    kotlin("jvm") version "1.9.22"
    `maven-publish`
    id("com.github.johnrengelman.shadow") version "8.1.1"
    id("com.undefinedcreations.runServer") version "0.1.6"
}

group = "com.undefined"
version = "0.0.1"

publishing {
    repositories {
        maven {
            name = "UndefinedCreations"
            url = uri("https://repo.undefinedcreations.com/releases")
            credentials(PasswordCredentials::class) {
                username = project.properties["mavenUser"].toString()
                password = project.properties["mavenPassword"].toString()
            }
        }
    }
    publications {
        register<MavenPublication>("maven") {
            artifactId = "utils"
            from(components["java"])
        }
    }
}

repositories {
    mavenCentral()
    maven("https://hub.spigotmc.org/nexus/content/repositories/snapshots/") {
        name = "spigotmc-repo"
    }
    maven("https://oss.sonatype.org/content/groups/public/") {
        name = "sonatype"
    }
}

dependencies {
    compileOnly("org.spigotmc:spigot-api:1.21.3-R0.1-SNAPSHOT")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
}

kotlin {
    jvmToolchain(21)
}

tasks {
    assemble {
        dependsOn("shadowJar")
    }
    processResources {
        val props = mapOf("version" to version)
        inputs.properties(props)
        filteringCharset = "UTF-8"
        filesMatching("plugin.yml") {
            expand(props)
        }
    }
    runServer {
        minecraftVersion("1.21.3")
        serverFolderName { "run" }
        acceptMojangEula()
    }
}