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
    private String pageContent = "";

    public BrowserScreen(Screen previousScreen) {
        super(Component.translatable("overlay.screen.browser.title"));
        this.previousScreen = previousScreen;
        // Initialize with welcome message
        this.pageContent = net.minecraft.client.resources.language.I18n.get("overlay.browser.welcome");
    }

    @Override
    protected void init() {
        int padding = 20; // Marge depuis les bords

        // URL input box avec marges
        this.urlBox = new EditBox(this.font, padding, 40, this.width - 140, 20, Component.translatable("overlay.browser.enter_url"));
        this.urlBox.setMaxLength(256);
        this.urlBox.setValue(currentUrl);
        this.addRenderableWidget(this.urlBox);

        // Go button
        this.goButton = Button.builder(Component.translatable("overlay.browser.go"), button -> loadUrl())
                .bounds(this.width - 115, 40, 50, 20)
                .build();
        this.addRenderableWidget(this.goButton);

        // Home button
        this.homeButton = Button.builder(Component.translatable("overlay.browser.home"), button -> goHome())
                .bounds(this.width - 60, 40, 50, 20)
                .build();
        this.addRenderableWidget(this.homeButton);

        // Quick links avec meilleur espacement
        int quickLinkSpacing = 10; // Espacement entre les boutons
        int quickLinkWidth = 110; // Largeur de chaque bouton
        int quickLinkY = 75; // Position Y

        this.addRenderableWidget(Button.builder(Component.translatable("overlay.browser.minecraft"), button -> quickLoad("https://www.minecraft.net"))
                .bounds(padding, quickLinkY, quickLinkWidth, 20)
                .build());

        this.addRenderableWidget(Button.builder(Component.translatable("overlay.browser.curseforge"), button -> quickLoad("https://www.curseforge.com"))
                .bounds(padding + quickLinkWidth + quickLinkSpacing, quickLinkY, quickLinkWidth, 20)
                .build());

        this.addRenderableWidget(Button.builder(Component.translatable("overlay.browser.modrinth"), button -> quickLoad("https://modrinth.com"))
                .bounds(padding + (quickLinkWidth + quickLinkSpacing) * 2, quickLinkY, quickLinkWidth, 20)
                .build());

        // Back button centré en bas
        this.addRenderableWidget(Button.builder(Component.translatable("overlay.button.close"), button -> this.onClose())
                .bounds(this.width / 2 - 75, this.height - 40, 150, 20)
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
                this.minecraft.player.sendSystemMessage(Component.translatable("overlay.browser.opening", url));
            }
            pageContent = net.minecraft.client.resources.language.I18n.get("overlay.browser.opened", url);
        } catch (Exception e) {
            if (this.minecraft != null && this.minecraft.player != null) {
                this.minecraft.player.sendSystemMessage(Component.translatable("overlay.browser.error", e.getMessage()));
            }
            pageContent = net.minecraft.client.resources.language.I18n.get("overlay.browser.error_message", url);
        }
    }

    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
        this.renderBackground(guiGraphics, mouseX, mouseY, partialTick);
        
        // Render widgets first (buttons, text boxes)
        super.render(guiGraphics, mouseX, mouseY, partialTick);

        // Draw title AFTER background but OVER everything else
        guiGraphics.drawCenteredString(this.font, this.title, this.width / 2, 20, 0xFFFFFF);

        // Draw content area background OVER the blur
        guiGraphics.fill(10, 100, this.width - 10, this.height - 45, 0xDD000000);

        // Draw page content OVER the background
        String[] lines = pageContent.split("\n");
        int yOffset = 110;
        for (String line : lines) {
            if (yOffset > this.height - 60) break;
            guiGraphics.drawString(this.font, line, 20, yOffset, 0xFFFFFF);
            yOffset += 12;
        }
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

