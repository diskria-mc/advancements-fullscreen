pluginManagement {
    repositories {
        fun resolvePluginMaven(repoName: String) {
            val mavenName = repoName.replaceFirstChar { it.uppercaseChar() }
            val localMavens = rootDir.parentFile.parentFile.resolve("Recrafter")
                .resolve(repoName).resolve("build/maven").listFiles().orEmpty()
            if (localMavens.isNotEmpty()) {
                maven(uri(localMavens.first())) {
                    name = "$mavenName Local"
                }
            } else {
                maven("https://recrafter.github.io/$repoName") {
                    name = mavenName
                }
            }
        }

        resolvePluginMaven("recipe")
        resolvePluginMaven("crafter")

        mavenLocal()
        gradlePluginPortal()
    }
}

plugins {
    id("io.github.recrafter.recipe") version "1.2.0"
}

recipe {
    crafter {
        clientOnly()
    }
}

dependencyResolutionManagement {
    @Suppress("UnstableApiUsage")
    repositories {
        mavenLocal()
    }
}
