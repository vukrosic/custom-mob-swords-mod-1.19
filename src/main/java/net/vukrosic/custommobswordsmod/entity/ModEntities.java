package net.vukrosic.custommobswordsmod.entity;

import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.entity.mob.CreeperEntity;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.vukrosic.custommobswordsmod.CustomMobSwordsMod;
import net.vukrosic.custommobswordsmod.entity.custom.InfinityCreeperEntity;

public class ModEntities {
    public static final EntityType<InfinityCreeperEntity> INFINITY_CREEPER = Registry.register(
            Registry.ENTITY_TYPE, new Identifier(CustomMobSwordsMod.MOD_ID, "infinity_creeper"),
            FabricEntityTypeBuilder.create(SpawnGroup.MONSTER, InfinityCreeperEntity::new)
                    .dimensions(EntityDimensions.fixed(.4f, 2)).build());




    public static void registerModEntities() {
        CustomMobSwordsMod.LOGGER.info("Registering Mod Entities for " + CustomMobSwordsMod.MOD_ID);
    }
}
