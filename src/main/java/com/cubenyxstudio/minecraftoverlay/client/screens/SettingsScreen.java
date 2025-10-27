package com.cubenyxstudio.minecraftoverlay.client.screens;

import com.cubenyxstudio.minecraftoverlay.Config;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.CycleButton;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;

/**
 * Screen for overlay settings
 */
public class SettingsScreen extends Screen {
    private final Screen previousScreen;
    private Button saveButton;

    // Config values (you could move these to the Config class later)
    private boolean showFPS = true;
    private boolean showCoordinates = true;
    private boolean showTime = true;
    private boolean showHealth = true;
    private boolean showDimension = true;

    public SettingsScreen(Screen previousScreen) {
        super(Component.literal("Overlay Settings"));
        this.previousScreen = previousScreen;
    }

    @Override
    protected void init() {
        int centerX = this.width / 2;
        int startY = 70;
        int spacing = 28;
        int buttonWidth = 280;
        int buttonHeight = 20;

        // === DISPLAY OPTIONS SECTION ===

        // Overlay enabled toggle
        this.addRenderableWidget(CycleButton.onOffBuilder(Config.overlayEnabled)
                .withInitialValue(Config.overlayEnabled)
                .create(centerX - buttonWidth / 2, startY, buttonWidth, buttonHeight,
                        Component.literal("Overlay Enabled"),
                        (button, value) -> Config.overlayEnabled = value));

        // Show FPS toggle
        this.addRenderableWidget(CycleButton.onOffBuilder(showFPS)
                .withInitialValue(showFPS)
                .create(centerX - buttonWidth / 2, startY + spacing, buttonWidth, buttonHeight,
                        Component.literal("Show FPS"),
                        (button, value) -> showFPS = value));

        // Show coordinates toggle
        this.addRenderableWidget(CycleButton.onOffBuilder(showCoordinates)
                .withInitialValue(showCoordinates)
                .create(centerX - buttonWidth / 2, startY + spacing * 2, buttonWidth, buttonHeight,
                        Component.literal("Show Coordinates"),
                        (button, value) -> showCoordinates = value));

        // Show time toggle
        this.addRenderableWidget(CycleButton.onOffBuilder(showTime)
                .withInitialValue(showTime)
                .create(centerX - buttonWidth / 2, startY + spacing * 3, buttonWidth, buttonHeight,
                        Component.literal("Show Real Time"),
                        (button, value) -> showTime = value));

        // Show health toggle
        this.addRenderableWidget(CycleButton.onOffBuilder(showHealth)
                .withInitialValue(showHealth)
                .create(centerX - buttonWidth / 2, startY + spacing * 4, buttonWidth, buttonHeight,
                        Component.literal("Show Health"),
                        (button, value) -> showHealth = value));

        // Show dimension toggle
        this.addRenderableWidget(CycleButton.onOffBuilder(showDimension)
                .withInitialValue(showDimension)
                .create(centerX - buttonWidth / 2, startY + spacing * 5, buttonWidth, buttonHeight,
                        Component.literal("Show Dimension"),
                        (button, value) -> showDimension = value));

        // === BOTTOM BUTTONS ===
        int bottomButtonWidth = 120;
        int bottomButtonSpacing = 10;
        int bottomY = this.height - 35;

        // Save button
        this.saveButton = Button.builder(Component.literal("✓ Save"), button -> saveSettings())
                .bounds(centerX - bottomButtonWidth - bottomButtonSpacing / 2, bottomY, bottomButtonWidth, 20)
                .build();
        this.addRenderableWidget(this.saveButton);

        // Back button
        this.addRenderableWidget(Button.builder(Component.literal("✕ Cancel"), button -> this.onClose())
                .bounds(centerX + bottomButtonSpacing / 2, bottomY, bottomButtonWidth, 20)
                .build());
    }

    private void saveSettings() {
        // Save all settings to Config class
        // For now, just Config.overlayEnabled is actually used
        // You can extend the Config class to include the other settings

        if (this.minecraft != null && this.minecraft.player != null) {
            this.minecraft.player.sendSystemMessage(Component.literal("§a[Settings] Settings saved successfully!"));
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

        // Draw section header
        String sectionHeader = "Display Options";
        guiGraphics.drawCenteredString(this.font, sectionHeader, this.width / 2, 55, 0xFFD700);

        // Draw a separator line under the header
        int lineY = 64;
        int lineStartX = this.width / 2 - 140;
        int lineEndX = this.width / 2 + 140;
        guiGraphics.fill(lineStartX, lineY, lineEndX, lineY + 1, 0xFF404040);
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

