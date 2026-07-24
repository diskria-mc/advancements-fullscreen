package io.github.diskria.advancements_fullscreen.client.schemas

import io.github.recrafter.lapis.annotations.Access
import io.github.recrafter.lapis.annotations.Class
import io.github.recrafter.lapis.annotations.Constructor
import io.github.recrafter.lapis.annotations.Side
import net.minecraft.client.renderer.texture.SpriteContents
import net.minecraft.client.renderer.texture.TextureAtlasSprite
import net.minecraft.resources.Identifier

@Class(TextureAtlasSprite::class, side = Side.ClientOnly)
object _TextureAtlasSprite {

    @Access
    @Constructor<(
        identifier: Identifier,
        contents: SpriteContents,
        atlasWidth: Int, atlasHeight: Int,
        x: Int, y: Int,
        padding: Int,
    ) -> Unit>
    object newInstance
}
