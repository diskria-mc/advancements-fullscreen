import io.github.diskria.projektor.gradle.extensions.configureMinecraftMod
import io.github.diskria.projektor.minecraft.ModEnvironment
import io.github.diskria.projektor.minecraft.ModLoader

plugins {
    idea
    alias(libs.plugins.kotlin.jvm)
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.projektor)
    alias(libs.plugins.build.config)
    alias(libs.plugins.modrinth.minotaur)
    alias(libs.plugins.fabric.loom)
}

configureMinecraftMod(
    environment = ModEnvironment.CLIENT_SIDE_ONLY,
    isFabricApiRequired = false,
    modrinthProjectId = "7BgsROit",
)
