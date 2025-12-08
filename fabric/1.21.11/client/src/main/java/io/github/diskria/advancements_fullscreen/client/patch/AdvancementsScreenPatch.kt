package io.github.diskria.advancements_fullscreen.client.patch

import io.github.diskria.advancements_fullscreen.client.AdvancementTab_
import io.github.diskria.advancements_fullscreen.client.AdvancementsScreen_
import io.github.diskria.advancements_fullscreen.client.GuiGraphics_
import io.github.diskria.advancements_fullscreen.client.HeaderAndFooterLayout_
import io.github.diskria.advancements_fullscreen.client.gui.FullscreenRenderer
import io.github.diskria.advancements_fullscreen.generated.*
import io.github.recrafter.lapis.Hook
import io.github.recrafter.lapis.Side
import io.github.recrafter.lapis.annotations.*
import net.minecraft.client.Minecraft
import net.minecraft.client.gui.layouts.LayoutElement
import net.minecraft.client.gui.screens.advancements.AdvancementTabType
import net.minecraft.client.gui.screens.advancements.AdvancementsScreen

@LaPatch(AdvancementsScreen::class, Side.ClientOnly)
abstract class AdvancementsScreenPatch : Lapis.Patch<AdvancementsScreen>() {

    var fullscreenWindowWidth: Int = 0
    var fullscreenWindowHeight: Int = 0
    var fullscreenHorizontalMargin: Int = 0
    var fullscreenVerticalMargin: Int = 0

    val fullscreenBackgroundWidth: Int
        get() = fullscreenWindowWidth - (AdvancementsScreen.WINDOW_INSIDE_X * 2)

    val fullscreenBackgroundHeight: Int
        get() = fullscreenWindowHeight - (AdvancementsScreen.WINDOW_INSIDE_Y + AdvancementsScreen.WINDOW_INSIDE_X)

    val horizontalTabWidth: Int
        get() = AdvancementTabType.LEFT.width

    val verticalTabHeight: Int
        get() = AdvancementTabType.ABOVE.height

    val horizontalTabOffset: Int
        get() = AdvancementTabType.LEFT.getX(0) + horizontalTabWidth

    val verticalTabOffset: Int
        get() = AdvancementTabType.ABOVE.getY(0) + verticalTabHeight

    private fun updateFullscreenUI() {
        fullscreenWindowWidth = instance.width - fullscreenHorizontalMargin * 2
        fullscreenWindowHeight = instance.height - fullscreenVerticalMargin * 2

        fullscreenHorizontalMargin = horizontalTabWidth - horizontalTabOffset + SCREEN_MARGIN
        fullscreenVerticalMargin = verticalTabHeight - verticalTabOffset + SCREEN_MARGIN
    }

    @LaHook(
        AdvancementsScreen_.mouseScrolled::class,
        Hook.Call
    )
    fun invertScrollWhenShiftDown(
        @LaTarget original: Lapis.Callable<AdvancementTab_.scroll>,
        @LaOrdinal(LaOrdinal.FIRST) ordinal: Int,
    ) {
        if (Minecraft.getInstance().hasShiftDown()) {
            original.invoke(scrollX = original.scrollY, scrollY = 0.toDouble())
        } else {
            original.invoke()
        }
    }

    @LaHook(
        AdvancementsScreen_.renderWindow::class,
        Hook.Call
    )
    fun overrideWindowBackgroundRender(
        @LaTarget original: Lapis.Callable<GuiGraphics_.blit>,
        @LaOrdinal(LaOrdinal.FIRST) ordinal: Int,
    ) {
        FullscreenRenderer.render(
            original.getReceiver(),
            original.renderPipeline,
            fullscreenHorizontalMargin, fullscreenVerticalMargin,
            fullscreenWindowWidth, fullscreenWindowHeight,
        )
    }

    @LaHook(
        AdvancementsScreen_.init::class,
        Hook.Call
    )
    fun hideFooter(
        @LaTarget original: Lapis.Callable<HeaderAndFooterLayout_.addToFooter>,
        @LaOrdinal(LaOrdinal.FIRST) ordinal: Int,
    ): LayoutElement? = null

    @LaHook(
        AdvancementsScreen_.init::class,
        Hook.Call
    )
    fun hideTitleHeader(
        @LaTarget original: Lapis.Callable<HeaderAndFooterLayout_.addTitleHeader>,
        @LaOrdinal(LaOrdinal.FIRST) ordinal: Int,
    ) {
    }

