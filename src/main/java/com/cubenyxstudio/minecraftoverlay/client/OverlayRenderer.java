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

        int padding = 10;
        int lineHeight = 12;
        int textColor = 0xFFFFFF;
        int shadowColor = 0x000000;

        // ===== TOP LEFT CORNER =====
        int leftX = padding;
        int leftY = padding;

        // Time
        String currentTime = "Time: " + LocalTime.now().format(TIME_FORMATTER);
        guiGraphics.drawString(minecraft.font, currentTime, leftX, leftY, textColor);
        leftY += lineHeight;

        // FPS
        String fps = "FPS: " + minecraft.getFps();
        guiGraphics.drawString(minecraft.font, fps, leftX, leftY, textColor);

        // ===== TOP RIGHT CORNER =====
        if (minecraft.player != null) {
            int rightY = padding;

            // Position
            BlockPos pos = minecraft.player.blockPosition();
            String coords = String.format("X: %d, Y: %d, Z: %d", pos.getX(), pos.getY(), pos.getZ());
            int coordsWidth = minecraft.font.width(coords);
            guiGraphics.drawString(minecraft.font, coords, screenWidth - coordsWidth - padding, rightY, textColor);
            rightY += lineHeight;

            // Dimension
            String dimension = "Dimension: " + minecraft.player.level().dimension().location().getPath();
            int dimWidth = minecraft.font.width(dimension);
            guiGraphics.drawString(minecraft.font, dimension, screenWidth - dimWidth - padding, rightY, textColor);
        }

        // ===== TOP CENTER =====
        if (minecraft.player != null) {
            int centerY = padding;

            // Health
            String health = String.format("Health: %.1f / %.1f", minecraft.player.getHealth(), minecraft.player.getMaxHealth());
            int healthWidth = minecraft.font.width(health);
            int healthX = (screenWidth - healthWidth) / 2;
            guiGraphics.drawString(minecraft.font, health, healthX, centerY, textColor);
            centerY += lineHeight;

            // Instructions
            String instruction = "Press Shift+Tab to close";
            int instructWidth = minecraft.font.width(instruction);
            int instructX = (screenWidth - instructWidth) / 2;
            guiGraphics.drawString(minecraft.font, instruction, instructX, centerY, 0xAAAAAA);
        }

        RenderSystem.disableBlend();
    }
}

