package net.cozystudios.tokimiboatyard.recipe;

import net.cozystudios.tokimiboatyard.entity.UpgradedBoatEntity;
import net.cozystudios.tokimiboatyard.item.UpgradedBoatItem;
import net.cozystudios.tokimiboatyard.registry.ModRecipes;
import net.minecraft.core.RegistryAccess;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.inventory.CraftingContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.CraftingBookCategory;
import net.minecraft.world.item.crafting.CustomRecipe;
import net.minecraft.world.item.crafting.RecipeSerializer;

public class SeatExpansionRecipe extends CustomRecipe {

    public SeatExpansionRecipe(ResourceLocation id, CraftingBookCategory category) {
        super(id, category);
    }

    @Override
    public boolean matches(CraftingContainer inv, net.minecraft.world.level.Level world) {
        ItemStack boatStack = null;
        int boatCount = 0;
        int saddleCount = 0;

        for (int i = 0; i < inv.getContainerSize(); i++) {
            ItemStack stack = inv.getItem(i);
            if (stack.isEmpty()) continue;
            if (stack.getItem() instanceof UpgradedBoatItem) {
                boatCount++;
                if (boatCount > 1) return false;
                boatStack = stack;
            } else if (stack.getItem() == Items.SADDLE) {
                saddleCount++;
            } else {
                return false;
            }
        }
        if (boatCount != 1) return false;
        if (saddleCount < 1 || saddleCount > UpgradedBoatEntity.MAX_EXTRA_SEATS) return false;

        int currentExtras = UpgradedBoatItem.readExtraSeats(boatStack);
        return currentExtras + saddleCount <= UpgradedBoatEntity.MAX_EXTRA_SEATS;
    }

    @Override
    public ItemStack assemble(CraftingContainer inv, RegistryAccess registries) {
        ItemStack boatStack = null;
        int saddleCount = 0;
        for (int i = 0; i < inv.getContainerSize(); i++) {
            ItemStack stack = inv.getItem(i);
            if (stack.isEmpty()) continue;
            if (stack.getItem() instanceof UpgradedBoatItem) boatStack = stack;
            else if (stack.getItem() == Items.SADDLE) saddleCount++;
        }
        if (boatStack == null) return ItemStack.EMPTY;

        ItemStack output = boatStack.copy();
        output.setCount(1);
        int currentExtras = UpgradedBoatItem.readExtraSeats(boatStack);
        int newExtras = Math.min(UpgradedBoatEntity.MAX_EXTRA_SEATS, currentExtras + saddleCount);
        output.getOrCreateTag().putInt("ExtraSeats", newExtras);
        return output;
    }

    @Override
    public boolean canCraftInDimensions(int width, int height) {
        return width * height >= 2;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return ModRecipes.SEAT_EXPANSION;
    }
}
