package io.github.diskria.advancements_fullscreen.client.schemas

import io.github.recrafter.lapis.annotations.*
import net.minecraft.client.gui.GuiGraphicsExtractor
import net.minecraft.client.gui.screens.advancements.AdvancementTab

@Class(AdvancementTab::class, side = Side.ClientOnly)
interface _AdvancementTab {

    @MappingName("centered")
    @Access(field = [Op.Set])
    @Field<Boolean>
    object isCenterSet

    @Method<(scrollX: Double, scrollY: Double) -> Unit>
    interface scroll

    @Method<(relativeMouseX: Int, relativeMouseY: Int) -> Unit>
    interface tick

    @Method<() -> Boolean>
    interface canScrollHorizontally

    @Method<() -> Boolean>
    interface canScrollVertically

    @Method<(graphics: GuiGraphicsExtractor, xo: Int, yo: Int) -> Unit>
    interface extractTooltips

    @Method<(graphics: GuiGraphicsExtractor, x: Int, y: Int) -> Unit>
    interface extractContents
}
