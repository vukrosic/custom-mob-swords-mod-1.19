package net.vukrosic.custommobswordsmod;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.render.entity.*;
import net.vukrosic.custommobswordsmod.entity.ModEntities;
import net.vukrosic.custommobswordsmod.entity.client.*;
import net.vukrosic.custommobswordsmod.entity.client.butcherboy.ButcherBoyEntityRendererGL;
import net.vukrosic.custommobswordsmod.entity.client.chunken.*;
import net.vukrosic.custommobswordsmod.entity.client.corruptedallay.CorruptedAllayVexEntityRendererGL;
import net.vukrosic.custommobswordsmod.entity.client.fireenderman.FireEndermanEntityRendererGL;
import net.vukrosic.custommobswordsmod.entity.client.frogking.FrogKingEntityRenderer;
import net.vukrosic.custommobswordsmod.entity.client.shieldingshulker.ShieldingShulkerEntityRendererGL;
import net.vukrosic.custommobswordsmod.entity.client.summoner.SummonerEntityRendererGL;
import net.vukrosic.custommobswordsmod.event.KeyInputHandler;
import net.vukrosic.custommobswordsmod.networking.ModMessages;
import net.vukrosic.custommobswordsmod.particle.ModParticles;
import net.vukrosic.custommobswordsmod.particle.custom.FeatherParticle;

public class CustomMobSwordsModClient implements ClientModInitializer {


    @Override
    public void onInitializeClient() {
        EntityRendererRegistry.register(ModEntities.FIRE_ENDERMAN, FireEndermanEntityRendererGL::new);
        EntityRendererRegistry.register(ModEntities.FIRE_ZOMBIE, FireZombieEntityRenderer::new);
        EntityRendererRegistry.register(ModEntities.CHUNKENGL, ChunkenEntityRendererGL::new);
        EntityRendererRegistry.register(ModEntities.CHUNKEN_ROCKET, ChunkenRocketEntityRenderer::new);
        //EntityRendererRegistry.register(ModEntities.HUNTER_EGG, HunterEggItemRenderer::new);
        EntityRendererRegistry.register(ModEntities.SPITTING_CHICKEN, ChickenEntityRenderer::new);
        EntityRendererRegistry.register(ModEntities.ENDER_ZOGLIN, EnderZoglinEntityRendererGL::new);
        EntityRendererRegistry.register(ModEntities.FROG_KING, FrogKingEntityRenderer::new);
        EntityRendererRegistry.register(ModEntities.CORRUPTED_ALLAY_VEX_GL, CorruptedAllayVexEntityRendererGL::new);
        EntityRendererRegistry.register(ModEntities.BLINKING_WARDEN, BlinkingWardenEntityRenderer::new);
        EntityRendererRegistry.register(ModEntities.SHIELDING_SHULKER, ShulkerEntityRenderer::new);
        EntityRendererRegistry.register(ModEntities.BUTCHER_BOY, ButcherBoyEntityRendererGL::new);
        EntityRendererRegistry.register(ModEntities.EXPLOSIVE_COW, CowEntityRenderer::new);
        EntityRendererRegistry.register(ModEntities.SUMMONER, SummonerEntityRendererGL::new);
        EntityRendererRegistry.register(ModEntities.SHIELDING_SHULKERGL, ShieldingShulkerEntityRendererGL::new);
        EntityRendererRegistry.register(ModEntities.NATE_DIM_SPIDER, SpiderEntityRenderer::new);
        EntityRendererRegistry.register(ModEntities.BUTCHER_COW_SPAWN_ENTITY, CowEntityRenderer::new);

        ParticleFactoryRegistry.getInstance().register(ModParticles.FEATHER_PARTICLE, FeatherParticle.Factory::new);

        ModMessages.registerS2CPacket();
        // register HunterEggEntity renderer (ItemEntityRenderer)
        // add egg breaking particles



        //ParticleFactoryRegistry.getInstance().register(ModParticles.ROBOT_CHICKEN_PARTICLE, RobotChickenParticle.Factory::new);
        //ParticleFactoryRegistry.getInstance().register(ModParticles.SHIELDING_SHULKER_PARTICLE, ShieldingShulkerParticle.Factory::new);
        //ParticleFactoryRegistry.getInstance().register(ModParticles.TESTING_BASIC_PARTICLE, TestingBasicParticle.Factory::new);
        //ClientPlayNetworking.getGlobalReceivers().
/*
        ClientPlayNetworking.registerReceiver(ChunkenLaserEntity.SPAWN_PACKET, (context, packet) -> {

                double x = packet.

                double y = packetByteBuf.readDouble();
                double z = packetByteBuf.readDouble();
                int id = packetByteBuf.readInt();
                UUID uuid = packetByteBuf.readUuid();

                ChunkenLaserEntity entity = new ChunkenLaserEntity(x, y, z, id, uuid);
                packetContext.getPlayer().world.addEntity(id, entity);
            });
        });
    } ChunkenLaserEntityRenderer::handleSpawnPacket);


        ClientPlayNetworking.registerEntitySpawnCallback(ModEntities.THROWN_ENTITY, (packetContext, packetByteBuf) -> {
            packetByteBuf.readDouble();
            packetByteBuf.readDouble();
            packetByteBuf.readDouble();
            int entityId = packetByteBuf.readInt();
            UUID uuid = packetByteBuf.readUuid();

        ParticleFactoryRegistry.getInstance().register(ModParticles.CHICKEN_PARTICLE, ChickenParticle.Factory::new);
        //ParticleFactoryRegistry.getInstance().register(ModParticles.ROBOT_CHICKEN_PARTICLE, RobotChickenParticle.Factory::new);
*/
        KeyInputHandler.register();
        //ModModelPredicateProvider.registerModModels();
    }
}
