rootProject.name = providers.gradleProperty("projectName").get()

object EnvironmentVariables {
    const val GITHUB_USERNAME = "GITHUB_USERNAME"
    const val GITHUB_PACKAGES_TOKEN = "GITHUB_PACKAGES_TOKEN"
    const val GITHUB_ORGANIZATIONS_PLUGIN_PATH = "GITHUB_ORGANIZATIONS_PLUGIN_PATH"

    fun getValue(name: String): String? =
        System.getenv(name)?.takeIf { it.isNotBlank() }
}

val organizationsPluginLocalPath = EnvironmentVariables.getValue(EnvironmentVariables.GITHUB_ORGANIZATIONS_PLUGIN_PATH)
val githubUsername = EnvironmentVariables.getValue(EnvironmentVariables.GITHUB_USERNAME)
val githubPackagesToken = EnvironmentVariables.getValue(EnvironmentVariables.GITHUB_PACKAGES_TOKEN)

fun RepositoryHandler.commonRepositories() {
    mavenCentral()
}

fun RepositoryHandler.attachGithubOrganizationsMaven() {
    if (organizationsPluginLocalPath != null || githubPackagesToken == null || githubUsername == null) {
        return
    }
    maven("https://maven.pkg.github.com/$githubUsername/organizations") {
        credentials {
            username = githubUsername
            password = githubPackagesToken
        }
    }
}

fun RepositoryHandler.attachPluginRepositories() {
    gradlePluginPortal()
    attachGithubOrganizationsMaven()
}

@Suppress("UnstableApiUsage")
fun setupRepositories() {
    dependencyResolutionManagement.repositories {
        commonRepositories()
    }

    pluginManagement.repositories {
        maven("https://maven.fabricmc.net")
        commonRepositories()
        attachPluginRepositories()
    }
}

setupRepositories()

when {
    organizationsPluginLocalPath != null -> {
        includeBuild(organizationsPluginLocalPath)
        pluginManagement.includeBuild(organizationsPluginLocalPath)
    }

    githubPackagesToken == null || githubUsername == null -> error(
        """
        Invalid configuration for plugin resolution.
    
        Both ${EnvironmentVariables.GITHUB_PACKAGES_TOKEN} and ${EnvironmentVariables.GITHUB_USERNAME}
        must be provided together when using GitHub Packages.
        """.trimIndent()
    )
}
