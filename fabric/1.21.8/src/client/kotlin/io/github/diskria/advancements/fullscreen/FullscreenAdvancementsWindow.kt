package io.github.diskria.advancements.fullscreen

import com.mojang.blaze3d.pipeline.RenderPipeline
import net.minecraft.client.gui.DrawContext
import net.minecraft.client.gui.screen.advancement.AdvancementsScreen
import net.minecraft.client.texture.NativeImage
import net.minecraft.client.texture.Scaling.NineSlice
import net.minecraft.client.texture.Sprite
import net.minecraft.client.texture.SpriteContents
import net.minecraft.client.texture.SpriteDimensions
import net.minecraft.resource.metadata.ResourceMetadata

private const val TEXTURE_WIDTH: Int = 256
private const val TEXTURE_HEIGHT: Int = 256
private const val SHADOW_OFFSET: Int = 6

object FullscreenAdvancementsWindow : Sprite(
    AdvancementsScreen.WINDOW_TEXTURE,
    SpriteContents(
        AdvancementsScreen.WINDOW_TEXTURE,
        SpriteDimensions(AdvancementsScreen.WINDOW_WIDTH, AdvancementsScreen.WINDOW_HEIGHT),
        NativeImage(TEXTURE_WIDTH, TEXTURE_HEIGHT, false),
        ResourceMetadata.NONE,
    ),
    TEXTURE_WIDTH,
    TEXTURE_HEIGHT,
    0,
    0,
) {
    private val NINE_SLICE: NineSlice by lazy {
        NineSlice(
            AdvancementsScreen.WINDOW_WIDTH,
            AdvancementsScreen.WINDOW_HEIGHT,
            NineSlice.Border(
                AdvancementsScreen.PAGE_OFFSET_X + SHADOW_OFFSET,
                AdvancementsScreen.PAGE_OFFSET_Y + SHADOW_OFFSET,
                AdvancementsScreen.PAGE_OFFSET_X + SHADOW_OFFSET,
                AdvancementsScreen.PAGE_OFFSET_X + SHADOW_OFFSET,
            ),
            true,
        )
    }

    @JvmStatic
    fun draw(context: DrawContext, pipeline: RenderPipeline, x: Int, y: Int, width: Int, height: Int) {
        context.drawSpriteNineSliced(pipeline, this, NINE_SLICE, x, y, width, height, -1)
    }
}
