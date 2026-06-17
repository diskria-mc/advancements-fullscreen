pluginManagement {
    repositories {
        mavenLocal()
        gradlePluginPortal()
    }
}

plugins {
    id("io.github.recrafter.recipe") version "1.2.7"
}

recipe {
    crafter {
        clientOnly()
    }
}
