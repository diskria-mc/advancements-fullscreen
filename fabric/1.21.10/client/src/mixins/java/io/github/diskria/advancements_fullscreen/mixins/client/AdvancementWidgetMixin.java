package io.github.diskria.advancements_fullscreen.mixins.client;

import io.github.diskria.advancements_fullscreen.extensions.AdvancementsScreenExtension;
import net.minecraft.client.gui.screens.advancements.AdvancementTab;
import net.minecraft.client.gui.screens.advancements.AdvancementWidget;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

import static net.minecraft.client.gui.screens.advancements.AdvancementsScreen.WINDOW_INSIDE_HEIGHT;

@Mixin(AdvancementWidget.class)
public class AdvancementWidgetMixin {

    @Shadow
    @Final
    private AdvancementTab tab;

    @ModifyConstant(method = "drawHover", constant = @Constant(intValue = WINDOW_INSIDE_HEIGHT, ordinal = 0))
    public int drawTooltipModifyHeight(int originalValue) {
        if (tab.getScreen() instanceof AdvancementsScreenExtension screenImpl) {
            return screenImpl.advancements_fullscreen_getWindowHeight(false);
        }
        return originalValue;
    }
}
