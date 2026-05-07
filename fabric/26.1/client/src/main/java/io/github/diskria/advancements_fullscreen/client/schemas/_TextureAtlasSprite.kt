package io.github.diskria.advancements_fullscreen.client.schemas

import io.github.diskria.advancements_fullscreen.generated.Lapis
import io.github.recrafter.lapis.annotations.Access
import io.github.recrafter.lapis.annotations.Schema
import io.github.recrafter.lapis.annotations.Side
import net.minecraft.client.renderer.texture.SpriteContents
import net.minecraft.resources.Identifier

@Schema("net.minecraft.client.renderer.texture.TextureAtlasSprite", side = Side.ClientOnly)
object _TextureAtlasSprite {

    @Access
    object newInstance : Lapis.Constructor<(
        identifier: Identifier,
        contents: SpriteContents,
        atlasWidth: Int, atlasHeight: Int,
        x: Int, y: Int,
        padding: Int,
    ) -> Unit>
}
