package io.github.diskria.advancements_fullscreen.client

import com.mojang.blaze3d.pipeline.RenderPipeline
import io.github.diskria.advancements_fullscreen.generated.Lapis
import io.github.recrafter.lapis.annotations.*
import net.minecraft.advancements.AdvancementHolder
import net.minecraft.client.gui.Font
import net.minecraft.client.gui.GuiGraphicsExtractor
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

@Schema(AdvancementWidget::class)
object _AdvancementWidget {

    @Access
    @Field
    object tab : Lapis.Desc<AdvancementWidget.() -> AdvancementTab>
        (AdvancementWidget::tab)

    @Method
    object drawHover : Lapis.Desc
    <AdvancementWidget.(
        graphics: GuiGraphicsExtractor,
        scrollX: Int, scrollY: Int,
        fade: Float,
        x: Int, y: Int,
    ) -> Unit>(AdvancementWidget::extractHover)
}

@Schema(AdvancementsScreen::class)
object _AdvancementsScreen {

    @Access
    @Static @Field
    object WINDOW_LOCATION : Lapis.Desc<() -> Identifier>
        (AdvancementsScreen::WINDOW_LOCATION)

    @Access
    @Static @Field
    object WINDOW_INSIDE_X : Lapis.Desc<() -> Int>
        (AdvancementsScreen::WINDOW_INSIDE_X)

    @Access
    @Static @Field
    object WINDOW_INSIDE_Y : Lapis.Desc<() -> Int>
        (AdvancementsScreen::WINDOW_INSIDE_Y)

    @Access
    @Static @Field
    object BACKGROUND_TEXTURE_WIDTH : Lapis.Desc<() -> Int>
        (AdvancementsScreen::BACKGROUND_TEXTURE_WIDTH)

    @Access
    @Static @Field
    object BACKGROUND_TEXTURE_HEIGHT : Lapis.Desc<() -> Int>
        (AdvancementsScreen::BACKGROUND_TEXTURE_HEIGHT)

    @Access
    @Field
    object tabs : Lapis.Desc<AdvancementsScreen.() -> Map<AdvancementHolder, AdvancementTab>>
        (AdvancementsScreen::tabs)

    @Access
    @Field
    object selectedTab : Lapis.Desc<AdvancementsScreen.() -> AdvancementTab?>
        (AdvancementsScreen::selectedTab)

    @Access
    @Method
    object init : Lapis.Desc
    <AdvancementsScreen.() -> Unit>
        (AdvancementsScreen::init)

    @Method
    object render : Lapis.Desc
    <AdvancementsScreen.(
        graphics: GuiGraphicsExtractor,
        x: Int, y: Int,
        color: Float,
    ) -> Unit>(AdvancementsScreen::extractRenderState)

    @Access
    @Method
    object renderInside : Lapis.Desc
    <AdvancementsScreen.(
        graphics: GuiGraphicsExtractor,
        x: Int, y: Int,
    ) -> Unit>(AdvancementsScreen::extractInside)

    @Method
    object mouseClicked : Lapis.Desc
    <AdvancementsScreen.(
        mouseButtonEvent: MouseButtonEvent,
        isDouble: Boolean,
    ) -> Boolean>(AdvancementsScreen::mouseClicked)

    @Method
    object mouseScrolled : Lapis.Desc
    <AdvancementsScreen.(
        x: Double, y: Double,
        dx: Double, dy: Double,
    ) -> Boolean>(AdvancementsScreen::mouseScrolled)

    @Access
    @Method
    object repositionElements : Lapis.Desc
    <AdvancementsScreen.() -> Unit>(AdvancementsScreen::repositionElements)

    @Method
    object renderWindow : Lapis.Desc
    <AdvancementsScreen.(
        graphics: GuiGraphicsExtractor,
        windowX: Int, windowY: Int,
        screenX: Int, screenY: Int,
    ) -> Unit>(AdvancementsScreen::extractWindow)
}

@Schema(AdvancementTab::class)
object _AdvancementTab {

    @Access
    @Field
    object centered : Lapis.Desc<AdvancementTab.() -> Boolean>
        (AdvancementTab::centered)

