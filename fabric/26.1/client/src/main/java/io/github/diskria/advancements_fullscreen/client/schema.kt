package io.github.diskria.advancements_fullscreen.client

import com.mojang.authlib.GameProfile
import com.mojang.blaze3d.pipeline.RenderPipeline
import io.github.diskria.advancements_fullscreen.generated.Lapis
import io.github.recrafter.lapis.annotations.Access
import io.github.recrafter.lapis.annotations.Schema
import io.github.recrafter.lapis.annotations.Static
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
import net.minecraft.world.entity.Entity
import net.minecraft.world.level.Level
import java.util.function.Consumer

@Schema("net.minecraft.client.gui.screens.advancements.AdvancementWidget")
object _AdvancementWidget {

    @Access object tab : Lapis.Field<AdvancementTab>

    object extractHover : Lapis.Method<(
        graphics: GuiGraphicsExtractor,
        scrollX: Int, scrollY: Int,
        fade: Float,
        x: Int, y: Int,
    ) -> Unit>
}

@Schema("net.minecraft.client.gui.screens.advancements.AdvancementsScreen")
object _AdvancementsScreen {

    @Access @Static object WINDOW_LOCATION : Lapis.Field<Identifier>
    @Access @Static object WINDOW_INSIDE_X : Lapis.Field<Int>
    @Access @Static object WINDOW_INSIDE_Y : Lapis.Field<Int>
    @Access @Static object BACKGROUND_TEXTURE_WIDTH : Lapis.Field<Int>
    @Access @Static object BACKGROUND_TEXTURE_HEIGHT : Lapis.Field<Int>
    @Access object tabs : Lapis.Field<Map<AdvancementHolder, AdvancementTab>>
    @Access object selectedTab : Lapis.Field<AdvancementTab?>
    object width : Lapis.Field<Int>

    @Access object init : Lapis.Method<() -> Unit>

    object extractRenderState : Lapis.Method<(
        graphics: GuiGraphicsExtractor,
        x: Int, y: Int,
        color: Float,
    ) -> Unit>

    @Access object extractInside : Lapis.Method<(
        graphics: GuiGraphicsExtractor,
        x: Int, y: Int,
    ) -> Unit>

    object mouseClicked : Lapis.Method<(mouseButtonEvent: MouseButtonEvent, isDouble: Boolean) -> Boolean>

    object mouseScrolled : Lapis.Method<(
        x: Double, y: Double,
        dx: Double, dy: Double,
    ) -> Boolean>

    @Access object repositionElements : Lapis.Method<() -> Unit>

    object extractWindow : Lapis.Method<(
        graphics: GuiGraphicsExtractor,
        windowX: Int, windowY: Int,
        screenX: Int, screenY: Int,
    ) -> Unit>
}

@Schema("net.minecraft.client.gui.screens.advancements.AdvancementTab")
object _AdvancementTab {

    @Access object fade : Lapis.Field<Float>
    @Access object centered : Lapis.Field<Boolean>

    object scroll : Lapis.Method<(scrollX: Double, scrollY: Double) -> Unit>
    object isMouseOver : Lapis.Method<(Int, Int, scrollX: Double, scrollY: Double) -> Boolean>
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

@Access
@Schema("net.minecraft.client.gui.screens.advancements.AdvancementTabType")
object _AdvancementTabType {

    object extractRenderState : Lapis.Method<(
        graphics: GuiGraphicsExtractor,
        x: Int, y: Int,
        isSelected: Boolean,
        index: Int,
    ) -> Unit>

    object getX : Lapis.Method<(index: Int) -> Int>
    object getY : Lapis.Method<(index: Int) -> Int>

    @Access
    @Schema("Sprites")
    object _Sprites
}

@Schema("net.minecraft.client.gui.GuiGraphicsExtractor")
object _GuiGraphics {

    @Access
    object blitNineSlicedSprite : Lapis.Method<(
        renderPipeline: RenderPipeline,
        textureAtlasSprite: TextureAtlasSprite,
        nineSlice: GuiSpriteScaling.NineSlice,
        x: Int, y: Int,
        width: Int, height: Int,
        color: Int,
    ) -> Unit>

    object blit : Lapis.Method<(
        renderPipeline: RenderPipeline,
        Identifier,
        x: Int, y: Int,
        Float, Float,
        Int, Int,
        Int, Int,
    ) -> Unit>

    object blitSprite : Lapis.Method<(
        renderPipeline: RenderPipeline,
        sprite: Identifier,
        x: Int, y: Int,
        width: Int, height: Int,
    ) -> Unit>
}

@Schema("net.minecraft.client.gui.layouts.HeaderAndFooterLayout")
object _HeaderAndFooterLayout {
    object addTitleHeader : Lapis.Method<(component: Component, font: Font) -> Unit>
    object addToFooter : Lapis.Method<(element: LayoutElement) -> LayoutElement>
    object visitWidgets : Lapis.Method<(consumer: Consumer<AbstractWidget>) -> Unit>
}

@Schema("net.minecraft.client.renderer.texture.TextureAtlasSprite")
object _TextureAtlasSprite {

    @Access object newInstance : Lapis.Constructor<(
        identifier: Identifier,
        contents: SpriteContents,
        atlasWidth: Int, atlasHeight: Int,
        x: Int, y: Int,
        padding: Int,
    ) -> Unit>
}

@Schema("net.minecraft.world.entity.player.Player")
object _Player {
    object crit : Lapis.Method<(Entity) -> Unit>
    object noPhysics : Lapis.Field<Boolean>
    object newInstance : Lapis.Constructor<(Level, GameProfile) -> Unit>
}

@Schema("net.minecraft.world.phys.Vec3")
object _Vec3 {
    object newInstance : Lapis.Constructor<(Double, Double, Double) -> Unit>
}
