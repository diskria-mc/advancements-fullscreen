package io.github.diskria.advancements_fullscreen.client.schemas

import io.github.diskria.advancements_fullscreen.generated.Lapis
import io.github.recrafter.lapis.annotations.Schema
import io.github.recrafter.lapis.annotations.Side
import net.minecraft.client.gui.GuiGraphicsExtractor

@Schema("net.minecraft.client.gui.screens.advancements.AdvancementWidget", side = Side.ClientOnly)
object _AdvancementWidget {

    object extractHover : Lapis.Method<(
        graphics: GuiGraphicsExtractor,
        scrollX: Int, scrollY: Int,
        fade: Float,
        x: Int, y: Int,
    ) -> Unit>
}
