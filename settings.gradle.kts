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

        resolvePluginMaven("crafter")
        gradlePluginPortal()
    }
}

plugins {
    id("io.github.recrafter.recipe") version "1.2.6"
}

recipe {
    crafter {
        clientOnly()
    }
}
