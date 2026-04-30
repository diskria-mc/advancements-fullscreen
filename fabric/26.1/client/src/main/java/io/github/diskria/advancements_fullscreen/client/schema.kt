package io.github.diskria.advancements_fullscreen.client

import com.mojang.blaze3d.pipeline.RenderPipeline
import io.github.diskria.advancements_fullscreen.generated.Lapis
import io.github.recrafter.lapis.annotations.*
import net.minecraft.advancements.AdvancementHolder
import net.minecraft.client.gui.Font
import net.minecraft.client.gui.GuiGraphicsExtractor
import net.minecraft.client.gui.components.AbstractWidget
import net.minecraft.client.gui.layouts.LayoutElement
import net.minecraft.client.gui.screens.advancements.AdvancementTab
import net.minecraft.client.input.MouseButtonEvent
import net.minecraft.client.renderer.texture.SpriteContents
import net.minecraft.client.renderer.texture.TextureAtlasSprite
import net.minecraft.client.resources.metadata.gui.GuiSpriteScaling
import net.minecraft.network.chat.Component
import net.minecraft.resources.Identifier
import java.util.function.Consumer

@Schema("net.minecraft.client.gui.screens.advancements.AdvancementWidget")
interface _AdvancementWidget {

    @Access() interface tab : Lapis.Field<AdvancementTab>

    interface extractHover : Lapis.Method<(
        graphics: GuiGraphicsExtractor,
        scrollX: Int, scrollY: Int,
        fade: Float,
        x: Int, y: Int,
    ) -> Unit>
}

@Schema("net.minecraft.client.gui.screens.advancements.AdvancementsScreen")
interface _AdvancementsScreen {

    @Access @Static interface WINDOW_LOCATION : Lapis.Field<Identifier>
    @Access @Static interface WINDOW_INSIDE_X : Lapis.Field<Int>
    @Access @Static interface WINDOW_INSIDE_Y : Lapis.Field<Int>
    @Access @Static interface BACKGROUND_TEXTURE_WIDTH : Lapis.Field<Int>
    @Access @Static interface BACKGROUND_TEXTURE_HEIGHT : Lapis.Field<Int>
    @Access() interface tabs : Lapis.Field<Map<AdvancementHolder, AdvancementTab>>
    interface selectedTab : Lapis.Field<AdvancementTab?>
    interface width : Lapis.Field<Int>

    interface init : Lapis.Method<() -> Unit>

    interface extractRenderState : Lapis.Method<(
        graphics: GuiGraphicsExtractor,
        x: Int, y: Int,
        color: Float,
    ) -> Unit>

    interface extractInside : Lapis.Method<(
        graphics: GuiGraphicsExtractor,
        x: Int, y: Int,
    ) -> Unit>

    interface mouseClicked : Lapis.Method<(mouseButtonEvent: MouseButtonEvent, isDouble: Boolean) -> Boolean>

    interface mouseScrolled : Lapis.Method<(
        x: Double, y: Double,
        dx: Double, dy: Double,
    ) -> Boolean>

    @MappingName("repositionElements")
    interface updateUI : Lapis.Method<() -> Unit>

    interface extractWindow : Lapis.Method<(
        graphics: GuiGraphicsExtractor,
        windowX: Int, windowY: Int,
        screenX: Int, screenY: Int,
    ) -> Unit>
}

@Schema("net.minecraft.client.gui.screens.advancements.AdvancementTab")
interface _AdvancementTab {

    interface fade : Lapis.Field<Float>
    @Access() interface centered : Lapis.Field<Boolean>

    interface scroll : Lapis.Method<(scrollX: Double, scrollY: Double) -> Unit>
    interface isMouseOver : Lapis.Method<(Int, Int, scrollX: Double, scrollY: Double) -> Boolean>
    interface canScrollHorizontally : Lapis.Method<() -> Boolean>
    interface canScrollVertically : Lapis.Method<() -> Boolean>

    interface extractTooltips : Lapis.Method<(
        graphics: GuiGraphicsExtractor,
        windowX: Int, windowY: Int,
        x: Int, y: Int,
    ) -> Unit>

    interface extractContents : Lapis.Method<(
        graphics: GuiGraphicsExtractor,
        x: Int, y: Int,
    ) -> Unit>
}

@Access
@Schema("net.minecraft.client.gui.screens.advancements.AdvancementTabType")
interface _AdvancementTabType {

    interface extractRenderState : Lapis.Method<(
        graphics: GuiGraphicsExtractor,
        x: Int, y: Int,
        isSelected: Boolean,
        index: Int,
    ) -> Unit>

    interface getX : Lapis.Method<(index: Int) -> Int>
    interface getY : Lapis.Method<(index: Int) -> Int>

    @Access
    @InnerSchema("Sprites")
    interface _Sprites
}

@Schema("net.minecraft.client.gui.GuiGraphicsExtractor")
interface _GuiGraphics {

    @Access interface blitNineSlicedSprite : Lapis.Method<(
        renderPipeline: RenderPipeline,
        textureAtlasSprite: TextureAtlasSprite,
        nineSlice: GuiSpriteScaling.NineSlice,
        x: Int, y: Int,
        width: Int, height: Int,
        color: Int,
    ) -> Unit>

    interface blit : Lapis.Method<(
        renderPipeline: RenderPipeline,
        Identifier,
        x: Int, y: Int,
        Float, Float,
        Int, Int,
        Int, Int,
    ) -> Unit>

    interface blitSprite : Lapis.Method<(
        renderPipeline: RenderPipeline,
        sprite: Identifier,
        x: Int, y: Int,
        width: Int, height: Int,
    ) -> Unit>
}

@Schema("net.minecraft.client.gui.layouts.HeaderAndFooterLayout")
interface _HeaderAndFooterLayout {
    interface addTitleHeader : Lapis.Method<(component: Component, font: Font) -> Unit>
    interface addToFooter : Lapis.Method<(element: LayoutElement) -> LayoutElement>
    interface visitWidgets : Lapis.Method<(consumer: Consumer<AbstractWidget>) -> Unit>
}

@Schema("net.minecraft.client.renderer.texture.TextureAtlasSprite")
interface _TextureAtlasSprite {

    @Access interface newInstance : Lapis.Constructor<(
        identifier: Identifier,
        contents: SpriteContents,
        atlasWidth: Int, atlasHeight: Int,
        x: Int, y: Int,
        padding: Int,
    ) -> Unit>
}
