package io.github.diskria.advancements_fullscreen.extensions

import net.fabricmc.api.EnvType
import net.fabricmc.api.Environment
import net.minecraft.client.Minecraft

@Environment(EnvType.CLIENT)
interface AdvancementsScreenExtension {
    fun advancements_fullscreen_getWindowWidth(withBorder: Boolean): Int
    fun advancements_fullscreen_getWindowHeight(withBorder: Boolean): Int
    fun advancements_fullscreen_getWindowHorizontalMargin(): Int
    fun advancements_fullscreen_getWindowVerticalMargin(): Int
    fun advancements_fullscreen_resize(minecraft: Minecraft, width: Int, height: Int)
}
