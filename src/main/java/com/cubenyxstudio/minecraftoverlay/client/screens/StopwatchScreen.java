package com.cubenyxstudio.minecraftoverlay.client.screens;

import com.cubenyxstudio.minecraftoverlay.client.OverlayState;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;

/**
 * Screen for the stopwatch functionality
 */
public class StopwatchScreen extends Screen {
    private final Screen previousScreen;
    private Button startButton;
    private Button stopButton;
    private Button resetButton;
    private Button lapButton;

    public StopwatchScreen(Screen previousScreen) {
        super(Component.translatable("overlay.screen.stopwatch.title"));
        this.previousScreen = previousScreen;
    }

    @Override
    protected void init() {
        int centerX = this.width / 2;
        int centerY = this.height / 2;

        // Buttons
        this.startButton = Button.builder(Component.translatable("overlay.button.start"), button -> OverlayState.startStopwatch())
                .bounds(centerX - 105, centerY + 40, 100, 20)
                .build();
        this.addRenderableWidget(this.startButton);

        this.stopButton = Button.builder(Component.translatable("overlay.button.stop"), button -> OverlayState.stopStopwatch())
                .bounds(centerX + 5, centerY + 40, 100, 20)
                .build();
        this.addRenderableWidget(this.stopButton);

        this.resetButton = Button.builder(Component.translatable("overlay.button.reset"), button -> OverlayState.resetStopwatch())
                .bounds(centerX - 105, centerY + 70, 100, 20)
                .build();
        this.addRenderableWidget(this.resetButton);

        this.lapButton = Button.builder(Component.translatable("overlay.button.lap"), button -> OverlayState.recordLap())
                .bounds(centerX + 5, centerY + 70, 100, 20)
                .build();
        this.addRenderableWidget(this.lapButton);

        // Back button
        this.addRenderableWidget(Button.builder(Component.translatable("overlay.button.back"), button -> this.onClose())
                .bounds(this.width / 2 - 50, this.height - 30, 100, 20)
                .build());

        updateButtonStates();
    }

    private void updateButtonStates() {
        boolean running = OverlayState.isStopwatchRunning();
        this.startButton.active = !running;
        this.stopButton.active = running;
        this.lapButton.active = running;
    }

    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
        this.renderBackground(guiGraphics, mouseX, mouseY, partialTick);

        // Render widgets first (buttons)
        super.render(guiGraphics, mouseX, mouseY, partialTick);

        // Draw title AFTER background but OVER everything else
        guiGraphics.drawCenteredString(this.font, this.title, this.width / 2, 20, 0xFFFFFF);

        // Get current time from persistent state
        long currentTime = OverlayState.getStopwatchTime();
        boolean running = OverlayState.isStopwatchRunning();

        // Draw stopwatch time OVER the background
        String timeStr = formatTime(currentTime);
        int scale = 2;
        guiGraphics.pose().pushPose();
        guiGraphics.pose().scale(scale, scale, scale);
        int scaledX = (this.width / 2) / scale - (this.font.width(timeStr) / 2);
        int scaledY = (this.height / 2 - 20) / scale;
        guiGraphics.drawString(this.font, timeStr, scaledX, scaledY, running ? 0x00FF00 : 0xFFFFFF);
        guiGraphics.pose().popPose();

        // Draw lap times OVER the background
        java.util.List<Long> lapTimes = OverlayState.getStopwatchLaps();
        if (!lapTimes.isEmpty()) {
            String lapTimesLabel = net.minecraft.client.resources.language.I18n.get("overlay.stopwatch.lap_times");
            guiGraphics.drawString(this.font, lapTimesLabel, this.width / 2 - 100, this.height / 2 + 100, 0xAAAAAA);
            int yOffset = 0;
            int maxLaps = Math.min(5, lapTimes.size());
            for (int i = lapTimes.size() - 1; i >= lapTimes.size() - maxLaps; i--) {
                String lapStr = net.minecraft.client.resources.language.I18n.get("overlay.stopwatch.lap_number", i + 1, formatTime(lapTimes.get(i)));
                guiGraphics.drawString(this.font, lapStr, this.width / 2 - 100, this.height / 2 + 115 + yOffset, 0xFFFFFF);
                yOffset += 12;
            }
        }

        // Update button states every frame
        updateButtonStates();
    }

    private String formatTime(long millis) {
        long seconds = millis / 1000;
        long minutes = seconds / 60;
        long hours = minutes / 60;

        long displayMillis = millis % 1000;
        long displaySeconds = seconds % 60;
        long displayMinutes = minutes % 60;

        return String.format("%02d:%02d:%02d.%03d", hours, displayMinutes, displaySeconds, displayMillis);
    }

    @Override
    public void onClose() {
        if (this.minecraft != null) {
            this.minecraft.setScreen(previousScreen);
        }
    }

    @Override
    public boolean isPauseScreen() {
        return true; // Keep game paused
    }
}

