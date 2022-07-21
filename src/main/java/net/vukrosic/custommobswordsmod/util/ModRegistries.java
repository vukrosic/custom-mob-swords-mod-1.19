package net.vukrosic.custommobswordsmod.util;

import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.vukrosic.custommobswordsmod.entity.ModEntities;
import net.vukrosic.custommobswordsmod.entity.custom.InfinityCreeperEntity;

public class ModRegistries {

    public static void registerModStuffs(){
        registerAttributes();
    }

    private static void registerAttributes(){
        FabricDefaultAttributeRegistry.register(ModEntities.INFINITY_CREEPER, InfinityCreeperEntity.setAttributes());
    }
}
