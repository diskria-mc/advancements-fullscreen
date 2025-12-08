pluginManagement {
    repositories {
        maven("https://recrafter.github.io/recipe") {
            name = "Recipe"
        }
        maven("https://recrafter.github.io/crafter") {
            name = "Crafter"
        }
        gradlePluginPortal()
    }
}

plugins {
    id("io.github.recrafter.recipe") version "0.2.8"
}

recipe {
    crafter {
        clientOnly()
    }
}
