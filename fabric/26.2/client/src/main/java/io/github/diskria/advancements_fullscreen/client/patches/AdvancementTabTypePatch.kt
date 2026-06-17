package io.github.diskria.advancements_fullscreen.client.patches

import io.github.diskria.advancements_fullscreen.client.schemas.*
import io.github.diskria.advancements_fullscreen.generated.Lapis
import io.github.recrafter.lapis.annotations.*
import net.minecraft.client.Minecraft
import net.minecraft.client.gui.screens.advancements.AdvancementTabType
import net.minecraft.client.gui.screens.advancements.AdvancementsScreen

@KMixin(AdvancementTabType::class, side = Side.ClientOnly)
abstract class AdvancementTabTypePatch(@Origin val type: AdvancementTabType) {

    private val advancementsScreen: AdvancementsScreen?
        get() = Minecraft.getInstance().gui.screen() as? AdvancementsScreen

    @Hook(_AdvancementTabType.getX::class, Ats.Literal)
    @AtLiteral(int = AdvancementsScreen.WINDOW_WIDTH - 4, ordinal = [0])
    fun overrideRightX(@Origin original: Int): Int =
        advancementsScreen?.let { it.fullscreenWindowWidth - it.horizontalTabOffset } ?: original

    @Hook(_AdvancementTabType.getY::class, Ats.Literal)
    @AtLiteral(int = AdvancementsScreen.WINDOW_HEIGHT - 4, ordinal = [0])
    fun overrideBelowY(@Origin original: Int): Int =
        advancementsScreen?.let { it.fullscreenWindowHeight - it.verticalTabOffset } ?: original

    @Hook(_AdvancementTabType.extractRenderState::class, Ats.Call)
    @AtCall(_GuiGraphics.blitSprite::class, ordinal = [0])
    fun fixSpriteAlignment(
        @Origin original: Lapis.Call<_GuiGraphics.blitSprite>,
        @KLocal sprites: AdvancementTabType.Sprites,
    ) {
        original(sprite = advancementsScreen?.let {
            val isVertical = type == AdvancementTabType.ABOVE || type == AdvancementTabType.BELOW

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
