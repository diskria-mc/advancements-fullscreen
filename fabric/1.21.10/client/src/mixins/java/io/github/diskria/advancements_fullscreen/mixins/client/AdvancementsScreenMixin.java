package io.github.diskria.advancements_fullscreen.mixins.client;

import com.mojang.blaze3d.pipeline.RenderPipeline;
import io.github.diskria.advancements_fullscreen.FullscreenAdvancementsWindow;
import io.github.diskria.advancements_fullscreen.extensions.AdvancementsScreenExtension;
import net.minecraft.advancements.AdvancementHolder;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.layouts.HeaderAndFooterLayout;
import net.minecraft.client.gui.layouts.LayoutElement;
import net.minecraft.client.gui.screens.advancements.AdvancementTab;
import net.minecraft.client.gui.screens.advancements.AdvancementTabType;
import net.minecraft.client.gui.screens.advancements.AdvancementsScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Map;
import java.util.function.Consumer;

import static net.minecraft.client.gui.screens.advancements.AdvancementsScreen.*;

@Mixin(AdvancementsScreen.class)
public abstract class AdvancementsScreenMixin implements AdvancementsScreenExtension {

    @Unique
    private static final int ADVANCEMENTS_SCREEN_MINIMUM_MARGIN = 30;

    @Unique
    private int windowWidth;

    @Unique
    private int windowHeight;

    @Unique
    private int windowHorizontalMargin;

    @Unique
    private int windowVerticalMargin;

    @Override
    public int advancements_fullscreen_getWindowWidth(boolean withBorder) {
        return withBorder ? windowWidth : windowWidth - (WINDOW_INSIDE_X * 2);
    }

    @Override
    public int advancements_fullscreen_getWindowHeight(boolean withBorder) {
        return withBorder ? windowHeight : windowHeight - (WINDOW_INSIDE_Y + WINDOW_INSIDE_X);
    }

    @Override
    public int advancements_fullscreen_getWindowHorizontalMargin() {
        return windowHorizontalMargin;
    }

    @Override
    public int advancements_fullscreen_getWindowVerticalMargin() {
        return windowVerticalMargin;
    }

    @Override
    public void advancements_fullscreen_resize(@NotNull Minecraft minecraft, int width, int height) {
        tabs.values().forEach((tab) -> tab.centered = false);
        calculateWindowSizeAndPosition(width, height);
    }

    @Unique
    private void calculateWindowSizeAndPosition(int screenWidth, int screenHeight) {
        int tabSize = AdvancementTabType.ABOVE.width;

        windowWidth = screenWidth - ADVANCEMENTS_SCREEN_MINIMUM_MARGIN * 2;
        windowHorizontalMargin = (screenWidth - windowWidth) / 2;

        windowHeight = screenHeight - ADVANCEMENTS_SCREEN_MINIMUM_MARGIN * 2;
        windowVerticalMargin = (screenHeight - windowHeight) / 2;
    }

    @Shadow
    @Final
    private Map<AdvancementHolder, AdvancementTab> tabs;

    @Redirect(method = "renderWindow", at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/client/gui/GuiGraphics;blit(Lcom/mojang/blaze3d/pipeline/RenderPipeline;Lnet/minecraft/resources/ResourceLocation;IIFFIIII)V"
    ))
    public void drawFullscreenWindow(
            GuiGraphics guiGraphics,
            RenderPipeline renderPipeline,
            ResourceLocation resourceLocation,
            int i,
            int j,
            float f,
            float g,
            int k,
            int l,
            int m,
            int n
    ) {
        FullscreenAdvancementsWindow.draw(
                guiGraphics,
                renderPipeline,
                windowHorizontalMargin,
                windowVerticalMargin,
                windowWidth,
                windowHeight
        );
    }

    @ModifyConstant(method = "render", constant = @Constant(intValue = WINDOW_WIDTH, ordinal = 0))
    private int calculateHalfOfScreenWidthOnRender(int originalValue) {
        return advancements_fullscreen_getWindowWidth(true);
    }

    @ModifyConstant(method = "render", constant = @Constant(intValue = WINDOW_HEIGHT, ordinal = 0))
    private int calculateHalfOfScreenHeightOnRender(int originalValue) {
        return advancements_fullscreen_getWindowHeight(true);
    }

    @ModifyConstant(method = "mouseClicked", constant = @Constant(intValue = WINDOW_WIDTH, ordinal = 0))
    private int calculateHalfOfScreenWidthOnMouseClicked(int originalValue) {
        return advancements_fullscreen_getWindowWidth(true);
    }

    @ModifyConstant(method = "mouseClicked", constant = @Constant(intValue = WINDOW_HEIGHT, ordinal = 0))
    private int calculateHalfOfScreenHeightOnMouseClicked(int originalValue) {
        return advancements_fullscreen_getWindowHeight(true);
    }

    @ModifyConstant(method = "renderInside", constant = @Constant(intValue = WINDOW_INSIDE_WIDTH, ordinal = 0))
    private int calculateWidthOfEmptyBlackBackground(int originalValue) {
        return advancements_fullscreen_getWindowWidth(false);
    }

    @ModifyConstant(method = "renderInside", constant = @Constant(intValue = WINDOW_INSIDE_HEIGHT, ordinal = 0))
    private int calculateHeightOfEmptyBlackBackground(int originalValue) {
        return advancements_fullscreen_getWindowHeight(false);
    }

    @ModifyConstant(method = "renderInside", constant = @Constant(intValue = WINDOW_INSIDE_WIDTH / 2, ordinal = 0))
    private int moveEmptyTextAndSadLabelTextToCenterOfWidth(int originalValue) {
        return advancements_fullscreen_getWindowWidth(false) / 2;
    }

    @ModifyConstant(method = "renderInside", constant = @Constant(intValue = WINDOW_INSIDE_HEIGHT / 2, ordinal = 0))
    private int moveEmptyTextToCenterOfHeight(int originalValue) {
        return advancements_fullscreen_getWindowHeight(false) / 2;
    }

    @ModifyConstant(method = "renderInside", constant = @Constant(intValue = WINDOW_INSIDE_HEIGHT, ordinal = 1))
    private int moveSadLabelTextToBottom(int originalValue) {
        return advancements_fullscreen_getWindowHeight(false);
    }

    @Redirect(method = "init", at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/client/gui/layouts/HeaderAndFooterLayout;addTitleHeader(Lnet/minecraft/network/chat/Component;Lnet/minecraft/client/gui/Font;)V",
            ordinal = 0
    ))
    private void removeHeader(HeaderAndFooterLayout instance, Component component, Font font) {
    }

    @Redirect(method = "init", at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/client/gui/layouts/HeaderAndFooterLayout;addToFooter(Lnet/minecraft/client/gui/layouts/LayoutElement;)Lnet/minecraft/client/gui/layouts/LayoutElement;",
            ordinal = 0
    ))
    private <T extends LayoutElement> T removeFooter(HeaderAndFooterLayout instance, T layoutElement) {
        return null;
    }

    @Redirect(method = "init", at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/client/gui/layouts/HeaderAndFooterLayout;visitWidgets(Ljava/util/function/Consumer;)V",
            ordinal = 0
    ))
    private void cancelAddDrawableChild(HeaderAndFooterLayout instance, Consumer<AbstractWidget> consumer) {
    }

    @Inject(method = "init", at = @At(value = "RETURN"))
    private void calculateWindowSizeAndPositionOnInit(CallbackInfo ci) {
        AdvancementsScreen screen = (AdvancementsScreen) (Object) this;
        calculateWindowSizeAndPosition(screen.width, screen.height);
    }
}
