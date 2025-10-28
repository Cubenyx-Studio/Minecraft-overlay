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
        super(Component.translatable("overlay.screen.settings.title"));
        this.previousScreen = previousScreen;
    }

    @Override
    protected void init() {
        int centerX = this.width / 2;
        int columnWidth = 220;
        int columnGap = 40; // Espace généreux entre les deux colonnes
        int verticalSpacing = 30;
        int startY = 80;
        int buttonHeight = 20;

        // Calcul des positions des colonnes
        int totalWidth = (columnWidth * 2) + columnGap;
        int leftColumnX = centerX - totalWidth / 2;
        int rightColumnX = leftColumnX + columnWidth + columnGap;

        // === COLONNE GAUCHE: DISPLAY OPTIONS ===

        // Overlay enabled toggle
        this.addRenderableWidget(CycleButton.onOffBuilder(Config.overlayEnabled)
                .withInitialValue(Config.overlayEnabled)
                .create(leftColumnX, startY, columnWidth, buttonHeight,
                        Component.translatable("overlay.settings.enabled"),
                        (button, value) -> Config.overlayEnabled = value));

        // Show FPS toggle
        this.addRenderableWidget(CycleButton.onOffBuilder(Config.showFPS)
                .withInitialValue(Config.showFPS)
                .create(leftColumnX, startY + verticalSpacing, columnWidth, buttonHeight,
                        Component.translatable("overlay.settings.show_fps"),
                        (button, value) -> Config.showFPS = value));

        // Show coordinates toggle
        this.addRenderableWidget(CycleButton.onOffBuilder(Config.showCoordinates)
                .withInitialValue(Config.showCoordinates)
                .create(leftColumnX, startY + verticalSpacing * 2, columnWidth, buttonHeight,
                        Component.translatable("overlay.settings.show_coordinates"),
                        (button, value) -> Config.showCoordinates = value));

        // Show real time toggle
        this.addRenderableWidget(CycleButton.onOffBuilder(Config.showRealTime)
                .withInitialValue(Config.showRealTime)
                .create(leftColumnX, startY + verticalSpacing * 3, columnWidth, buttonHeight,
                        Component.translatable("overlay.settings.show_real_time"),
                        (button, value) -> Config.showRealTime = value));


        // === COLONNE DROITE: TIME TRACKING ===

        // Show play time toggle
        this.addRenderableWidget(CycleButton.onOffBuilder(Config.showPlayTime)
                .withInitialValue(Config.showPlayTime)
                .create(rightColumnX, startY, columnWidth, buttonHeight,
                        Component.translatable("overlay.settings.show_play_time"),
                        (button, value) -> Config.showPlayTime = value));

        // Show health toggle
        this.addRenderableWidget(CycleButton.onOffBuilder(Config.showHealth)
                .withInitialValue(Config.showHealth)
                .create(rightColumnX, startY + verticalSpacing, columnWidth, buttonHeight,
                        Component.translatable("overlay.settings.show_health"),
                        (button, value) -> Config.showHealth = value));

        // Show dimension toggle
        this.addRenderableWidget(CycleButton.onOffBuilder(Config.showDimension)
                .withInitialValue(Config.showDimension)
                .create(rightColumnX, startY + verticalSpacing * 2, columnWidth, buttonHeight,
                        Component.translatable("overlay.settings.show_dimension"),
                        (button, value) -> Config.showDimension = value));


        // === BOTTOM BUTTONS ===
        int bottomButtonWidth = 100;
        int bottomButtonGap = 20;
        int totalBottomWidth = (bottomButtonWidth * 2) + bottomButtonGap;
        int leftBottomX = centerX - totalBottomWidth / 2;
        int rightBottomX = leftBottomX + bottomButtonWidth + bottomButtonGap;
        int bottomY = this.height - 50;

        // Save button
        this.addRenderableWidget(Button.builder(Component.translatable("overlay.button.save"), button -> saveSettings())
                .bounds(leftBottomX, bottomY, bottomButtonWidth, 20)
                .build());

        // Back button (cancel without saving)
        this.addRenderableWidget(Button.builder(Component.translatable("overlay.button.cancel"), button -> this.onClose())
                .bounds(rightBottomX, bottomY, bottomButtonWidth, 20)
                .build());
    }

    private void saveSettings() {
        // Save configuration to file
        Config.save();
        
        if (this.minecraft != null && this.minecraft.player != null) {
            this.minecraft.player.sendSystemMessage(Component.translatable("overlay.message.settings_saved"));
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
        String leftHeader = net.minecraft.client.resources.language.I18n.get("overlay.settings.category.display");
        int leftHeaderX = leftX + columnWidth / 2;
        guiGraphics.drawCenteredString(this.font, leftHeader, leftHeaderX, 55, 0xFFD700);

        // Left separator line
        int lineY = 70;
        guiGraphics.fill(leftX, lineY, leftX + columnWidth, lineY + 1, 0xFF404040);

        // Right column header: Time Tracking
        String rightHeader = net.minecraft.client.resources.language.I18n.get("overlay.settings.category.time");
        int rightHeaderX = rightX + columnWidth / 2;
        guiGraphics.drawCenteredString(this.font, rightHeader, rightHeaderX, 55, 0x00FF00);

        // Right separator line
        guiGraphics.fill(rightX, lineY, rightX + columnWidth, lineY + 1, 0xFF404040);

        // Instructions for key binding
        String instruction1 = net.minecraft.client.resources.language.I18n.get("overlay.settings.instruction1");
        String instruction2 = net.minecraft.client.resources.language.I18n.get("overlay.settings.instruction2");
        int instrY = this.height - 75;
        guiGraphics.drawCenteredString(this.font, instruction1, this.width / 2, instrY, 0xFFAAAAA);
        guiGraphics.drawCenteredString(this.font, instruction2, this.width / 2, instrY + 12, 0xFFAAAAA);
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

