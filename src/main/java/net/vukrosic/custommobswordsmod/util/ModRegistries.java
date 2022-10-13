package net.vukrosic.custommobswordsmod.util;



import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.vukrosic.custommobswordsmod.command.ControlSummonerCommand;
import net.vukrosic.custommobswordsmod.command.ResetChunkenCommand;
import net.vukrosic.custommobswordsmod.command.RestoreDeathCommand;
import net.vukrosic.custommobswordsmod.command.SetHunterCommand;
import net.vukrosic.custommobswordsmod.entity.ModEntities;
import net.vukrosic.custommobswordsmod.entity.custom.*;
import net.vukrosic.custommobswordsmod.entity.custom.butcherboy.ButcherBoyEntityGL;
import net.vukrosic.custommobswordsmod.entity.custom.butcherboy.ButcherCowSpawnEntity;
import net.vukrosic.custommobswordsmod.entity.custom.butcherboy.ExplosiveCowEntity;
import net.vukrosic.custommobswordsmod.entity.custom.chunken.ChunkenEntityGL;
import net.vukrosic.custommobswordsmod.entity.custom.chunken.SpittingChickenEntity;
import net.vukrosic.custommobswordsmod.entity.custom.corruptedallay.CorruptedAllayVexEntityGL;
import net.vukrosic.custommobswordsmod.entity.custom.fireenderman.FireEndermanEntityGL;
import net.vukrosic.custommobswordsmod.entity.custom.frogking.FrogKingEntity;
import net.vukrosic.custommobswordsmod.entity.custom.shieldingshulker.ShieldingShulkerEntity;
import net.vukrosic.custommobswordsmod.entity.custom.shieldingshulker.ShieldingShulkerEntityGL;
import net.vukrosic.custommobswordsmod.entity.custom.summoner.SummonerEntityGL;

public class ModRegistries {

    public static void registerModStuffs(){
        registerAttributes();
        CommandRegistrationCallback.EVENT.register(SetHunterCommand::register);
        CommandRegistrationCallback.EVENT.register(RestoreDeathCommand::register);
        CommandRegistrationCallback.EVENT.register(ResetChunkenCommand::register);
        CommandRegistrationCallback.EVENT.register(ControlSummonerCommand::register);
    }

    private static void registerAttributes(){
        //FabricDefaultAttributeRegistry.register(ModEntities.ENDERZOGLIN, EnderZoglinEntity.setAttributes());
        FabricDefaultAttributeRegistry.register(ModEntities.FIRE_ZOMBIE, FireZombieEntity.setAttributes());
        FabricDefaultAttributeRegistry.register(ModEntities.FIRE_ENDERMAN, FireEndermanEntityGL.setAttributes());
        FabricDefaultAttributeRegistry.register(ModEntities.SPITTING_CHICKEN, SpittingChickenEntity.setAttributes());
        //FabricDefaultAttributeRegistry.register(ModEntities.BIG_FROG, BigFrogEntity.setAttributes());
        //FabricDefaultAttributeRegistry.register(ModEntities.HORSE_FROG, HorseFrogEntity.setAttributes());
        //FabricDefaultAttributeRegistry.register(ModEntities.CHUNKEN, ChunkenEntity.setAttributes());
        FabricDefaultAttributeRegistry.register(ModEntities.CHUNKENGL, ChunkenEntityGL.setAttributes());
        //FabricDefaultAttributeRegistry.register(ModEntities.CHUNKEN_LASERGL, ChunkenLaserEntityGL.setAttributes());
        //FabricDefaultAttributeRegistry.register(ModEntities.CHUNKENRANGEDGL, ChunkenEntityRangedGL.setAttributes());
        FabricDefaultAttributeRegistry.register(ModEntities.ENDER_ZOGLIN, EnderZoglinEntityGL.setAttributes());
        FabricDefaultAttributeRegistry.register(ModEntities.FROG_KING, FrogKingEntity.setAttributes());
        FabricDefaultAttributeRegistry.register(ModEntities.BLINKING_WARDEN, BlinkingWardenEntity.setAttributes());
        FabricDefaultAttributeRegistry.register(ModEntities.SHIELDING_SHULKER, ShieldingShulkerEntity.setAttributes());
        FabricDefaultAttributeRegistry.register(ModEntities.CORRUPTED_ALLAY_VEX_GL, CorruptedAllayVexEntityGL.setAttributes());
        FabricDefaultAttributeRegistry.register(ModEntities.BUTCHER_BOY, ButcherBoyEntityGL.setAttributes());
        FabricDefaultAttributeRegistry.register(ModEntities.EXPLOSIVE_COW, ExplosiveCowEntity.setAttributes());
        FabricDefaultAttributeRegistry.register(ModEntities.SUMMONER, SummonerEntityGL.setAttributes());
        FabricDefaultAttributeRegistry.register(ModEntities.SHIELDING_SHULKERGL, ShieldingShulkerEntityGL.setAttributes());
        FabricDefaultAttributeRegistry.register(ModEntities.NATE_DIM_SPIDER, NateDimSpiderEntity.setAttributes());
        FabricDefaultAttributeRegistry.register(ModEntities.BUTCHER_COW_SPAWN_ENTITY, ButcherCowSpawnEntity.setAttributes());
    }


}
