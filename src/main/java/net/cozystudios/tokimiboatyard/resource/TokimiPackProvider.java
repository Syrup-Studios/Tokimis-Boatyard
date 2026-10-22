package net.cozystudios.tokimiboatyard.resource;

import java.util.function.Consumer;
import net.minecraft.network.chat.Component;
import net.minecraft.server.packs.PackType;
import net.minecraft.server.packs.repository.Pack;
import net.minecraft.server.packs.repository.PackSource;
import net.minecraft.server.packs.repository.RepositorySource;

public class TokimiPackProvider implements RepositorySource {
    @Override
    public void loadPacks(Consumer<Pack> adder) {
        Pack profile = Pack.readMetaAndCreate(
            DynamicResourcePack.NAME,
            Component.literal("Tokimi's Boatyard Dynamic"),
            true,
            name -> new DynamicResourcePack(),
            PackType.CLIENT_RESOURCES,
            Pack.Position.BOTTOM,
            PackSource.BUILT_IN
        );
        if (profile != null) adder.accept(profile);
    }
}
