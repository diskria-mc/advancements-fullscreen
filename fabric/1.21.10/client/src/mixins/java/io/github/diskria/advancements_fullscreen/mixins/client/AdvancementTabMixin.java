package io.github.diskria.advancements_fullscreen.mixins.client;

import io.github.diskria.advancements_fullscreen.extensions.AdvancementsScreenExtension;
import net.minecraft.client.gui.screens.advancements.AdvancementTab;
import net.minecraft.client.gui.screens.advancements.AdvancementsScreen;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

import static net.minecraft.client.gui.screens.advancements.AdvancementsScreen.*;

@Mixin(AdvancementTab.class)
public class AdvancementTabMixin {

    @Shadow
    @Final
    private AdvancementsScreen screen;

    @ModifyConstant(method = "scroll", constant = @Constant(intValue = WINDOW_INSIDE_WIDTH, ordinal = 0))
    private int calculateMoveLimitByX(int originalValue) {
        if (screen instanceof AdvancementsScreenExtension advancementsScreenExtension) {
            return advancementsScreenExtension.advancements_fullscreen_getWindowWidth(false);
        }
        return originalValue;
    }

    @ModifyConstant(method = "scroll", constant = @Constant(intValue = WINDOW_INSIDE_HEIGHT, ordinal = 0))
    private int calculateMoveLimitByY(int originalValue) {
        if (screen instanceof AdvancementsScreenExtension advancementsScreenExtension) {
            return advancementsScreenExtension.advancements_fullscreen_getWindowHeight(false);
        }
        return originalValue;
    }

    @ModifyConstant(method = "scroll", constant = @Constant(intValue = WINDOW_INSIDE_WIDTH, ordinal = 1))
    private int calculateMoveMinimumX(int originalValue) {
        if (screen instanceof AdvancementsScreenExtension advancementsScreenExtension) {
            return advancementsScreenExtension.advancements_fullscreen_getWindowWidth(false);
        }
        return originalValue;
    }

    @ModifyConstant(method = "scroll", constant = @Constant(intValue = WINDOW_INSIDE_HEIGHT, ordinal = 1))
    private int calculateMoveMinimumY(int originalValue) {
        if (screen instanceof AdvancementsScreenExtension advancementsScreenExtension) {
            return advancementsScreenExtension.advancements_fullscreen_getWindowHeight(false);
        }
        return originalValue;
    }

    @ModifyConstant(method = "drawTooltips", constant = @Constant(intValue = WINDOW_INSIDE_WIDTH, ordinal = 0))
    private int calculateWidthForTooltipDim(int originalValue) {
        if (screen instanceof AdvancementsScreenExtension advancementsScreenExtension) {
            return advancementsScreenExtension.advancements_fullscreen_getWindowWidth(false);
        }
        return originalValue;
    }

    @ModifyConstant(method = "drawTooltips", constant = @Constant(intValue = WINDOW_INSIDE_HEIGHT, ordinal = 0))
    private int calculateHeightForTooltipDim(int originalValue) {
        if (screen instanceof AdvancementsScreenExtension advancementsScreenExtension) {
            return advancementsScreenExtension.advancements_fullscreen_getWindowHeight(false);
        }
        return originalValue;
    }

    @ModifyConstant(method = "drawTooltips", constant = @Constant(intValue = WINDOW_INSIDE_WIDTH, ordinal = 1))
    private int calculateWidthForWidgetHoverCheck(int originalValue) {
        if (screen instanceof AdvancementsScreenExtension advancementsScreenExtension) {
            return advancementsScreenExtension.advancements_fullscreen_getWindowWidth(false);
        }
        return originalValue;
    }

    @ModifyConstant(method = "drawTooltips", constant = @Constant(intValue = WINDOW_INSIDE_HEIGHT, ordinal = 1))
    private int calculateHeightForWidgetHoverCheck(int originalValue) {
        if (screen instanceof AdvancementsScreenExtension advancementsScreenExtension) {
            return advancementsScreenExtension.advancements_fullscreen_getWindowHeight(false);
        }
        return originalValue;
    }

    @ModifyConstant(method = "drawContents", constant = @Constant(intValue = WINDOW_INSIDE_WIDTH, ordinal = 0))
    private int drawFullscreenBackgroundByWidth(int originalValue) {
        if (screen instanceof AdvancementsScreenExtension advancementsScreenExtension) {
            return advancementsScreenExtension.advancements_fullscreen_getWindowWidth(false);
        }
        return originalValue;
    }

    @ModifyConstant(method = "drawContents", constant = @Constant(intValue = WINDOW_INSIDE_HEIGHT, ordinal = 0))
    private int drawFullscreenBackgroundByHeight(int originalValue) {
        if (screen instanceof AdvancementsScreenExtension advancementsScreenExtension) {
            return advancementsScreenExtension.advancements_fullscreen_getWindowHeight(false);
        }
        return originalValue;
    }

    @ModifyConstant(method = "drawContents", constant = @Constant(intValue = WINDOW_INSIDE_WIDTH / 2, ordinal = 0))
    private int calculateWidthForOriginX(int originalValue) {
        if (screen instanceof AdvancementsScreenExtension advancementsScreenExtension) {
            return advancementsScreenExtension.advancements_fullscreen_getWindowWidth(false) / 2;
        }
        return originalValue;
    }

    @ModifyConstant(method = "drawContents", constant = @Constant(intValue = WINDOW_INSIDE_HEIGHT / 2, ordinal = 0))
    private int calculateHeightForOriginY(int originalValue) {
        if (screen instanceof AdvancementsScreenExtension advancementsScreenExtension) {
            return advancementsScreenExtension.advancements_fullscreen_getWindowHeight(false) / 2;
        }
        return originalValue;
    }

    @ModifyConstant(method = "drawContents", constant = @Constant(intValue = BACKGROUND_TILE_COUNT_X + 1, ordinal = 0))
    private int calculateWidthForBackgroundGridColumnsCount(int originalValue) {
        if (screen instanceof AdvancementsScreenExtension advancementsScreenExtension) {
            return advancementsScreenExtension.advancements_fullscreen_getWindowWidth(true) / 16 + 1;
        }
        return originalValue;
    }

    @ModifyConstant(method = "drawContents", constant = @Constant(intValue = BACKGROUND_TILE_COUNT_Y + 1, ordinal = 0))
    private int calculateHeightForBackgroundGridRowsCount(int originalValue) {
        if (screen instanceof AdvancementsScreenExtension advancementsScreenExtension) {
            return advancementsScreenExtension.advancements_fullscreen_getWindowHeight(true) / 16 + 1;
        }
        return originalValue;
    }
}
