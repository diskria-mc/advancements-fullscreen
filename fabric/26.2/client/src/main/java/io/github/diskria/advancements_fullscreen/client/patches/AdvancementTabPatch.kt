package io.github.diskria.advancements_fullscreen.client.patches

import io.github.diskria.advancements_fullscreen.client.schemas._AdvancementTab
import io.github.recrafter.lapis.annotations.*
import net.minecraft.client.gui.screens.advancements.AdvancementTab
import net.minecraft.client.gui.screens.advancements.AdvancementsScreen
import javax.lang.model.element.Modifier

@KMixin(AdvancementTab::class, side = Side.ClientOnly)
abstract class AdvancementTabPatch {

    private val advancementsScreen: AdvancementsScreen get() = getScreen()

    @Hook(_AdvancementTab.scroll::class, Ats.Literal)
    @AtLiteral(int = AdvancementsScreen.WINDOW_INSIDE_WIDTH, ordinal = [0])
    fun overrideScrollXLimit(): Int = advancementsScreen.fullscreenBackgroundWidth

    @Hook(_AdvancementTab.scroll::class, Ats.Literal)
    @AtLiteral(int = AdvancementsScreen.WINDOW_INSIDE_HEIGHT, ordinal = [0])
    fun overrideScrollYLimit(): Int = advancementsScreen.fullscreenBackgroundHeight

    @Hook(_AdvancementTab.tick::class, Ats.Literal)
    @AtLiteral(int = AdvancementsScreen.WINDOW_INSIDE_WIDTH, ordinal = [0])
    fun overrideTickXLimit(): Int = advancementsScreen.fullscreenBackgroundWidth

    @Hook(_AdvancementTab.tick::class, Ats.Literal)
    @AtLiteral(int = AdvancementsScreen.WINDOW_INSIDE_HEIGHT, ordinal = [0])
    fun overrideTickYLimit(): Int = advancementsScreen.fullscreenBackgroundHeight

    @KShadow(Modifier.PRIVATE)
    abstract val fade: Float

    @Hook(_AdvancementTab.tick::class, Ats.Literal)
    @AtLiteral(float = 0.06f, ordinal = [0])
    fun overrideFadeInSpeed(): Float {
        val target = 0.3f
        val distance = target - fade
        return (distance * 0.25f).coerceAtLeast(0.01f)
    }

    @Hook(_AdvancementTab.tick::class, Ats.Literal)
    @AtLiteral(float = 0.12f, ordinal = [0])
    fun overrideFadeOutSpeed(): Float {
        val target = 0.0f
        val distance = fade - target
        return (distance * 0.1f).coerceAtLeast(0.002f)
    }

    @Hook(_AdvancementTab.canScrollHorizontally::class, Ats.Literal)
    @AtLiteral(int = AdvancementsScreen.WINDOW_INSIDE_WIDTH, ordinal = [0])
    fun overrideScrollXLimitCheck(): Int = advancementsScreen.fullscreenBackgroundWidth

    @Hook(_AdvancementTab.canScrollVertically::class, Ats.Literal)
    @AtLiteral(int = AdvancementsScreen.WINDOW_INSIDE_HEIGHT, ordinal = [0])
    fun overrideScrollYLimitCheck(): Int = advancementsScreen.fullscreenBackgroundHeight

    @Hook(_AdvancementTab.extractTooltips::class, Ats.Literal)
    @AtLiteral(int = AdvancementsScreen.WINDOW_INSIDE_WIDTH, ordinal = [0])
    fun overrideHoverOverlayWidth(): Int = advancementsScreen.fullscreenBackgroundWidth

    @Hook(_AdvancementTab.extractTooltips::class, Ats.Literal)
    @AtLiteral(int = AdvancementsScreen.WINDOW_INSIDE_HEIGHT, ordinal = [0])
    fun overrideHoverOverlayHeight(): Int = advancementsScreen.fullscreenBackgroundHeight

    @Hook(_AdvancementTab.extractContents::class, Ats.Literal)
    @AtLiteral(int = AdvancementsScreen.WINDOW_INSIDE_WIDTH, ordinal = [0])
    fun overrideBackgroundWidth(): Int = advancementsScreen.fullscreenBackgroundWidth

    @Hook(_AdvancementTab.extractContents::class, Ats.Literal)
    @AtLiteral(int = AdvancementsScreen.WINDOW_INSIDE_HEIGHT, ordinal = [0])
    fun overrideBackgroundHeight(): Int = advancementsScreen.fullscreenBackgroundHeight

    @Hook(_AdvancementTab.extractContents::class, Ats.Literal)
    @AtLiteral(int = AdvancementsScreen.WINDOW_INSIDE_WIDTH / 2, ordinal = [0])
    fun overrideBackgroundX(): Int = advancementsScreen.fullscreenBackgroundWidth / 2

    @Hook(_AdvancementTab.extractContents::class, Ats.Literal)
    @AtLiteral(int = AdvancementsScreen.WINDOW_INSIDE_HEIGHT / 2, ordinal = [0])
    fun overrideBackgroundY(): Int = advancementsScreen.fullscreenBackgroundHeight / 2

    @Hook(_AdvancementTab.extractContents::class, Ats.Literal)
    @AtLiteral(int = AdvancementsScreen.BACKGROUND_TILE_COUNT_X + 1, ordinal = [0])
    fun overrideBackgroundColumns(): Int = advancementsScreen.fullscreenBackgroundWidth / 16 + 1

    @Hook(_AdvancementTab.extractContents::class, Ats.Literal)
    @AtLiteral(int = AdvancementsScreen.BACKGROUND_TILE_COUNT_Y + 1, ordinal = [0])
    fun overrideBackgroundRows(): Int = advancementsScreen.fullscreenBackgroundHeight / 16 + 1

    @KShadow(Modifier.PUBLIC)
    abstract fun getScreen(): AdvancementsScreen
}
