package net.cozystudios.tokimiboatyard.registry;

import net.cozystudios.tokimiboatyard.util.ModIds;
import net.cozystudios.tokimiboatyard.TokimiBoatyard;
import net.cozystudios.tokimiboatyard.entity.UpgradedBoatEntity;
import net.cozystudios.tokimiboatyard.entity.UpgradedBoatPart;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;

public final class ModEntities {
    public static final EntityType<UpgradedBoatEntity> UPGRADED_BOAT = Registry.register(
        BuiltInRegistries.ENTITY_TYPE,
        ModIds.of(TokimiBoatyard.MOD_ID, "upgraded_boat"),
        FabricEntityTypeBuilder.<UpgradedBoatEntity>create(MobCategory.MISC, UpgradedBoatEntity::new)
            .dimensions(EntityDimensions.fixed(1.375F, 0.5625F))
            .trackRangeChunks(10)
            .trackedUpdateRate(3)
            .forceTrackedVelocityUpdates(true)
            .build()
    );

    public static final EntityType<UpgradedBoatPart> UPGRADED_BOAT_PART = Registry.register(
        BuiltInRegistries.ENTITY_TYPE,
        ModIds.of(TokimiBoatyard.MOD_ID, "upgraded_boat_part"),
        FabricEntityTypeBuilder.<UpgradedBoatPart>create(MobCategory.MISC, UpgradedBoatPart::new)
            .dimensions(EntityDimensions.fixed(1.0F, 0.5625F))
            .trackRangeChunks(10)
            .trackedUpdateRate(3)
            .forceTrackedVelocityUpdates(true)
            .build()
    );

    private ModEntities() {}
    public static void register() {}
}
