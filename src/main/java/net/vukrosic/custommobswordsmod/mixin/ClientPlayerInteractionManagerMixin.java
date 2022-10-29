package net.vukrosic.custommobswordsmod.mixin;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.network.ClientPlayerInteractionManager;
import net.minecraft.client.sound.PositionedSoundInstance;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.vukrosic.custommobswordsmod.effect.ModEffects;
import net.vukrosic.custommobswordsmod.entity.custom.PlayerEntityExt;
import net.vukrosic.custommobswordsmod.networking.ModMessages;
import net.vukrosic.custommobswordsmod.particle.ModParticles;
import net.vukrosic.custommobswordsmod.util.custom.ClientPlayerInteractionManagerExt;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Random;

@Environment(EnvType.CLIENT)
@Mixin(ClientPlayerInteractionManager.class)
public abstract class ClientPlayerInteractionManagerMixin implements ClientPlayerInteractionManagerExt {





    @Inject(method = "breakBlock", at = @At("HEAD"), cancellable = true)
    public void breakBlock(BlockPos pos, CallbackInfoReturnable info) {

        PlayerEntity player = MinecraftClient.getInstance().player;
        /*
        MinecraftClient client = MinecraftClient.getInstance();
        PacketByteBuf buf = PacketByteBufs.create();
        buf.writeBlockPos(pos);*/
        //ClientPlayNetworking.send(ModMessages.CHICKEN_EFFECT_POST_ID, buf);

        if(((PlayerEntityExt)player).isInNateDimension()) {
            info.setReturnValue(false);
        }
        if(((PlayerEntityExt)player).hasChickenEffect()) {
            SpawnParticlesAndSound(pos, player);
        }


    }

    @Inject(method = "attackEntity", at = @At("HEAD"), cancellable = true)
    public void attackEntity(PlayerEntity player, Entity target, CallbackInfo ci) {
        if(((PlayerEntityExt)player).hasChickenEffect()) {
            SpawnParticlesAndSound(target.getBlockPos(), player);
        }
    }

    void SpawnParticlesAndSound(BlockPos pos, PlayerEntity player){
        player.world.playSound(player.getX(), player.getY(), player.getZ(), SoundEvents.ENTITY_CHICKEN_HURT, SoundCategory.PLAYERS, 10.0F, 1.0F, false);
        player.world.playSound(player, player.getX(), player.getY(), player.getZ(), SoundEvents.ENTITY_CHICKEN_DEATH, SoundCategory.PLAYERS, 5.0F, 1.0F + (player.world.random.nextFloat() - player.world.random.nextFloat()) * 0.2F);
        for (int i = 0; i < 50; i++) {
            Random rand = new Random();
            double x = pos.getX() + (rand.nextDouble() - 0.5) * 2;
            double y = pos.getY() + (rand.nextDouble() - 0.5) * 2;
            double z = pos.getZ() + (rand.nextDouble() - 0.5) * 2;
            player.world.addParticle(ModParticles.FEATHER_PARTICLE, x, y, z, 0.0D, 0.0D, 0.0D);
        }
    }

/*
    @Inject(method = "updateBlockBreakingProgress", at = @At("HEAD"), cancellable = true)
    public void updateBlockBreakingProgress(BlockPos pos, Direction direction, CallbackInfoReturnable callbackInfoReturnable) {

        PlayerEntity player = MinecraftClient.getInstance().player;
        MinecraftClient client = MinecraftClient.getInstance();
        MinecraftServer server = MinecraftClient.getInstance().getServer();
        ServerPlayerEntity serverPlayer = (ServerPlayerEntity) server.getPlayerManager().getPlayer(player.getUuid());
        if((serverPlayer.getWorld().getRegistryKey().getValue().toString() == "custommobswordsmod:nate_dim")){
            callbackInfoReturnable.setReturnValue(false);
        }
        if(player.getStatusEffect(ModEffects.CHICKEN) != null){
            for(int i = 0; i < 360; i++) {
                if(i % 20 == 0) {
                    client.particleManager.addParticle(ParticleTypes.WITCH, pos.getX() + i, pos.getY() + i, pos.getZ() + i, i, i, i);
                }
            }
        }

    }*/
}
