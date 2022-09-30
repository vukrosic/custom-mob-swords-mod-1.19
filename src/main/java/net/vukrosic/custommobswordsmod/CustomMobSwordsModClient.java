package net.vukrosic.custommobswordsmod;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.render.entity.ShulkerEntityRenderer;
import net.vukrosic.custommobswordsmod.entity.ModEntities;
import net.vukrosic.custommobswordsmod.entity.client.*;
import net.vukrosic.custommobswordsmod.entity.client.chunken.ChunkenEntityRendererGL;
import net.vukrosic.custommobswordsmod.entity.client.corruptedallay.CorruptedAllayAllayEntityRenderer;
import net.vukrosic.custommobswordsmod.entity.client.corruptedallay.CorruptedAllayVexEntityRenderer;
import net.vukrosic.custommobswordsmod.entity.client.frogking.FrogKingTongueEntityRenderer;
import net.vukrosic.custommobswordsmod.entity.client.frogking.FrogKingTongueProjectileEntityRenderer;
import net.vukrosic.custommobswordsmod.event.KeyInputHandler;
import net.vukrosic.custommobswordsmod.particle.ModParticles;
import net.vukrosic.custommobswordsmod.particle.custom.ChickenParticle;
import net.vukrosic.custommobswordsmod.util.ModModelPredicateProvider;

public class CustomMobSwordsModClient implements ClientModInitializer {


    @Override
    public void onInitializeClient() {
        EntityRendererRegistry.register(ModEntities.FIRE_ENDERMAN, FireEndermanEntityRenderer::new);
        EntityRendererRegistry.register(ModEntities.FIRE_ZOMBIE, FireZombieEntityRenderer::new);
        EntityRendererRegistry.register(ModEntities.ENDER_ZOGLIN, EnderZoglinEntityRenderer::new);
        EntityRendererRegistry.register(ModEntities.CHUNKEN, ChunkenEntityRenderer::new);
        EntityRendererRegistry.register(ModEntities.CHUNKENGL, ChunkenEntityRendererGL::new);
        EntityRendererRegistry.register(ModEntities.ENDERZOGLINGL, EnderZoglinEntityRendererGL::new);
        EntityRendererRegistry.register(ModEntities.FROG_KING, FrogKingEntityRenderer::new);
        EntityRendererRegistry.register(ModEntities.FROG_KING_TONGUE, FrogKingTongueEntityRenderer::new);
        EntityRendererRegistry.register(ModEntities.FROG_KING_TONGUE_PROJECTILE, FrogKingTongueProjectileEntityRenderer::new);
        EntityRendererRegistry.register(ModEntities.CORRUPTED_ALLAY_VEX, CorruptedAllayVexEntityRenderer::new);
        EntityRendererRegistry.register(ModEntities.CORRUPTED_ALLAY_ALLAY, CorruptedAllayAllayEntityRenderer::new);
        EntityRendererRegistry.register(ModEntities.BLINKING_WARDEN, BlinkingWardenEntityRenderer::new);
        EntityRendererRegistry.register(ModEntities.SHIELDING_SHULKER, ShulkerEntityRenderer::new);

        ParticleFactoryRegistry.getInstance().register(ModParticles.CHICKEN_PARTICLE, ChickenParticle.Factory::new);

        KeyInputHandler.register();
        ModModelPredicateProvider.registerModModels();
    }
}
