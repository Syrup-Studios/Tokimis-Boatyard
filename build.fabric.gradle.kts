import dev.kikugie.stonecutter.build.StonecutterBuildExtension
import org.gradle.api.plugins.JavaPluginExtension
import org.gradle.api.tasks.compile.JavaCompile
import org.gradle.api.file.RelativePath

plugins {
    id("dev.kikugie.loom-back-compat")
    `maven-publish`
}

val target = extensions.getByType(StonecutterBuildExtension::class.java)
val minecraftVersion = target.current.version
val javaVersion = project.property("java.version").toString().toInt()
val jeiVersion = project.property("jei_version").toString()
val modVersion = "${project.property("mod.version")}+$minecraftVersion-fabric"

group = project.property("mod.group") as String
version = modVersion
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
    modCompileOnly("mezz.jei:jei-${minecraftVersion}-common-api-intermediary:$jeiVersion") {
        isTransitive = false
    }
}

tasks.processResources {
    inputs.properties(mapOf("version" to project.version, "minecraftVersion" to minecraftVersion, "javaVersion" to javaVersion))
    duplicatesStrategy = DuplicatesStrategy.EXCLUDE
    filesMatching("fabric.mod.json") {
        expand(
            "version" to project.version,
            "minecraft_version" to "~$minecraftVersion",
            "java_version" to ">=$javaVersion",
            "java_level" to javaVersion
        )
    }
    filesMatching("tokimiboatyard.mixins.json") {
        expand("java_level" to javaVersion)
    }
    eachFile {
        if (minecraftVersion == "1.21.1" && path.startsWith("data/tokimiboatyard/recipes/")) {
            relativePath = RelativePath(true, *path.replaceFirst("/recipes/", "/recipe/").split('/').toTypedArray())
        }
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
