package io.github.diskria.advancements_fullscreen

import com.mojang.blaze3d.pipeline.RenderPipeline
import com.mojang.blaze3d.platform.NativeImage
import net.minecraft.client.gui.GuiGraphics
import net.minecraft.client.gui.screens.advancements.AdvancementsScreen
import net.minecraft.client.renderer.texture.SpriteContents
import net.minecraft.client.renderer.texture.TextureAtlasSprite
import net.minecraft.client.resources.metadata.animation.FrameSize
import net.minecraft.client.resources.metadata.gui.GuiSpriteScaling

private const val TEXTURE_WIDTH: Int = 256
private const val TEXTURE_HEIGHT: Int = 256
private const val SHADOW_OFFSET: Int = 6

object FullscreenAdvancementsWindow : TextureAtlasSprite(
    AdvancementsScreen.WINDOW_LOCATION,
    SpriteContents(
        AdvancementsScreen.WINDOW_LOCATION,
        FrameSize(AdvancementsScreen.WINDOW_WIDTH, AdvancementsScreen.WINDOW_HEIGHT),
        NativeImage(TEXTURE_WIDTH, TEXTURE_HEIGHT, false),
    ),
    TEXTURE_WIDTH,
    TEXTURE_HEIGHT,
    0,
    0,
) {
    private val NINE_SLICE: GuiSpriteScaling.NineSlice by lazy {
        GuiSpriteScaling.NineSlice(
            AdvancementsScreen.WINDOW_WIDTH,
            AdvancementsScreen.WINDOW_HEIGHT,
            GuiSpriteScaling.NineSlice.Border(
                AdvancementsScreen.WINDOW_INSIDE_X + SHADOW_OFFSET,
                AdvancementsScreen.WINDOW_INSIDE_Y + SHADOW_OFFSET,
                AdvancementsScreen.WINDOW_INSIDE_X + SHADOW_OFFSET,
                AdvancementsScreen.WINDOW_INSIDE_X + SHADOW_OFFSET,
            ),
            true,
        )
    }

    @JvmStatic
    fun draw(context: GuiGraphics, pipeline: RenderPipeline, x: Int, y: Int, width: Int, height: Int) {
        context.blitNineSlicedSprite(pipeline, this, NINE_SLICE, x, y, width, height, -1)
    }
}
