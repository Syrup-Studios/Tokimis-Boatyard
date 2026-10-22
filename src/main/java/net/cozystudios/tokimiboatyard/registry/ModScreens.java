package net.cozystudios.tokimiboatyard.registry;

import net.cozystudios.tokimiboatyard.TokimiBoatyard;
import net.cozystudios.tokimiboatyard.screen.UpgradedBoatScreenHandler;
import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerType;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.inventory.MenuType;

public final class ModScreens {
    public static final MenuType<UpgradedBoatScreenHandler> UPGRADED_BOAT =
        new ExtendedScreenHandlerType<>(UpgradedBoatScreenHandler::new);

    public static void register() {
        Registry.register(BuiltInRegistries.MENU,
            new ResourceLocation(TokimiBoatyard.MOD_ID, "upgraded_boat"),
            UPGRADED_BOAT);
    }

    private ModScreens() {}
}
