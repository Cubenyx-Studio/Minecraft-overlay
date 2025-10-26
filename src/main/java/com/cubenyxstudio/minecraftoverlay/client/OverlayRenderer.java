package com.cubenyxstudio.minecraftoverlay.client;

import com.mojang.blaze3d.systems.RenderSystem;
import com.cubenyxstudio.minecraftoverlay.Config;
import com.cubenyxstudio.minecraftoverlay.MinecraftOverlay;
import com.cubenyxstudio.minecraftoverlay.client.screens.BrowserScreen;
import com.cubenyxstudio.minecraftoverlay.client.screens.SettingsScreen;
import com.cubenyxstudio.minecraftoverlay.client.screens.StopwatchScreen;
import com.cubenyxstudio.minecraftoverlay.client.screens.TimerScreen;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.core.BlockPos;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RenderGuiLayerEvent;
import net.neoforged.neoforge.client.event.ScreenEvent;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * Renders the Steam-like overlay on the Minecraft screen
 */
@EventBusSubscriber(modid = MinecraftOverlay.MODID, bus = EventBusSubscriber.Bus.GAME, value = Dist.CLIENT)
public class OverlayRenderer {

    private static boolean overlayVisible = false;
    private static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("HH:mm:ss");
    private static final List<OverlayButton> buttons = new ArrayList<>();

    // Enum for button types
    private enum ButtonType {
        BROWSER("Browser", "🌐"),
        TIMER("Timer", "⏱"),
        STOPWATCH("Chrono", "⏲"),
        SETTINGS("Settings", "⚙");

        final String label;
        final String icon;

        ButtonType(String label, String icon) {
            this.label = label;
            this.icon = icon;
        }
    }

    // Simple button class
    private static class OverlayButton {
        final ButtonType type;
        int x, y, width, height;

        OverlayButton(ButtonType type) {
            this.type = type;
            this.width = 100;
            this.height = 20;
        }

        void updatePosition(int x, int y) {
            this.x = x;
            this.y = y;
        }

        boolean isMouseOver(double mouseX, double mouseY) {
            return mouseX >= x && mouseX <= x + width && mouseY >= y && mouseY <= y + height;
        }

        void render(GuiGraphics guiGraphics, Minecraft minecraft, boolean hovered) {
            // Button background
            int bgColor = hovered ? 0x80505050 : 0x80303030;
            guiGraphics.fill(x, y, x + width, y + height, bgColor);

            // Button border
            int borderColor = hovered ? 0xFFFFFFFF : 0xFF808080;
            guiGraphics.fill(x, y, x + width, y + 1, borderColor); // Top
            guiGraphics.fill(x, y + height - 1, x + width, y + height, borderColor); // Bottom
            guiGraphics.fill(x, y, x + 1, y + height, borderColor); // Left
            guiGraphics.fill(x + width - 1, y, x + width, y + height, borderColor); // Right

            // Button text
            String text = type.icon + " " + type.label;
            int textWidth = minecraft.font.width(text);
            int textX = x + (width - textWidth) / 2;
            int textY = y + (height - 8) / 2;
            guiGraphics.drawString(minecraft.font, text, textX, textY, hovered ? 0xFFFFFF : 0xCCCCCC);
        }

        void onClick(Minecraft minecraft) {
            if (minecraft.player == null) return;

            // Close the overlay first
            overlayVisible = false;

            switch (type) {
                case BROWSER:
                    minecraft.setScreen(new BrowserScreen(null));
                    break;
                case TIMER:
                    minecraft.setScreen(new TimerScreen(null));
                    break;
                case STOPWATCH:
                    minecraft.setScreen(new StopwatchScreen(null));
                    break;
                case SETTINGS:
                    minecraft.setScreen(new SettingsScreen(null));
                    break;
            }
        }
    }

    public static void toggleOverlay() {
        overlayVisible = !overlayVisible;
        if (overlayVisible) {
            initButtons();
        }
    }

    private static void initButtons() {
        buttons.clear();
        buttons.add(new OverlayButton(ButtonType.BROWSER));
        buttons.add(new OverlayButton(ButtonType.TIMER));
        buttons.add(new OverlayButton(ButtonType.STOPWATCH));
        buttons.add(new OverlayButton(ButtonType.SETTINGS));
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

        // ===== BOTTOM CENTER - BUTTONS =====
        renderButtons(guiGraphics, screenWidth, screenHeight, minecraft);

        RenderSystem.disableBlend();
    }

    private static void renderButtons(GuiGraphics guiGraphics, int screenWidth, int screenHeight, Minecraft minecraft) {
        int buttonSpacing = 10;
        int totalButtonWidth = buttons.size() * 100 + (buttons.size() - 1) * buttonSpacing;
        int startX = (screenWidth - totalButtonWidth) / 2;
        int buttonY = screenHeight - 35; // 35 pixels from bottom

        double mouseX = minecraft.mouseHandler.xpos() * screenWidth / minecraft.getWindow().getScreenWidth();
        double mouseY = minecraft.mouseHandler.ypos() * screenHeight / minecraft.getWindow().getScreenHeight();

        for (int i = 0; i < buttons.size(); i++) {
            OverlayButton button = buttons.get(i);
            button.updatePosition(startX + i * (100 + buttonSpacing), buttonY);
            boolean hovered = button.isMouseOver(mouseX, mouseY);
            button.render(guiGraphics, minecraft, hovered);
        }
    }

    @SubscribeEvent
    public static void onMouseClick(ScreenEvent.MouseButtonPressed.Pre event) {
        if (!overlayVisible || event.getButton() != 0) { // 0 = left click
            return;
        }

        Minecraft minecraft = Minecraft.getInstance();
        if (minecraft.player == null) {
            return;
        }

        double mouseX = event.getMouseX();
        double mouseY = event.getMouseY();

        for (OverlayButton button : buttons) {
            if (button.isMouseOver(mouseX, mouseY)) {
                button.onClick(minecraft);
                event.setCanceled(true);
                return;
            }
        }
    }
}

