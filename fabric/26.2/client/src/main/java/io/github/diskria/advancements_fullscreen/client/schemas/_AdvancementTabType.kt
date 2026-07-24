package io.github.diskria.advancements_fullscreen.client.schemas

import io.github.recrafter.lapis.annotations.*
import net.minecraft.client.gui.GuiGraphicsExtractor

@Access(strategy = AccessStrategy.Tweak)
@Class(name = "net.minecraft.client.gui.screens.advancements.AdvancementTabType", side = Side.ClientOnly)
object _AdvancementTabType {

    @Method<(graphics: GuiGraphicsExtractor, x: Int, y: Int, isSelected: Boolean, index: Int) -> Unit>
    object extractRenderState

    @Method<(index: Int) -> Int>
    object getX

    @Method<(index: Int) -> Int>
    object getY

    @Access(strategy = AccessStrategy.Tweak)
    @InnerClass(name = "Sprites")
    object _Sprites
}
