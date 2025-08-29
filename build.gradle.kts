import io.github.diskria.organizations.extensions.configureMinecraftMod
import io.github.diskria.organizations.minecraft.ModEnvironment
import io.github.diskria.organizations.minecraft.ModLoader

plugins {
    alias(libs.plugins.kotlin.jvm)
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.organizations)
    alias(libs.plugins.build.config)
    alias(libs.plugins.fabric.loom)
    alias(libs.plugins.modrinth.minotaur)
}

configureMinecraftMod(
    "1.21.8",
    ModEnvironment.CLIENT_ONLY,
    ModLoader.FABRIC,
    isFabricApiRequired = false,
)
