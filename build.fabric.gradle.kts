import dev.kikugie.stonecutter.build.StonecutterBuildExtension
import org.gradle.api.plugins.JavaPluginExtension
import org.gradle.api.tasks.compile.JavaCompile

plugins {
    id("dev.kikugie.loom-back-compat")
    `maven-publish`
}

val target = extensions.getByType(StonecutterBuildExtension::class.java)
val minecraftVersion = target.current.version
val javaVersion = project.property("java.version").toString().toInt()

group = project.property("mod.group") as String
version = project.property("mod.version") as String
base.archivesName = project.property("mod.id") as String

repositories {
    maven("https://maven.fabricmc.net/")
    maven("https://maven.blamejared.com/")
    mavenCentral()
}

dependencies {
    minecraft("com.mojang:minecraft:$minecraftVersion")
    loomx.applyMojangMappings()
    modImplementation("net.fabricmc:fabric-loader:${project.property("loader_version")}")
    modImplementation("net.fabricmc.fabric-api:fabric-api:${project.property("fabric_version")}")
    modCompileOnly("mezz.jei:jei-1.20.1-common-api-intermediary:${project.property("jei_version")}") {
        isTransitive = false
    }
}

tasks.processResources {
    inputs.property("version", project.version)
    duplicatesStrategy = DuplicatesStrategy.EXCLUDE
    filesMatching("fabric.mod.json") {
        expand("version" to project.version)
    }
}

extensions.configure<JavaPluginExtension> {
    withSourcesJar()
    sourceCompatibility = JavaVersion.toVersion(javaVersion)
    targetCompatibility = JavaVersion.toVersion(javaVersion)
}

tasks.withType<JavaCompile>().configureEach {
    options.release.set(javaVersion)
}

tasks.withType<Jar>().configureEach {
    duplicatesStrategy = DuplicatesStrategy.EXCLUDE
}

tasks.jar {
    inputs.property("archivesName", project.base.archivesName)
    from(rootProject.file("LICENSE")) {
        rename { "${it}_${inputs.properties["archivesName"]}" }
    }
}

loom {
    runConfigs.configureEach {
        preferGradleTask = true
        runDirectory = rootProject.file("run")
    }
}
