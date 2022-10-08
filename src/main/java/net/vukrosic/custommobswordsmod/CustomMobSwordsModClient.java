package net.vukrosic.custommobswordsmod;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.render.entity.CowEntityRenderer;
import net.minecraft.client.render.entity.ShulkerEntityRenderer;
import net.vukrosic.custommobswordsmod.entity.ModEntities;
import net.vukrosic.custommobswordsmod.entity.client.*;
import net.vukrosic.custommobswordsmod.entity.client.butcherboy.ButcherBoyEntityRendererGL;
import net.vukrosic.custommobswordsmod.entity.client.chunken.ChunkenEntityRangedRendererGL;
import net.vukrosic.custommobswordsmod.entity.client.chunken.ChunkenEntityRendererGL;
import net.vukrosic.custommobswordsmod.entity.client.chunken.ChunkenLaserEntityRendererGL;
import net.vukrosic.custommobswordsmod.entity.client.chunken.ChunkenLaserProjectileEntityRendererGL;
import net.vukrosic.custommobswordsmod.entity.client.corruptedallay.CorruptedAllayVexEntityRendererGL;
import net.vukrosic.custommobswordsmod.entity.client.fireenderman.FireEndermanEntityRendererGL;
import net.vukrosic.custommobswordsmod.entity.client.frogking.FrogKingEntityRenderer;
import net.vukrosic.custommobswordsmod.entity.client.frogking.FrogKingTongueEntityRenderer;
import net.vukrosic.custommobswordsmod.entity.client.frogking.FrogKingTongueProjectileEntityRenderer;
import net.vukrosic.custommobswordsmod.event.KeyInputHandler;
import net.vukrosic.custommobswordsmod.util.ModModelPredicateProvider;

public class CustomMobSwordsModClient implements ClientModInitializer {


    @Override
    public void onInitializeClient() {
        EntityRendererRegistry.register(ModEntities.FIRE_ENDERMAN, FireEndermanEntityRendererGL::new);
        EntityRendererRegistry.register(ModEntities.FIRE_ZOMBIE, FireZombieEntityRenderer::new);
        EntityRendererRegistry.register(ModEntities.ENDERZOGLIN, EnderZoglinEntityRenderer::new);
        EntityRendererRegistry.register(ModEntities.CHUNKENGL, ChunkenEntityRendererGL::new);
        EntityRendererRegistry.register(ModEntities.CHUNKEN_LASER_PROJECTILE, ChunkenLaserProjectileEntityRendererGL::new);
        EntityRendererRegistry.register(ModEntities.CHUNKENRANGEDGL, ChunkenEntityRangedRendererGL::new);
        EntityRendererRegistry.register(ModEntities.ENDER_ZOGLIN, EnderZoglinEntityRendererGL::new);
        EntityRendererRegistry.register(ModEntities.FROG_KING, FrogKingEntityRenderer::new);
        EntityRendererRegistry.register(ModEntities.FROG_KING_TONGUE, FrogKingTongueEntityRenderer::new);
        EntityRendererRegistry.register(ModEntities.FROG_KING_TONGUE_PROJECTILE, FrogKingTongueProjectileEntityRenderer::new);
        EntityRendererRegistry.register(ModEntities.CORRUPTED_ALLAY_VEX_GL, CorruptedAllayVexEntityRendererGL::new);
        EntityRendererRegistry.register(ModEntities.BLINKING_WARDEN, BlinkingWardenEntityRenderer::new);
        EntityRendererRegistry.register(ModEntities.SHIELDING_SHULKER, ShulkerEntityRenderer::new);
        EntityRendererRegistry.register(ModEntities.BUTCHER_BOY, ButcherBoyEntityRendererGL::new);
        EntityRendererRegistry.register(ModEntities.EXPLOSIVE_COW, CowEntityRenderer::new);


/*
        ClientPlayNetworking.getGlobalReceivers().

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
        ModModelPredicateProvider.registerModModels();
    }
}
