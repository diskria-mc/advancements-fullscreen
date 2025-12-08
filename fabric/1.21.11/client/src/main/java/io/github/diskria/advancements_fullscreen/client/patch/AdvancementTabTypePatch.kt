package io.github.diskria.advancements_fullscreen.client.patch

import io.github.diskria.advancements_fullscreen.client.AdvancementTabType_
import io.github.diskria.advancements_fullscreen.client.GuiGraphics_
import io.github.diskria.advancements_fullscreen.generated.*
import io.github.recrafter.lapis.Hook
import io.github.recrafter.lapis.Side
import io.github.recrafter.lapis.annotations.*
import net.minecraft.client.Minecraft
import net.minecraft.client.gui.screens.advancements.AdvancementTabType
import net.minecraft.client.gui.screens.advancements.AdvancementsScreen

@LaPatch(AdvancementTabType::class, Side.ClientOnly)
abstract class AdvancementTabTypePatch : Lapis.Patch<AdvancementTabType>() {

    private val advancementsScreen: AdvancementsScreen?
        get() = Minecraft.getInstance().screen as? AdvancementsScreen

    @LaHook(
        AdvancementTabType_.getX::class,
        Hook.Literal
    )
    fun overrideRightX(
        @LaLiteral(int = AdvancementsScreen.WINDOW_WIDTH - 4) original: Int,
        @LaOrdinal(LaOrdinal.FIRST) ordinal: Int,
    ): Int = advancementsScreen?.let { it.fullscreenWindowWidth - it.horizontalTabOffset } ?: original

    @LaHook(
        AdvancementTabType_.getY::class,
        Hook.Literal
    )
    fun overrideBelowY(
        @LaLiteral(int = AdvancementsScreen.WINDOW_HEIGHT - 4) original: Int,
        @LaOrdinal(LaOrdinal.FIRST) ordinal: Int,
    ): Int = advancementsScreen?.let { it.fullscreenWindowHeight - it.verticalTabOffset } ?: original

    @LaHook(
        AdvancementTabType_.draw::class,
        Hook.Call
    )
    fun fixSpriteAlignment(
        @LaTarget original: Lapis.Callable<GuiGraphics_.blitSprite>,
        @LaOrdinal(LaOrdinal.FIRST) ordinal: Int,
        @LaLocal(0) sprites: AdvancementTabType.Sprites,
    ) {
        original.invoke(sprite = advancementsScreen?.let {
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
