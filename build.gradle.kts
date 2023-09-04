import java.net.URI

plugins {
    id("java")
}

java {
    toolchain.languageVersion.set(JavaLanguageVersion.of(17))
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
    compileOnly("me.playbosswar.com:commandtimer:8.3.3")
    compileOnly("org.jeasy:easy-rules-core:4.1.0")
    compileOnly("org.jetbrains:annotations:23.1.0")
    compileOnly("com.github.MilkBowl:VaultAPI:1.7")
    implementation("net.kyori:event-api:3.0.0")
    implementation("org.spigotmc:spigot-api:1.17.1-R0.1-SNAPSHOT")
}
