package io.github.diskria.advancements_fullscreen.client.patch

import io.github.diskria.advancements_fullscreen.client._AdvancementTab
import io.github.diskria.advancements_fullscreen.client._AdvancementsScreen
import io.github.diskria.advancements_fullscreen.client._GuiGraphics
import io.github.diskria.advancements_fullscreen.client._HeaderAndFooterLayout
import io.github.diskria.advancements_fullscreen.client.gui.FullscreenRenderer
import io.github.diskria.advancements_fullscreen.generated.*
import io.github.recrafter.lapis.annotations.*
import net.minecraft.client.Minecraft
import net.minecraft.client.gui.layouts.LayoutElement
import net.minecraft.client.gui.screens.advancements.AdvancementTabType
import net.minecraft.client.gui.screens.advancements.AdvancementsScreen

@Patch(_AdvancementsScreen::class, Side.ClientOnly)
abstract class AdvancementsScreenPatch(@Origin val screen: AdvancementsScreen) {

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
        fullscreenHorizontalMargin = horizontalTabWidth - horizontalTabOffset + SCREEN_MARGIN
        fullscreenVerticalMargin = verticalTabHeight - verticalTabOffset + SCREEN_MARGIN

        fullscreenWindowWidth = screen.width - fullscreenHorizontalMargin * 2
        fullscreenWindowHeight = screen.height - fullscreenVerticalMargin * 2
    }

    @Hook(_AdvancementsScreen.init::class, At.Body)
    fun calculateOnInit(@Origin original: Lapis.Body<_AdvancementsScreen.init>) {
        original()
        updateFullscreenUI()
    }

    @Hook(_AdvancementsScreen.updateUI::class, At.Body)
    fun calculateOnReposition(@Origin original: Lapis.Body<_AdvancementsScreen.updateUI>) {
        original()
        screen.tabs.values.forEach { it.centered = false }
        updateFullscreenUI()
    }

    @Hook(_AdvancementsScreen.mouseScrolled::class, At.Call)
    @AtCall(_AdvancementTab.scroll::class, ordinal = [0])
    fun invertScrollWhenShiftDown(@Origin original: Lapis.Call<_AdvancementTab.scroll>) {
        if (Minecraft.getInstance().hasShiftDown()) {
            original(scrollX = original.scrollY, scrollY = 0.toDouble())
        } else {
            original()
        }
    }

    @Hook(_AdvancementsScreen.extractWindow::class, At.Call)
    @AtCall(_GuiGraphics.blit::class, ordinal = [0])
    fun overrideWindowBackgroundRender(@Origin original: Lapis.Call<_GuiGraphics.blit>) {
        FullscreenRenderer.render(
            original.getReceiver(),
            original.renderPipeline,
            original.x, original.y,
            fullscreenWindowWidth, fullscreenWindowHeight,
        )
    }

    @Require(min = 0)
    @Hook(_AdvancementsScreen.init::class, At.Call)
    @AtCall(_HeaderAndFooterLayout.addTitleHeader::class, ordinal = [0])
    fun hideTitleHeader() {
    }

    @Require(min = 0)
    @Hook(_AdvancementsScreen.init::class, At.Call)
    @AtCall(_HeaderAndFooterLayout.addToFooter::class, ordinal = [0])
    fun hideFooter(): LayoutElement? = null

    @Require(min = 0)
    @Hook(_AdvancementsScreen.init::class, At.Call)
    @AtCall(_HeaderAndFooterLayout.visitWidgets::class, ordinal = [0])
    fun hideWidgets() {
    }

    @Hook(_AdvancementsScreen.extractRenderState::class, At.Literal)
    @AtLiteral(int = AdvancementsScreen.WINDOW_WIDTH, ordinal = [0])
    fun overrideWindowX(): Int = fullscreenWindowWidth

    @Hook(_AdvancementsScreen.extractRenderState::class, At.Literal)
    @AtLiteral(int = AdvancementsScreen.WINDOW_HEIGHT, ordinal = [0])
    fun overrideWindowY(): Int = fullscreenWindowHeight

    @Hook(_AdvancementsScreen.mouseClicked::class, At.Literal)
    @AtLiteral(int = AdvancementsScreen.WINDOW_WIDTH, ordinal = [0])
    fun overrideClickableAreaX(): Int = fullscreenWindowWidth

    @Hook(_AdvancementsScreen.mouseClicked::class, At.Literal)
    @AtLiteral(int = AdvancementsScreen.WINDOW_HEIGHT, ordinal = [0])
    fun overrideClickableAreaY(): Int = fullscreenWindowHeight

    @Hook(_AdvancementsScreen.extractInside::class, At.Literal)
    @AtLiteral(int = AdvancementsScreen.WINDOW_INSIDE_WIDTH, ordinal = [0])
    fun overrideEmptyBackgroundWidth(): Int = fullscreenBackgroundWidth

    @Hook(_AdvancementsScreen.extractInside::class, At.Literal)
    @AtLiteral(int = AdvancementsScreen.WINDOW_INSIDE_HEIGHT, ordinal = [0])
    fun overrideEmptyBackgroundHeight(): Int = fullscreenBackgroundHeight

    @Hook(_AdvancementsScreen.extractInside::class, At.Literal)
    @AtLiteral(int = AdvancementsScreen.WINDOW_INSIDE_WIDTH / 2, ordinal = [0])
    fun overrideEmptyLabelsX(): Int = fullscreenBackgroundWidth / 2

    @Hook(_AdvancementsScreen.extractInside::class, At.Literal)
    @AtLiteral(int = AdvancementsScreen.WINDOW_INSIDE_HEIGHT / 2, ordinal = [0])
    fun overrideNoAdvancementsLabelY(): Int = fullscreenBackgroundHeight / 2

    @Hook(_AdvancementsScreen.extractInside::class, At.Literal)
    @AtLiteral(int = AdvancementsScreen.WINDOW_INSIDE_HEIGHT, ordinal = [1])
    fun overrideVerySadLabelY(): Int = fullscreenBackgroundHeight

    companion object {
        private const val SCREEN_MARGIN: Int = 4
    }
}
