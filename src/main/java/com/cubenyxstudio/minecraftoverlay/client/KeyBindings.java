package com.cubenyxstudio.minecraftoverlay.client;

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
 * Handles key bindings for the overlay (like Steam's Shift+Tab)
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
                GLFW.GLFW_KEY_TAB, // Tab key (Shift will be checked separately)
                CATEGORY
            );

            event.register(TOGGLE_OVERLAY);
        }
    }

    @SubscribeEvent
    public static void onKeyInput(InputEvent.Key event) {
        Minecraft minecraft = Minecraft.getInstance();

        // Check for Shift+Tab combination
        if (event.getKey() == GLFW.GLFW_KEY_TAB && event.getAction() == GLFW.GLFW_PRESS) {
            // Check if Shift is pressed
            long window = minecraft.getWindow().getWindow();
            boolean shiftPressed = GLFW.glfwGetKey(window, GLFW.GLFW_KEY_LEFT_SHIFT) == GLFW.GLFW_PRESS ||
                                   GLFW.glfwGetKey(window, GLFW.GLFW_KEY_RIGHT_SHIFT) == GLFW.GLFW_PRESS;

            if (shiftPressed) {
                // Toggle overlay screen
                if (minecraft.screen == null) {
                    minecraft.setScreen(new OverlayScreen());
                } else if (minecraft.screen instanceof OverlayScreen) {
                    minecraft.setScreen(null);
                }
            }
        }
    }
}

