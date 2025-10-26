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

    public SettingsScreen(Screen previousScreen) {
        super(Component.literal("Overlay Settings"));
        this.previousScreen = previousScreen;
    }

    @Override
    protected void init() {
        int centerX = this.width / 2;
        int startY = 60;
        int spacing = 30;

        // Overlay enabled toggle
        this.addRenderableWidget(CycleButton.onOffBuilder(Config.overlayEnabled)
                .withInitialValue(Config.overlayEnabled)
                .create(centerX - 150, startY, 300, 20,
                        Component.literal("Overlay Enabled"),
                        (button, value) -> Config.overlayEnabled = value));

        // Show FPS toggle
        this.addRenderableWidget(CycleButton.onOffBuilder(true)
                .withInitialValue(true)
                .create(centerX - 150, startY + spacing, 300, 20,
                        Component.literal("Show FPS"),
                        (button, value) -> {
                            // This would be stored in Config
                        }));

        // Show coordinates toggle
        this.addRenderableWidget(CycleButton.onOffBuilder(true)
                .withInitialValue(true)
                .create(centerX - 150, startY + spacing * 2, 300, 20,
                        Component.literal("Show Coordinates"),
                        (button, value) -> {
                            // This would be stored in Config
                        }));

        // Show time toggle
        this.addRenderableWidget(CycleButton.onOffBuilder(true)
                .withInitialValue(true)
                .create(centerX - 150, startY + spacing * 3, 300, 20,
                        Component.literal("Show Time"),
                        (button, value) -> {
                            // This would be stored in Config
                        }));

        // Show health toggle
        this.addRenderableWidget(CycleButton.onOffBuilder(true)
                .withInitialValue(true)
                .create(centerX - 150, startY + spacing * 4, 300, 20,
                        Component.literal("Show Health"),
                        (button, value) -> {
                            // This would be stored in Config
                        }));

        // Show dimension toggle
        this.addRenderableWidget(CycleButton.onOffBuilder(true)
                .withInitialValue(true)
                .create(centerX - 150, startY + spacing * 5, 300, 20,
                        Component.literal("Show Dimension"),
                        (button, value) -> {
                            // This would be stored in Config
                        }));

        // Transparency slider would go here (more complex to implement)

        // Save button
        this.saveButton = Button.builder(Component.literal("Save Settings"), button -> saveSettings())
                .bounds(centerX - 100, this.height - 60, 200, 20)
                .build();
        this.addRenderableWidget(this.saveButton);

        // Back button
        this.addRenderableWidget(Button.builder(Component.literal("Back"), button -> this.onClose())
                .bounds(centerX - 50, this.height - 30, 100, 20)
                .build());
    }

    private void saveSettings() {
        if (this.minecraft != null && this.minecraft.player != null) {
            this.minecraft.player.sendSystemMessage(Component.literal("§a[Settings] Settings saved successfully!"));
        }
        // Here you would save to a config file
        this.onClose();
    }

    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
        this.renderBackground(guiGraphics, mouseX, mouseY, partialTick);

        // Draw title
        guiGraphics.drawCenteredString(this.font, this.title, this.width / 2, 20, 0xFFFFFF);

        // Draw subtitle
        guiGraphics.drawCenteredString(this.font, "Configure your overlay preferences", this.width / 2, 35, 0xAAAAAA);

        super.render(guiGraphics, mouseX, mouseY, partialTick);
    }

    @Override
    public void onClose() {
        if (this.minecraft != null) {
            this.minecraft.setScreen(previousScreen);
        }
    }

    @Override
    public boolean isPauseScreen() {
        return false;
    }
}

