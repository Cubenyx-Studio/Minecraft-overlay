package com.cubenyxstudio.minecraftoverlay;

import com.cubenyxstudio.minecraftoverlay.client.KeyBindings;
import com.cubenyxstudio.minecraftoverlay.client.OverlayRenderer;
import com.cubenyxstudio.minecraftoverlay.client.PinnedOverlayRenderer;
import com.cubenyxstudio.minecraftoverlay.client.PlayTimeTracker;
import net.fabricmc.api.ClientModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Minecraft Overlay - A Steam-like overlay for Minecraft
 * CLIENT-ONLY MOD - Does not need to be installed on servers
 */
public class MinecraftOverlay implements ClientModInitializer {
    public static final String MODID = "minecraftoverlay";
    public static final Logger LOGGER = LoggerFactory.getLogger(MODID);

    @Override
    public void onInitializeClient() {
        LOGGER.info("Initializing Minecraft Overlay...");

        // Load configuration
        Config.load();

        // Register key bindings
        KeyBindings.register();

        // Register renderers
        OverlayRenderer.register();
        PinnedOverlayRenderer.register();

        // Initialize play time tracker
        PlayTimeTracker.register();

        LOGGER.info("Minecraft Overlay initialized successfully!");
    }
}

