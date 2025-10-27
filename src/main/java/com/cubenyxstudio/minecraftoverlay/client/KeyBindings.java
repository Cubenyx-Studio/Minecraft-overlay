package com.cubenyxstudio.minecraftoverlay.client;

import com.cubenyxstudio.minecraftoverlay.Config;
import com.cubenyxstudio.minecraftoverlay.client.screens.OverlayScreen;
import com.mojang.blaze3d.platform.InputConstants;
import com.cubenyxstudio.minecraftoverlay.MinecraftOverlay;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.InputEvent;
import net.neoforged.neoforge.client.event.RegisterKeyMappingsEvent;
import net.neoforged.neoforge.client.settings.KeyConflictContext;
import org.lwjgl.glfw.GLFW;

/**
 * Handles key bindings for the overlay (default: Shift+Tab, but configurable)
 */
@EventBusSubscriber(modid = MinecraftOverlay.MODID, value = Dist.CLIENT)
public class KeyBindings {

    public static final String CATEGORY = "key.categories." + MinecraftOverlay.MODID;

    public static KeyMapping TOGGLE_OVERLAY;

    @EventBusSubscriber(modid = MinecraftOverlay.MODID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ModEvents {
        @SubscribeEvent
        public static void registerKeyMappings(RegisterKeyMappingsEvent event) {
            TOGGLE_OVERLAY = new KeyMapping(
                "key." + MinecraftOverlay.MODID + ".toggle_overlay",
                KeyConflictContext.IN_GAME,
                InputConstants.Type.KEYSYM,
                GLFW.GLFW_KEY_TAB, // Default: Tab key
                CATEGORY
            );

            event.register(TOGGLE_OVERLAY);
        }
    }

    @SubscribeEvent
    public static void onKeyInput(InputEvent.Key event) {
        Minecraft minecraft = Minecraft.getInstance();

        // Get the configured key from KeyMapping
        int configuredKey = TOGGLE_OVERLAY.getKey().getValue();

        // Check if the pressed key matches our configured key
        if (event.getKey() == configuredKey && event.getAction() == GLFW.GLFW_PRESS) {
            // Check for the configured modifier key
            if (isModifierPressed(minecraft)) {
                // Toggle overlay screen
                if (minecraft.screen == null) {
                    minecraft.setScreen(new OverlayScreen());
                } else if (minecraft.screen instanceof OverlayScreen) {
                    minecraft.setScreen(null);
                }
            }
        }
    }

    /**
     * Check if the configured modifier key is pressed
     */
    private static boolean isModifierPressed(Minecraft minecraft) {
        long window = minecraft.getWindow().getWindow();

        // Use "shift" as default if overlayModifier is null or empty
        String modifier = (Config.overlayModifier != null && !Config.overlayModifier.isEmpty())
                          ? Config.overlayModifier.toLowerCase()
                          : "shift";

        switch (modifier) {
            case "shift":
                return GLFW.glfwGetKey(window, GLFW.GLFW_KEY_LEFT_SHIFT) == GLFW.GLFW_PRESS ||
                       GLFW.glfwGetKey(window, GLFW.GLFW_KEY_RIGHT_SHIFT) == GLFW.GLFW_PRESS;
            case "ctrl":
            case "control":
                return GLFW.glfwGetKey(window, GLFW.GLFW_KEY_LEFT_CONTROL) == GLFW.GLFW_PRESS ||
                       GLFW.glfwGetKey(window, GLFW.GLFW_KEY_RIGHT_CONTROL) == GLFW.GLFW_PRESS;
            case "alt":
                return GLFW.glfwGetKey(window, GLFW.GLFW_KEY_LEFT_ALT) == GLFW.GLFW_PRESS ||
                       GLFW.glfwGetKey(window, GLFW.GLFW_KEY_RIGHT_ALT) == GLFW.GLFW_PRESS;
            case "none":
                return true; // No modifier required
            default:
                // Default to Shift if invalid config
                return GLFW.glfwGetKey(window, GLFW.GLFW_KEY_LEFT_SHIFT) == GLFW.GLFW_PRESS ||
                       GLFW.glfwGetKey(window, GLFW.GLFW_KEY_RIGHT_SHIFT) == GLFW.GLFW_PRESS;
        }
    }
}

