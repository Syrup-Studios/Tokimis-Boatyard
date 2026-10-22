package net.cozystudios.tokimiboatyard.entity.client;

import net.cozystudios.tokimiboatyard.TokimiBoatyard;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.resources.ResourceLocation;

public final class ModModelLayers {
    public static final ModelLayerLocation UPGRADED_BOAT_CHUNK =
        new ModelLayerLocation(new ResourceLocation(TokimiBoatyard.MOD_ID, "upgraded_boat_chunk"), "main");

    public static final ModelLayerLocation UPGRADED_BOAT_BAND =
        new ModelLayerLocation(new ResourceLocation(TokimiBoatyard.MOD_ID, "upgraded_boat_band"), "main");

    public static final ModelLayerLocation UPGRADED_RAFT_CHUNK =
        new ModelLayerLocation(new ResourceLocation(TokimiBoatyard.MOD_ID, "upgraded_raft_chunk"), "main");

    public static final ModelLayerLocation UPGRADED_RAFT_BAND =
        new ModelLayerLocation(new ResourceLocation(TokimiBoatyard.MOD_ID, "upgraded_raft_band"), "main");

    private ModModelLayers() {}
}
