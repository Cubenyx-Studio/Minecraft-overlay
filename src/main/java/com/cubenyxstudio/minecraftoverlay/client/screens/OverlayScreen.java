package com.cubenyxstudio.minecraftoverlay.client.screens;

import com.mojang.blaze3d.systems.RenderSystem;
import com.cubenyxstudio.minecraftoverlay.Config;
import com.cubenyxstudio.minecraftoverlay.client.OverlayState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * Main overlay screen that pauses the game
 */
public class OverlayScreen extends Screen {
    private static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("HH:mm:ss");
    private final List<OverlayButton> buttons = new ArrayList<>();

    private enum ButtonType {
        BROWSER("Browser", "🌐"),
        TIMER("Timer", "⏱"),
        PIN("Pin", "📌"),
        STOPWATCH("Chrono", "⏲"),
        SETTINGS("Settings", "⚙");

        final String label;
        final String icon;

        ButtonType(String label, String icon) {
            this.label = label;
            this.icon = icon;
        }
    }

    private static class OverlayButton {
        final ButtonType type;
        int x, y, width, height;

        OverlayButton(ButtonType type) {
            this.type = type;
            this.width = 80;  // Réduit de 100 à 80
            this.height = 18; // Réduit de 20 à 18
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
            guiGraphics.fill(x, y, x + width, y + 1, borderColor);
            guiGraphics.fill(x, y + height - 1, x + width, y + height, borderColor);
            guiGraphics.fill(x, y, x + 1, y + height, borderColor);
            guiGraphics.fill(x + width - 1, y, x + width, y + height, borderColor);

            // Button text
            String text = type.icon + " " + type.label;
            int textWidth = minecraft.font.width(text);
            int textX = x + (width - textWidth) / 2;
            int textY = y + (height - 8) / 2;
            guiGraphics.drawString(minecraft.font, text, textX, textY, hovered ? 0xFFFFFF : 0xCCCCCC);
        }
    }

    public OverlayScreen() {
        super(Component.translatable("overlay.screen.overlay.title"));
        initButtons();
    }

    private void initButtons() {
        buttons.clear();
        buttons.add(new OverlayButton(ButtonType.BROWSER));
        buttons.add(new OverlayButton(ButtonType.TIMER));
        buttons.add(new OverlayButton(ButtonType.PIN));
        buttons.add(new OverlayButton(ButtonType.STOPWATCH));
        buttons.add(new OverlayButton(ButtonType.SETTINGS));
    }

    @Override
    protected void init() {
        super.init();
    }

    @Override
    public void tick() {
        super.tick();
        // Continue ticking timer even when overlay is open
        OverlayState.tickTimer();
    }

    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
        // D'abord appeler super.render pour le fond grisé par défaut de Minecraft
        super.render(guiGraphics, mouseX, mouseY, partialTick);

        // Ensuite dessiner notre overlay PAR DESSUS
        RenderSystem.enableBlend();
        RenderSystem.defaultBlendFunc();

        int padding = 10;
        int lineHeight = 12;
        int textColor = 0xFFFFFF;

        // ===== TOP LEFT CORNER =====
        int leftX = padding;
        int leftY = padding;

        // Show real time (if enabled)
        if (Config.showRealTime) {
            String currentTime = "Time: " + LocalTime.now().format(TIME_FORMATTER);
            guiGraphics.drawString(this.font, currentTime, leftX, leftY, textColor);
            leftY += lineHeight;
        }

        // Show FPS (if enabled)
        if (Config.showFPS) {
            String fps = "FPS: " + this.minecraft.getFps();
            guiGraphics.drawString(this.font, fps, leftX, leftY, textColor);
            leftY += lineHeight;
        }

