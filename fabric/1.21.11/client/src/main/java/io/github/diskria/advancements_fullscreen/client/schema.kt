package io.github.diskria.advancements_fullscreen.client

import com.mojang.blaze3d.pipeline.RenderPipeline
import io.github.diskria.advancements_fullscreen.generated.Lapis
import io.github.recrafter.lapis.annotations.*
import net.minecraft.advancements.AdvancementHolder
import net.minecraft.client.gui.Font
import net.minecraft.client.gui.GuiGraphics
import net.minecraft.client.gui.components.AbstractWidget
import net.minecraft.client.gui.layouts.HeaderAndFooterLayout
import net.minecraft.client.gui.layouts.LayoutElement
import net.minecraft.client.gui.screens.advancements.AdvancementTab
import net.minecraft.client.gui.screens.advancements.AdvancementTabType
import net.minecraft.client.gui.screens.advancements.AdvancementWidget
import net.minecraft.client.gui.screens.advancements.AdvancementsScreen
import net.minecraft.client.input.MouseButtonEvent
import net.minecraft.client.renderer.texture.SpriteContents
import net.minecraft.client.renderer.texture.TextureAtlasSprite
import net.minecraft.client.resources.metadata.gui.GuiSpriteScaling
import net.minecraft.network.chat.Component
import net.minecraft.resources.Identifier
import java.util.function.Consumer

@LaSchema(AdvancementWidget::class)
object AdvancementWidget_ {

    @LaField @LaAccess
    object tab : Lapis.Descriptor<AdvancementWidget.() -> AdvancementTab>
        (AdvancementWidget::tab)

    @LaMethod
    object drawHover : Lapis.Descriptor
    <AdvancementWidget.(
        guiGraphics: GuiGraphics,
        scrollX: Int, scrollY: Int,
        fade: Float,
        x: Int, y: Int,
    ) -> Unit>
        (AdvancementWidget::drawHover)
}

@LaSchema(AdvancementsScreen::class)
object AdvancementsScreen_ {

    @LaStatic @LaField @LaAccess
    object WINDOW_LOCATION : Lapis.Descriptor<() -> Identifier>
        (AdvancementsScreen::WINDOW_LOCATION)

    @LaStatic @LaField @LaAccess
    object WINDOW_INSIDE_X : Lapis.Descriptor<() -> Int>
        (AdvancementsScreen::WINDOW_INSIDE_X)

    @LaStatic @LaField @LaAccess
    object WINDOW_INSIDE_Y : Lapis.Descriptor<() -> Int>
        (AdvancementsScreen::WINDOW_INSIDE_Y)

    @LaStatic @LaField @LaAccess
    object BACKGROUND_TEXTURE_WIDTH : Lapis.Descriptor<() -> Int>
        (AdvancementsScreen::BACKGROUND_TEXTURE_WIDTH)

    @LaStatic @LaField @LaAccess
    object BACKGROUND_TEXTURE_HEIGHT : Lapis.Descriptor<() -> Int>
        (AdvancementsScreen::BACKGROUND_TEXTURE_HEIGHT)

    @LaField @LaAccess
    object tabs : Lapis.Descriptor<AdvancementsScreen.() -> Map<AdvancementHolder, AdvancementTab>>
        (AdvancementsScreen::tabs)

    @LaField @LaAccess
    object selectedTab : Lapis.Descriptor<AdvancementsScreen.() -> AdvancementTab?>
        (AdvancementsScreen::selectedTab)

    @LaMethod @LaAccess
    object init : Lapis.Descriptor<AdvancementsScreen.() -> Unit>
        (AdvancementsScreen::init)

    @LaMethod
    object render : Lapis.Descriptor
    <AdvancementsScreen.(
        guiGraphics: GuiGraphics,
        x: Int, y: Int,
        color: Float,
    ) -> Unit>
        (AdvancementsScreen::render)

    @LaMethod @LaAccess
    object renderInside : Lapis.Descriptor
    <AdvancementsScreen.(
        guiGraphics: GuiGraphics,
        x: Int, y: Int,
    ) -> Unit>
        (AdvancementsScreen::renderInside)

    @LaMethod
    object mouseClicked : Lapis.Descriptor
    <AdvancementsScreen.(
        mouseButtonEvent: MouseButtonEvent,
        isDouble: Boolean,
    ) -> Boolean>
        (AdvancementsScreen::mouseClicked)

    @LaMethod
    object mouseScrolled : Lapis.Descriptor
    <AdvancementsScreen.(
        x: Double, y: Double,
        dx: Double, dy: Double,
    ) -> Boolean>
        (AdvancementsScreen::mouseScrolled)

    @LaMethod @LaAccess
    object repositionElements : Lapis.Descriptor
    <AdvancementsScreen.() -> Unit>
        (AdvancementsScreen::repositionElements)

