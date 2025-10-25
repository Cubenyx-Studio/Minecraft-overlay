package com.cubenyxstudio.minecraftoverlay.client;

import com.mojang.blaze3d.platform.InputConstants;
import com.cubenyxstudio.minecraftoverlay.MinecraftOverlay;
import net.minecraft.client.KeyMapping;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RegisterKeyMappingsEvent;
import net.neoforged.neoforge.client.settings.KeyConflictContext;
import org.lwjgl.glfw.GLFW;

/**
 * Handles key bindings for the overlay (like Steam's Shift+Tab)
 */
@EventBusSubscriber(modid = MinecraftOverlay.MODID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class KeyBindings {

    public static final String CATEGORY = "key.categories." + MinecraftOverlay.MODID;

    public static KeyMapping TOGGLE_OVERLAY;

    @SubscribeEvent
    public static void registerKeyMappings(RegisterKeyMappingsEvent event) {
        TOGGLE_OVERLAY = new KeyMapping(
            "key." + MinecraftOverlay.MODID + ".toggle_overlay",
            KeyConflictContext.IN_GAME,
            InputConstants.Type.KEYSYM,
            GLFW.GLFW_KEY_F9, // Default key: F9 (can be changed to Shift+Tab later)
            CATEGORY
        );

        event.register(TOGGLE_OVERLAY);
    }
}

