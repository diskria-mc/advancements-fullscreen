package io.github.diskria.advancements.fullscreen.extensions

import net.fabricmc.api.EnvType
import net.fabricmc.api.Environment
import net.minecraft.client.MinecraftClient

@Environment(EnvType.CLIENT)
interface AdvancementsScreenExtension {
    fun advancements_fullscreen_getWindowWidth(isWithBorder: Boolean): Int
    fun advancements_fullscreen_getWindowHeight(isWithBorder: Boolean): Int
    fun advancements_fullscreen_getWindowHorizontalMargin(): Int
    fun advancements_fullscreen_getWindowVerticalMargin(): Int
    fun advancements_fullscreen_resize(client: MinecraftClient, width: Int, height: Int)
}