    @LaMethod
    object renderWindow : Lapis.Descriptor
    <AdvancementsScreen.(
        guiGraphics: GuiGraphics,
        windowX: Int, windowY: Int,
        screenX: Int, screenY: Int,
    ) -> Unit>
        (AdvancementsScreen::renderWindow)
}

@LaSchema(AdvancementTab::class)
object AdvancementTab_ {

    @LaField @LaAccess
    object centered : Lapis.Descriptor<AdvancementTab.() -> Boolean>
        (AdvancementTab::centered)

    @LaMethod
    object scroll : Lapis.Descriptor
    <AdvancementTab.(scrollX: Double, scrollY: Double) -> Unit>
        (AdvancementTab::scroll)

    @LaMethod
    object canScrollHorizontally : Lapis.Descriptor
    <AdvancementTab.() -> Boolean>
        (AdvancementTab::canScrollHorizontally)

    @LaMethod
    object canScrollVertically : Lapis.Descriptor
    <AdvancementTab.() -> Boolean>
        (AdvancementTab::canScrollVertically)

    @LaMethod
    object drawTooltips : Lapis.Descriptor
    <AdvancementTab.(
        guiGraphics: GuiGraphics,
        windowX: Int, windowY: Int,
        x: Int, y: Int,
    ) -> Unit>
        (AdvancementTab::drawTooltips)

    @LaMethod
    object drawContents : Lapis.Descriptor
    <AdvancementTab.(
        guiGraphics: GuiGraphics,
        x: Int, y: Int,
    ) -> Unit>
        (AdvancementTab::drawContents)
}

@LaSchema(
    widener = "net.minecraft.client.gui.screens.advancements.AdvancementTabType",
    target = AdvancementTabType::class
)
object AdvancementTabType_ {

    @LaMethod
    object draw : Lapis.Descriptor
    <AdvancementTabType.(
        guiGraphics: GuiGraphics,
        x: Int, y: Int,
        isSelected: Boolean,
        index: Int,
    ) -> Unit>
        (AdvancementTabType::draw)

    @LaMethod
    object getX : Lapis.Descriptor
    <AdvancementTabType.(index: Int) -> Int>
        (AdvancementTabType::getX)

    @LaMethod
    object getY : Lapis.Descriptor
    <AdvancementTabType.(index: Int) -> Int>
        (AdvancementTabType::getY)

    @LaSchema(widener = ".Sprites")
    object Sprites_
}

@LaSchema(GuiGraphics::class)
object GuiGraphics_ {

    @LaMethod @LaAccess
    object blitNineSlicedSprite : Lapis.Descriptor
    <GuiGraphics.(
        renderPipeline: RenderPipeline,
        textureAtlasSprite: TextureAtlasSprite,
        nineSlice: GuiSpriteScaling.NineSlice,
        x: Int, y: Int,
        width: Int, height: Int,
        color: Int,
    ) -> Unit>
        (GuiGraphics::blitNineSlicedSprite)

    @LaMethod
    object blit : Lapis.Descriptor
    <GuiGraphics.(
        renderPipeline: RenderPipeline,
        identifier: Identifier,
        x: Int, y: Int,
        u: Float, v: Float,
        width: Int, height: Int,
        textureWidth: Int, textureHeight: Int,
    ) -> Unit>
        (GuiGraphics::blit)

    @LaMethod
    object blitSprite : Lapis.Descriptor
    <GuiGraphics.(
        renderPipeline: RenderPipeline,
        sprite: Identifier,
        x: Int, y: Int,
        width: Int, height: Int,
    ) -> Unit>
        (GuiGraphics::blitSprite)
}

@LaSchema(HeaderAndFooterLayout::class)
object HeaderAndFooterLayout_ {

    @LaMethod
    object addTitleHeader : Lapis.Descriptor
    <HeaderAndFooterLayout.(component: Component, font: Font) -> Unit>
        (HeaderAndFooterLayout::addTitleHeader)

    @LaMethod
    object addToFooter : Lapis.Descriptor
    <HeaderAndFooterLayout.(element: LayoutElement) -> LayoutElement>
        (HeaderAndFooterLayout::addToFooter)

    @LaMethod
    object visitWidgets : Lapis.Descriptor
    <HeaderAndFooterLayout.(consumer: Consumer<AbstractWidget>) -> Unit>
        (HeaderAndFooterLayout::visitWidgets)
}

@LaSchema(TextureAtlasSprite::class)
object TextureAtlasSprite_ {

    @LaConstructor @LaAccess
    object newInstance : Lapis.Descriptor
    <(
        identifier: Identifier,
        contents: SpriteContents,
        atlasWidth: Int, atlasHeight: Int,
        x: Int, y: Int,
        padding: Int,
    ) -> TextureAtlasSprite>
        (::TextureAtlasSprite)
}
