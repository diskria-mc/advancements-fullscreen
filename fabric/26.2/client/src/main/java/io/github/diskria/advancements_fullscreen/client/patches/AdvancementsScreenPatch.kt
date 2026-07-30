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
    val fullscreenHorizontalMargin: Int
        get() = horizontalTabWidth - horizontalTabOffset + SCREEN_MARGIN

    @Extension
    val fullscreenVerticalMargin: Int
        get() = verticalTabHeight - verticalTabOffset + SCREEN_MARGIN

    @Extension
    val fullscreenWindowWidth: Int
        get() = screen.width - fullscreenHorizontalMargin * 2

    @Extension
    val fullscreenWindowHeight: Int
        get() = screen.height - fullscreenVerticalMargin * 2

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

    @Hook<_AdvancementsScreen.repositionElements>(Ats.Body)
    fun calculateOnReposition(@Origin original: Lapis.Body<_AdvancementsScreen.repositionElements>) {
        original()
        tabs.values.forEach { it.isCentered(false) }
    }

    @Hook<_AdvancementsScreen.mouseScrolled>(Ats.Call)
    @AtCall<_AdvancementTab.scroll>
    fun invertScrollWhenShiftDown(@Origin original: Lapis.Call<_AdvancementTab.scroll>) {
        if (Minecraft.getInstance().hasShiftDown()) {
            original(scrollX = original.scrollY, scrollY = 0.toDouble())
        } else {
            original()
        }
    }

    @Hook<_AdvancementsScreen.extractWindow>(Ats.Call)
    @AtCall<_GuiGraphics.blit>
    fun overrideWindowBackgroundRender(@Origin original: Lapis.Call<_GuiGraphics.blit>) {
        FullscreenRenderer.render(
            graphics = original.getReceiver(),
            pipeline = original.renderPipeline,
            x = original.x, y = original.y,
            width = fullscreenWindowWidth, height = fullscreenWindowHeight,
        )
    }

    @Hook<_AdvancementsScreen.init>(Ats.Call)
    @AtCall<_HeaderAndFooterLayout.addTitleHeader>
    fun hideTitleHeader() {
    }

    @Hook<_AdvancementsScreen.init>(Ats.Call)
    @AtCall<_HeaderAndFooterLayout.addToFooter>
    fun hideFooter(): LayoutElement? = null

    @Hook<_AdvancementsScreen.init>(Ats.Call)
    @AtCall<_HeaderAndFooterLayout.visitWidgets>
    fun hideWidgets() {
    }

    @Hook<_AdvancementsScreen.repositionElements>(Ats.Literal)
    @AtLiteral(int = AdvancementsScreen.WINDOW_WIDTH)
    fun overrideWindowX(): Int = fullscreenWindowWidth

    @Hook<_AdvancementsScreen.repositionElements>(Ats.Literal)
    @AtLiteral(int = AdvancementsScreen.WINDOW_HEIGHT)
    fun overrideWindowY(): Int = fullscreenWindowHeight

    @Hook<_AdvancementsScreen.mouseClicked>(Ats.Literal)
    @AtLiteral(int = AdvancementsScreen.WINDOW_WIDTH)
    fun overrideClickableAreaX(): Int = fullscreenWindowWidth

    @Hook<_AdvancementsScreen.mouseClicked>(Ats.Literal)
    @AtLiteral(int = AdvancementsScreen.WINDOW_HEIGHT)
    fun overrideClickableAreaY(): Int = fullscreenWindowHeight

    @Hook<_AdvancementsScreen.extractInside>(Ats.Literal)
    @AtLiteral(int = AdvancementsScreen.WINDOW_INSIDE_WIDTH)
    fun overrideEmptyBackgroundWidth(): Int = fullscreenBackgroundWidth

    @Hook<_AdvancementsScreen.extractInside>(Ats.Literal)
    @AtLiteral(int = AdvancementsScreen.WINDOW_INSIDE_HEIGHT)
    fun overrideEmptyBackgroundHeight(): Int = fullscreenBackgroundHeight

    @Hook<_AdvancementsScreen.extractInside>(Ats.Literal)
    @AtLiteral(int = AdvancementsScreen.WINDOW_INSIDE_WIDTH / 2)
    fun overrideEmptyLabelsX(): Int = fullscreenBackgroundWidth / 2

    @Hook<_AdvancementsScreen.extractInside>(Ats.Literal)
    @AtLiteral(int = AdvancementsScreen.WINDOW_INSIDE_HEIGHT / 2)
    fun overrideNoAdvancementsLabelY(): Int = fullscreenBackgroundHeight / 2

    @KShadow(Modifier.PRIVATE, Modifier.FINAL)
    abstract val tabs: Map<AdvancementHolder, AdvancementTab>

    companion object {
        private const val SCREEN_MARGIN: Int = 4
    }
}
