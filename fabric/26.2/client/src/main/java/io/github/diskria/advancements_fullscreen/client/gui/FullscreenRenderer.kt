package io.github.diskria.advancements_fullscreen.client.gui

import com.mojang.blaze3d.pipeline.RenderPipeline
import com.mojang.blaze3d.platform.NativeImage
import io.github.diskria.advancements_fullscreen.client.schemas._AdvancementsScreen
import io.github.diskria.advancements_fullscreen.client.schemas._TextureAtlasSprite
import io.github.diskria.advancements_fullscreen.client.schemas.blitNineSlicedSprite
import io.github.diskria.advancements_fullscreen.client.schemas.invoke
import net.minecraft.client.gui.GuiGraphicsExtractor
import net.minecraft.client.gui.screens.advancements.AdvancementsScreen
import net.minecraft.client.renderer.texture.SpriteContents
import net.minecraft.client.renderer.texture.TextureAtlasSprite
import net.minecraft.client.resources.metadata.animation.FrameSize
import net.minecraft.client.resources.metadata.gui.GuiSpriteScaling

object FullscreenRenderer {

    private val sprite: TextureAtlasSprite by lazy {
        _TextureAtlasSprite.newInstance(
            identifier = _AdvancementsScreen.WINDOW_LOCATION(),
            contents = SpriteContents(
                /* name = */ _AdvancementsScreen.WINDOW_LOCATION(),
                /* frameSize = */ FrameSize(
                    /* width = */ AdvancementsScreen.WINDOW_WIDTH,
                    /* height = */ AdvancementsScreen.WINDOW_HEIGHT,
                ),
                /* image = */ NativeImage(
                    /* width = */ _AdvancementsScreen.BACKGROUND_TEXTURE_WIDTH(),
                    /* height = */ _AdvancementsScreen.BACKGROUND_TEXTURE_HEIGHT(),
                    /* zero = */ false,
                )
            ),
            atlasWidth = _AdvancementsScreen.BACKGROUND_TEXTURE_WIDTH(),
            atlasHeight = _AdvancementsScreen.BACKGROUND_TEXTURE_HEIGHT(),
            x = 0, y = 0,
            padding = 0,
        )
    }

    private val nineSlice: GuiSpriteScaling.NineSlice by lazy {
        val shadowDepth = 6
        GuiSpriteScaling.NineSlice(
            /* width = */ AdvancementsScreen.WINDOW_WIDTH, /* height = */ AdvancementsScreen.WINDOW_HEIGHT,
            /* border = */
            GuiSpriteScaling.NineSlice.Border(
                /* left = */ _AdvancementsScreen.WINDOW_INSIDE_X() + shadowDepth,
                /* top = */ _AdvancementsScreen.WINDOW_INSIDE_Y() + shadowDepth,
                /* right = */ _AdvancementsScreen.WINDOW_INSIDE_X() + shadowDepth,
                /* bottom = */ _AdvancementsScreen.WINDOW_INSIDE_X() + shadowDepth,
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
            pipeline = pipeline,
            sprite = sprite,
            nineSlice = nineSlice,
            x = x, y = y,
            width = width, height = height,
            color = -1,
        )
    }
}
