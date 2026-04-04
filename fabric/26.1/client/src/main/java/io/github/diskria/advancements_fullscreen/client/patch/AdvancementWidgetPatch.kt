package io.github.diskria.advancements_fullscreen.client.patch

import io.github.diskria.advancements_fullscreen.client._AdvancementWidget
import io.github.diskria.advancements_fullscreen.generated.fullscreenBackgroundHeight
import io.github.diskria.advancements_fullscreen.generated.fullscreenVerticalMargin
import io.github.recrafter.lapis.annotations.*
import net.minecraft.client.Minecraft
import net.minecraft.client.gui.screens.advancements.AdvancementWidget
import net.minecraft.client.gui.screens.advancements.AdvancementsScreen

@Patch(_AdvancementWidget::class, Side.ClientOnly)
abstract class AdvancementWidgetPatch(@Origin val widget: AdvancementWidget) {

    private val advancementsScreen: AdvancementsScreen get() = widget.tab.screen

    @Hook(_AdvancementWidget.extractHover::class, At.Local)
    @AtLocal(
        op = Op.Set,
        type = Boolean::class,
        local = Local("topSide"),
        ordinal = [0]
    )
    fun fixHoverOutOfScreen(
        @Local titleTop: Int,
        @Local titleBarBottom: Int,
        @Local descriptionTextHeight: Int,
        @Local descriptionHeight: Int,
    ): Boolean {
        val hoverBottom = titleBarBottom + descriptionHeight
        val hoverTop = titleTop - descriptionTextHeight + 1

        val backgroundBottom = advancementsScreen.fullscreenBackgroundHeight
        val backgroundTop = descriptionHeight - descriptionTextHeight

        val verticalMargin = advancementsScreen.fullscreenVerticalMargin
        val windowBottom = advancementsScreen.fullscreenBackgroundHeight + AdvancementsScreen.WINDOW_INSIDE_Y +
            verticalMargin
        val windowTop = -AdvancementsScreen.WINDOW_INSIDE_X - verticalMargin

        return when {
            hoverBottom < backgroundBottom -> false
            hoverTop >= backgroundTop -> true

            hoverBottom <= windowBottom -> false
            hoverTop >= windowTop -> true

            else -> Minecraft.getInstance().hasAltDown()
        }
    }
}