    @LaHook(
        AdvancementsScreen_.init::class,
        Hook.Call
    )
    fun hideWidgets(
        @LaTarget original: Lapis.Callable<HeaderAndFooterLayout_.visitWidgets>,
        @LaOrdinal(LaOrdinal.FIRST) ordinal: Int,
    ) {
    }

    @LaHook(
        AdvancementsScreen_.init::class,
        Hook.Body
    )
    fun calculateOnInit(
        @LaTarget original: Lapis.Callable<AdvancementsScreen_.init>,
    ) {
        original.invoke()
        updateFullscreenUI()
    }

    @LaHook(
        AdvancementsScreen_.repositionElements::class,
        Hook.Body
    )
    fun calculateOnReposition(
        @LaTarget original: Lapis.Callable<AdvancementsScreen_.repositionElements>,
    ) {
        original.invoke()
        instance.tabs.values.forEach { it.centered = false }
        updateFullscreenUI()
    }

    @LaHook(
        AdvancementsScreen_.render::class,
        Hook.Literal
    )
    fun overrideWindowX(
        @LaLiteral(int = AdvancementsScreen.WINDOW_WIDTH) original: Int,
        @LaOrdinal(LaOrdinal.FIRST) ordinal: Int,
    ): Int = fullscreenWindowWidth

    @LaHook(
        AdvancementsScreen_.render::class,
        Hook.Literal
    )
    fun overrideWindowY(
        @LaLiteral(int = AdvancementsScreen.WINDOW_HEIGHT) original: Int,
        @LaOrdinal(LaOrdinal.FIRST) ordinal: Int,
    ): Int = fullscreenWindowHeight

    @LaHook(
        AdvancementsScreen_.mouseClicked::class,
        Hook.Literal
    )
    fun overrideClickableAreaX(
        @LaLiteral(int = AdvancementsScreen.WINDOW_WIDTH) original: Int,
        @LaOrdinal(LaOrdinal.FIRST) ordinal: Int,
    ): Int = fullscreenWindowWidth

    @LaHook(
        AdvancementsScreen_.mouseClicked::class,
        Hook.Literal
    )
    fun overrideClickableAreaY(
        @LaLiteral(int = AdvancementsScreen.WINDOW_HEIGHT) original: Int,
        @LaOrdinal(LaOrdinal.FIRST) ordinal: Int,
    ): Int = fullscreenWindowHeight

    @LaHook(
        AdvancementsScreen_.renderInside::class,
        Hook.Literal
    )
    fun overrideEmptyBackgroundWidth(
        @LaLiteral(int = AdvancementsScreen.WINDOW_INSIDE_WIDTH) original: Int,
        @LaOrdinal(LaOrdinal.FIRST) ordinal: Int,
    ): Int = fullscreenBackgroundWidth

    @LaHook(
        AdvancementsScreen_.renderInside::class,
        Hook.Literal
    )
    fun overrideEmptyBackgroundHeight(
        @LaLiteral(int = AdvancementsScreen.WINDOW_INSIDE_HEIGHT) original: Int,
        @LaOrdinal(LaOrdinal.FIRST) ordinal: Int,
    ): Int = fullscreenBackgroundHeight

    @LaHook(
        AdvancementsScreen_.renderInside::class,
        Hook.Literal
    )
    fun overrideEmptyLabelsX(
        @LaLiteral(int = AdvancementsScreen.WINDOW_INSIDE_WIDTH / 2) original: Int,
        @LaOrdinal(LaOrdinal.FIRST) ordinal: Int,
    ): Int = fullscreenBackgroundWidth / 2

    @LaHook(
        AdvancementsScreen_.renderInside::class,
        Hook.Literal
    )
    fun overrideNoAdvancementsLabelY(
        @LaLiteral(int = AdvancementsScreen.WINDOW_INSIDE_HEIGHT / 2) original: Int,
        @LaOrdinal(LaOrdinal.FIRST) ordinal: Int,
    ): Int = fullscreenBackgroundHeight / 2

    @LaHook(
        AdvancementsScreen_.renderInside::class,
        Hook.Literal
    )
    fun overrideVerySadLabelY(
        @LaLiteral(int = AdvancementsScreen.WINDOW_INSIDE_HEIGHT) original: Int,
        @LaOrdinal(LaOrdinal.FIRST) ordinal: Int,
    ): Int = fullscreenBackgroundHeight

    companion object {
        private const val SCREEN_MARGIN: Int = 4
    }
}
