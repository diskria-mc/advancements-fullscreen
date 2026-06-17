package io.github.diskria.advancements_fullscreen.client.schemas

import io.github.diskria.advancements_fullscreen.generated.Lapis
import io.github.recrafter.lapis.annotations.*
import net.minecraft.client.gui.GuiGraphicsExtractor

@Access(strategy = AccessStrategy.Tweak)
@Schema("net.minecraft.client.gui.screens.advancements.AdvancementTabType", side = Side.ClientOnly)
object _AdvancementTabType {

    object extractRenderState : Lapis.Method<(
        graphics: GuiGraphicsExtractor,
        x: Int, y: Int,
        isSelected: Boolean,
        index: Int,
    ) -> Unit>

    object getX : Lapis.Method<(index: Int) -> Int>
    object getY : Lapis.Method<(index: Int) -> Int>

    @Access(strategy = AccessStrategy.Tweak)
    @InnerSchema("Sprites")
    object _Sprites
}
