package io.github.diskria.advancements_fullscreen.mixins.client;

import com.llamalad7.mixinextras.sugar.Local;
import io.github.diskria.advancements_fullscreen.extensions.AdvancementsScreenExtension;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.advancements.AdvancementTabType;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;

import static net.minecraft.client.gui.screens.advancements.AdvancementsScreen.WINDOW_HEIGHT;
import static net.minecraft.client.gui.screens.advancements.AdvancementsScreen.WINDOW_WIDTH;

@Mixin(AdvancementTabType.class)
public class AdvancementTabTypeMixin {

    @Unique
    private Screen getCurrentScreen() {
        return Minecraft.getInstance().screen;
    }

    @ModifyConstant(
            method = "getX",
            constant = @Constant(
                    intValue = WINDOW_WIDTH - 4,
                    ordinal = 0
            )
    )
    public int calculateTabXForFullscreenAtRightTabType(int originalValue) {
        if (getCurrentScreen() instanceof AdvancementsScreenExtension advancementsScreenExtension) {
            return advancementsScreenExtension.advancements_fullscreen_getWindowWidth(true) - 4;
        }
        return originalValue;
    }

    @ModifyConstant(
            method = "getY",
            constant = @Constant(
                    intValue = WINDOW_HEIGHT - 4,
                    ordinal = 0
            )
    )
    public int calculateTabYForFullscreenAtBelowTabType(int originalValue) {
        if (getCurrentScreen() instanceof AdvancementsScreenExtension advancementsScreenExtension) {
            return advancementsScreenExtension.advancements_fullscreen_getWindowHeight(true) - 4;
        }
        return originalValue;
    }

    @ModifyArgs(
            method = "draw",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/gui/GuiGraphics;blitSprite(Lcom/mojang/blaze3d/pipeline/RenderPipeline;Lnet/minecraft/resources/ResourceLocation;IIII)V"
            )
    )
    public void fixTabTexture(
            @NotNull Args args,
            @Local(argsOnly = true) boolean isSelected,
            @Local(ordinal = 0) @NotNull AdvancementTabType.Sprites textures
    ) {
        Screen currentScreen = getCurrentScreen();
        if (currentScreen instanceof AdvancementsScreenExtension advancementsScreenExtension) {
            AdvancementTabType tabType = (AdvancementTabType) (Object) this;
            int tabLeft = args.get(2);
            int tabTop = args.get(3);
            int tabWidth = args.get(4);
            int tabHeight = args.get(5);
            int tabRight = tabLeft + tabWidth;
            int tabBottom = tabTop + tabHeight;
            int windowLeft = advancementsScreenExtension.advancements_fullscreen_getWindowHorizontalMargin();
            int windowTop = advancementsScreenExtension.advancements_fullscreen_getWindowVerticalMargin();
            int windowRight = currentScreen.width - windowLeft;
            int windowBottom = currentScreen.height - windowTop;
            boolean isFirst;
            boolean isLast;
            if (tabType == AdvancementTabType.ABOVE || tabType == AdvancementTabType.BELOW) {
                isFirst = tabLeft == windowLeft;
                isLast = tabRight == windowRight;
            } else {
                isFirst = tabTop == windowTop;
                isLast = tabBottom == windowBottom;
            }
            ResourceLocation texture;
            if (isFirst) {
                texture = textures.first();
            } else if (isLast) {
                texture = textures.last();
            } else {
                texture = textures.middle();
            }
            args.set(1, texture);
        }
    }
}
