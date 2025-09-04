plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "0.10.0"
}

rootProject.name = providers.gradleProperty("projectName").get()

object EnvironmentVariables {
    const val GITHUB_USERNAME = "GITHUB_USERNAME"
    const val GITHUB_PACKAGES_TOKEN = "GITHUB_PACKAGES_TOKEN"
    const val PROJEKTOR_PLUGIN_PATH = "PROJEKTOR_PLUGIN_PATH"

    fun getValue(name: String): String? =
        System.getenv(name)?.takeIf { it.isNotBlank() }
}

val projektorPluginLocalPath = EnvironmentVariables.getValue(EnvironmentVariables.PROJEKTOR_PLUGIN_PATH)

val githubUsername = EnvironmentVariables.getValue(EnvironmentVariables.GITHUB_USERNAME)
val githubPackagesToken = EnvironmentVariables.getValue(EnvironmentVariables.GITHUB_PACKAGES_TOKEN)

fun RepositoryHandler.mavenMinecraftLibraries() {
    maven("https://libraries.minecraft.net")
}

fun RepositoryHandler.mavenFabricMinecraft() {
    maven("https://maven.fabricmc.net")
}

fun RepositoryHandler.mavenForgeMinecraft() {
    maven("https://maven.minecraftforge.net")
}

fun RepositoryHandler.commonRepositories() {
    mavenCentral()
    mavenMinecraftLibraries()
    mavenFabricMinecraft()
    mavenForgeMinecraft()
}

fun RepositoryHandler.attachProjektorGradlePluginMaven() {
    if (projektorPluginLocalPath != null || githubPackagesToken == null || githubUsername == null) {
        return
    }
    maven("https://maven.pkg.github.com/$githubUsername/projektor") {
        credentials {
            username = githubUsername
            password = githubPackagesToken
        }
    }
}

fun RepositoryHandler.attachPluginRepositories() {
    gradlePluginPortal()
    attachProjektorGradlePluginMaven()
}

@Suppress("UnstableApiUsage")
fun setupRepositories() {
    dependencyResolutionManagement.repositories {
        commonRepositories()
    }

    pluginManagement.repositories {
        commonRepositories()
        attachPluginRepositories()
    }
}

setupRepositories()

when {
    projektorPluginLocalPath != null -> {
        includeBuild(projektorPluginLocalPath)
        pluginManagement.includeBuild(projektorPluginLocalPath)
    }

    githubPackagesToken == null || githubUsername == null -> error(
        """
        Invalid configuration for plugin resolution.
    
        Both ${EnvironmentVariables.GITHUB_PACKAGES_TOKEN} and ${EnvironmentVariables.GITHUB_USERNAME}
        must be provided together when using GitHub Packages.
        """.trimIndent()
    )
}

include(":common")

