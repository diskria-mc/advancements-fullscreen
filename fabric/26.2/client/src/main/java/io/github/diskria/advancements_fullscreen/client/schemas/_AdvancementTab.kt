package io.github.diskria.advancements_fullscreen.client.schemas

import io.github.recrafter.lapis.annotations.*
import net.minecraft.client.gui.GuiGraphicsExtractor
import net.minecraft.client.gui.screens.advancements.AdvancementTab

@Class(AdvancementTab::class, side = Side.ClientOnly)
object _AdvancementTab {

    @MappingName("centered")
    @Access(field = [Op.Set])
    @Field<Boolean>
    object isCenterSet

    @Method<(scrollX: Double, scrollY: Double) -> Unit>
    object scroll

    @Method<(relativeMouseX: Int, relativeMouseY: Int) -> Unit>
    object tick

    @Method<() -> Boolean>
    object canScrollHorizontally

    @Method<() -> Boolean>
    object canScrollVertically

    @Method<(graphics: GuiGraphicsExtractor, xo: Int, yo: Int) -> Unit>
    object extractTooltips

    @Method<(graphics: GuiGraphicsExtractor, x: Int, y: Int) -> Unit>
    object extractContents
}
