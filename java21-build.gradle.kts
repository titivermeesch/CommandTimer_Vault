import java.net.URI

plugins {
    id("java")
}

java {
    toolchain.languageVersion.set(JavaLanguageVersion.of(21))
}

group = "me.playbosswar.vaultconditions"
version = "1.0.0"

repositories {
    mavenCentral()
    mavenLocal()

    maven {
        url = URI("https://hub.spigotmc.org/nexus/content/repositories/snapshots/")
    }

    maven {
        url = URI("https://jitpack.io")
    }
}

dependencies {
    compileOnly("me.playbosswar.com:commandtimer:8.14.0")
    compileOnly("org.jeasy:easy-rules-core:4.1.0")
    compileOnly("org.jetbrains:annotations:23.1.0")
    compileOnly("com.github.MilkBowl:VaultAPI:1.7")
    compileOnly("org.spigotmc:spigot-api:1.21.8-R0.1-SNAPSHOT")
    implementation("net.kyori:event-api:3.0.0")
}
