package io.github.diskria.advancements_fullscreen.client

import io.github.diskria.advancements_fullscreen.generated.Lapis
import io.github.recrafter.lapis.annotations.*
import net.minecraft.advancement.SimpleAdvancement
import net.minecraft.class_3268
import net.minecraft.class_3269
import net.minecraft.client.gui.AchievementNotification
import net.minecraft.client.gui.DrawableHelper
import net.minecraft.client.gui.screen.AdvancementsScreen
import net.minecraft.util.Identifier

@Schema(AchievementNotification::class)
object _AdvancementWidget {

    @Access
    @Field
    object tab : Lapis.Desc<AchievementNotification.() -> class_3268>
        (AchievementNotification::field_15986)

    @Method
    object drawHover : Lapis.Desc
    <AchievementNotification.(
        scrollX: Int, scrollY: Int,
        fade: Float,
        x: Int, y: Int,
    ) -> Unit>(AchievementNotification::method_14526)
}

@Schema(AdvancementsScreen::class)
object _AdvancementsScreen {

    const val WINDOW_WIDTH: Int = 252
    const val WINDOW_HEIGHT: Int = 140
    const val WINDOW_INSIDE_X: Int = 9
    const val WINDOW_INSIDE_Y: Int = 18
    const val WINDOW_INSIDE_WIDTH: Int = 234
    const val WINDOW_INSIDE_HEIGHT: Int = 113
    const val WINDOW_TITLE_X: Int = 8
    const val WINDOW_TITLE_Y: Int = 6
    const val BACKGROUND_TEXTURE_WIDTH: Int = 256
    const val BACKGROUND_TEXTURE_HEIGHT: Int = 256
    const val BACKGROUND_TILE_WIDTH: Int = 16
    const val BACKGROUND_TILE_HEIGHT: Int = 16
    const val BACKGROUND_TILE_COUNT_X: Int = 14
    const val BACKGROUND_TILE_COUNT_Y: Int = 7

    @Access
    @Static @Field
    object WINDOW_LOCATION : Lapis.Desc<() -> Identifier>
        (AdvancementsScreen::WINDOW_TEXTURE)

    @Access
    @Field
    object tabs : Lapis.Desc<AdvancementsScreen.() -> Map<SimpleAdvancement, class_3268>>
        (AdvancementsScreen::field_16011)

    @Access
    @Field
    object selectedTab : Lapis.Desc<AdvancementsScreen.() -> class_3268?>
        (AdvancementsScreen::field_16012)

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
    ) -> Unit>(AdvancementsScreen::method_14544)

    @Access
    @Method
    object mouseClicked : Lapis.Desc
    <AdvancementsScreen.(
        mouseX: Int, mouseY: Int, button: Int
    ) -> Unit>(AdvancementsScreen::mouseClicked)

    @Method
    object renderWindow : Lapis.Desc
    <AdvancementsScreen.(
        windowX: Int, windowY: Int,
    ) -> Unit>(AdvancementsScreen::method_14543)

    @Method
    object drawTexture : Lapis.Desc
    <AdvancementsScreen.(
        x: Int, y: Int,
        u: Int, v: Int,
        width: Int, height: Int,
    ) -> Unit>(AdvancementsScreen::drawTexture)
}

@Schema(class_3268::class)
object _AdvancementTab {

    @Access
    @Field
    object centered : Lapis.Desc<class_3268.() -> Boolean>
        (class_3268::field_15971)

    @Access
    @Field
    object screen : Lapis.Desc<class_3268.() -> AdvancementsScreen>
        (class_3268::field_15955)

    @Method
    object scroll : Lapis.Desc
    <class_3268.(
        scrollX: Int, scrollY: Int,
    ) -> Unit>(class_3268::method_14506)

    @Method
    object drawTooltips : Lapis.Desc
    <class_3268.(
        windowX: Int, windowY: Int,
        x: Int, y: Int,
    ) -> Unit>(class_3268::method_14514)

    @Method
    object drawContents : Lapis.Desc
    <class_3268.() -> Unit>(class_3268::method_14517)
}

@Schema(
    access = "net.minecraft.class_3269",
    target = class_3269::class
)
object _AdvancementTabType {

    @Access
    @Field
    object u : Lapis.Desc<class_3269.() -> Int>(class_3269::field_15977)

    @Access
    @Field
    object width : Lapis.Desc<class_3269.() -> Int>(class_3269::field_15979)

    @Access
    @Field
    object height : Lapis.Desc<class_3269.() -> Int>(class_3269::field_15980)

    @Method
    object draw : Lapis.Desc
    <class_3269.(
        drawableHelper: DrawableHelper,
        x: Int, y: Int,
        isSelected: Boolean,
        index: Int,
    ) -> Unit>(class_3269::method_14523)

    @Method
    object getX : Lapis.Desc
    <class_3269.(index: Int) -> Int>(class_3269::method_14520)

    @Method
    object getY : Lapis.Desc
    <class_3269.(index: Int) -> Int>(class_3269::method_14524)
}

@Schema(DrawableHelper::class)
object _DrawableHelper {

    @Access
    @Method
    object drawTexture : Lapis.Desc
    <DrawableHelper.(
        x: Int, y: Int,
        u: Int, v: Int,
        width: Int, height: Int,
    ) -> Unit>(DrawableHelper::drawTexture)
}
