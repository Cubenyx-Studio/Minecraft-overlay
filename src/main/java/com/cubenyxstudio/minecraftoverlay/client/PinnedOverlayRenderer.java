package com.cubenyxstudio.minecraftoverlay.client;

import com.cubenyxstudio.minecraftoverlay.Config;
import com.cubenyxstudio.minecraftoverlay.MinecraftOverlay;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RenderGuiEvent;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * Renders pinned information on screen permanently (even when overlay is closed)
 */
@EventBusSubscriber(modid = MinecraftOverlay.MODID, bus = EventBusSubscriber.Bus.GAME, value = Dist.CLIENT)
public class PinnedOverlayRenderer {

    private static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("HH:mm:ss");

    @SubscribeEvent
    public static void onRenderGui(RenderGuiEvent.Post event) {
        Minecraft minecraft = Minecraft.getInstance();

        // Don't render if overlay is disabled or player is null
        if (!Config.overlayEnabled || minecraft.player == null) {
            return;
        }

        GuiGraphics guiGraphics = event.getGuiGraphics();
        int padding = 10;
        int lineHeight = 12;
        int pinnedColor = 0xFFFFD700; // Gold color for pinned items

        // ===== TOP LEFT CORNER =====
        int leftX = padding;
        int leftY = padding;

        // Pin Real Time
        if (Config.pinRealTime && Config.showRealTime) {
            String currentTime = LocalTime.now().format(TIME_FORMATTER);
            guiGraphics.drawString(minecraft.font,
                    Component.translatable("overlay.time", currentTime),
                    leftX, leftY, pinnedColor);
            leftY += lineHeight;
        }

        // Pin FPS
        if (Config.pinFPS && Config.showFPS) {
            guiGraphics.drawString(minecraft.font,
                    Component.translatable("overlay.fps", minecraft.getFps()),
                    leftX, leftY, pinnedColor);
            leftY += lineHeight;
        }

        // Pin Play Time
        if (Config.pinPlayTime && Config.showPlayTime) {
            leftY += 3; // Small gap

            guiGraphics.drawString(minecraft.font,
                    Component.translatable("overlay.instance_time", OverlayState.formatPlayTime(OverlayState.getInstancePlayTime())),
                    leftX, leftY, pinnedColor);
            leftY += lineHeight;

            if (OverlayState.getWorldPlayTime() > 0) {
                String worldName = OverlayState.getCurrentWorldName();
                if (worldName.length() > 20) {
                    worldName = worldName.substring(0, 17) + "...";
                }

                guiGraphics.drawString(minecraft.font,
                        Component.translatable("overlay.world_time",
                                OverlayState.formatPlayTime(OverlayState.getWorldPlayTime()), worldName),
                        leftX, leftY, pinnedColor);
                leftY += lineHeight;
            }

            if (OverlayState.getDimensionPlayTime() > 0) {
                String dimensionName = OverlayState.getCurrentDimensionName();

                guiGraphics.drawString(minecraft.font,
                        Component.translatable("overlay.dimension_time",
                                OverlayState.formatPlayTime(OverlayState.getDimensionPlayTime()), dimensionName),
                        leftX, leftY, pinnedColor);
            }
        }

        // ===== TOP RIGHT CORNER =====
        int rightY = padding;
        int screenWidth = minecraft.getWindow().getGuiScaledWidth();

        // Pin Coordinates
        if (Config.pinCoordinates && Config.showCoordinates) {
            BlockPos pos = minecraft.player.blockPosition();
            Component coords = Component.translatable("overlay.coordinates", pos.getX(), pos.getY(), pos.getZ());
            int coordsWidth = minecraft.font.width(coords);
            guiGraphics.drawString(minecraft.font, coords, screenWidth - coordsWidth - padding, rightY, pinnedColor);
            rightY += lineHeight;
        }

        // Pin Dimension
        if (Config.pinDimension && Config.showDimension) {
            String dimPath = minecraft.player.level().dimension().location().getPath();
            Component dimension = Component.translatable("overlay.dimension", dimPath);
            int dimWidth = minecraft.font.width(dimension);
            guiGraphics.drawString(minecraft.font, dimension, screenWidth - dimWidth - padding, rightY, pinnedColor);
            rightY += lineHeight;
        }

        // ===== TOP CENTER =====
        int centerY = padding;

        // Pin Health
        if (Config.pinHealth && Config.showHealth) {
            Component health = Component.translatable("overlay.health",
                    String.format("%.1f", minecraft.player.getHealth()),
                    String.format("%.1f", minecraft.player.getMaxHealth()));
            int healthWidth = minecraft.font.width(health);
            int healthX = (screenWidth - healthWidth) / 2;
            guiGraphics.drawString(minecraft.font, health, healthX, centerY, pinnedColor);
        }
    }
}
