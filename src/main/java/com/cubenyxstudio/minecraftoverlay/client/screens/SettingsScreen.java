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

    public SettingsScreen(Screen previousScreen) {
        super(Component.literal("Overlay Settings"));
        this.previousScreen = previousScreen;
    }

    @Override
    protected void init() {
        int centerX = this.width / 2;
        int columnWidth = 200;
        int columnSpacing = 20;
        int startY = 80;
        int spacing = 26;
        int buttonHeight = 20;

        // === LEFT COLUMN: DISPLAY OPTIONS ===
        int leftX = centerX - columnWidth - columnSpacing / 2;

        // Overlay enabled toggle
        this.addRenderableWidget(CycleButton.onOffBuilder(Config.overlayEnabled)
                .withInitialValue(Config.overlayEnabled)
                .create(leftX, startY, columnWidth, buttonHeight,
                        Component.literal("Overlay Enabled"),
                        (button, value) -> Config.overlayEnabled = value));

        // Show FPS toggle
        this.addRenderableWidget(CycleButton.onOffBuilder(Config.showFPS)
                .withInitialValue(Config.showFPS)
                .create(leftX, startY + spacing, columnWidth, buttonHeight,
                        Component.literal("Show FPS"),
                        (button, value) -> Config.showFPS = value));

        // Show coordinates toggle
        this.addRenderableWidget(CycleButton.onOffBuilder(Config.showCoordinates)
                .withInitialValue(Config.showCoordinates)
                .create(leftX, startY + spacing * 2, columnWidth, buttonHeight,
                        Component.literal("Show Coordinates"),
                        (button, value) -> Config.showCoordinates = value));

        // Show real time toggle
        this.addRenderableWidget(CycleButton.onOffBuilder(Config.showRealTime)
                .withInitialValue(Config.showRealTime)
                .create(leftX, startY + spacing * 3, columnWidth, buttonHeight,
                        Component.literal("Show Real Time"),
                        (button, value) -> Config.showRealTime = value));


        // === RIGHT COLUMN: TIME TRACKING ===
        int rightX = centerX + columnSpacing / 2;

        // Show play time toggle
        this.addRenderableWidget(CycleButton.onOffBuilder(Config.showPlayTime)
                .withInitialValue(Config.showPlayTime)
                .create(rightX, startY, columnWidth, buttonHeight,
                        Component.literal("Show Play Time"),
                        (button, value) -> Config.showPlayTime = value));

        // Show health toggle
        this.addRenderableWidget(CycleButton.onOffBuilder(Config.showHealth)
                .withInitialValue(Config.showHealth)
                .create(rightX, startY + spacing, columnWidth, buttonHeight,
                        Component.literal("Show Health"),
                        (button, value) -> Config.showHealth = value));

        // Show dimension toggle
        this.addRenderableWidget(CycleButton.onOffBuilder(Config.showDimension)
                .withInitialValue(Config.showDimension)
                .create(rightX, startY + spacing * 2, columnWidth, buttonHeight,
                        Component.literal("Show Dimension"),
                        (button, value) -> Config.showDimension = value));


        // === BOTTOM BUTTONS ===
        int bottomButtonWidth = 120;
        int bottomButtonSpacing = 10;
        int bottomY = this.height - 35;

        // Save button
        this.addRenderableWidget(Button.builder(Component.literal("✓ Save"), button -> saveSettings())
                .bounds(centerX - bottomButtonWidth - bottomButtonSpacing / 2, bottomY, bottomButtonWidth, 20)
                .build());

        // Back button (cancel without saving)
        this.addRenderableWidget(Button.builder(Component.literal("✕ Cancel"), button -> this.onClose())
                .bounds(centerX + bottomButtonSpacing / 2, bottomY, bottomButtonWidth, 20)
                .build());
    }

    private void saveSettings() {
        // Save configuration to file
        Config.save();
        
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

        // Draw section headers
        int centerX = this.width / 2;
        int columnWidth = 200;
        int columnSpacing = 20;
        int leftX = centerX - columnWidth - columnSpacing / 2;
        int rightX = centerX + columnSpacing / 2;

        // Left column header: Display Options
        String leftHeader = "Display Options";
        int leftHeaderX = leftX + columnWidth / 2;
        guiGraphics.drawCenteredString(this.font, leftHeader, leftHeaderX, 55, 0xFFD700);

        // Left separator line
        int lineY = 70;
        guiGraphics.fill(leftX, lineY, leftX + columnWidth, lineY + 1, 0xFF404040);

        // Right column header: Time Tracking
        String rightHeader = "Time Tracking";
        int rightHeaderX = rightX + columnWidth / 2;
        guiGraphics.drawCenteredString(this.font, rightHeader, rightHeaderX, 55, 0x00FF00);

        // Right separator line
        guiGraphics.fill(rightX, lineY, rightX + columnWidth, lineY + 1, 0xFF404040);

        // Instructions for key binding
        String instruction1 = "To change keys (Main + Modifier):";
        String instruction2 = "Options > Controls > Minecraft Overlay";
        String instruction3 = "Use the 📌 Pin button to pin elements on screen";
        int instrY = this.height - 75;
        guiGraphics.drawCenteredString(this.font, instruction1, this.width / 2, instrY, 0xFFAAAAA);
        guiGraphics.drawCenteredString(this.font, instruction2, this.width / 2, instrY + 12, 0xFFAAAAA);
        guiGraphics.drawCenteredString(this.font, instruction3, this.width / 2, instrY + 30, 0xFFFF6B6B);
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

