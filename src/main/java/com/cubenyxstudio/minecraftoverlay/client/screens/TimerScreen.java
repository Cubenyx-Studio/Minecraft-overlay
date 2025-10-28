package com.cubenyxstudio.minecraftoverlay.client.screens;

import com.cubenyxstudio.minecraftoverlay.client.OverlayState;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.EditBox;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;

/**
 * Screen for the timer functionality
 */
public class TimerScreen extends Screen {
    private final Screen previousScreen;
    private EditBox hoursBox;
    private EditBox minutesBox;
    private EditBox secondsBox;
    private Button startButton;
    private Button stopButton;
    private Button resetButton;

    public TimerScreen(Screen previousScreen) {
        super(Component.translatable("overlay.screen.timer.title"));
        this.previousScreen = previousScreen;
    }

    @Override
    protected void init() {
        int centerX = this.width / 2;
        int centerY = this.height / 2;

        // Input boxes for hours, minutes, seconds
        this.hoursBox = new EditBox(this.font, centerX - 120, centerY - 40, 60, 20, Component.translatable("overlay.timer.hours"));
        this.hoursBox.setMaxLength(2);
        this.hoursBox.setValue("0");
        this.addRenderableWidget(this.hoursBox);

        this.minutesBox = new EditBox(this.font, centerX - 30, centerY - 40, 60, 20, Component.translatable("overlay.timer.minutes"));
        this.minutesBox.setMaxLength(2);
        this.minutesBox.setValue("1");
        this.addRenderableWidget(this.minutesBox);

        this.secondsBox = new EditBox(this.font, centerX + 60, centerY - 40, 60, 20, Component.translatable("overlay.timer.seconds"));
        this.secondsBox.setMaxLength(2);
        this.secondsBox.setValue("0");
        this.addRenderableWidget(this.secondsBox);

        // Buttons
        this.startButton = Button.builder(Component.translatable("overlay.timer.start"), button -> startTimer())
                .bounds(centerX - 105, centerY + 20, 100, 20)
                .build();
        this.addRenderableWidget(this.startButton);

        this.stopButton = Button.builder(Component.translatable("overlay.timer.stop"), button -> OverlayState.stopTimer())
                .bounds(centerX + 5, centerY + 20, 100, 20)
                .build();
        this.addRenderableWidget(this.stopButton);

        this.resetButton = Button.builder(Component.translatable("overlay.timer.reset"), button -> resetTimer())
                .bounds(centerX - 50, centerY + 50, 100, 20)
                .build();
        this.addRenderableWidget(this.resetButton);

        // Back button
        this.addRenderableWidget(Button.builder(Component.translatable("overlay.button.back"), button -> this.onClose())
                .bounds(this.width / 2 - 50, this.height - 30, 100, 20)
                .build());

        updateButtonStates();
    }

    private void startTimer() {
        try {
            int hours = Integer.parseInt(this.hoursBox.getValue());
            int minutes = Integer.parseInt(this.minutesBox.getValue());
            int seconds = Integer.parseInt(this.secondsBox.getValue());

            int totalSeconds = hours * 3600 + minutes * 60 + seconds;
            OverlayState.startTimer(totalSeconds);
            updateButtonStates();
        } catch (NumberFormatException e) {
            // Invalid input
        }
    }

    private void resetTimer() {
        OverlayState.resetTimer();
        this.hoursBox.setValue("0");
        this.minutesBox.setValue("1");
        this.secondsBox.setValue("0");
        updateButtonStates();
    }

    private void updateButtonStates() {
        boolean running = OverlayState.isTimerRunning();
        this.startButton.active = !running;
        this.stopButton.active = running;
        this.hoursBox.setEditable(!running);
        this.minutesBox.setEditable(!running);
        this.secondsBox.setEditable(!running);
    }

    @Override
    public void tick() {
        super.tick();

        // Continue ticking timer
        OverlayState.tickTimer();

        // Check if timer just finished
        int remaining = OverlayState.getTimerRemainingSeconds();
        boolean wasRunning = OverlayState.isTimerRunning();

        if (remaining == 0 && !wasRunning && this.stopButton.active) {
            // Timer just finished
            if (this.minecraft != null && this.minecraft.player != null) {
                this.minecraft.player.sendSystemMessage(Component.translatable("overlay.timer.finished"));
            }
        }

        updateButtonStates();
    }

    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
        this.renderBackground(guiGraphics, mouseX, mouseY, partialTick);

        // Render widgets first (buttons, text boxes)
        super.render(guiGraphics, mouseX, mouseY, partialTick);

        // Draw title AFTER background but OVER everything else
        guiGraphics.drawCenteredString(this.font, this.title, this.width / 2, 20, 0xFFFFFF);

        // Draw labels OVER the background
        String hoursLabel = net.minecraft.client.resources.language.I18n.get("overlay.timer.hours");
        String minutesLabel = net.minecraft.client.resources.language.I18n.get("overlay.timer.minutes");
        String secondsLabel = net.minecraft.client.resources.language.I18n.get("overlay.timer.seconds");

        guiGraphics.drawString(this.font, hoursLabel, this.width / 2 - 120, this.height / 2 - 55, 0xFFFFFF);
        guiGraphics.drawString(this.font, minutesLabel, this.width / 2 - 30, this.height / 2 - 55, 0xFFFFFF);
        guiGraphics.drawString(this.font, secondsLabel, this.width / 2 + 60, this.height / 2 - 55, 0xFFFFFF);

        // Draw remaining time from persistent state OVER the background
        int remainingSeconds = OverlayState.getTimerRemainingSeconds();
        int hours = remainingSeconds / 3600;
        int minutes = (remainingSeconds % 3600) / 60;
        int seconds = remainingSeconds % 60;
        String timeStr = net.minecraft.client.resources.language.I18n.get("overlay.timer.remaining", hours, minutes, seconds);
        boolean running = OverlayState.isTimerRunning();
        guiGraphics.drawCenteredString(this.font, timeStr, this.width / 2, this.height / 2 - 10, running ? 0x00FF00 : 0xFFFFFF);
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

