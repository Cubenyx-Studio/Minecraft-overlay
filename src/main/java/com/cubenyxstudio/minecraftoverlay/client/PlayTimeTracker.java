package com.cubenyxstudio.minecraftoverlay.client;

import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.Level;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.ClientPlayerNetworkEvent;
import net.neoforged.neoforge.event.tick.PlayerTickEvent;

/**
 * Tracks player play time across instances, worlds, and dimensions
 */
@EventBusSubscriber(value = Dist.CLIENT)
public class PlayTimeTracker {
    private static ResourceKey<Level> lastDimension = null;
    private static boolean initialized = false;

    @SubscribeEvent
    public static void onPlayerLoggedIn(ClientPlayerNetworkEvent.LoggingIn event) {
        // Player joined a world/server
        Minecraft minecraft = Minecraft.getInstance();
        if (minecraft.player != null) {
            String worldName = getWorldName(minecraft);
            OverlayState.onWorldJoin(worldName);

            // Also track dimension
            if (minecraft.player.level() != null) {
                String dimensionName = getDimensionName(minecraft.player.level().dimension());
                OverlayState.onDimensionChange(dimensionName);
                lastDimension = minecraft.player.level().dimension();
            }
        }
    }

    @SubscribeEvent
    public static void onPlayerLoggedOut(ClientPlayerNetworkEvent.LoggingOut event) {
        // Player left the world/server
        OverlayState.onWorldLeave();
        lastDimension = null;
    }

    @SubscribeEvent
    public static void onPlayerTick(PlayerTickEvent.Post event) {
        // Initialize instance start time on first tick
        if (!initialized) {
            OverlayState.initInstanceStartTime();
            initialized = true;
        }

        // Check for dimension changes
        Minecraft minecraft = Minecraft.getInstance();
        if (minecraft.player != null && minecraft.player.level() != null) {
            ResourceKey<Level> currentDimension = minecraft.player.level().dimension();
            if (lastDimension == null || !lastDimension.equals(currentDimension)) {
                String dimensionName = getDimensionName(currentDimension);
                OverlayState.onDimensionChange(dimensionName);
                lastDimension = currentDimension;
            }
        }
    }

    private static String getWorldName(Minecraft minecraft) {
        if (minecraft.getCurrentServer() != null) {
            // Multiplayer - use server name
            return minecraft.getCurrentServer().name;
        } else if (minecraft.level != null) {
            // Singleplayer - use world name
            if (minecraft.isLocalServer() && minecraft.getSingleplayerServer() != null) {
                return minecraft.getSingleplayerServer().getWorldData().getLevelName();
            }
            return "Singleplayer";
        }
        return "Unknown";
    }

    private static String getDimensionName(ResourceKey<Level> dimension) {
        String path = dimension.location().getPath();
        // Capitalize first letter for better display
        if (!path.isEmpty()) {
            return path.substring(0, 1).toUpperCase() + path.substring(1);
        }
        return path;
    }
}

