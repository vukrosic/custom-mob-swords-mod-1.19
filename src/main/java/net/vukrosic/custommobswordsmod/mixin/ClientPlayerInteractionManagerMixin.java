package net.vukrosic.custommobswordsmod.mixin;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerInteractionManager;
import net.minecraft.client.sound.PositionedSoundInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.vukrosic.custommobswordsmod.entity.custom.PlayerEntityExt;
import net.vukrosic.custommobswordsmod.networking.ModMessages;
import net.vukrosic.custommobswordsmod.util.custom.ClientPlayerInteractionManagerExt;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Environment(EnvType.CLIENT)
@Mixin(ClientPlayerInteractionManager.class)
public abstract class ClientPlayerInteractionManagerMixin implements ClientPlayerInteractionManagerExt {





    @Inject(method = "breakBlock", at = @At("HEAD"), cancellable = true)
    public void breakBlock(BlockPos pos, CallbackInfoReturnable info) {

        PlayerEntity player = MinecraftClient.getInstance().player;
        MinecraftClient client = MinecraftClient.getInstance();
        ClientPlayNetworking.send(ModMessages.CHICKEN_EFFECT_GET_ID, PacketByteBufs.create());

        if(((PlayerEntityExt)player).isInNateDimension()) {
            info.setReturnValue(false);
        }
        if(((PlayerEntityExt)client.player).hasChickenEffect()){

            client.getSoundManager().play(PositionedSoundInstance.master(SoundEvents.ENTITY_CHICKEN_AMBIENT, 1.0F, 15.0F));
            player.world.playSound(player.getX(), player.getY(), player.getZ(), SoundEvents.ENTITY_CHICKEN_AMBIENT, SoundCategory.PLAYERS, 10.0F, 1.0F, false);
            player.world.playSound(player, player.getX(), player.getY(), player.getZ(), SoundEvents.ENTITY_CHICKEN_AMBIENT, SoundCategory.PLAYERS, 5.0F, 1.0F + (player.world.random.nextFloat() - player.world.random.nextFloat()) * 0.2F);
        }
    }
/*
    @Inject(method = "attackEntity", at = @At("HEAD"), cancellable = true)
    public void attackEntity(PlayerEntity player, Entity target, CallbackInfo ci) {
        /*
        MinecraftServer server = MinecraftClient.getInstance().getServer();
        ServerPlayerEntity serverPlayer = (ServerPlayerEntity) server.getPlayerManager().getPlayer(player.getUuid());
        MinecraftClient client = MinecraftClient.getInstance();
        if(((PlayerEntityExt)serverPlayer).hasChickenEffect()){
            client.getSoundManager().play(PositionedSoundInstance.master(SoundEvents.ENTITY_CHICKEN_AMBIENT, 1.0F, 15.0F));
            player.world.playSound(player.getX(), player.getY(), player.getZ(), SoundEvents.ENTITY_CHICKEN_AMBIENT, SoundCategory.PLAYERS, 10.0F, 1.0F, false);
            player.world.playSound(player, player.getX(), player.getY(), player.getZ(), SoundEvents.ENTITY_CHICKEN_AMBIENT, SoundCategory.PLAYERS, 5.0F, 1.0F + (player.world.random.nextFloat() - player.world.random.nextFloat()) * 0.2F);
            for (int i = 0; i < 50; i++) {
                Random rand = new Random();
                double x = target.getX() + (rand.nextDouble() - 0.5) * 2;
                double y = target.getY() + (rand.nextDouble() - 0.5) * 2;
                double z = target.getZ() + (rand.nextDouble() - 0.5) * 2;
                player.world.addParticle(ModParticles.FEATHER_PARTICLE, x, y, z, 0.0D, 0.0D, 0.0D);
            }
        }
    }

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
