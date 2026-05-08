package io.github.diskria.advancements_fullscreen.client.schemas

import io.github.diskria.advancements_fullscreen.generated.Lapis
import io.github.recrafter.lapis.annotations.*
import net.minecraft.client.gui.GuiGraphicsExtractor
import net.minecraft.client.input.MouseButtonEvent
import net.minecraft.resources.Identifier

@Schema("net.minecraft.client.gui.screens.advancements.AdvancementsScreen", side = Side.ClientOnly)
object _AdvancementsScreen {

    @Access(field = [Op.Get])
    @Static
    object WINDOW_LOCATION : Lapis.Field<Identifier>

    @Access(field = [Op.Get])
    @Static
    object WINDOW_INSIDE_X : Lapis.Field<Int>

    @Access(field = [Op.Get])
    @Static
    object WINDOW_INSIDE_Y : Lapis.Field<Int>

    @Access(field = [Op.Get])
    @Static
    object BACKGROUND_TEXTURE_WIDTH : Lapis.Field<Int>

    @Access(field = [Op.Get])
    @Static object BACKGROUND_TEXTURE_HEIGHT : Lapis.Field<Int>

    object width : Lapis.Field<Int>

    object init : Lapis.Method<() -> Unit>

    object extractRenderState : Lapis.Method<(
        graphics: GuiGraphicsExtractor,
        x: Int, y: Int,
        color: Float,
    ) -> Unit>

    object extractInside : Lapis.Method<(
        graphics: GuiGraphicsExtractor,
        x: Int, y: Int,
    ) -> Unit>

    object mouseClicked : Lapis.Method<(mouseButtonEvent: MouseButtonEvent, isDouble: Boolean) -> Boolean>

    object mouseScrolled : Lapis.Method<(
        x: Double, y: Double,
        dx: Double, dy: Double,
    ) -> Boolean>

    object repositionElements : Lapis.Method<() -> Unit>

    object extractWindow : Lapis.Method<(
        graphics: GuiGraphicsExtractor,
        windowX: Int, windowY: Int,
        screenX: Int, screenY: Int,
    ) -> Unit>
}
