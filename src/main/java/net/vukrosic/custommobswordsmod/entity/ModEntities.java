package net.vukrosic.custommobswordsmod.entity;

import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.vukrosic.custommobswordsmod.CustomMobSwordsMod;
import net.vukrosic.custommobswordsmod.entity.custom.*;
import net.vukrosic.custommobswordsmod.entity.custom.chunken.ChunkenEntityGL;
import net.vukrosic.custommobswordsmod.entity.custom.corruptedallay.CorruptedAllayAllayEntity;
import net.vukrosic.custommobswordsmod.entity.custom.corruptedallay.CorruptedAllayVexEntity;
import net.vukrosic.custommobswordsmod.entity.custom.frogking.FrogKingEntity;
import net.vukrosic.custommobswordsmod.entity.custom.frogking.FrogKingTongueEntity;
import net.vukrosic.custommobswordsmod.entity.custom.frogking.FrogKingTongueProjectileEntity;

public class ModEntities {

    public static final EntityType<ShieldingShulkerEntity> SHIELDING_SHULKER = Registry.register(
            Registry.ENTITY_TYPE, new Identifier(CustomMobSwordsMod.MOD_ID, "shielding_shulker"),
            FabricEntityTypeBuilder.create(SpawnGroup.MONSTER, ShieldingShulkerEntity::new)
                    // give it chicken dimensions
                    .dimensions(EntityDimensions.fixed(1, 1))
                    .build());

    public static final EntityType<ChunkenEntityGL> CHUNKENGL = Registry.register(
            Registry.ENTITY_TYPE, new Identifier(CustomMobSwordsMod.MOD_ID, "chunkengl"),
            FabricEntityTypeBuilder.create(SpawnGroup.MONSTER, ChunkenEntityGL::new)
                    // give it chicken dimensions
                    .dimensions(EntityDimensions.fixed(1.3964844F, 1.4F))
                    .build());
    public static final EntityType<BlinkingWardenEntity> BLINKING_WARDEN = Registry.register(
            Registry.ENTITY_TYPE, new Identifier(CustomMobSwordsMod.MOD_ID, "blinking_warden"),
            FabricEntityTypeBuilder.create(SpawnGroup.MONSTER, BlinkingWardenEntity::new)
                    .dimensions(EntityDimensions.fixed(2, 3))
                    .build());

    public static final EntityType<CorruptedAllayAllayEntity> CORRUPTED_ALLAY_ALLAY = Registry.register(
            Registry.ENTITY_TYPE, new Identifier(CustomMobSwordsMod.MOD_ID, "corrupted_allay_allay"),
            FabricEntityTypeBuilder.create(SpawnGroup.CREATURE, CorruptedAllayAllayEntity::new)
                    .dimensions(EntityDimensions.fixed(0.5F, 0.4F))
                    .build());

    public static final EntityType<CorruptedAllayVexEntity> CORRUPTED_ALLAY_VEX = Registry.register(
            Registry.ENTITY_TYPE, new Identifier(CustomMobSwordsMod.MOD_ID, "corrupted_allay_vex"),
            FabricEntityTypeBuilder.create(SpawnGroup.CREATURE, CorruptedAllayVexEntity::new)
                    .dimensions(EntityDimensions.fixed(0.5F, 0.4F))
                    .build());
    public static final EntityType<FrogKingTongueProjectileEntity> FROG_KING_TONGUE_PROJECTILE = Registry.register(
            Registry.ENTITY_TYPE, new Identifier(CustomMobSwordsMod.MOD_ID, "frog_king_tongue_projectile"),
            FabricEntityTypeBuilder.create(SpawnGroup.AMBIENT, FrogKingTongueProjectileEntity::new)
                    .dimensions(EntityDimensions.fixed(0.5F, 0.4F))
                    .build());

    public static final EntityType<FrogKingTongueEntity> FROG_KING_TONGUE = Registry.register(
            Registry.ENTITY_TYPE, new Identifier(CustomMobSwordsMod.MOD_ID, "frog_king_tongue"),
            FabricEntityTypeBuilder.create(SpawnGroup.AMBIENT, FrogKingTongueEntity::new)
                    .dimensions(EntityDimensions.fixed(0.5F, 0.4F))
                    .build());

