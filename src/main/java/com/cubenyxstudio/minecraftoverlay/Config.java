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

    static final ModConfigSpec SPEC = BUILDER.build();

    public static boolean overlayEnabled;
    public static int overlayOpacity;
    public static String overlayPosition;

    @SubscribeEvent
    static void onLoad(final ModConfigEvent event) {
        overlayEnabled = OVERLAY_ENABLED.get();
        overlayOpacity = OVERLAY_OPACITY.get();
        overlayPosition = OVERLAY_POSITION.get();
    }
}

