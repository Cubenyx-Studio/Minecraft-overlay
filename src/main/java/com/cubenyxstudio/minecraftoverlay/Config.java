package com.cubenyxstudio.minecraftoverlay;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.fabricmc.loader.api.FabricLoader;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Config {
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    private static final Path CONFIG_PATH = FabricLoader.getInstance().getConfigDir().resolve("minecraftoverlay.json");

    // Configuration options for the overlay
    public static boolean overlayEnabled = true;
    public static int overlayOpacity = 90;
    public static String overlayPosition = "TOP_LEFT";
    public static String overlayModifier = "Shift";

    // Display Options
    public static boolean showFPS = true;
    public static boolean showCoordinates = true;
    public static boolean showRealTime = true;
    public static boolean showPlayTime = true;
    public static boolean showHealth = true;
    public static boolean showDimension = true;

    // Pin Options (display elements even when overlay is closed)
    public static boolean pinFPS = false;
    public static boolean pinCoordinates = false;
    public static boolean pinRealTime = false;
    public static boolean pinPlayTime = false;
    public static boolean pinHealth = false;
    public static boolean pinDimension = false;

    public static void load() {
        try {
            if (Files.exists(CONFIG_PATH)) {
                String json = Files.readString(CONFIG_PATH);
                ConfigData data = GSON.fromJson(json, ConfigData.class);

                overlayEnabled = data.overlayEnabled;
                overlayOpacity = data.overlayOpacity;
                overlayPosition = data.overlayPosition;
                overlayModifier = data.overlayModifier;
                showFPS = data.showFPS;
                showCoordinates = data.showCoordinates;
                showRealTime = data.showRealTime;
                showPlayTime = data.showPlayTime;
                showHealth = data.showHealth;
                showDimension = data.showDimension;
                pinFPS = data.pinFPS;
                pinCoordinates = data.pinCoordinates;
                pinRealTime = data.pinRealTime;
                pinPlayTime = data.pinPlayTime;
                pinHealth = data.pinHealth;
                pinDimension = data.pinDimension;

                MinecraftOverlay.LOGGER.info("Configuration loaded successfully");
            } else {
                save(); // Create default config
                MinecraftOverlay.LOGGER.info("Created default configuration");
            }
        } catch (IOException e) {
            MinecraftOverlay.LOGGER.error("Failed to load configuration", e);
        }
    }

    public static void save() {
        try {
            ConfigData data = new ConfigData();
            data.overlayEnabled = overlayEnabled;
            data.overlayOpacity = overlayOpacity;
            data.overlayPosition = overlayPosition;
            data.overlayModifier = overlayModifier;
            data.showFPS = showFPS;
            data.showCoordinates = showCoordinates;
            data.showRealTime = showRealTime;
            data.showPlayTime = showPlayTime;
            data.showHealth = showHealth;
            data.showDimension = showDimension;
            data.pinFPS = pinFPS;
            data.pinCoordinates = pinCoordinates;
            data.pinRealTime = pinRealTime;
            data.pinPlayTime = pinPlayTime;
            data.pinHealth = pinHealth;
            data.pinDimension = pinDimension;

            String json = GSON.toJson(data);
            Files.writeString(CONFIG_PATH, json);
            MinecraftOverlay.LOGGER.info("Configuration saved successfully");
        } catch (IOException e) {
            MinecraftOverlay.LOGGER.error("Failed to save configuration", e);
        }
    }

    private static class ConfigData {
        boolean overlayEnabled = true;
        int overlayOpacity = 90;
        String overlayPosition = "TOP_LEFT";
        String overlayModifier = "Shift";
        boolean showFPS = true;
        boolean showCoordinates = true;
        boolean showRealTime = true;
        boolean showPlayTime = true;
        boolean showHealth = true;
        boolean showDimension = true;
        boolean pinFPS = false;
        boolean pinCoordinates = false;
        boolean pinRealTime = false;
        boolean pinPlayTime = false;
        boolean pinHealth = false;
        boolean pinDimension = false;
    }
}


