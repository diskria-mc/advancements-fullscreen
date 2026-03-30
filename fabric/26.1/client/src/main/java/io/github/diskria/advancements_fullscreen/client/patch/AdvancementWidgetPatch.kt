package io.github.diskria.advancements_fullscreen.client.patch

import io.github.diskria.advancements_fullscreen.client._AdvancementWidget
import io.github.diskria.advancements_fullscreen.generated.Lapis
import io.github.diskria.advancements_fullscreen.generated.fullscreenBackgroundHeight
import io.github.diskria.advancements_fullscreen.generated.fullscreenVerticalMargin
import io.github.recrafter.lapis.annotations.*
import net.minecraft.client.Minecraft
import net.minecraft.client.gui.screens.advancements.AdvancementWidget
import net.minecraft.client.gui.screens.advancements.AdvancementsScreen

@Patch(_AdvancementWidget::class, Side.ClientOnly)
abstract class AdvancementWidgetPatch : Lapis.Patch<AdvancementWidget>() {

    private val advancementsScreen: AdvancementsScreen get() = instance.tab.screen

    @Hook(_AdvancementWidget.drawHover::class, Hook.At.Literal)
    @AtLiteral(int = AdvancementsScreen.WINDOW_INSIDE_HEIGHT, ordinal = [0])
    fun fixHoverOutOfScreen(
        @Local(1) titleTop: Int,
        @Local(2) titleBarBottom: Int,
        @Local(3) descriptionTextHeight: Int,
        @Local(4) descriptionHeight: Int,
    ): Int {
        val hoverBottom = titleBarBottom + descriptionHeight
        val hoverTop = titleTop - descriptionTextHeight + 1

        val backgroundBottom = advancementsScreen.fullscreenBackgroundHeight
        val backgroundTop = descriptionHeight - descriptionTextHeight

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
        return if (useTopMode) Int.MIN_VALUE else Int.MAX_VALUE
    }
}
