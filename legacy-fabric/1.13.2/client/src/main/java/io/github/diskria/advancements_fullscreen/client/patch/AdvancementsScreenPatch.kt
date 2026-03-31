package io.github.diskria.advancements_fullscreen.client.patch

import io.github.diskria.advancements_fullscreen.client._AdvancementsScreen
import io.github.diskria.advancements_fullscreen.client._DrawableHelper
import io.github.diskria.advancements_fullscreen.client.gui.FullscreenRenderer
import io.github.diskria.advancements_fullscreen.generated.*
import io.github.recrafter.lapis.annotations.*
import net.minecraft.class_3269
import net.minecraft.client.gui.screen.AdvancementsScreen

@Patch(_AdvancementsScreen::class, Side.ClientOnly)
abstract class AdvancementsScreenPatch : Lapis.Patch<AdvancementsScreen>() {

    var fullscreenWindowWidth: Int = 0
    var fullscreenWindowHeight: Int = 0
    var fullscreenHorizontalMargin: Int = 0
    var fullscreenVerticalMargin: Int = 0

    val fullscreenBackgroundWidth: Int
        get() = fullscreenWindowWidth - (_AdvancementsScreen.WINDOW_INSIDE_X * 2)

    val fullscreenBackgroundHeight: Int
        get() = fullscreenWindowHeight - (_AdvancementsScreen.WINDOW_INSIDE_Y + _AdvancementsScreen.WINDOW_INSIDE_X)

    val horizontalTabWidth: Int
        get() = class_3269.LEFT.field_15979

    val verticalTabHeight: Int
        get() = class_3269.ABOVE.field_15980

    val horizontalTabOffset: Int
        get() = class_3269.LEFT.method_14520(0) + horizontalTabWidth

    val verticalTabOffset: Int
        get() = class_3269.ABOVE.method_14524(0) + verticalTabHeight

    private fun updateFullscreenUI() {
        fullscreenHorizontalMargin = horizontalTabWidth - horizontalTabOffset + SCREEN_MARGIN
        fullscreenVerticalMargin = verticalTabHeight - verticalTabOffset + SCREEN_MARGIN

        fullscreenWindowWidth = instance.width - fullscreenHorizontalMargin * 2
        fullscreenWindowHeight = instance.height - fullscreenVerticalMargin * 2
    }

    @Hook(_AdvancementsScreen.init::class, Hook.At.Body)
    fun calculateOnInit(@Origin original: Lapis.Call<_AdvancementsScreen.init>) {
        original()
        updateFullscreenUI()
    }

    @Hook(_AdvancementsScreen.renderWindow::class, Hook.At.Call)
    @AtCall(_AdvancementsScreen.drawTexture::class, ordinal = [0])
    fun overrideWindowBackgroundRender(@Origin original: Lapis.Call<_DrawableHelper.drawTexture>) {
        FullscreenRenderer.render(
            original.x, original.y,
            fullscreenWindowWidth, fullscreenWindowHeight,
        )
    }

    @Hook(_AdvancementsScreen.render::class, Hook.At.Literal)
    @AtLiteral(int = _AdvancementsScreen.WINDOW_WIDTH, ordinal = [0])
    fun overrideWindowX(): Int = fullscreenWindowWidth

    @Hook(_AdvancementsScreen.render::class, Hook.At.Literal)
    @AtLiteral(int = _AdvancementsScreen.WINDOW_HEIGHT, ordinal = [0])
    fun overrideWindowY(): Int = fullscreenWindowHeight

    @Hook(_AdvancementsScreen.mouseClicked::class, Hook.At.Literal)
    @AtLiteral(int = _AdvancementsScreen.WINDOW_WIDTH, ordinal = [0])
    fun overrideClickableAreaX(): Int = fullscreenWindowWidth

    @Hook(_AdvancementsScreen.mouseClicked::class, Hook.At.Literal)
    @AtLiteral(int = _AdvancementsScreen.WINDOW_HEIGHT, ordinal = [0])
    fun overrideClickableAreaY(): Int = fullscreenWindowHeight

    @Hook(_AdvancementsScreen.renderInside::class, Hook.At.Literal)
    @AtLiteral(int = _AdvancementsScreen.WINDOW_INSIDE_WIDTH, ordinal = [0])
    fun overrideEmptyBackgroundWidth(): Int = fullscreenBackgroundWidth

    @Hook(_AdvancementsScreen.renderInside::class, Hook.At.Literal)
    @AtLiteral(int = _AdvancementsScreen.WINDOW_INSIDE_HEIGHT, ordinal = [0])
    fun overrideEmptyBackgroundHeight(): Int = fullscreenBackgroundHeight

    @Hook(_AdvancementsScreen.renderInside::class, Hook.At.Literal)
    @AtLiteral(int = _AdvancementsScreen.WINDOW_INSIDE_WIDTH / 2, ordinal = [0, 1])
    fun overrideEmptyLabelsX(): Int = fullscreenBackgroundWidth / 2

    @Hook(_AdvancementsScreen.renderInside::class, Hook.At.Literal)
    @AtLiteral(int = _AdvancementsScreen.WINDOW_INSIDE_HEIGHT / 2, ordinal = [0])
    fun overrideNoAdvancementsLabelY(): Int = fullscreenBackgroundHeight / 2

    @Hook(_AdvancementsScreen.renderInside::class, Hook.At.Literal)
    @AtLiteral(int = _AdvancementsScreen.WINDOW_INSIDE_HEIGHT, ordinal = [1])
    fun overrideVerySadLabelY(): Int = fullscreenBackgroundHeight

    companion object {
        private const val SCREEN_MARGIN: Int = 4
    }
}
