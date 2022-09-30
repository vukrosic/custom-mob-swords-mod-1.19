package net.vukrosic.custommobswordsmod.util;



import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.vukrosic.custommobswordsmod.command.SetHunterCommand;
import net.vukrosic.custommobswordsmod.entity.ModEntities;
import net.vukrosic.custommobswordsmod.entity.custom.*;
import net.vukrosic.custommobswordsmod.entity.custom.chunken.ChunkenEntityGL;
import net.vukrosic.custommobswordsmod.entity.custom.frogking.FrogKingEntity;

public class ModRegistries {

    public static void registerModStuffs(){
        registerAttributes();
        CommandRegistrationCallback.EVENT.register(SetHunterCommand::register);
    }

    private static void registerAttributes(){
        FabricDefaultAttributeRegistry.register(ModEntities.FIRE_ENDERMAN, FireEndermanEntity.setAttributes());
        FabricDefaultAttributeRegistry.register(ModEntities.ENDER_ZOGLIN, EnderZoglinEntity.setAttributes());
        FabricDefaultAttributeRegistry.register(ModEntities.FIRE_ZOMBIE, FireZombieEntity.setAttributes());
        FabricDefaultAttributeRegistry.register(ModEntities.BIG_FROG, BigFrogEntity.setAttributes());
        FabricDefaultAttributeRegistry.register(ModEntities.HORSE_FROG, HorseFrogEntity.setAttributes());
        FabricDefaultAttributeRegistry.register(ModEntities.CHUNKEN, ChunkenEntity.setAttributes());
        FabricDefaultAttributeRegistry.register(ModEntities.CHUNKENGL, ChunkenEntityGL.setAttributes());
        FabricDefaultAttributeRegistry.register(ModEntities.ENDERZOGLINGL, EnderZoglinEntityGL.setAttributes());
        FabricDefaultAttributeRegistry.register(ModEntities.FROG_KING, FrogKingEntity.setAttributes());
        FabricDefaultAttributeRegistry.register(ModEntities.BLINKING_WARDEN, BlinkingWardenEntity.setAttributes());
        FabricDefaultAttributeRegistry.register(ModEntities.SHIELDING_SHULKER, ShieldingShulkerEntity.setAttributes());
    }


}
