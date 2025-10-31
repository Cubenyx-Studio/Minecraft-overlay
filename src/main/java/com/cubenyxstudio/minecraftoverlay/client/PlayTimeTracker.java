package com.cubenyxstudio.minecraftoverlay.client;

import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayConnectionEvents;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.Level;

/**
 * Tracks player play time across instances, worlds, and dimensions
 */
public class PlayTimeTracker {
    private static ResourceKey<Level> lastDimension = null;
    private static boolean initialized = false;

    public static void register() {
        // Player joined a world/server
        ClientPlayConnectionEvents.JOIN.register((handler, sender, client) -> {
            if (client.player != null) {
                String worldName = getWorldName(client);
                OverlayState.onWorldJoin(worldName);

                // Also track dimension
                if (client.player.level() != null) {
                    String dimensionName = getDimensionName(client.player.level().dimension());
                    OverlayState.onDimensionChange(dimensionName);
                    lastDimension = client.player.level().dimension();
                }
            }
        });

        // Player left the world/server
        ClientPlayConnectionEvents.DISCONNECT.register((handler, client) -> {
            OverlayState.onWorldLeave();
            lastDimension = null;
        });

        // Player tick event
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            // Initialize instance start time on first tick
            if (!initialized) {
                OverlayState.initInstanceStartTime();
                initialized = true;
            }

            // Check for dimension changes
            if (client.player != null && client.player.level() != null) {
                ResourceKey<Level> currentDimension = client.player.level().dimension();
                if (lastDimension == null || !lastDimension.equals(currentDimension)) {
                    String dimensionName = getDimensionName(currentDimension);
                    OverlayState.onDimensionChange(dimensionName);
                    lastDimension = currentDimension;
                }
            }
        });
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

