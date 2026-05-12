package io.github.diskria.advancements_fullscreen.client.patches

import io.github.diskria.advancements_fullscreen.client.schemas._AdvancementWidget
import io.github.diskria.advancements_fullscreen.client.schemas._AdvancementsScreen
import io.github.diskria.advancements_fullscreen.client.schemas.value
import io.github.recrafter.lapis.annotations.*
import net.minecraft.client.Minecraft
import net.minecraft.client.gui.screens.advancements.AdvancementTab
import net.minecraft.client.gui.screens.advancements.AdvancementsScreen
import javax.lang.model.element.Modifier

@Patch(_AdvancementWidget::class, side = Side.ClientOnly)
abstract class AdvancementWidgetPatch {

    private val advancementsScreen: AdvancementsScreen get() = tab.screen

    @KShadow(Modifier.PRIVATE, Modifier.FINAL)
    abstract val tab: AdvancementTab

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
        val windowBottom = advancementsScreen.fullscreenBackgroundHeight + _AdvancementsScreen.WINDOW_INSIDE_Y.value +
            verticalMargin
        val windowTop = _AdvancementsScreen.WINDOW_INSIDE_X.value.unaryMinus() - verticalMargin

        return when {
            hoverBottom < backgroundBottom -> false
            hoverTop >= backgroundTop -> true

            hoverBottom <= windowBottom -> false
            hoverTop >= windowTop -> true

            else -> Minecraft.getInstance().hasAltDown()
        }
    }
}
