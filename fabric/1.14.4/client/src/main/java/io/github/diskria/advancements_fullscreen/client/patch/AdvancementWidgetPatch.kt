package io.github.diskria.advancements_fullscreen.client.patch

import io.github.diskria.advancements_fullscreen.client._AdvancementWidget
import io.github.diskria.advancements_fullscreen.client._AdvancementsScreen
import io.github.diskria.advancements_fullscreen.generated.Lapis
import io.github.diskria.advancements_fullscreen.generated.fullscreenBackgroundHeight
import io.github.recrafter.lapis.annotations.AtLiteral
import io.github.recrafter.lapis.annotations.Hook
import io.github.recrafter.lapis.annotations.Patch
import io.github.recrafter.lapis.annotations.Side
import net.minecraft.client.gui.screens.advancements.AdvancementWidget
import net.minecraft.client.gui.screens.advancements.AdvancementsScreen

@Patch(_AdvancementWidget::class, Side.ClientOnly)
abstract class AdvancementWidgetPatch : Lapis.Patch<AdvancementWidget>() {

    private val advancementsScreen: AdvancementsScreen get() = instance.tab.screen

    @Hook(_AdvancementWidget.drawHover::class, Hook.At.Literal)
    @AtLiteral(int = _AdvancementsScreen.WINDOW_INSIDE_HEIGHT, ordinal = [0])
    fun fixHoverOutOfScreen(): Int = advancementsScreen.fullscreenBackgroundHeight
}