    @Method
    object scroll : Lapis.Desc
    <AdvancementTab.(
        scrollX: Double, scrollY: Double,
    ) -> Unit>(AdvancementTab::scroll)

    @Method
    object canScrollHorizontally : Lapis.Desc
    <AdvancementTab.() -> Boolean>(AdvancementTab::canScrollHorizontally)

    @Method
    object canScrollVertically : Lapis.Desc
    <AdvancementTab.() -> Boolean>(AdvancementTab::canScrollVertically)

    @Method
    object drawTooltips : Lapis.Desc
    <AdvancementTab.(
        graphics: GuiGraphicsExtractor,
        windowX: Int, windowY: Int,
        x: Int, y: Int,
    ) -> Unit>(AdvancementTab::extractTooltips)

    @Method
    object drawContents : Lapis.Desc
    <AdvancementTab.(
        graphics: GuiGraphicsExtractor,
        x: Int, y: Int,
    ) -> Unit>(AdvancementTab::extractContents)
}

@Schema(
    access = "net.minecraft.client.gui.screens.advancements.AdvancementTabType",
    target = AdvancementTabType::class
)
object _AdvancementTabType {

    @Method
    object draw : Lapis.Desc
    <AdvancementTabType.(
        graphics: GuiGraphicsExtractor,
        x: Int, y: Int,
        isSelected: Boolean,
        index: Int,
    ) -> Unit>(AdvancementTabType::extractRenderState)

    @Method
    object getX : Lapis.Desc
    <AdvancementTabType.(index: Int) -> Int>(AdvancementTabType::getX)

    @Method
    object getY : Lapis.Desc
    <AdvancementTabType.(index: Int) -> Int>(AdvancementTabType::getY)

    @Schema(access = ".Sprites")
    object Sprites_
}

@Schema(GuiGraphicsExtractor::class)
object _GuiGraphics {

    @Access
    @Method
    object blitNineSlicedSprite : Lapis.Desc
    <GuiGraphicsExtractor.(
        renderPipeline: RenderPipeline,
        textureAtlasSprite: TextureAtlasSprite,
        nineSlice: GuiSpriteScaling.NineSlice,
        x: Int, y: Int,
        width: Int, height: Int,
        color: Int,
    ) -> Unit>(GuiGraphicsExtractor::blitNineSlicedSprite)

    @Method
    object blit : Lapis.Desc
    <GuiGraphicsExtractor.(
        renderPipeline: RenderPipeline,
        identifier: Identifier,
        x: Int, y: Int,
        u: Float, v: Float,
        width: Int, height: Int,
        textureWidth: Int, textureHeight: Int,
    ) -> Unit>(GuiGraphicsExtractor::blit)

    @Method
    object blitSprite : Lapis.Desc
    <GuiGraphicsExtractor.(
        renderPipeline: RenderPipeline,
        sprite: Identifier,
        x: Int, y: Int,
        width: Int, height: Int,
    ) -> Unit>(GuiGraphicsExtractor::blitSprite)
}

@Schema(HeaderAndFooterLayout::class)
object _HeaderAndFooterLayout {

    @Method
    object addTitleHeader : Lapis.Desc
    <HeaderAndFooterLayout.(
        component: Component, font: Font
    ) -> Unit>(HeaderAndFooterLayout::addTitleHeader)

    @Method
    object addToFooter : Lapis.Desc
    <HeaderAndFooterLayout.(
        element: LayoutElement
    ) -> LayoutElement>(HeaderAndFooterLayout::addToFooter)

    @Method
    object visitWidgets : Lapis.Desc
    <HeaderAndFooterLayout.(
        consumer: Consumer<AbstractWidget>
    ) -> Unit>(HeaderAndFooterLayout::visitWidgets)
}

@Schema(TextureAtlasSprite::class)
object _TextureAtlasSprite {

    @Access
    @Constructor
    object newInstance : Lapis.Desc<(
        identifier: Identifier,
        contents: SpriteContents,
        atlasWidth: Int, atlasHeight: Int,
        x: Int, y: Int,
        padding: Int,
    ) -> TextureAtlasSprite>(::TextureAtlasSprite)
}
