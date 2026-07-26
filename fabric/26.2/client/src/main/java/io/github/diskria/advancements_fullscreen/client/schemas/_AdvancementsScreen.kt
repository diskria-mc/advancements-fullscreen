package io.github.diskria.advancements_fullscreen.client.schemas

import io.github.recrafter.lapis.annotations.*
import net.minecraft.client.gui.GuiGraphicsExtractor
import net.minecraft.client.gui.screens.advancements.AdvancementsScreen
import net.minecraft.client.input.MouseButtonEvent
import net.minecraft.resources.Identifier

@Class(AdvancementsScreen::class, side = Side.ClientOnly)
interface _AdvancementsScreen {

    @Access(field = [Op.Get])
    @Field<Identifier>(static = true)
    object WINDOW_LOCATION

    @Access(field = [Op.Get])
    @Field<Int>(static = true)
    object WINDOW_INSIDE_X

    @Access(field = [Op.Get])
    @Field<Int>(static = true)
    object WINDOW_INSIDE_Y

    @Access(field = [Op.Get])
    @Field<Int>(static = true)
    object BACKGROUND_TEXTURE_WIDTH

    @Access(field = [Op.Get])
    @Field<Int>(static = true)
    object BACKGROUND_TEXTURE_HEIGHT

    @Field<Int>
    interface width

    @Method<() -> Unit>
    interface init

    @Method<(graphics: GuiGraphicsExtractor) -> Unit>
    interface extractInside

    @Method<(mouseButtonEvent: MouseButtonEvent, isDouble: Boolean) -> Boolean>
    interface mouseClicked

    @Method<(x: Double, y: Double, dx: Double, dy: Double) -> Boolean>
    interface mouseScrolled

    @Method<() -> Unit>
    interface repositionElements

    @Method<(graphics: GuiGraphicsExtractor, mouseX: Int, mouseY: Int) -> Unit>
    interface extractWindow
}
