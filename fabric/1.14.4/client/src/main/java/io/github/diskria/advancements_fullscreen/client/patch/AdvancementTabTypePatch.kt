package io.github.diskria.advancements_fullscreen.client.patch

import io.github.diskria.advancements_fullscreen.client._AdvancementTabType
import io.github.diskria.advancements_fullscreen.client._AdvancementsScreen
import io.github.diskria.advancements_fullscreen.client._GuiComponent
import io.github.diskria.advancements_fullscreen.generated.*
import io.github.recrafter.lapis.annotations.*
import net.minecraft.client.Minecraft
import net.minecraft.client.gui.screens.advancements.AdvancementTabType
import net.minecraft.client.gui.screens.advancements.AdvancementsScreen

@Patch(_AdvancementTabType::class, Side.ClientOnly)
abstract class AdvancementTabTypePatch : Lapis.Patch<AdvancementTabType>() {

    private val advancementsScreen: AdvancementsScreen? get() =
        Minecraft.getInstance().screen as? AdvancementsScreen

    @Hook(_AdvancementTabType.getX::class, Hook.At.Literal)
    @AtLiteral(int = _AdvancementsScreen.WINDOW_WIDTH - 4, ordinal = [0])
    fun overrideRightX(@Origin original: Int): Int =
        advancementsScreen?.let { it.fullscreenWindowWidth - it.horizontalTabOffset } ?: original

    @Hook(_AdvancementTabType.getY::class, Hook.At.Literal)
    @AtLiteral(int = _AdvancementsScreen.WINDOW_HEIGHT - 4, ordinal = [0])
    fun overrideBelowY(@Origin original: Int): Int =
        advancementsScreen?.let { it.fullscreenWindowHeight - it.verticalTabOffset } ?: original

    @Hook(_AdvancementTabType.draw::class, Hook.At.Call)
    @AtCall(_GuiComponent.drawTexture::class, ordinal = [0])
    fun fixSpriteAlignment(@Origin original: Lapis.Call<_GuiComponent.drawTexture>) {
        original(u = advancementsScreen?.let {
            val isVertical = instance == AdvancementTabType.ABOVE || instance == AdvancementTabType.BELOW

            val tabX = original.x
            val tabY = original.y
            val tabW = original.width
            val tabH = original.height

            val tabPosition = if (isVertical) tabX else tabY
            val tabSize = if (isVertical) tabW else tabH

            val screenMargin = if (isVertical) it.fullscreenHorizontalMargin else it.fullscreenVerticalMargin
            val screenSize = if (isVertical) it.width else it.height

            val baseU = instance.textureX
            when {
                tabPosition <= screenMargin -> baseU
                tabPosition + tabSize >= screenSize - screenMargin -> baseU + (tabW * 2)
                else -> baseU + tabW
            }
        } ?: original.u)
    }
}
