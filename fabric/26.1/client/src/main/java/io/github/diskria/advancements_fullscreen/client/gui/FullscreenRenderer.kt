package io.github.diskria.advancements_fullscreen.client.gui

import com.mojang.blaze3d.pipeline.RenderPipeline
import com.mojang.blaze3d.platform.NativeImage
import io.github.diskria.advancements_fullscreen.client.schemas.*
import net.minecraft.client.gui.GuiGraphicsExtractor
import net.minecraft.client.gui.screens.advancements.AdvancementsScreen
import net.minecraft.client.renderer.texture.SpriteContents
import net.minecraft.client.renderer.texture.TextureAtlasSprite
import net.minecraft.client.resources.metadata.animation.FrameSize
import net.minecraft.client.resources.metadata.gui.GuiSpriteScaling

object FullscreenRenderer {

    private val atlasSprite: TextureAtlasSprite by lazy {
        _TextureAtlasSprite.newInstance(
            _AdvancementsScreen.WINDOW_LOCATION.value,
            SpriteContents(
                _AdvancementsScreen.WINDOW_LOCATION.value,
                FrameSize(
                    AdvancementsScreen.WINDOW_WIDTH,
                    AdvancementsScreen.WINDOW_HEIGHT,
                ),
                NativeImage(
                    _AdvancementsScreen.BACKGROUND_TEXTURE_WIDTH.value,
                    _AdvancementsScreen.BACKGROUND_TEXTURE_HEIGHT.value,
                    false,
                )
            ),
            _AdvancementsScreen.BACKGROUND_TEXTURE_WIDTH.value,
            _AdvancementsScreen.BACKGROUND_TEXTURE_HEIGHT.value,
            0,
            0,
            0,
        )
    }

    private val nineSlice: GuiSpriteScaling.NineSlice by lazy {
        val shadowDepth = 6
        GuiSpriteScaling.NineSlice(
            AdvancementsScreen.WINDOW_WIDTH, AdvancementsScreen.WINDOW_HEIGHT,
            GuiSpriteScaling.NineSlice.Border(
                _AdvancementsScreen.WINDOW_INSIDE_X.value + shadowDepth,
                _AdvancementsScreen.WINDOW_INSIDE_Y.value + shadowDepth,
                _AdvancementsScreen.WINDOW_INSIDE_X.value + shadowDepth,
                _AdvancementsScreen.WINDOW_INSIDE_X.value + shadowDepth,
            ),
            true,
        )
    }

    fun render(
        graphics: GuiGraphicsExtractor,
        pipeline: RenderPipeline,
        x: Int, y: Int,
        width: Int, height: Int,
    ) {
        graphics.blitNineSlicedSprite(
            pipeline,
            atlasSprite,
            nineSlice,
            x, y,
            width, height,
            -1,
        )
    }
}
