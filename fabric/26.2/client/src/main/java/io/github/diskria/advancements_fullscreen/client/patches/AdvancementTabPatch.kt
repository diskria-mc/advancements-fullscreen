package io.github.diskria.advancements_fullscreen.client.patches

import io.github.diskria.advancements_fullscreen.client.schemas._AdvancementTab
import io.github.recrafter.lapis.annotations.*
import net.minecraft.client.gui.screens.advancements.AdvancementsScreen
import javax.lang.model.element.Modifier

@Patch(_AdvancementTab::class, side = Side.ClientOnly)
abstract class AdvancementTabPatch {

    private val advancementsScreen: AdvancementsScreen get() = getScreen()

    @Hook<_AdvancementTab.scroll>(Ats.Literal)
    @AtLiteral(int = AdvancementsScreen.WINDOW_INSIDE_WIDTH)
    fun overrideScrollXLimit(): Int = advancementsScreen.fullscreenBackgroundWidth

    @Hook<_AdvancementTab.scroll>(Ats.Literal)
    @AtLiteral(int = AdvancementsScreen.WINDOW_INSIDE_HEIGHT)
    fun overrideScrollYLimit(): Int = advancementsScreen.fullscreenBackgroundHeight

    @Hook<_AdvancementTab.tick>(Ats.Literal)
    @AtLiteral(int = AdvancementsScreen.WINDOW_INSIDE_WIDTH)
    fun overrideTickXLimit(): Int = advancementsScreen.fullscreenBackgroundWidth

    @Hook<_AdvancementTab.tick>(Ats.Literal)
    @AtLiteral(int = AdvancementsScreen.WINDOW_INSIDE_HEIGHT)
    fun overrideTickYLimit(): Int = advancementsScreen.fullscreenBackgroundHeight

    @Hook<_AdvancementTab.tick>(Ats.Literal)
    @AtLiteral(float = 0.06f)
    fun overrideFadeInSpeed(): Float {
        val target = 0.3f
        val distance = target - fade
        return (distance * 0.25f).coerceAtLeast(0.01f)
    }

    @Hook<_AdvancementTab.tick>(Ats.Literal)
    @AtLiteral(float = 0.12f)
    fun overrideFadeOutSpeed(): Float {
        val target = 0.0f
        val distance = fade - target
        return (distance * 0.1f).coerceAtLeast(0.002f)
    }

    @Hook<_AdvancementTab.canScrollHorizontally>(Ats.Literal)
    @AtLiteral(int = AdvancementsScreen.WINDOW_INSIDE_WIDTH)
    fun overrideScrollXLimitCheck(): Int = advancementsScreen.fullscreenBackgroundWidth

    @Hook<_AdvancementTab.canScrollVertically>(Ats.Literal)
    @AtLiteral(int = AdvancementsScreen.WINDOW_INSIDE_HEIGHT)
    fun overrideScrollYLimitCheck(): Int = advancementsScreen.fullscreenBackgroundHeight

    @Hook<_AdvancementTab.extractTooltips>(Ats.Literal)
    @AtLiteral(int = AdvancementsScreen.WINDOW_INSIDE_WIDTH)
    fun overrideHoverOverlayWidth(): Int = advancementsScreen.fullscreenBackgroundWidth

    @Hook<_AdvancementTab.extractTooltips>(Ats.Literal)
    @AtLiteral(int = AdvancementsScreen.WINDOW_INSIDE_HEIGHT)
    fun overrideHoverOverlayHeight(): Int = advancementsScreen.fullscreenBackgroundHeight

    @Hook<_AdvancementTab.extractContents>(Ats.Literal)
    @AtLiteral(int = AdvancementsScreen.WINDOW_INSIDE_WIDTH)
    fun overrideBackgroundWidth(): Int = advancementsScreen.fullscreenBackgroundWidth

    @Hook<_AdvancementTab.extractContents>(Ats.Literal)
    @AtLiteral(int = AdvancementsScreen.WINDOW_INSIDE_HEIGHT)
    fun overrideBackgroundHeight(): Int = advancementsScreen.fullscreenBackgroundHeight

    @Hook<_AdvancementTab.extractContents>(Ats.Literal)
    @AtLiteral(int = AdvancementsScreen.WINDOW_INSIDE_WIDTH / 2)
    fun overrideBackgroundX(): Int = advancementsScreen.fullscreenBackgroundWidth / 2

    @Hook<_AdvancementTab.extractContents>(Ats.Literal)
    @AtLiteral(int = AdvancementsScreen.WINDOW_INSIDE_HEIGHT / 2)
    fun overrideBackgroundY(): Int = advancementsScreen.fullscreenBackgroundHeight / 2

    @Hook<_AdvancementTab.extractContents>(Ats.Literal)
    @AtLiteral(int = AdvancementsScreen.BACKGROUND_TILE_COUNT_X + 1)
    fun overrideBackgroundColumns(): Int = advancementsScreen.fullscreenBackgroundWidth / 16 + 1

    @Hook<_AdvancementTab.extractContents>(Ats.Literal)
    @AtLiteral(int = AdvancementsScreen.BACKGROUND_TILE_COUNT_Y + 1)
    fun overrideBackgroundRows(): Int = advancementsScreen.fullscreenBackgroundHeight / 16 + 1

    @KShadow(Modifier.PRIVATE)
    abstract val fade: Float

    @KShadow(Modifier.PUBLIC)
    abstract fun getScreen(): AdvancementsScreen
}
