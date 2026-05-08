package io.github.diskria.advancements_fullscreen.client.schemas

import io.github.diskria.advancements_fullscreen.generated.Lapis
import io.github.recrafter.lapis.annotations.Access
import io.github.recrafter.lapis.annotations.Op
import io.github.recrafter.lapis.annotations.Schema
import io.github.recrafter.lapis.annotations.Side
import net.minecraft.client.gui.GuiGraphicsExtractor

@Schema("net.minecraft.client.gui.screens.advancements.AdvancementTab", side = Side.ClientOnly)
object _AdvancementTab {

    @Access(field = [Op.Set]) object centered : Lapis.Field<Boolean>

    object scroll : Lapis.Method<(scrollX: Double, scrollY: Double) -> Unit>
    object canScrollHorizontally : Lapis.Method<() -> Boolean>
    object canScrollVertically : Lapis.Method<() -> Boolean>

    object extractTooltips : Lapis.Method<(
        graphics: GuiGraphicsExtractor,
        windowX: Int, windowY: Int,
        x: Int, y: Int,
    ) -> Unit>

    object extractContents : Lapis.Method<(
        graphics: GuiGraphicsExtractor,
        x: Int, y: Int,
    ) -> Unit>
}
