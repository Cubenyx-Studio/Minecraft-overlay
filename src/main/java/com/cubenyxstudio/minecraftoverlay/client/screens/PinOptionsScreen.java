package com.cubenyxstudio.minecraftoverlay.client.screens;

import com.cubenyxstudio.minecraftoverlay.Config;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.CycleButton;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;

/**
 * Screen for pin options (sticky overlay elements)
 */
public class PinOptionsScreen extends Screen {
    private final Screen previousScreen;

    public PinOptionsScreen(Screen previousScreen) {
        super(Component.literal("Pin Options"));
        this.previousScreen = previousScreen;
    }

    @Override
    protected void init() {
        int centerX = this.width / 2;
        int startY = 80;
        int spacing = 26;
        int buttonWidth = 200;
        int buttonHeight = 20;

        // === PIN OPTIONS ===

        // Pin FPS
        this.addRenderableWidget(CycleButton.onOffBuilder(Config.pinFPS)
                .withInitialValue(Config.pinFPS)
                .create(centerX - buttonWidth / 2, startY, buttonWidth, buttonHeight,
                        Component.literal("📌 Pin FPS"),
                        (button, value) -> Config.pinFPS = value));

        // Pin Coordinates
        this.addRenderableWidget(CycleButton.onOffBuilder(Config.pinCoordinates)
                .withInitialValue(Config.pinCoordinates)
                .create(centerX - buttonWidth / 2, startY + spacing, buttonWidth, buttonHeight,
                        Component.literal("📌 Pin Coordinates"),
                        (button, value) -> Config.pinCoordinates = value));

        // Pin Real Time
        this.addRenderableWidget(CycleButton.onOffBuilder(Config.pinRealTime)
                .withInitialValue(Config.pinRealTime)
                .create(centerX - buttonWidth / 2, startY + spacing * 2, buttonWidth, buttonHeight,
                        Component.literal("📌 Pin Real Time"),
                        (button, value) -> Config.pinRealTime = value));

        // Pin Play Time
        this.addRenderableWidget(CycleButton.onOffBuilder(Config.pinPlayTime)
                .withInitialValue(Config.pinPlayTime)
                .create(centerX - buttonWidth / 2, startY + spacing * 3, buttonWidth, buttonHeight,
                        Component.literal("📌 Pin Play Time"),
                        (button, value) -> Config.pinPlayTime = value));

        // Pin Health
        this.addRenderableWidget(CycleButton.onOffBuilder(Config.pinHealth)
                .withInitialValue(Config.pinHealth)
                .create(centerX - buttonWidth / 2, startY + spacing * 4, buttonWidth, buttonHeight,
                        Component.literal("📌 Pin Health"),
                        (button, value) -> Config.pinHealth = value));

        // Pin Dimension
        this.addRenderableWidget(CycleButton.onOffBuilder(Config.pinDimension)
                .withInitialValue(Config.pinDimension)
                .create(centerX - buttonWidth / 2, startY + spacing * 5, buttonWidth, buttonHeight,
                        Component.literal("📌 Pin Dimension"),
                        (button, value) -> Config.pinDimension = value));

        // === BOTTOM BUTTONS ===
        int bottomButtonWidth = 120;
        int bottomButtonSpacing = 10;
        int bottomY = this.height - 35;

        // Save button
        this.addRenderableWidget(Button.builder(Component.literal("✓ Save"), button -> saveSettings())
                .bounds(centerX - bottomButtonWidth - bottomButtonSpacing / 2, bottomY, bottomButtonWidth, 20)
                .build());

        // Back button
        this.addRenderableWidget(Button.builder(Component.literal("✕ Back"), button -> this.onClose())
                .bounds(centerX + bottomButtonSpacing / 2, bottomY, bottomButtonWidth, 20)
                .build());
    }

    private void saveSettings() {
        // Save configuration to file
        Config.save();

        if (this.minecraft != null && this.minecraft.player != null) {
            this.minecraft.player.sendSystemMessage(Component.literal("§a[Pin] Pin settings saved successfully!"));
        }
        this.onClose();
    }

    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
        this.renderBackground(guiGraphics, mouseX, mouseY, partialTick);

        // Render widgets AFTER background
        super.render(guiGraphics, mouseX, mouseY, partialTick);

        // Draw title OVER everything
        guiGraphics.drawCenteredString(this.font, this.title, this.width / 2, 20, 0xFFFFFF);

        // Draw subtitle
        String subtitle = "📌 Pin elements to keep them always visible";
        guiGraphics.drawCenteredString(this.font, subtitle, this.width / 2, 35, 0xFFFF6B6B);

        // Draw separator line
        int lineY = 50;
        guiGraphics.fill(this.width / 2 - 150, lineY, this.width / 2 + 150, lineY + 1, 0xFF404040);

        // Draw info about pinned elements
        String info1 = "Pinned elements will appear with a golden ⭐ star";
        String info2 = "and stay visible even when the overlay is closed";
        int infoY = this.height - 70;
        guiGraphics.drawCenteredString(this.font, info1, this.width / 2, infoY, 0xFFAAAAA);
        guiGraphics.drawCenteredString(this.font, info2, this.width / 2, infoY + 12, 0xFFAAAAA);
    }

    @Override
    public void onClose() {
        if (this.minecraft != null) {
            this.minecraft.setScreen(previousScreen);
        }
    }

    @Override
    public boolean isPauseScreen() {
        return true;
    }
}

