package net.cozystudios.tokimiboatyard.recipe;

import net.cozystudios.tokimiboatyard.item.UpgradedBoatItem;
import net.cozystudios.tokimiboatyard.registry.ModRecipes;
import net.minecraft.core.NonNullList;
import net.minecraft.core.RegistryAccess;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.inventory.CraftingContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.CraftingBookCategory;
import net.minecraft.world.item.crafting.CustomRecipe;
import net.minecraft.world.item.crafting.RecipeSerializer;

public class SeatDowngradeRecipe extends CustomRecipe {

    public SeatDowngradeRecipe(ResourceLocation id, CraftingBookCategory category) {
        super(id, category);
    }

    @Override
    public boolean matches(CraftingContainer inv, net.minecraft.world.level.Level world) {
        ItemStack boatStack = null;
        int boatCount = 0;
        for (int i = 0; i < inv.getContainerSize(); i++) {
            ItemStack stack = inv.getItem(i);
            if (stack.isEmpty()) continue;
            if (stack.getItem() instanceof UpgradedBoatItem) {
                boatCount++;
                if (boatCount > 1) return false;
                boatStack = stack;
            } else {
                return false;
            }
        }
        if (boatCount != 1) return false;
        return UpgradedBoatItem.readExtraSeats(boatStack) > 0;
    }

    @Override
    public ItemStack assemble(CraftingContainer inv, RegistryAccess registries) {
        return new ItemStack(Items.SADDLE);
    }

    @Override
    public NonNullList<ItemStack> getRemainingItems(CraftingContainer inv) {
        NonNullList<ItemStack> remainders = NonNullList.withSize(inv.getContainerSize(), ItemStack.EMPTY);
        for (int i = 0; i < inv.getContainerSize(); i++) {
            ItemStack stack = inv.getItem(i);
            if (!(stack.getItem() instanceof UpgradedBoatItem)) continue;
            ItemStack modified = stack.copy();
            modified.setCount(1);
            int extras = UpgradedBoatItem.readExtraSeats(stack);
            int newExtras = Math.max(0, extras - 1);
            if (newExtras > 0) {
                modified.getOrCreateTag().putInt("ExtraSeats", newExtras);
            } else if (modified.hasTag()) {
                CompoundTag nbt = modified.getTag();
                nbt.remove("ExtraSeats");
                if (nbt.isEmpty()) modified.setTag(null);
            }
            remainders.set(i, modified);
        }
        return remainders;
    }

    @Override
    public boolean canCraftInDimensions(int width, int height) {
        return width * height >= 1;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return ModRecipes.SEAT_DOWNGRADE;
    }
}
