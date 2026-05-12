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
            identifier = _AdvancementsScreen.WINDOW_LOCATION.value,
            contents = SpriteContents(
                /* name = */ _AdvancementsScreen.WINDOW_LOCATION.value,
                /* frameSize = */ FrameSize(
                    /* width = */ AdvancementsScreen.WINDOW_WIDTH,
                    /* height = */ AdvancementsScreen.WINDOW_HEIGHT,
                ),
                /* image = */ NativeImage(
                    /* width = */ _AdvancementsScreen.BACKGROUND_TEXTURE_WIDTH.value,
                    /* height = */ _AdvancementsScreen.BACKGROUND_TEXTURE_HEIGHT.value,
                    /* zero = */ false,
                )
            ),
            atlasWidth = _AdvancementsScreen.BACKGROUND_TEXTURE_WIDTH.value,
            atlasHeight = _AdvancementsScreen.BACKGROUND_TEXTURE_HEIGHT.value,
            x = 0,
            y = 0,
            padding = 0,
        )
    }

    private val nineSlice: GuiSpriteScaling.NineSlice by lazy {
        val shadowDepth = 6
        GuiSpriteScaling.NineSlice(
            /* width = */ AdvancementsScreen.WINDOW_WIDTH, /* height = */ AdvancementsScreen.WINDOW_HEIGHT,
            /* border = */
            GuiSpriteScaling.NineSlice.Border(
                /* left = */ _AdvancementsScreen.WINDOW_INSIDE_X.value + shadowDepth,
                /* top = */ _AdvancementsScreen.WINDOW_INSIDE_Y.value + shadowDepth,
                /* right = */ _AdvancementsScreen.WINDOW_INSIDE_X.value + shadowDepth,
                /* bottom = */ _AdvancementsScreen.WINDOW_INSIDE_X.value + shadowDepth,
            ),
            /* stretchInner = */ true,
        )
    }

    fun render(
        graphics: GuiGraphicsExtractor,
        pipeline: RenderPipeline,
        x: Int, y: Int,
        width: Int, height: Int,
    ) {
        graphics.blitNineSlicedSprite(
            renderPipeline = pipeline,
            textureAtlasSprite = atlasSprite,
            nineSlice = nineSlice,
            x = x, y = y,
            width = width, height = height,
            color = -1,
        )
    }
}
