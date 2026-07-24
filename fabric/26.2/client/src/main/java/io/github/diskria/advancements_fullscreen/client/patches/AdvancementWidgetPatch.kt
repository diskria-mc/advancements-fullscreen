package io.github.diskria.advancements_fullscreen.client.patches

import io.github.diskria.advancements_fullscreen.client.schemas._AdvancementWidget
import io.github.diskria.advancements_fullscreen.client.schemas._AdvancementsScreen
import io.github.diskria.advancements_fullscreen.client.schemas.invoke
import io.github.recrafter.lapis.annotations.*
import net.minecraft.client.Minecraft
import net.minecraft.client.gui.screens.advancements.AdvancementTab
import net.minecraft.client.gui.screens.advancements.AdvancementWidget
import net.minecraft.client.gui.screens.advancements.AdvancementsScreen
import javax.lang.model.element.Modifier

@Patch(AdvancementWidget::class, side = Side.ClientOnly)
abstract class AdvancementWidgetPatch {

    private val advancementsScreen: AdvancementsScreen get() = tab.screen

    @Hook<_AdvancementWidget.extractHover>(Ats.Local)
    @AtLocal<Boolean>(
        op = Op.Set,
        local = KLocal("topSide"),
        ordinal = [0]
    )
    fun fixHoverOutOfScreen(
        @KLocal titleTop: Int,
        @KLocal titleBarBottom: Int,
        @KLocal descriptionTextHeight: Int,
        @KLocal descriptionHeight: Int,
    ): Boolean = with(advancementsScreen) {
        val hoverBottom = titleBarBottom + descriptionHeight
        val hoverTop = titleTop - descriptionTextHeight + 1
        val backgroundTop = descriptionHeight - descriptionTextHeight
        val windowBottom = fullscreenBackgroundHeight + _AdvancementsScreen.WINDOW_INSIDE_Y() + fullscreenVerticalMargin
        val windowTop = -(_AdvancementsScreen.WINDOW_INSIDE_X() + fullscreenVerticalMargin)
        return when {
            hoverBottom < fullscreenBackgroundHeight -> false
            hoverTop >= backgroundTop -> true
            hoverBottom <= windowBottom -> false
            hoverTop >= windowTop -> true
            else -> Minecraft.getInstance().hasAltDown()
        }
    }

    @KShadow(Modifier.PRIVATE, Modifier.FINAL)
    abstract val tab: AdvancementTab
}
