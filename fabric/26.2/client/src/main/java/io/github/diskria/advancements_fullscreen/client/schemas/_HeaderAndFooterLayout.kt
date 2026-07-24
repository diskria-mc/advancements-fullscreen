package io.github.diskria.advancements_fullscreen.client.schemas

import io.github.recrafter.lapis.annotations.Class
import io.github.recrafter.lapis.annotations.Method
import io.github.recrafter.lapis.annotations.Side
import net.minecraft.client.gui.Font
import net.minecraft.client.gui.components.AbstractWidget
import net.minecraft.client.gui.layouts.HeaderAndFooterLayout
import net.minecraft.client.gui.layouts.LayoutElement
import net.minecraft.network.chat.Component
import java.util.function.Consumer

@Class(HeaderAndFooterLayout::class, side = Side.ClientOnly)
object _HeaderAndFooterLayout {

    @Method<(component: Component, font: Font) -> Unit>
    object addTitleHeader

    @Method<(element: LayoutElement) -> LayoutElement>
    object addToFooter

    @Method<(consumer: Consumer<AbstractWidget>) -> Unit>
    object visitWidgets
}
