package io.github.diskria.advancements_fullscreen.client.schemas

import io.github.diskria.advancements_fullscreen.generated.Lapis
import io.github.recrafter.lapis.annotations.Schema
import io.github.recrafter.lapis.annotations.Side
import net.minecraft.client.gui.Font
import net.minecraft.client.gui.components.AbstractWidget
import net.minecraft.client.gui.layouts.LayoutElement
import net.minecraft.network.chat.Component
import java.util.function.Consumer

@Schema("net.minecraft.client.gui.layouts.HeaderAndFooterLayout", side = Side.ClientOnly)
object _HeaderAndFooterLayout {
    object addTitleHeader : Lapis.Method<(component: Component, font: Font) -> Unit>
    object addToFooter : Lapis.Method<(element: LayoutElement) -> LayoutElement>
    object visitWidgets : Lapis.Method<(consumer: Consumer<AbstractWidget>) -> Unit>
}
