package io.github.diskria.advancements_fullscreen.client.patch

import io.github.diskria.advancements_fullscreen.client._AdvancementTabType
import io.github.diskria.advancements_fullscreen.client._GuiGraphics
import io.github.diskria.advancements_fullscreen.generated.*
import io.github.recrafter.lapis.annotations.*
import net.minecraft.client.Minecraft
import net.minecraft.client.gui.screens.advancements.AdvancementTabType
import net.minecraft.client.gui.screens.advancements.AdvancementsScreen

@Patch(_AdvancementTabType::class, Side.ClientOnly)
abstract class AdvancementTabTypePatch : Lapis.Patch<AdvancementTabType>() {

    private val advancementsScreen: AdvancementsScreen? get() = Minecraft.getInstance().screen as? AdvancementsScreen

    @Hook(_AdvancementTabType.getX::class, Hook.At.Literal)
    @AtLiteral(int = AdvancementsScreen.WINDOW_WIDTH - 4, ordinal = [0])
    fun overrideRightX(@Origin original: Int): Int =
        advancementsScreen?.let { it.fullscreenWindowWidth - it.horizontalTabOffset } ?: original

    @Hook(_AdvancementTabType.getY::class, Hook.At.Literal)
    @AtLiteral(int = AdvancementsScreen.WINDOW_HEIGHT - 4, ordinal = [0])
    fun overrideBelowY(@Origin original: Int): Int =
        advancementsScreen?.let { it.fullscreenWindowHeight - it.verticalTabOffset } ?: original

    @Hook(_AdvancementTabType.draw::class, Hook.At.Call)
    @AtCall(_GuiGraphics.blitSprite::class, ordinal = [0])
    fun fixSpriteAlignment(
        @Origin original: Lapis.Call<_GuiGraphics.blitSprite>,
        @Local(0) sprites: AdvancementTabType.Sprites,
    ) {
        original(sprite = advancementsScreen?.let {
            val isVertical = instance == AdvancementTabType.ABOVE || instance == AdvancementTabType.BELOW

            val tabPosition = if (isVertical) original.x else original.y
            val tabSize = if (isVertical) original.width else original.height

            val screenMargin = if (isVertical) it.fullscreenHorizontalMargin else it.fullscreenVerticalMargin
            val screenSize = if (isVertical) it.width else it.height

            when {
                tabPosition == screenMargin -> sprites.first()
                tabPosition + tabSize == screenSize - screenMargin -> sprites.last()
                else -> sprites.middle()
            }
        } ?: original.sprite)
    }
}
