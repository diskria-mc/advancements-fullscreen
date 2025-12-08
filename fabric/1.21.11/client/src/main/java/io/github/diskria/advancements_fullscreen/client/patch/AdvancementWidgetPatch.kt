package io.github.diskria.advancements_fullscreen.client.patch

import io.github.diskria.advancements_fullscreen.client.AdvancementWidget_
import io.github.diskria.advancements_fullscreen.generated.Lapis
import io.github.diskria.advancements_fullscreen.generated.fullscreenBackgroundHeight
import io.github.diskria.advancements_fullscreen.generated.fullscreenVerticalMargin
import io.github.recrafter.lapis.Hook
import io.github.recrafter.lapis.Side
import io.github.recrafter.lapis.annotations.*
import net.minecraft.client.Minecraft
import net.minecraft.client.gui.screens.advancements.AdvancementWidget
import net.minecraft.client.gui.screens.advancements.AdvancementsScreen

@LaPatch(AdvancementWidget::class, Side.ClientOnly)
abstract class AdvancementWidgetPatch : Lapis.Patch<AdvancementWidget>() {

    private val advancementsScreen: AdvancementsScreen
        get() = instance.tab.screen

    @LaHook(
        AdvancementWidget_.drawHover::class,
        Hook.Literal
    )
    fun overrideHoverIntersectsWindowBorderCheck(
        @LaLiteral(int = AdvancementsScreen.WINDOW_INSIDE_HEIGHT) original: Int,
        @LaOrdinal(LaOrdinal.FIRST) ordinal: Int,
    ): Int = advancementsScreen.fullscreenBackgroundHeight

    @LaHook(
        AdvancementWidget_.drawHover::class,
        Hook.Literal
    )
    fun fixHoverOutOfScreen(
        @LaLiteral(int = 113) original: Int,
        @LaLocal(1) titleFrameY: Int,
        @LaLocal(2) titleFrameBottom: Int,
        @LaLocal(3) descriptionHeight: Int,
        @LaLocal(4) descriptionHeightWithBorderPadding: Int,
    ): Int {
        val hoverBottom = titleFrameBottom + descriptionHeightWithBorderPadding
        val hoverTop = titleFrameY - descriptionHeight + 1

        val backgroundBottom = original
        val backgroundTop = descriptionHeightWithBorderPadding - descriptionHeight

        val verticalMargin = advancementsScreen.fullscreenVerticalMargin
        val windowBottom = advancementsScreen.fullscreenBackgroundHeight + AdvancementsScreen.WINDOW_INSIDE_Y +
            verticalMargin
        val windowTop = -AdvancementsScreen.WINDOW_INSIDE_X - verticalMargin

        val useTopMode = when {
            hoverBottom < backgroundBottom -> false
            hoverTop >= backgroundTop -> true

            hoverBottom <= windowBottom -> false
            hoverTop >= windowTop -> true

            else -> Minecraft.getInstance().hasAltDown()
        }
        return if (useTopMode) Int.MAX_VALUE else Int.MIN_VALUE
    }
}
