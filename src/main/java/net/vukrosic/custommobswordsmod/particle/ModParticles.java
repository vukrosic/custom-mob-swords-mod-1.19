package net.vukrosic.custommobswordsmod.particle;

import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes;
import net.minecraft.particle.DefaultParticleType;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.vukrosic.custommobswordsmod.CustomMobSwordsMod;

public class ModParticles {
    public static final DefaultParticleType CHICKEN_PARTICLE = FabricParticleTypes.simple();
    public static final DefaultParticleType ROBOT_CHICKEN_PARTICLE = FabricParticleTypes.simple();

    public static void registerParticles() {
        Registry.register(Registry.PARTICLE_TYPE, new Identifier(CustomMobSwordsMod.MOD_ID, "chicken_particle"),
                CHICKEN_PARTICLE);
        Registry.register(Registry.PARTICLE_TYPE, new Identifier(CustomMobSwordsMod.MOD_ID, "robot_chicken_particle"),
                ROBOT_CHICKEN_PARTICLE);
    }
}
