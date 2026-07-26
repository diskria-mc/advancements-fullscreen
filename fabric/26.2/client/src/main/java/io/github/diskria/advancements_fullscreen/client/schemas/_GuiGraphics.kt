package io.github.diskria.advancements_fullscreen.client.schemas

import com.mojang.blaze3d.pipeline.RenderPipeline
import io.github.recrafter.lapis.annotations.Access
import io.github.recrafter.lapis.annotations.Class
import io.github.recrafter.lapis.annotations.Method
import io.github.recrafter.lapis.annotations.Side
import net.minecraft.client.gui.GuiGraphicsExtractor
import net.minecraft.client.renderer.texture.TextureAtlasSprite
import net.minecraft.client.resources.metadata.gui.GuiSpriteScaling
import net.minecraft.resources.Identifier

@Class(GuiGraphicsExtractor::class, side = Side.ClientOnly)
interface _GuiGraphics {

    @Access
    @Method<(
        pipeline: RenderPipeline,
        sprite: TextureAtlasSprite,
        nineSlice: GuiSpriteScaling.NineSlice,
        x: Int, y: Int,
        width: Int, height: Int,
        color: Int,
    ) -> Unit>
    interface blitNineSlicedSprite

    @Method<(renderPipeline: RenderPipeline, Identifier, x: Int, y: Int, Float, Float, Int, Int, Int, Int) -> Unit>
    interface blit

    @Method<(renderPipeline: RenderPipeline, sprite: Identifier, x: Int, y: Int, width: Int, height: Int) -> Unit>
    interface blitSprite
}
