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
        int margin = 30; // Marge depuis les bords
        int topY = 40;

        // URL input box - prend toute la largeur disponible moins l'espace pour les 2 boutons
        int buttonAreaWidth = 120; // Espace réservé pour Go + Home + espaces
        this.urlBox = new EditBox(this.font, margin, topY, this.width - margin - buttonAreaWidth, 20, Component.translatable("overlay.browser.enter_url"));
        this.urlBox.setMaxLength(256);
        this.urlBox.setValue(currentUrl);
        this.addRenderableWidget(this.urlBox);

        // Boutons Go et Home alignés à droite
        int goButtonWidth = 50;
        int homeButtonWidth = 55;
        int buttonGap = 5;

        this.goButton = Button.builder(Component.translatable("overlay.browser.go"), button -> loadUrl())
                .bounds(this.width - margin - homeButtonWidth - buttonGap - goButtonWidth, topY, goButtonWidth, 20)
                .build();
        this.addRenderableWidget(this.goButton);

        this.homeButton = Button.builder(Component.translatable("overlay.browser.home"), button -> goHome())
                .bounds(this.width - margin - homeButtonWidth, topY, homeButtonWidth, 20)
                .build();
        this.addRenderableWidget(this.homeButton);

        // Quick links - 3 boutons espacés horizontalement
        int quickLinkY = 75;
        int quickLinkWidth = 110;
        int quickLinkGap = 15;
        int totalQuickLinkWidth = (quickLinkWidth * 3) + (quickLinkGap * 2);
        int quickLinkStartX = (this.width - totalQuickLinkWidth) / 2;

        this.addRenderableWidget(Button.builder(Component.translatable("overlay.browser.minecraft"), button -> quickLoad("https://www.minecraft.net"))
                .bounds(quickLinkStartX, quickLinkY, quickLinkWidth, 20)
                .build());

        this.addRenderableWidget(Button.builder(Component.translatable("overlay.browser.curseforge"), button -> quickLoad("https://www.curseforge.com"))
                .bounds(quickLinkStartX + quickLinkWidth + quickLinkGap, quickLinkY, quickLinkWidth, 20)
                .build());

        this.addRenderableWidget(Button.builder(Component.translatable("overlay.browser.modrinth"), button -> quickLoad("https://modrinth.com"))
                .bounds(quickLinkStartX + (quickLinkWidth + quickLinkGap) * 2, quickLinkY, quickLinkWidth, 20)
                .build());

        // Back button centré en bas
        this.addRenderableWidget(Button.builder(Component.translatable("overlay.button.close"), button -> this.onClose())
                .bounds(this.width / 2 - 75, this.height - 50, 150, 20)
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

