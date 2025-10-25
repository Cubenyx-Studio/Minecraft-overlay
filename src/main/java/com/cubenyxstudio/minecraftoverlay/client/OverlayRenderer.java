package com.cubenyxstudio.minecraftoverlay.client;

import com.mojang.blaze3d.systems.RenderSystem;
import com.cubenyxstudio.minecraftoverlay.Config;
import com.cubenyxstudio.minecraftoverlay.MinecraftOverlay;
import net.minecraft.client.DeltaTracker;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RenderGuiLayerEvent;

/**
 * Renders the Steam-like overlay on the Minecraft screen
 */
@EventBusSubscriber(modid = MinecraftOverlay.MODID, bus = EventBusSubscriber.Bus.GAME, value = Dist.CLIENT)
public class OverlayRenderer {

    @SubscribeEvent
    public static void onRenderGuiOverlay(RenderGuiLayerEvent.Post event) {
        if (!Config.overlayEnabled) {
            return;
        }

        Minecraft minecraft = Minecraft.getInstance();
        if (minecraft.player == null) {
            return;
        }

        GuiGraphics guiGraphics = event.getGuiGraphics();
        int screenWidth = minecraft.getWindow().getGuiScaledWidth();
        int screenHeight = minecraft.getWindow().getGuiScaledHeight();

        // Basic overlay rendering - will be expanded later
        renderBasicOverlay(guiGraphics, screenWidth, screenHeight);
    }

    private static void renderBasicOverlay(GuiGraphics guiGraphics, int screenWidth, int screenHeight) {
        RenderSystem.enableBlend();
        RenderSystem.defaultBlendFunc();

        // Calculate opacity from config (0-100 to 0.0-1.0)
        float alpha = Config.overlayOpacity / 100.0f;

        // Draw a simple overlay panel as a placeholder
        int panelWidth = 200;
        int panelHeight = 100;
        int x = 10;
        int y = 10;

        // Position based on config
        switch (Config.overlayPosition.toUpperCase()) {
            case "TOP_RIGHT":
                x = screenWidth - panelWidth - 10;
                y = 10;
                break;
            case "BOTTOM_LEFT":
                x = 10;
                y = screenHeight - panelHeight - 10;
                break;
            case "BOTTOM_RIGHT":
                x = screenWidth - panelWidth - 10;
                y = screenHeight - panelHeight - 10;
                break;
            default: // TOP_LEFT
                x = 10;
                y = 10;
                break;
        }

        // Draw semi-transparent background (dark gray with alpha)
        int backgroundColor = (int)(alpha * 255) << 24 | 0x1a1a1a;
        guiGraphics.fill(x, y, x + panelWidth, y + panelHeight, backgroundColor);

        // Draw border
        int borderColor = (int)(alpha * 255) << 24 | 0x3a3a3a;
        guiGraphics.fill(x, y, x + panelWidth, y + 2, borderColor); // Top
        guiGraphics.fill(x, y + panelHeight - 2, x + panelWidth, y + panelHeight, borderColor); // Bottom
        guiGraphics.fill(x, y, x + 2, y + panelHeight, borderColor); // Left
        guiGraphics.fill(x + panelWidth - 2, y, x + panelWidth, y + panelHeight, borderColor); // Right

        // Draw title text
        guiGraphics.drawString(
            Minecraft.getInstance().font,
            "Minecraft Overlay",
            x + 10,
            y + 10,
            0xFFFFFF
        );

        // Draw placeholder text
        guiGraphics.drawString(
            Minecraft.getInstance().font,
            "Steam-like overlay",
            x + 10,
            y + 30,
            0xAAAAAA
        );

        RenderSystem.disableBlend();
    }
}

