package net.cozystudios.tokimiboatyard.registry;

import net.cozystudios.tokimiboatyard.TokimiBoatyard;
import net.cozystudios.tokimiboatyard.recipe.SeatDowngradeRecipe;
import net.cozystudios.tokimiboatyard.recipe.SeatExpansionRecipe;
import net.cozystudios.tokimiboatyard.recipe.TierUpgradeRecipe;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.SimpleCraftingRecipeSerializer;

public final class ModRecipes {
    public static final RecipeSerializer<TierUpgradeRecipe> TIER_UPGRADE =
        Registry.register(BuiltInRegistries.RECIPE_SERIALIZER,
            new ResourceLocation(TokimiBoatyard.MOD_ID, "tier_upgrade"),
            new SimpleCraftingRecipeSerializer<>(TierUpgradeRecipe::new));

    public static final RecipeSerializer<SeatExpansionRecipe> SEAT_EXPANSION =
        Registry.register(BuiltInRegistries.RECIPE_SERIALIZER,
            new ResourceLocation(TokimiBoatyard.MOD_ID, "seat_expansion"),
            new SimpleCraftingRecipeSerializer<>(SeatExpansionRecipe::new));

    public static final RecipeSerializer<SeatDowngradeRecipe> SEAT_DOWNGRADE =
        Registry.register(BuiltInRegistries.RECIPE_SERIALIZER,
            new ResourceLocation(TokimiBoatyard.MOD_ID, "seat_downgrade"),
            new SimpleCraftingRecipeSerializer<>(SeatDowngradeRecipe::new));

    private ModRecipes() {}
    public static void register() {}
}
