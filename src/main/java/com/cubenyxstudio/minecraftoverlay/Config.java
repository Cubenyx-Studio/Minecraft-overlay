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

    static final ModConfigSpec SPEC = BUILDER.build();

    // Public static fields for easy access
    public static boolean overlayEnabled;
    public static int overlayOpacity;
    public static String overlayPosition;
    public static boolean showFPS;
    public static boolean showCoordinates;
    public static boolean showRealTime;
    public static boolean showPlayTime;
    public static boolean showHealth;
    public static boolean showDimension;

    @SubscribeEvent
    static void onLoad(final ModConfigEvent event) {
        overlayEnabled = OVERLAY_ENABLED.get();
        overlayOpacity = OVERLAY_OPACITY.get();
        overlayPosition = OVERLAY_POSITION.get();
        showFPS = SHOW_FPS.get();
        showCoordinates = SHOW_COORDINATES.get();
        showRealTime = SHOW_REAL_TIME.get();
        showPlayTime = SHOW_PLAY_TIME.get();
        showHealth = SHOW_HEALTH.get();
        showDimension = SHOW_DIMENSION.get();
    }

    // Method to save configuration programmatically
    public static void save() {
        OVERLAY_ENABLED.set(overlayEnabled);
        OVERLAY_OPACITY.set(overlayOpacity);
        OVERLAY_POSITION.set(overlayPosition);
        SHOW_FPS.set(showFPS);
        SHOW_COORDINATES.set(showCoordinates);
        SHOW_REAL_TIME.set(showRealTime);
        SHOW_PLAY_TIME.set(showPlayTime);
        SHOW_HEALTH.set(showHealth);
        SHOW_DIMENSION.set(showDimension);
        SPEC.save();
    }
}


