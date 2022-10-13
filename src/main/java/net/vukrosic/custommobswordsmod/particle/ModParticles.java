package net.vukrosic.custommobswordsmod.particle;

import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes;
import net.minecraft.particle.DefaultParticleType;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.vukrosic.custommobswordsmod.CustomMobSwordsMod;

public class ModParticles {

    //public static final DefaultParticleType CHICKEN_PARTICLE = FabricParticleTypes.simple();
    public static final DefaultParticleType ROBOT_CHICKEN_PARTICLE = FabricParticleTypes.simple();
    public static final DefaultParticleType SHIELDING_SHULKER_PARTICLE = FabricParticleTypes.simple();
    public static final DefaultParticleType SHIELDING_SHULKER_PL_PARTICLE = FabricParticleTypes.simple();
    public static final DefaultParticleType TESTING_BASIC_PARTICLE = FabricParticleTypes.simple();
    public static final DefaultParticleType FEATHER_PARTICLE = FabricParticleTypes.simple();

    public static void registerParticles() {
        /*
        Registry.register(Registry.PARTICLE_TYPE, new Identifier(CustomMobSwordsMod.MOD_ID, "shulker_loading_particle"),
                SHIELDING_SHULKER_PARTICLE);

        Registry.register(Registry.PARTICLE_TYPE, new Identifier(CustomMobSwordsMod.MOD_ID, "shulker_pl_loading_particle"),
                SHIELDING_SHULKER_PL_PARTICLE);
*/
        Registry.register(Registry.PARTICLE_TYPE, new Identifier(CustomMobSwordsMod.MOD_ID, "feather_particle"),
                FEATHER_PARTICLE);
/*
        Registry.register(Registry.PARTICLE_TYPE, new Identifier(CustomMobSwordsMod.MOD_ID, "testing_basic_particle"),
                TESTING_BASIC_PARTICLE);*/
/*
        Registry.register(Registry.PARTICLE_TYPE, new Identifier(CustomMobSwordsMod.MOD_ID, "robot_chicken_particle"),
                ROBOT_CHICKEN_PARTICLE);*/
    }
}
