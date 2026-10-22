package net.cozystudios.tokimiboatyard;

import net.cozystudios.tokimiboatyard.entity.client.ModModelLayers;
import net.cozystudios.tokimiboatyard.entity.client.UpgradedBoatBandModel;
import net.cozystudios.tokimiboatyard.entity.client.UpgradedBoatPartRenderer;
import net.cozystudios.tokimiboatyard.entity.client.UpgradedBoatRenderer;
import net.cozystudios.tokimiboatyard.entity.client.UpgradedRaftBandModel;
import net.cozystudios.tokimiboatyard.registry.ModEntities;
import net.cozystudios.tokimiboatyard.registry.ModScreens;
import net.cozystudios.tokimiboatyard.screen.UpgradedBoatScreen;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.model.ChestBoatModel;
import net.minecraft.client.model.ChestRaftModel;

public class TokimiBoatyardClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        EntityModelLayerRegistry.registerModelLayer(
            ModModelLayers.UPGRADED_BOAT_CHUNK, ChestBoatModel::createBodyModel);
        EntityModelLayerRegistry.registerModelLayer(
            ModModelLayers.UPGRADED_BOAT_BAND, UpgradedBoatBandModel::getTexturedModelData);
        EntityModelLayerRegistry.registerModelLayer(
            ModModelLayers.UPGRADED_RAFT_CHUNK, ChestRaftModel::createBodyModel);
        EntityModelLayerRegistry.registerModelLayer(
            ModModelLayers.UPGRADED_RAFT_BAND, UpgradedRaftBandModel::getTexturedModelData);
        EntityRendererRegistry.register(ModEntities.UPGRADED_BOAT, UpgradedBoatRenderer::new);
        EntityRendererRegistry.register(ModEntities.UPGRADED_BOAT_PART, UpgradedBoatPartRenderer::new);
        MenuScreens.register(ModScreens.UPGRADED_BOAT, UpgradedBoatScreen::new);
    }
}
