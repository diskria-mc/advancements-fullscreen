package io.github.diskria.advancements_fullscreen.client.patch

import io.github.diskria.advancements_fullscreen.client._AdvancementTab
import io.github.diskria.advancements_fullscreen.client._AdvancementsScreen
import io.github.diskria.advancements_fullscreen.generated.Lapis
import io.github.diskria.advancements_fullscreen.generated.fullscreenBackgroundHeight
import io.github.diskria.advancements_fullscreen.generated.fullscreenBackgroundWidth
import io.github.recrafter.lapis.annotations.*
import net.minecraft.class_3268
import net.minecraft.client.gui.screen.AdvancementsScreen

@Patch(_AdvancementTab::class, Side.ClientOnly)
abstract class AdvancementTabPatch : Lapis.Patch<class_3268>() {

    private val advancementsScreen: AdvancementsScreen get() = instance.field_15955

    @Hook(_AdvancementTab.scroll::class, Hook.At.Literal)
    @AtLiteral(int = _AdvancementsScreen.WINDOW_INSIDE_WIDTH, ordinal = [0, 1])
    fun overrideScrollXLimit(): Int = advancementsScreen.fullscreenBackgroundWidth

    @Hook(_AdvancementTab.scroll::class, Hook.At.Literal)
    @AtLiteral(int = _AdvancementsScreen.WINDOW_INSIDE_HEIGHT, ordinal = [0, 1])
    fun overrideScrollYLimit(): Int = advancementsScreen.fullscreenBackgroundHeight

    @Hook(_AdvancementTab.drawTooltips::class, Hook.At.Literal)
    @AtLiteral(int = _AdvancementsScreen.WINDOW_INSIDE_WIDTH, ordinal = [0, 1])
    fun overrideHoverOverlayWidth(): Int = advancementsScreen.fullscreenBackgroundWidth

    @Hook(_AdvancementTab.drawTooltips::class, Hook.At.Literal)
    @AtLiteral(int = _AdvancementsScreen.WINDOW_INSIDE_HEIGHT, ordinal = [0, 1])
    fun overrideHoverOverlayHeight(): Int = advancementsScreen.fullscreenBackgroundHeight

    @Hook(_AdvancementTab.drawContents::class, Hook.At.Literal)
    @AtLiteral(int = _AdvancementsScreen.WINDOW_INSIDE_WIDTH, ordinal = [0])
    fun overrideBackgroundWidth(): Int = advancementsScreen.fullscreenBackgroundWidth

    @Hook(_AdvancementTab.drawContents::class, Hook.At.Literal)
    @AtLiteral(int = _AdvancementsScreen.WINDOW_INSIDE_HEIGHT, ordinal = [0])
    fun overrideBackgroundHeight(): Int = advancementsScreen.fullscreenBackgroundHeight

    @Hook(_AdvancementTab.drawContents::class, Hook.At.Literal)
    @AtLiteral(int = _AdvancementsScreen.WINDOW_INSIDE_WIDTH / 2, ordinal = [0])
    fun overrideBackgroundX(): Int = advancementsScreen.fullscreenBackgroundWidth / 2

    @Hook(_AdvancementTab.drawContents::class, Hook.At.Literal)
    @AtLiteral(int = _AdvancementsScreen.WINDOW_INSIDE_HEIGHT / 2, ordinal = [0])
    fun overrideBackgroundY(): Int = advancementsScreen.fullscreenBackgroundHeight / 2

    @Hook(_AdvancementTab.drawContents::class, Hook.At.Literal)
    @AtLiteral(int = _AdvancementsScreen.BACKGROUND_TILE_COUNT_X + 1, ordinal = [0])
    fun overrideBackgroundColumns(): Int = advancementsScreen.fullscreenBackgroundWidth / 16 + 1

    @Hook(_AdvancementTab.drawContents::class, Hook.At.Literal)
    @AtLiteral(int = _AdvancementsScreen.BACKGROUND_TILE_COUNT_Y + 1, ordinal = [0])
    fun overrideBackgroundRows(): Int = advancementsScreen.fullscreenBackgroundHeight / 16 + 1
}
