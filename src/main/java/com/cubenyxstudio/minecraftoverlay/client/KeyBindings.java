package com.cubenyxstudio.minecraftoverlay.client;

import com.cubenyxstudio.minecraftoverlay.MinecraftOverlay;
import com.cubenyxstudio.minecraftoverlay.client.screens.OverlayScreen;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import org.lwjgl.glfw.GLFW;

/**
 * Handles key bindings for the overlay (fully configurable via Minecraft Controls)
 */
public class KeyBindings {

    public static final String CATEGORY = "key.categories." + MinecraftOverlay.MODID;

    public static KeyMapping TOGGLE_OVERLAY;
    public static KeyMapping MODIFIER_KEY;

    public static void register() {
        // Main key (default: Tab)
        TOGGLE_OVERLAY = KeyBindingHelper.registerKeyBinding(new KeyMapping(
                "key." + MinecraftOverlay.MODID + ".toggle_overlay",
                GLFW.GLFW_KEY_TAB,
                CATEGORY
        ));

        // Modifier key (default: Left Shift)
        MODIFIER_KEY = KeyBindingHelper.registerKeyBinding(new KeyMapping(
                "key." + MinecraftOverlay.MODID + ".modifier_key",
                GLFW.GLFW_KEY_LEFT_SHIFT,
                CATEGORY
        ));

        // Register key press event
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            while (TOGGLE_OVERLAY.consumeClick()) {
                handleKeyPress(client);
            }
        });
    }

    private static void handleKeyPress(Minecraft minecraft) {
        // Check if modifier is required and if it's pressed
        boolean modifierPressed = MODIFIER_KEY.isDown();

        if (modifierPressed) {
            // Toggle overlay screen
            if (minecraft.screen == null) {
                minecraft.setScreen(new OverlayScreen());
            } else if (minecraft.screen instanceof OverlayScreen) {
                minecraft.setScreen(null);
            }
        }
    }
}


