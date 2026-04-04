package io.github.diskria.advancements_fullscreen.client

import io.github.diskria.advancements_fullscreen.generated.Lapis
import io.github.recrafter.lapis.annotations.*
import net.minecraft.advancements.Advancement
import net.minecraft.client.gui.GuiComponent
import net.minecraft.client.gui.screens.advancements.AdvancementTab
import net.minecraft.client.gui.screens.advancements.AdvancementTabType
import net.minecraft.client.gui.screens.advancements.AdvancementWidget
import net.minecraft.client.gui.screens.advancements.AdvancementsScreen
import net.minecraft.resources.ResourceLocation

@Schema(AdvancementWidget::class)
object _AdvancementWidget {

    @Access
    @Field
    object tab : Lapis.Desc<AdvancementWidget.() -> AdvancementTab>
        (AdvancementWidget::tab)

    @Method
    object drawHover : Lapis.Desc
    <AdvancementWidget.(
        scrollX: Int, scrollY: Int,
        fade: Float,
        x: Int, y: Int,
    ) -> Unit>(AdvancementWidget::drawHover)
}

@Schema(AdvancementsScreen::class)
object _AdvancementsScreen {

    const val WINDOW_WIDTH: Int = 252
    const val WINDOW_HEIGHT: Int = 140
    const val WINDOW_INSIDE_X: Int = 9
    const val WINDOW_INSIDE_Y: Int = 18
    const val WINDOW_INSIDE_WIDTH: Int = 234
    const val WINDOW_INSIDE_HEIGHT: Int = 113
    const val BACKGROUND_TILE_COUNT_X: Int = 14
    const val BACKGROUND_TILE_COUNT_Y: Int = 7

    @Access
    @Static @Field
    object WINDOW_LOCATION : Lapis.Desc<() -> ResourceLocation>
        (AdvancementsScreen::WINDOW_LOCATION)

    @Access
    @Field
    object tabs : Lapis.Desc<AdvancementsScreen.() -> Map<Advancement, AdvancementTab>>
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
        mouseX: Int, mouseY: Int,
        tickDelta: Float,
    ) -> Unit>(AdvancementsScreen::render)

    @Access
    @Method
    object renderInside : Lapis.Desc
    <AdvancementsScreen.(
        Int, Int,
        x: Int, y: Int,
    ) -> Unit>(AdvancementsScreen::renderInside)

    @Access
    @Method
    object mouseClicked : Lapis.Desc
    <AdvancementsScreen.(
        mouseX: Double, mouseY: Double, button: Int
    ) -> Boolean>(AdvancementsScreen::mouseClicked)

    @Method
    object renderWindow : Lapis.Desc
    <AdvancementsScreen.(
        windowX: Int, windowY: Int,
    ) -> Unit>(AdvancementsScreen::renderWindow)

    @Method
    object blit : Lapis.Desc
    <AdvancementsScreen.(
        x: Int, y: Int,
        u: Int, v: Int,
        width: Int, height: Int,
    ) -> Unit>(AdvancementsScreen::blit)
}

@Schema(AdvancementTab::class)
object _AdvancementTab {

    @Access
    @Field
    object centered : Lapis.Desc<AdvancementTab.() -> Boolean>
        (AdvancementTab::centered)

    @Access
    @Field
    object screen : Lapis.Desc<AdvancementTab.() -> AdvancementsScreen>
        (AdvancementTab::screen)

    @Method
    object scroll : Lapis.Desc
    <AdvancementTab.(
        scrollX: Double, scrollY: Double,
    ) -> Unit>(AdvancementTab::scroll)

    @Method
    object drawTooltips : Lapis.Desc
    <AdvancementTab.(
        windowX: Int, windowY: Int,
        x: Int, y: Int,
    ) -> Unit>(AdvancementTab::drawTooltips)

    @Method
    object drawContents : Lapis.Desc
    <AdvancementTab.() -> Unit>(AdvancementTab::drawContents)
}

@Schema(
    access = "net.minecraft.client.gui.screens.advancements.AdvancementTabType",
    target = AdvancementTabType::class
)
object _AdvancementTabType {

    @Access
    @Field
    object u : Lapis.Desc<AdvancementTabType.() -> Int>(AdvancementTabType::textureX)

    @Access
    @Field
    object width : Lapis.Desc<AdvancementTabType.() -> Int>(AdvancementTabType::width)

    @Access
    @Field
    object height : Lapis.Desc<AdvancementTabType.() -> Int>(AdvancementTabType::height)

    @Method
    object draw : Lapis.Desc
    <AdvancementTabType.(
        drawableHelper: GuiComponent,
        x: Int, y: Int,
        isSelected: Boolean,
        index: Int,
    ) -> Unit>(AdvancementTabType::draw)

    @Method
    object getX : Lapis.Desc
    <AdvancementTabType.(index: Int) -> Int>(AdvancementTabType::getX)

    @Method
    object getY : Lapis.Desc
    <AdvancementTabType.(index: Int) -> Int>(AdvancementTabType::getY)
}

@Schema(GuiComponent::class)
object _GuiComponent {

    @Access
    @Method
    object drawTexture : Lapis.Desc
    <GuiComponent.(
        x: Int, y: Int,
        u: Int, v: Int,
        width: Int, height: Int,
    ) -> Unit>(GuiComponent::blit)
}
