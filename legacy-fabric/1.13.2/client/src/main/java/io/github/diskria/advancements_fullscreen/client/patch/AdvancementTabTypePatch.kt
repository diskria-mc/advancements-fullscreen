package io.github.diskria.advancements_fullscreen.client.patch

import io.github.diskria.advancements_fullscreen.client._AdvancementTabType
import io.github.diskria.advancements_fullscreen.client._AdvancementsScreen
import io.github.diskria.advancements_fullscreen.client._DrawableHelper
import io.github.diskria.advancements_fullscreen.generated.*
import io.github.recrafter.lapis.annotations.*
import net.minecraft.class_3269
import net.minecraft.client.MinecraftClient
import net.minecraft.client.gui.screen.AdvancementsScreen

@Patch(_AdvancementTabType::class, Side.ClientOnly)
abstract class AdvancementTabTypePatch : Lapis.Patch<class_3269>() {

    private val advancementsScreen: AdvancementsScreen? get() =
        MinecraftClient.getInstance().currentScreen as? AdvancementsScreen

    @Hook(_AdvancementTabType.getX::class, Hook.At.Literal)
    @AtLiteral(int = _AdvancementsScreen.WINDOW_WIDTH - 4, ordinal = [0])
    fun overrideRightX(@Origin original: Int): Int =
        advancementsScreen?.let { it.fullscreenWindowWidth - it.horizontalTabOffset } ?: original

    @Hook(_AdvancementTabType.getY::class, Hook.At.Literal)
    @AtLiteral(int = _AdvancementsScreen.WINDOW_HEIGHT - 4, ordinal = [0])
    fun overrideBelowY(@Origin original: Int): Int =
        advancementsScreen?.let { it.fullscreenWindowHeight - it.verticalTabOffset } ?: original

    @Hook(_AdvancementTabType.draw::class, Hook.At.Call)
    @AtCall(_DrawableHelper.drawTexture::class, ordinal = [0])
    fun fixSpriteAlignment(@Origin original: Lapis.Call<_DrawableHelper.drawTexture>) {
        original(u = advancementsScreen?.let {
            val isVertical = instance == class_3269.ABOVE || instance == class_3269.BELOW

            val tabX = original.x
            val tabY = original.y
            val tabW = original.width
            val tabH = original.height

            val tabPosition = if (isVertical) tabX else tabY
            val tabSize = if (isVertical) tabW else tabH

            val screenMargin = if (isVertical) it.fullscreenHorizontalMargin else it.fullscreenVerticalMargin
            val screenSize = if (isVertical) it.width else it.height

            val baseU = instance.field_15977
            when {
                tabPosition <= screenMargin -> baseU
                tabPosition + tabSize >= screenSize - screenMargin -> baseU + (tabW * 2)
                else -> baseU + tabW
            }
        } ?: original.u)
    }
}
