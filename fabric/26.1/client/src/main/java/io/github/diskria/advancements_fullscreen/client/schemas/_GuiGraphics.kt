package io.github.diskria.advancements_fullscreen.client.schemas

import com.mojang.blaze3d.pipeline.RenderPipeline
import io.github.diskria.advancements_fullscreen.generated.Lapis
import io.github.recrafter.lapis.annotations.Access
import io.github.recrafter.lapis.annotations.Schema
import io.github.recrafter.lapis.annotations.Side
import net.minecraft.client.renderer.texture.TextureAtlasSprite
import net.minecraft.client.resources.metadata.gui.GuiSpriteScaling
import net.minecraft.resources.Identifier

@Schema("net.minecraft.client.gui.GuiGraphicsExtractor", side = Side.ClientOnly)
object _GuiGraphics {

    @Access
    object blitNineSlicedSprite : Lapis.Method<(
        renderPipeline: RenderPipeline,
        textureAtlasSprite: TextureAtlasSprite,
        nineSlice: GuiSpriteScaling.NineSlice,
        x: Int, y: Int,
        width: Int, height: Int,
        color: Int,
    ) -> Unit>

    object blit : Lapis.Method<(
        renderPipeline: RenderPipeline,
        Identifier,
        x: Int, y: Int,
        Float, Float,
        Int, Int,
        Int, Int,
    ) -> Unit>

    object blitSprite : Lapis.Method<(
        renderPipeline: RenderPipeline,
        sprite: Identifier,
        x: Int, y: Int,
        width: Int, height: Int,
    ) -> Unit>
}
