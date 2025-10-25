package com.cubenyxstudio.minecraftoverlay;

import com.mojang.logging.LogUtils;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import org.slf4j.Logger;

/**
 * Minecraft Overlay - A Steam-like overlay for Minecraft
 * CLIENT-ONLY MOD - Does not need to be installed on servers
 */
@Mod(value = MinecraftOverlay.MODID, dist = Dist.CLIENT)
public class MinecraftOverlay {
    public static final String MODID = "minecraftoverlay";
    private static final Logger LOGGER = LogUtils.getLogger();

    public MinecraftOverlay(IEventBus modEventBus, ModContainer modContainer) {
        LOGGER.info("Initializing Minecraft Overlay mod (Client-Only)");

        // Register client setup listener
        modEventBus.addListener(this::clientSetup);

        // Register client configuration
        modContainer.registerConfig(ModConfig.Type.CLIENT, Config.SPEC);
    }

    private void clientSetup(final FMLClientSetupEvent event) {
        LOGGER.info("Minecraft Overlay client setup complete");
    }

    @EventBusSubscriber(modid = MODID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {
            LOGGER.info("Minecraft Overlay initialized on client side");
        }
    }
}

