package io.github.diskria.advancements_fullscreen.client.schemas

import io.github.recrafter.lapis.annotations.Class
import io.github.recrafter.lapis.annotations.Method
import io.github.recrafter.lapis.annotations.Side
import net.minecraft.client.gui.GuiGraphicsExtractor
import net.minecraft.client.gui.screens.advancements.AdvancementWidget

@Class(AdvancementWidget::class, side = Side.ClientOnly)
interface _AdvancementWidget {

    @Method<(graphics: GuiGraphicsExtractor, scrollX: Int, scrollY: Int, fade: Float, x: Int, y: Int) -> Unit>
    interface extractHover
}
