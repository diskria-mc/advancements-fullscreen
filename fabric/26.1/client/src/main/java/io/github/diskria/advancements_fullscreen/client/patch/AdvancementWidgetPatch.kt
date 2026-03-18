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
        @Origin original: Int,
        @Local(1) titleFrameY: Int,
        @Local(2) titleFrameBottom: Int,
        @Local(3) descriptionHeight: Int,
        @Local(4) descriptionHeightWithBorderPadding: Int,
    ): Int {
        val hoverBottom = titleFrameBottom + descriptionHeightWithBorderPadding
        val hoverTop = titleFrameY - descriptionHeight + 1

        val backgroundBottom = advancementsScreen.fullscreenBackgroundHeight
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
        return if (useTopMode) Int.MIN_VALUE else Int.MAX_VALUE
    }
}
