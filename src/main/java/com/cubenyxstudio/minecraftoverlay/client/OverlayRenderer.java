package com.cubenyxstudio.minecraftoverlay.client;

import com.mojang.blaze3d.systems.RenderSystem;
import com.cubenyxstudio.minecraftoverlay.Config;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.Minecraft;

/**
 * Renders pinned elements on the HUD
 */
public class OverlayRenderer {

    public static void register() {
        HudRenderCallback.EVENT.register((guiGraphics, tickDelta) -> {
            if (!Config.overlayEnabled) {
                return;
            }

            Minecraft minecraft = Minecraft.getInstance();
            if (minecraft.player == null) {
                return;
            }

            RenderSystem.enableBlend();
            RenderSystem.defaultBlendFunc();

            // This renderer is only used for the pinned overlay display
            // The actual overlay screen is handled by OverlayScreen.java

            RenderSystem.disableBlend();
        });
    }
}