    public static final EntityType<FrogKingEntity> FROG_KING = Registry.register(
            Registry.ENTITY_TYPE, new Identifier(CustomMobSwordsMod.MOD_ID, "frog_king"),
            FabricEntityTypeBuilder.create(SpawnGroup.CREATURE, FrogKingEntity::new)
                    // give it chicken dimensions
                    .dimensions(EntityDimensions.fixed(1.3964844F, 1.4F))
                    .build());

    public static final EntityType<EnderZoglinEntityGL> ENDERZOGLINGL = Registry.register(
            Registry.ENTITY_TYPE, new Identifier(CustomMobSwordsMod.MOD_ID, "enderzoglin"),
            FabricEntityTypeBuilder.create(SpawnGroup.MONSTER, EnderZoglinEntityGL::new)
                    // give it chicken dimensions
                    .dimensions(EntityDimensions.fixed(0.6F, 1.95F))
                    .build());

    public static final EntityType<ChunkenEntity> CHUNKEN = Registry.register(
            Registry.ENTITY_TYPE, new Identifier(CustomMobSwordsMod.MOD_ID, "chunken"),
            FabricEntityTypeBuilder.create(SpawnGroup.MONSTER, ChunkenEntity::new)
                    // give it chicken dimensions
                    .dimensions(EntityDimensions.fixed(1.3964844F, 1.4F))
                    .build());
    public static final EntityType<HorseFrogEntity> HORSE_FROG = Registry.register(
            Registry.ENTITY_TYPE, new Identifier(CustomMobSwordsMod.MOD_ID, "horse_frog"),
            FabricEntityTypeBuilder.create(SpawnGroup.MONSTER, HorseFrogEntity::new)
                    .dimensions(EntityDimensions.fixed(1.3964844F, 1.4F))
                    .build());

    public static final EntityType<BigFrogEntity> BIG_FROG = Registry.register(
            Registry.ENTITY_TYPE, new Identifier(CustomMobSwordsMod.MOD_ID, "big_frog"),
            FabricEntityTypeBuilder.create(SpawnGroup.MONSTER, BigFrogEntity::new)
                    .dimensions(EntityDimensions.fixed(1.3964844F, 1.4F))
                    .build());

    public static final EntityType<FireEndermanEntity> FIRE_ENDERMAN = Registry.register(
            Registry.ENTITY_TYPE, new Identifier(CustomMobSwordsMod.MOD_ID, "fire_enderman"),
            FabricEntityTypeBuilder.create(SpawnGroup.MONSTER, FireEndermanEntity::new)
                    .dimensions(EntityDimensions.fixed(0.6F, 2.9F))
                    .build());


    public static final EntityType<EnderZoglinEntity> ENDER_ZOGLIN = Registry.register(
            Registry.ENTITY_TYPE, new Identifier(CustomMobSwordsMod.MOD_ID, "ender_zoglin"),
            FabricEntityTypeBuilder.create(SpawnGroup.MONSTER, EnderZoglinEntity::new)
                    .dimensions(EntityDimensions.fixed(0.6F, 1.95F))
                    .build());


    public static final EntityType<FireZombieEntity> FIRE_ZOMBIE = Registry.register(
            Registry.ENTITY_TYPE, new Identifier(CustomMobSwordsMod.MOD_ID, "fire_zombie"),
            FabricEntityTypeBuilder.create(SpawnGroup.MONSTER, FireZombieEntity::new)
                    .dimensions(EntityDimensions.fixed(0.6F, 1.95F))
                    .build());


    private static Item registerEntityItem(EntityType<?> entityType, Item item) {
        Registry.register(Registry.ITEM, new Identifier(CustomMobSwordsMod.MOD_ID, "infinity_block_item"), item);
        return item;
    }


    public static void registerModEntities() {
        CustomMobSwordsMod.LOGGER.info("Registering Mod Entities for " + CustomMobSwordsMod.MOD_ID);
    }
}
