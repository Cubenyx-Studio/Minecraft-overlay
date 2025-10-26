package com.cubenyxstudio.minecraftoverlay.client.screens;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.EditBox;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;

/**
 * Screen for the browser functionality
 * Note: This is a simplified version. A real browser would require WebView integration
 */
public class BrowserScreen extends Screen {
    private final Screen previousScreen;
    private EditBox urlBox;
    private Button goButton;
    private Button homeButton;
    private String currentUrl = "https://www.minecraft.net";
    private String pageContent = "Welcome to the Overlay Browser!\n\n" +
            "Click on a quick link or enter a URL to open it in your system browser.\n\n" +
            "Note: Full in-game web rendering would require JCEF (Java Chromium Embedded Framework),\n" +
            "which is a large dependency. For now, links open in your default browser.\n\n" +
            "Quick Links:\n" +
            "• Minecraft.net - Official Minecraft website\n" +
            "• CurseForge - Minecraft mods and modpacks\n" +
            "• Modrinth - Modern mod hosting platform";

    public BrowserScreen(Screen previousScreen) {
        super(Component.literal("Browser"));
        this.previousScreen = previousScreen;
    }

    @Override
    protected void init() {
        // URL input box
        this.urlBox = new EditBox(this.font, 10, 40, this.width - 130, 20, Component.literal("URL"));
        this.urlBox.setMaxLength(256);
        this.urlBox.setValue(currentUrl);
        this.addRenderableWidget(this.urlBox);

        // Go button
        this.goButton = Button.builder(Component.literal("Go"), button -> loadUrl())
                .bounds(this.width - 115, 40, 50, 20)
                .build();
        this.addRenderableWidget(this.goButton);

        // Home button
        this.homeButton = Button.builder(Component.literal("Home"), button -> goHome())
                .bounds(this.width - 60, 40, 50, 20)
                .build();
        this.addRenderableWidget(this.homeButton);

        // Quick links
        this.addRenderableWidget(Button.builder(Component.literal("Minecraft.net"), button -> quickLoad("https://www.minecraft.net"))
                .bounds(10, 70, 100, 20)
                .build());

        this.addRenderableWidget(Button.builder(Component.literal("CurseForge"), button -> quickLoad("https://www.curseforge.com"))
                .bounds(115, 70, 100, 20)
                .build());

        this.addRenderableWidget(Button.builder(Component.literal("Modrinth"), button -> quickLoad("https://modrinth.com"))
                .bounds(220, 70, 100, 20)
                .build());

        // Back button
        this.addRenderableWidget(Button.builder(Component.literal("Close Browser"), button -> this.onClose())
                .bounds(this.width / 2 - 75, this.height - 30, 150, 20)
                .build());
    }

    private void loadUrl() {
        currentUrl = this.urlBox.getValue();
        if (!currentUrl.startsWith("http://") && !currentUrl.startsWith("https://")) {
            currentUrl = "https://" + currentUrl;
            this.urlBox.setValue(currentUrl);
        }

        openUrlInBrowser(currentUrl);
    }

    private void goHome() {
        currentUrl = "https://www.minecraft.net";
        this.urlBox.setValue(currentUrl);
        openUrlInBrowser(currentUrl);
    }

    private void quickLoad(String url) {
        currentUrl = url;
        this.urlBox.setValue(currentUrl);
        openUrlInBrowser(url);
    }

    private void openUrlInBrowser(String url) {
        try {
            net.minecraft.Util.getPlatform().openUri(new java.net.URI(url));
            if (this.minecraft != null && this.minecraft.player != null) {
                this.minecraft.player.sendSystemMessage(Component.literal("§a[Browser] Opening in system browser: " + url));
            }
            pageContent = "Opened in your system browser:\n\n" + url + "\n\n" +
                    "The link has been opened in your default web browser.\n" +
                    "You can continue playing while browsing.\n\n" +
                    "Alt+Tab to switch between Minecraft and your browser.";
        } catch (Exception e) {
            if (this.minecraft != null && this.minecraft.player != null) {
                this.minecraft.player.sendSystemMessage(Component.literal("§c[Browser] Failed to open URL: " + e.getMessage()));
            }
            pageContent = "Error: Failed to open URL\n\n" + url + "\n\n" +
                    "Please check that the URL is valid and your system\n" +
                    "has a default browser configured.";
        }
    }

    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
        this.renderBackground(guiGraphics, mouseX, mouseY, partialTick);

        // Draw title
        guiGraphics.drawCenteredString(this.font, this.title, this.width / 2, 20, 0xFFFFFF);

        // Draw content area background
        guiGraphics.fill(10, 100, this.width - 10, this.height - 45, 0xDD000000);

        // Draw page content
        String[] lines = pageContent.split("\n");
        int yOffset = 110;
        for (String line : lines) {
            if (yOffset > this.height - 60) break;
            guiGraphics.drawString(this.font, line, 20, yOffset, 0xFFFFFF);
            yOffset += 12;
        }

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
        return true;
    }
}

