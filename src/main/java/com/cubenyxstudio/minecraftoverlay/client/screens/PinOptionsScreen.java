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
        super(Component.translatable("overlay.screen.pin.title"));
        this.previousScreen = previousScreen;
    }

    @Override
    protected void init() {
        int centerX = this.width / 2;
        int buttonWidth = 200;

        // LISTE VERTICALE - POSITIONS FIXES
        this.addRenderableWidget(CycleButton.onOffBuilder(Config.pinFPS)
                .withInitialValue(Config.pinFPS)
                .create(centerX - buttonWidth / 2, 60, buttonWidth, 20,
                        Component.translatable("overlay.pin.fps"),
                        (button, value) -> Config.pinFPS = value));

        this.addRenderableWidget(CycleButton.onOffBuilder(Config.pinCoordinates)
                .withInitialValue(Config.pinCoordinates)
                .create(centerX - buttonWidth / 2, 85, buttonWidth, 20,
                        Component.translatable("overlay.pin.coordinates"),
                        (button, value) -> Config.pinCoordinates = value));

        this.addRenderableWidget(CycleButton.onOffBuilder(Config.pinRealTime)
                .withInitialValue(Config.pinRealTime)
                .create(centerX - buttonWidth / 2, 110, buttonWidth, 20,
                        Component.translatable("overlay.pin.real_time"),
                        (button, value) -> Config.pinRealTime = value));

        this.addRenderableWidget(CycleButton.onOffBuilder(Config.pinPlayTime)
                .withInitialValue(Config.pinPlayTime)
                .create(centerX - buttonWidth / 2, 135, buttonWidth, 20,
                        Component.translatable("overlay.pin.play_time"),
                        (button, value) -> Config.pinPlayTime = value));

        this.addRenderableWidget(CycleButton.onOffBuilder(Config.pinHealth)
                .withInitialValue(Config.pinHealth)
                .create(centerX - buttonWidth / 2, 160, buttonWidth, 20,
                        Component.translatable("overlay.pin.health"),
                        (button, value) -> Config.pinHealth = value));

        this.addRenderableWidget(CycleButton.onOffBuilder(Config.pinDimension)
                .withInitialValue(Config.pinDimension)
                .create(centerX - buttonWidth / 2, 185, buttonWidth, 20,
                        Component.translatable("overlay.pin.dimension"),
                        (button, value) -> Config.pinDimension = value));

        // BOUTONS DU BAS
        int spacing = 10;
        int btnWidth = 90;
        this.addRenderableWidget(Button.builder(Component.translatable("overlay.button.save"), button -> saveSettings())
                .bounds(centerX - btnWidth - spacing/2, this.height - 40, btnWidth, 20)
                .build());

        this.addRenderableWidget(Button.builder(Component.translatable("overlay.button.back"), button -> this.onClose())
                .bounds(centerX + spacing/2, this.height - 40, btnWidth, 20)
                .build());
    }

    private void saveSettings() {
        // Save configuration to file
        Config.save();

        if (this.minecraft != null && this.minecraft.player != null) {
            this.minecraft.player.sendSystemMessage(Component.translatable("overlay.pin.saved"));
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

