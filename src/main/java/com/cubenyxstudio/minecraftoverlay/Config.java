package com.cubenyxstudio.minecraftoverlay;

import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.config.ModConfigEvent;
import net.neoforged.neoforge.common.ModConfigSpec;

@EventBusSubscriber(modid = MinecraftOverlay.MODID, bus = EventBusSubscriber.Bus.MOD)
public class Config {
    private static final ModConfigSpec.Builder BUILDER = new ModConfigSpec.Builder();

    // Configuration options for the overlay
    private static final ModConfigSpec.BooleanValue OVERLAY_ENABLED = BUILDER
            .comment("Enable or disable the overlay")
            .define("overlayEnabled", true);

    private static final ModConfigSpec.IntValue OVERLAY_OPACITY = BUILDER
            .comment("Overlay opacity (0-100)")
            .defineInRange("overlayOpacity", 90, 0, 100);

    private static final ModConfigSpec.ConfigValue<String> OVERLAY_POSITION = BUILDER
            .comment("Overlay position (TOP_LEFT, TOP_RIGHT, BOTTOM_LEFT, BOTTOM_RIGHT)")
            .define("overlayPosition", "TOP_LEFT");

    private static final ModConfigSpec.ConfigValue<String> OVERLAY_MODIFIER = BUILDER
            .comment("Modifier key for opening overlay (Shift, Ctrl, Alt, None)")
            .define("overlayModifier", "Shift");

    // Display Options
    private static final ModConfigSpec.BooleanValue SHOW_FPS = BUILDER
            .comment("Show FPS counter")
            .define("showFPS", true);

    private static final ModConfigSpec.BooleanValue SHOW_COORDINATES = BUILDER
            .comment("Show player coordinates")
            .define("showCoordinates", true);

    private static final ModConfigSpec.BooleanValue SHOW_REAL_TIME = BUILDER
            .comment("Show real-world time")
            .define("showRealTime", true);

    // Time Tracking Options
    private static final ModConfigSpec.BooleanValue SHOW_PLAY_TIME = BUILDER
            .comment("Show play time tracking (instance/world/dimension)")
            .define("showPlayTime", true);

    private static final ModConfigSpec.BooleanValue SHOW_HEALTH = BUILDER
            .comment("Show player health")
            .define("showHealth", true);

    private static final ModConfigSpec.BooleanValue SHOW_DIMENSION = BUILDER
            .comment("Show current dimension")
            .define("showDimension", true);

    // Pin Options (display elements even when overlay is closed)
    private static final ModConfigSpec.BooleanValue PIN_FPS = BUILDER
            .comment("Pin FPS to screen (always visible)")
            .define("pinFPS", false);

    private static final ModConfigSpec.BooleanValue PIN_COORDINATES = BUILDER
            .comment("Pin coordinates to screen (always visible)")
            .define("pinCoordinates", false);

    private static final ModConfigSpec.BooleanValue PIN_REAL_TIME = BUILDER
            .comment("Pin real time to screen (always visible)")
            .define("pinRealTime", false);

    private static final ModConfigSpec.BooleanValue PIN_PLAY_TIME = BUILDER
            .comment("Pin play time to screen (always visible)")
            .define("pinPlayTime", false);

    private static final ModConfigSpec.BooleanValue PIN_HEALTH = BUILDER
            .comment("Pin health to screen (always visible)")
            .define("pinHealth", false);

    private static final ModConfigSpec.BooleanValue PIN_DIMENSION = BUILDER
            .comment("Pin dimension to screen (always visible)")
            .define("pinDimension", false);

    static final ModConfigSpec SPEC = BUILDER.build();

    // Public static fields for easy access
    public static boolean overlayEnabled = true;
    public static int overlayOpacity = 90;
    public static String overlayPosition = "TOP_LEFT";
    public static String overlayModifier = "Shift";
    public static boolean showFPS = true;
    public static boolean showCoordinates = true;
    public static boolean showRealTime = true;
    public static boolean showPlayTime = true;
    public static boolean showHealth = true;
    public static boolean showDimension = true;
    // Pin options
    public static boolean pinFPS = false;
    public static boolean pinCoordinates = false;
    public static boolean pinRealTime = false;
    public static boolean pinPlayTime = false;
    public static boolean pinHealth = false;
    public static boolean pinDimension = false;

    @SubscribeEvent
    static void onLoad(final ModConfigEvent event) {
        overlayEnabled = OVERLAY_ENABLED.get();
        overlayOpacity = OVERLAY_OPACITY.get();
        overlayPosition = OVERLAY_POSITION.get();
        overlayModifier = OVERLAY_MODIFIER.get();
        showFPS = SHOW_FPS.get();
        showCoordinates = SHOW_COORDINATES.get();
        showRealTime = SHOW_REAL_TIME.get();
        showPlayTime = SHOW_PLAY_TIME.get();
        showHealth = SHOW_HEALTH.get();
        showDimension = SHOW_DIMENSION.get();
        pinFPS = PIN_FPS.get();
        pinCoordinates = PIN_COORDINATES.get();
        pinRealTime = PIN_REAL_TIME.get();
        pinPlayTime = PIN_PLAY_TIME.get();
        pinHealth = PIN_HEALTH.get();
        pinDimension = PIN_DIMENSION.get();
    }

    // Method to save configuration programmatically
    public static void save() {
        OVERLAY_ENABLED.set(overlayEnabled);
        OVERLAY_OPACITY.set(overlayOpacity);
        OVERLAY_POSITION.set(overlayPosition);
        OVERLAY_MODIFIER.set(overlayModifier);
        SHOW_FPS.set(showFPS);
        SHOW_COORDINATES.set(showCoordinates);
        SHOW_REAL_TIME.set(showRealTime);
        SHOW_PLAY_TIME.set(showPlayTime);
        SHOW_HEALTH.set(showHealth);
        SHOW_DIMENSION.set(showDimension);
        PIN_FPS.set(pinFPS);
        PIN_COORDINATES.set(pinCoordinates);
        PIN_REAL_TIME.set(pinRealTime);
        PIN_PLAY_TIME.set(pinPlayTime);
        PIN_HEALTH.set(pinHealth);
        PIN_DIMENSION.set(pinDimension);
        SPEC.save();
    }
}