        // Show play time information (if enabled)
        if (Config.showPlayTime) {
            leftY += 3; // Small gap

            String instanceTime = "Instance: " + OverlayState.formatPlayTime(OverlayState.getInstancePlayTime());
            guiGraphics.drawString(this.font, instanceTime, leftX, leftY, 0xFFD700); // Gold color
            leftY += lineHeight;

            if (OverlayState.getWorldPlayTime() > 0) {
                String worldName = OverlayState.getCurrentWorldName();
                if (worldName.length() > 20) {
                    worldName = worldName.substring(0, 17) + "...";
                }
                String worldTime = "World: " + OverlayState.formatPlayTime(OverlayState.getWorldPlayTime());
                if (!worldName.isEmpty()) {
                    worldTime += " (" + worldName + ")";
                }
                guiGraphics.drawString(this.font, worldTime, leftX, leftY, 0x00FF00); // Green color
                leftY += lineHeight;
            }

            if (OverlayState.getDimensionPlayTime() > 0) {
                String dimensionName = OverlayState.getCurrentDimensionName();
                String dimTime = "Dimension: " + OverlayState.formatPlayTime(OverlayState.getDimensionPlayTime());
                if (!dimensionName.isEmpty()) {
                    dimTime += " (" + dimensionName + ")";
                }
                guiGraphics.drawString(this.font, dimTime, leftX, leftY, 0x00FFFF); // Cyan color
                leftY += lineHeight;
            }
        }

        // ===== TOP RIGHT CORNER =====
        if (this.minecraft.player != null) {
            int rightY = padding;

            // Show coordinates (if enabled)
            if (Config.showCoordinates) {
                BlockPos pos = this.minecraft.player.blockPosition();
                String coords = String.format("X: %d, Y: %d, Z: %d", pos.getX(), pos.getY(), pos.getZ());
                int coordsWidth = this.font.width(coords);
                guiGraphics.drawString(this.font, coords, this.width - coordsWidth - padding, rightY, textColor);
                rightY += lineHeight;
            }

            // Show dimension (if enabled)
            if (Config.showDimension) {
                String dimension = "Dimension: " + this.minecraft.player.level().dimension().location().getPath();
                int dimWidth = this.font.width(dimension);
                guiGraphics.drawString(this.font, dimension, this.width - dimWidth - padding, rightY, textColor);
            }
        }

        // ===== TOP CENTER =====
        if (this.minecraft.player != null) {
            int centerY = padding;

            // Show health (if enabled)
            if (Config.showHealth) {
                String health = String.format("Health: %.1f / %.1f", this.minecraft.player.getHealth(), this.minecraft.player.getMaxHealth());
                int healthWidth = this.font.width(health);
                int healthX = (this.width - healthWidth) / 2;
                guiGraphics.drawString(this.font, health, healthX, centerY, textColor);
                centerY += lineHeight;
            }

            String instruction = "Press Shift+Tab to close";
            int instructWidth = this.font.width(instruction);
            int instructX = (this.width - instructWidth) / 2;
            guiGraphics.drawString(this.font, instruction, instructX, centerY, 0xAAAAAA);
        }

        // ===== BOTTOM CENTER - BUTTONS =====
        renderButtons(guiGraphics, mouseX, mouseY);

        RenderSystem.disableBlend();
    }

    private void renderButtons(GuiGraphics guiGraphics, int mouseX, int mouseY) {
        int buttonSpacing = 8; // Réduit de 10 à 8
        int totalButtonWidth = buttons.size() * 80 + (buttons.size() - 1) * buttonSpacing;
        int startX = (this.width - totalButtonWidth) / 2;
        int buttonY = this.height - 30; // Plus près du bas

        for (int i = 0; i < buttons.size(); i++) {
            OverlayButton button = buttons.get(i);
            button.updatePosition(startX + i * (80 + buttonSpacing), buttonY);
            boolean hovered = button.isMouseOver(mouseX, mouseY);
            button.render(guiGraphics, this.minecraft, hovered);
        }
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        if (button == 0) { // Left click
            for (OverlayButton overlayButton : buttons) {
                if (overlayButton.isMouseOver(mouseX, mouseY)) {
                    handleButtonClick(overlayButton.type);
                    return true;
                }
            }
        }
        return super.mouseClicked(mouseX, mouseY, button);
    }

    private void handleButtonClick(ButtonType type) {
        switch (type) {
            case BROWSER:
                this.minecraft.setScreen(new BrowserScreen(this));
                break;
            case TIMER:
                this.minecraft.setScreen(new TimerScreen(this));
                break;
            case PIN:
                this.minecraft.setScreen(new PinOptionsScreen(this));
                break;
            case STOPWATCH:
                this.minecraft.setScreen(new StopwatchScreen(this));
                break;
            case SETTINGS:
                this.minecraft.setScreen(new SettingsScreen(this));
                break;
        }
    }

    @Override
    public boolean isPauseScreen() {
        return true; // This pauses the game in singleplayer
    }

    @Override
    public boolean shouldCloseOnEsc() {
        return true;
    }
}

