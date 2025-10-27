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
 * Handles key bindings for the overlay (fully configurable via Minecraft Controls)
 */
@EventBusSubscriber(modid = MinecraftOverlay.MODID, value = Dist.CLIENT)
public class KeyBindings {

    public static final String CATEGORY = "key.categories." + MinecraftOverlay.MODID;

    public static KeyMapping TOGGLE_OVERLAY;
    public static KeyMapping MODIFIER_KEY;

    @EventBusSubscriber(modid = MinecraftOverlay.MODID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ModEvents {
        @SubscribeEvent
        public static void registerKeyMappings(RegisterKeyMappingsEvent event) {
            // Main key (default: Tab)
            TOGGLE_OVERLAY = new KeyMapping(
                "key." + MinecraftOverlay.MODID + ".toggle_overlay",
                KeyConflictContext.IN_GAME,
                InputConstants.Type.KEYSYM,
                GLFW.GLFW_KEY_TAB,
                CATEGORY
            );

            // Modifier key (default: Left Shift)
            MODIFIER_KEY = new KeyMapping(
                "key." + MinecraftOverlay.MODID + ".modifier_key",
                KeyConflictContext.IN_GAME,
                InputConstants.Type.KEYSYM,
                GLFW.GLFW_KEY_LEFT_SHIFT,
                CATEGORY
            );

            event.register(TOGGLE_OVERLAY);
            event.register(MODIFIER_KEY);
        }
    }

    @SubscribeEvent
    public static void onKeyInput(InputEvent.Key event) {
        Minecraft minecraft = Minecraft.getInstance();

        // Get the configured keys
        int configuredKey = TOGGLE_OVERLAY.getKey().getValue();
        int modifierKey = MODIFIER_KEY.getKey().getValue();

        // Check if the main key is pressed
        if (event.getKey() == configuredKey && event.getAction() == GLFW.GLFW_PRESS) {
            // Check if modifier is required and if it's pressed
            boolean modifierRequired = !Config.overlayModifier.equalsIgnoreCase("none");
            boolean modifierPressed = isKeyPressed(minecraft, modifierKey);

            if (!modifierRequired || modifierPressed) {
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
     * Check if a specific key is currently pressed
     */
    private static boolean isKeyPressed(Minecraft minecraft, int keyCode) {
        if (keyCode == InputConstants.UNKNOWN.getValue()) {
            return false;
        }

        long window = minecraft.getWindow().getWindow();
        return GLFW.glfwGetKey(window, keyCode) == GLFW.GLFW_PRESS;
    }
}

