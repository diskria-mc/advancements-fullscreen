package io.github.diskria.advancements_fullscreen.mixins.client;

import io.github.diskria.advancements_fullscreen.extensions.AdvancementsScreenExtension;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.Screen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Screen.class)
public class ScreenMixin {

    @Inject(
        method = "resize",
        at = @At(value = "HEAD")
    )
    private void resizeInAdvancementsScreen(Minecraft minecraft, int width, int height, CallbackInfo ci) {
        Screen screen = (Screen) (Object) this;
        if (screen instanceof AdvancementsScreenExtension advancementsScreenExtension) {
            advancementsScreenExtension.advancements_fullscreen_resize(minecraft, width, height);
        }
    }
}
