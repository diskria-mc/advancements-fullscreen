package io.github.diskria.advancements_fullscreen.client.patch

import io.github.diskria.advancements_fullscreen.client.AdvancementTab_
import io.github.diskria.advancements_fullscreen.generated.Lapis
import io.github.diskria.advancements_fullscreen.generated.fullscreenBackgroundHeight
import io.github.diskria.advancements_fullscreen.generated.fullscreenBackgroundWidth
import io.github.recrafter.lapis.Hook
import io.github.recrafter.lapis.Side
import io.github.recrafter.lapis.annotations.LaHook
import io.github.recrafter.lapis.annotations.LaLiteral
import io.github.recrafter.lapis.annotations.LaOrdinal
import io.github.recrafter.lapis.annotations.LaPatch
import net.minecraft.client.gui.screens.advancements.AdvancementTab
import net.minecraft.client.gui.screens.advancements.AdvancementsScreen

@LaPatch(AdvancementTab::class, Side.ClientOnly)
abstract class AdvancementTabPatch : Lapis.Patch<AdvancementTab>() {

    private val advancementsScreen: AdvancementsScreen
        get() = instance.screen

    @LaHook(
        AdvancementTab_.scroll::class,
        Hook.Literal
    )
    fun overrideScrollXLimit(
        @LaLiteral(int = AdvancementsScreen.WINDOW_INSIDE_WIDTH) original: Int,
        @LaOrdinal(LaOrdinal.FIRST) ordinal: Int,
    ): Int = advancementsScreen.fullscreenBackgroundWidth

    @LaHook(
        AdvancementTab_.scroll::class,
        Hook.Literal
    )
    fun overrideScrollYLimit(
        @LaLiteral(int = AdvancementsScreen.WINDOW_INSIDE_HEIGHT) original: Int,
        @LaOrdinal(LaOrdinal.FIRST) ordinal: Int,
    ): Int = advancementsScreen.fullscreenBackgroundHeight

    @LaHook(
        AdvancementTab_.canScrollHorizontally::class,
        Hook.Literal
    )
    fun overrideScrollXLimitCheck(
        @LaLiteral(int = AdvancementsScreen.WINDOW_INSIDE_WIDTH) original: Int,
        @LaOrdinal(LaOrdinal.FIRST) ordinal: Int,
    ): Int = advancementsScreen.fullscreenBackgroundWidth

    @LaHook(
        AdvancementTab_.canScrollVertically::class,
        Hook.Literal
    )
    fun overrideScrollYLimitCheck(
        @LaLiteral(int = AdvancementsScreen.WINDOW_INSIDE_HEIGHT) original: Int,
        @LaOrdinal(LaOrdinal.FIRST) ordinal: Int,
    ): Int = advancementsScreen.fullscreenBackgroundHeight

    @LaHook(
        AdvancementTab_.drawTooltips::class,
        Hook.Literal
    )
    fun overrideHoverOverlayWidth(
        @LaLiteral(int = AdvancementsScreen.WINDOW_INSIDE_WIDTH) original: Int,
        @LaOrdinal(0, 1) ordinal: Int,
    ): Int = instance.screen.fullscreenBackgroundWidth

    @LaHook(
        AdvancementTab_.drawTooltips::class,
        Hook.Literal
    )
    fun overrideHoverOverlayHeight(
        @LaLiteral(int = AdvancementsScreen.WINDOW_INSIDE_HEIGHT) original: Int,
        @LaOrdinal(0, 1) ordinal: Int,
    ): Int = advancementsScreen.fullscreenBackgroundHeight

    @LaHook(
        AdvancementTab_.drawContents::class,
        Hook.Literal
    )
    fun overrideBackgroundWidth(
        @LaLiteral(int = AdvancementsScreen.WINDOW_INSIDE_WIDTH) original: Int,
        @LaOrdinal(LaOrdinal.FIRST) ordinal: Int,
    ): Int = advancementsScreen.fullscreenBackgroundWidth

    @LaHook(
        AdvancementTab_.drawContents::class,
        Hook.Literal
    )
    fun overrideBackgroundHeight(
        @LaLiteral(int = AdvancementsScreen.WINDOW_INSIDE_HEIGHT) original: Int,
        @LaOrdinal(LaOrdinal.FIRST) ordinal: Int,
    ): Int = advancementsScreen.fullscreenBackgroundHeight

    @LaHook(
        AdvancementTab_.drawContents::class,
        Hook.Literal
    )
    fun overrideBackgroundX(
        @LaLiteral(int = AdvancementsScreen.WINDOW_INSIDE_WIDTH / 2) original: Int,
        @LaOrdinal(LaOrdinal.FIRST) ordinal: Int,
    ): Int = advancementsScreen.fullscreenBackgroundWidth / 2

    @LaHook(
        AdvancementTab_.drawContents::class,
        Hook.Literal
    )
    fun overrideBackgroundY(
        @LaLiteral(int = AdvancementsScreen.WINDOW_INSIDE_HEIGHT / 2) original: Int,
        @LaOrdinal(LaOrdinal.FIRST) ordinal: Int,
    ): Int = advancementsScreen.fullscreenBackgroundHeight / 2

    @LaHook(
        AdvancementTab_.drawContents::class,
        Hook.Literal
    )
    fun overrideBackgroundGridColumns(
        @LaLiteral(int = AdvancementsScreen.BACKGROUND_TILE_COUNT_X + 1) original: Int,
        @LaOrdinal(LaOrdinal.FIRST) ordinal: Int,
    ): Int = advancementsScreen.fullscreenBackgroundWidth / 16 + 1

    @LaHook(
        AdvancementTab_.drawContents::class,
        Hook.Literal
    )
    fun overrideBackgroundGridRows(
        @LaLiteral(int = AdvancementsScreen.BACKGROUND_TILE_COUNT_Y + 1) original: Int,
        @LaOrdinal(LaOrdinal.FIRST) ordinal: Int,
    ): Int = advancementsScreen.fullscreenBackgroundHeight / 16 + 1
}
