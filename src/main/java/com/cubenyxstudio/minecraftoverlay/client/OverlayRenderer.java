package com.cubenyxstudio.minecraftoverlay.client;

import com.mojang.blaze3d.systems.RenderSystem;
import com.cubenyxstudio.minecraftoverlay.Config;
import com.cubenyxstudio.minecraftoverlay.MinecraftOverlay;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.core.BlockPos;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RenderGuiLayerEvent;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * Renders the Steam-like overlay on the Minecraft screen
 */
@EventBusSubscriber(modid = MinecraftOverlay.MODID, bus = EventBusSubscriber.Bus.GAME, value = Dist.CLIENT)
public class OverlayRenderer {

    private static boolean overlayVisible = false;
    private static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("HH:mm:ss");

    public static void toggleOverlay() {
        overlayVisible = !overlayVisible;
    }

    public static boolean isOverlayVisible() {
        return overlayVisible;
    }

    @SubscribeEvent
    public static void onRenderGuiOverlay(RenderGuiLayerEvent.Post event) {
        if (!Config.overlayEnabled || !overlayVisible) {
            return;
        }

        Minecraft minecraft = Minecraft.getInstance();
        if (minecraft.player == null) {
            return;
        }

        GuiGraphics guiGraphics = event.getGuiGraphics();
        int screenWidth = minecraft.getWindow().getGuiScaledWidth();
        int screenHeight = minecraft.getWindow().getGuiScaledHeight();

        // Render Steam-like overlay
        renderSteamOverlay(guiGraphics, screenWidth, screenHeight, minecraft);
    }

    private static void renderSteamOverlay(GuiGraphics guiGraphics, int screenWidth, int screenHeight, Minecraft minecraft) {
        RenderSystem.enableBlend();
        RenderSystem.defaultBlendFunc();

        // Draw full screen semi-transparent gray overlay (like Steam)
        int overlayColor = 0x80000000; // 50% transparent black
        guiGraphics.fill(0, 0, screenWidth, screenHeight, overlayColor);

        // Panel dimensions
        int panelWidth = 300;
        int panelHeight = 200;
        int panelX = (screenWidth - panelWidth) / 2;
        int panelY = (screenHeight - panelHeight) / 2;

        // Draw panel background
        int panelBg = 0xE0171717; // Dark background with high opacity
        guiGraphics.fill(panelX, panelY, panelX + panelWidth, panelY + panelHeight, panelBg);

        // Draw panel border
        int borderColor = 0xFF3a7ebf; // Steam-like blue border
        guiGraphics.fill(panelX - 2, panelY - 2, panelX + panelWidth + 2, panelY, borderColor); // Top
        guiGraphics.fill(panelX - 2, panelY + panelHeight, panelX + panelWidth + 2, panelY + panelHeight + 2, borderColor); // Bottom
        guiGraphics.fill(panelX - 2, panelY, panelX, panelY + panelHeight, borderColor); // Left
        guiGraphics.fill(panelX + panelWidth, panelY, panelX + panelWidth + 2, panelY + panelHeight, borderColor); // Right

        // Title
        String title = "Minecraft Overlay";
        int titleX = panelX + (panelWidth - minecraft.font.width(title)) / 2;
        guiGraphics.drawString(minecraft.font, title, titleX, panelY + 15, 0xFFFFFF);

        // Separator line
        guiGraphics.fill(panelX + 10, panelY + 35, panelX + panelWidth - 10, panelY + 37, 0xFF3a7ebf);

        // Current time
        String currentTime = "Time: " + LocalTime.now().format(TIME_FORMATTER);
        guiGraphics.drawString(minecraft.font, currentTime, panelX + 20, panelY + 50, 0xCCCCCC);

        // Player coordinates
        if (minecraft.player != null) {
            BlockPos pos = minecraft.player.blockPosition();
            String coords = String.format("Position: X: %d, Y: %d, Z: %d", pos.getX(), pos.getY(), pos.getZ());
            guiGraphics.drawString(minecraft.font, coords, panelX + 20, panelY + 70, 0xCCCCCC);

            // Dimension
            String dimension = "Dimension: " + minecraft.player.level().dimension().location().getPath();
            guiGraphics.drawString(minecraft.font, dimension, panelX + 20, panelY + 90, 0xCCCCCC);

            // FPS
            String fps = "FPS: " + minecraft.getFps();
            guiGraphics.drawString(minecraft.font, fps, panelX + 20, panelY + 110, 0xCCCCCC);

            // Health
            String health = String.format("Health: %.1f / %.1f", minecraft.player.getHealth(), minecraft.player.getMaxHealth());
            guiGraphics.drawString(minecraft.font, health, panelX + 20, panelY + 130, 0xCCCCCC);
        }

        // Instructions
        String instruction = "Press Shift+Tab to close";
        int instructX = panelX + (panelWidth - minecraft.font.width(instruction)) / 2;
        guiGraphics.drawString(minecraft.font, instruction, instructX, panelY + panelHeight - 25, 0x888888);

        RenderSystem.disableBlend();
    }
}

