package io.github.diskria.advancements_fullscreen.client.patch

import io.github.diskria.advancements_fullscreen.client._AdvancementTab
import io.github.diskria.advancements_fullscreen.generated.Lapis
import io.github.diskria.advancements_fullscreen.generated.fullscreenBackgroundHeight
import io.github.diskria.advancements_fullscreen.generated.fullscreenBackgroundWidth
import io.github.recrafter.lapis.annotations.*
import net.minecraft.client.gui.screens.advancements.AdvancementTab
import net.minecraft.client.gui.screens.advancements.AdvancementsScreen

@Patch(_AdvancementTab::class, Side.ClientOnly)
abstract class AdvancementTabPatch : Lapis.Patch<AdvancementTab>() {

    private val advancementsScreen: AdvancementsScreen get() = instance.screen

    @Hook(_AdvancementTab.scroll::class, Hook.At.Literal)
    @AtLiteral(int = AdvancementsScreen.WINDOW_INSIDE_WIDTH, ordinal = [0])
    fun overrideScrollXLimit(@Origin original: Int): Int = advancementsScreen.fullscreenBackgroundWidth

    @Hook(_AdvancementTab.scroll::class, Hook.At.Literal)
    @AtLiteral(int = AdvancementsScreen.WINDOW_INSIDE_HEIGHT, ordinal = [0])
    fun overrideScrollYLimit(@Origin original: Int): Int = advancementsScreen.fullscreenBackgroundHeight

    @Hook(_AdvancementTab.canScrollHorizontally::class, Hook.At.Literal)
    @AtLiteral(int = AdvancementsScreen.WINDOW_INSIDE_WIDTH, ordinal = [0])
    fun overrideScrollXLimitCheck(@Origin original: Int): Int = advancementsScreen.fullscreenBackgroundWidth

    @Hook(_AdvancementTab.canScrollVertically::class, Hook.At.Literal)
    @AtLiteral(int = AdvancementsScreen.WINDOW_INSIDE_HEIGHT, ordinal = [0])
    fun overrideScrollYLimitCheck(@Origin original: Int): Int = advancementsScreen.fullscreenBackgroundHeight

    @Hook(_AdvancementTab.drawTooltips::class, Hook.At.Literal)
    @AtLiteral(int = AdvancementsScreen.WINDOW_INSIDE_WIDTH, ordinal = [0, 1])
    fun overrideHoverOverlayWidth(@Origin original: Int): Int = advancementsScreen.fullscreenBackgroundWidth

    @Hook(_AdvancementTab.drawTooltips::class, Hook.At.Literal)
    @AtLiteral(int = AdvancementsScreen.WINDOW_INSIDE_HEIGHT, ordinal = [0, 1])
    fun overrideHoverOverlayHeight(@Origin original: Int): Int = advancementsScreen.fullscreenBackgroundHeight

    @Hook(_AdvancementTab.drawContents::class, Hook.At.Literal)
    @AtLiteral(int = AdvancementsScreen.WINDOW_INSIDE_WIDTH, ordinal = [0])
    fun overrideBackgroundWidth(@Origin original: Int): Int = advancementsScreen.fullscreenBackgroundWidth

    @Hook(_AdvancementTab.drawContents::class, Hook.At.Literal)
    @AtLiteral(int = AdvancementsScreen.WINDOW_INSIDE_HEIGHT, ordinal = [0])
    fun overrideBackgroundHeight(@Origin original: Int): Int = advancementsScreen.fullscreenBackgroundHeight

    @Hook(_AdvancementTab.drawContents::class, Hook.At.Literal)
    @AtLiteral(int = AdvancementsScreen.WINDOW_INSIDE_WIDTH / 2, ordinal = [0])
    fun overrideBackgroundX(@Origin original: Int): Int = advancementsScreen.fullscreenBackgroundWidth / 2

    @Hook(_AdvancementTab.drawContents::class, Hook.At.Literal)
    @AtLiteral(int = AdvancementsScreen.WINDOW_INSIDE_HEIGHT / 2, ordinal = [0])
    fun overrideBackgroundY(@Origin original: Int): Int = advancementsScreen.fullscreenBackgroundHeight / 2

    @Hook(_AdvancementTab.drawContents::class, Hook.At.Literal)
    @AtLiteral(int = AdvancementsScreen.BACKGROUND_TILE_COUNT_X + 1, ordinal = [0])
    fun overrideBackgroundColumns(@Origin original: Int): Int = advancementsScreen.fullscreenBackgroundWidth / 16 + 1

    @Hook(_AdvancementTab.drawContents::class, Hook.At.Literal)
    @AtLiteral(int = AdvancementsScreen.BACKGROUND_TILE_COUNT_Y + 1, ordinal = [0])
    fun overrideBackgroundRows(@Origin original: Int): Int = advancementsScreen.fullscreenBackgroundHeight / 16 + 1
}
