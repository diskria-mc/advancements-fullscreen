package io.github.diskria.advancements_fullscreen.client.patches

import io.github.diskria.advancements_fullscreen.client.gui.FullscreenRenderer
import io.github.diskria.advancements_fullscreen.client.schemas.*
import io.github.diskria.advancements_fullscreen.generated.Lapis
import io.github.recrafter.lapis.annotations.*
import net.minecraft.advancements.AdvancementHolder
import net.minecraft.client.Minecraft
import net.minecraft.client.gui.layouts.LayoutElement
import net.minecraft.client.gui.screens.advancements.AdvancementTab
import net.minecraft.client.gui.screens.advancements.AdvancementTabType
import net.minecraft.client.gui.screens.advancements.AdvancementsScreen
import javax.lang.model.element.Modifier

@Patch(_AdvancementsScreen::class, side = Side.ClientOnly)
abstract class AdvancementsScreenPatch(@Origin val screen: AdvancementsScreen) {

    @Extension
    var fullscreenWindowWidth: Int = 0
        private set

    @Extension
    var fullscreenWindowHeight: Int = 0
        private set

    @Extension
    var fullscreenHorizontalMargin: Int = 0
        private set

    @Extension
    var fullscreenVerticalMargin: Int = 0
        private set

    @Extension
    val fullscreenBackgroundWidth: Int
        get() = fullscreenWindowWidth - (_AdvancementsScreen.WINDOW_INSIDE_X() * 2)

    @Extension
    val fullscreenBackgroundHeight: Int
        get() = fullscreenWindowHeight - (_AdvancementsScreen.WINDOW_INSIDE_Y() + _AdvancementsScreen.WINDOW_INSIDE_X())

    @Extension
    val horizontalTabOffset: Int
        get() = AdvancementTabType.LEFT.getX(0) + horizontalTabWidth

    @Extension
    val verticalTabOffset: Int
        get() = AdvancementTabType.ABOVE.getY(0) + verticalTabHeight

    private val horizontalTabWidth: Int
        get() = AdvancementTabType.LEFT.width

    private val verticalTabHeight: Int
        get() = AdvancementTabType.ABOVE.height

    private fun updateFullscreenUI() {
        fullscreenHorizontalMargin = horizontalTabWidth - horizontalTabOffset + SCREEN_MARGIN
        fullscreenVerticalMargin = verticalTabHeight - verticalTabOffset + SCREEN_MARGIN

        fullscreenWindowWidth = screen.width - fullscreenHorizontalMargin * 2
        fullscreenWindowHeight = screen.height - fullscreenVerticalMargin * 2
    }

    @Hook(_AdvancementsScreen.init::class, Ats.Body)
    fun calculateOnInit(@Origin original: Lapis.Body<_AdvancementsScreen.init>) {
        original()
        updateFullscreenUI()
    }

    @Hook(_AdvancementsScreen.repositionElements::class, Ats.Body)
    fun calculateOnReposition(@Origin original: Lapis.Body<_AdvancementsScreen.repositionElements>) {
        original()
        tabs.values.forEach { it.centered(false) }
        updateFullscreenUI()
    }

    @Hook(_AdvancementsScreen.mouseScrolled::class, Ats.Call)
    @AtCall(_AdvancementTab.scroll::class, ordinal = [0])
    fun invertScrollWhenShiftDown(@Origin original: Lapis.Call<_AdvancementTab.scroll>) {
        if (Minecraft.getInstance().hasShiftDown()) {
            original(scrollX = original.scrollY, scrollY = 0.toDouble())
        } else {
            original()
        }
    }

    @Hook(_AdvancementsScreen.extractWindow::class, Ats.Call)
    @AtCall(_GuiGraphics.blit::class, ordinal = [0])
    fun overrideWindowBackgroundRender(@Origin original: Lapis.Call<_GuiGraphics.blit>) {
        FullscreenRenderer.render(
            graphics = original.getReceiver(),
            pipeline = original.renderPipeline,
            x = original.x, y = original.y,
            width = fullscreenWindowWidth, height = fullscreenWindowHeight,
        )
    }

    @Hook(_AdvancementsScreen.init::class, Ats.Call)
    @AtCall(_HeaderAndFooterLayout.addTitleHeader::class, ordinal = [0])
    fun hideTitleHeader() {
    }

    @Hook(_AdvancementsScreen.init::class, Ats.Call)
    @AtCall(_HeaderAndFooterLayout.addToFooter::class, ordinal = [0])
    fun hideFooter(): LayoutElement? = null

    @Hook(_AdvancementsScreen.init::class, Ats.Call)
    @AtCall(_HeaderAndFooterLayout.visitWidgets::class, ordinal = [0])
    fun hideWidgets() {
    }

    @Hook(_AdvancementsScreen.extractRenderState::class, Ats.Literal)
    @AtLiteral(int = AdvancementsScreen.WINDOW_WIDTH, ordinal = [0])
    fun overrideWindowX(): Int = fullscreenWindowWidth

    @Hook(_AdvancementsScreen.extractRenderState::class, Ats.Literal)
    @AtLiteral(int = AdvancementsScreen.WINDOW_HEIGHT, ordinal = [0])
    fun overrideWindowY(): Int = fullscreenWindowHeight

    @Hook(_AdvancementsScreen.mouseClicked::class, Ats.Literal)
    @AtLiteral(int = AdvancementsScreen.WINDOW_WIDTH, ordinal = [0])
    fun overrideClickableAreaX(): Int = fullscreenWindowWidth

    @Hook(_AdvancementsScreen.mouseClicked::class, Ats.Literal)
    @AtLiteral(int = AdvancementsScreen.WINDOW_HEIGHT, ordinal = [0])
    fun overrideClickableAreaY(): Int = fullscreenWindowHeight

    @Hook(_AdvancementsScreen.extractInside::class, Ats.Literal)
    @AtLiteral(int = AdvancementsScreen.WINDOW_INSIDE_WIDTH, ordinal = [0])
    fun overrideEmptyBackgroundWidth(): Int = fullscreenBackgroundWidth

    @Hook(_AdvancementsScreen.extractInside::class, Ats.Literal)
    @AtLiteral(int = AdvancementsScreen.WINDOW_INSIDE_HEIGHT, ordinal = [0])
    fun overrideEmptyBackgroundHeight(): Int = fullscreenBackgroundHeight

    @Hook(_AdvancementsScreen.extractInside::class, Ats.Literal)
    @AtLiteral(int = AdvancementsScreen.WINDOW_INSIDE_WIDTH / 2, ordinal = [0])
    fun overrideEmptyLabelsX(): Int = fullscreenBackgroundWidth / 2

    @Hook(_AdvancementsScreen.extractInside::class, Ats.Literal)
    @AtLiteral(int = AdvancementsScreen.WINDOW_INSIDE_HEIGHT / 2, ordinal = [0])
    fun overrideNoAdvancementsLabelY(): Int = fullscreenBackgroundHeight / 2

    @Hook(_AdvancementsScreen.extractInside::class, Ats.Literal)
    @AtLiteral(int = AdvancementsScreen.WINDOW_INSIDE_HEIGHT, ordinal = [1])
    fun overrideVerySadLabelY(): Int = fullscreenBackgroundHeight

    @KShadow(Modifier.PRIVATE, Modifier.FINAL)
    abstract val tabs: Map<AdvancementHolder, AdvancementTab>

    companion object {
        private const val SCREEN_MARGIN: Int = 4
    }
}
