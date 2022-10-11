package net.vukrosic.custommobswordsmod.mixin;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.block.Blocks;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerInteractionManager;
import net.minecraft.client.sound.PositionedSoundInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.particle.BlockStateParticleEffect;
import net.minecraft.particle.ParticleType;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.vukrosic.custommobswordsmod.effect.ModEffects;
import net.vukrosic.custommobswordsmod.entity.custom.PlayerEntityExt;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Environment(EnvType.CLIENT)
@Mixin(ClientPlayerInteractionManager.class)
public class ClientPlayerInteractionManagerMixin {

    @Inject(method = "breakBlock", at = @At("HEAD"), cancellable = true)
    public void breakBlock(BlockPos pos, CallbackInfoReturnable info) {
        PlayerEntity player = MinecraftClient.getInstance().player;
        MinecraftClient client = MinecraftClient.getInstance();
        if(((PlayerEntityExt)player).hasChickenEffect()){
            player.sendMessage(Text.of("Sound and particles should play"), false);
            client.getSoundManager().play(PositionedSoundInstance.master(SoundEvents.ENTITY_CHICKEN_AMBIENT, 1.0F, 15.0F));
            if(!player.world.isClient){
                player.world.playSound(player, player.getX(), player.getY(), player.getZ(), SoundEvents.ENTITY_CHICKEN_AMBIENT, SoundCategory.PLAYERS, 10.0F, 1.0F);
            }else
                MinecraftClient.getInstance().getSoundManager().play(PositionedSoundInstance.master(SoundEvents.ENTITY_CHICKEN_AMBIENT, 1.0F));
            player.world.playSound(player.getX(), player.getY(), player.getZ(), SoundEvents.ENTITY_CHICKEN_AMBIENT, SoundCategory.PLAYERS, 10.0F, 1.0F, false);
            player.world.playSound(player, player.getX(), player.getY(), player.getZ(), SoundEvents.ENTITY_CHICKEN_AMBIENT, SoundCategory.PLAYERS, 5.0F, 1.0F + (player.world.random.nextFloat() - player.world.random.nextFloat()) * 0.2F);

            // spawn particles
            for(int i = 0; i < 10; i++){
                player.world.addParticle(ParticleTypes.CRIT, pos.getX(), pos.getY(), pos.getZ(), 0.0D, 0.0D, 0.0D);
                player.world.addParticle(ParticleTypes.CRIT, pos.getX(), pos.getY(), pos.getZ(), 1.0D, 1.0D, 1.0D);
            }

        }
    }

    @Inject(method = "updateBlockBreakingProgress", at = @At("HEAD"), cancellable = true)
    public void updateBlockBreakingProgress(BlockPos pos, Direction direction, CallbackInfoReturnable callbackInfoReturnable) {
        PlayerEntity player = MinecraftClient.getInstance().player;
        MinecraftClient client = MinecraftClient.getInstance();
        if(player.getStatusEffect(ModEffects.CHICKEN) != null){
            for(int i = 0; i < 360; i++) {
                if(i % 20 == 0) {
                    client.particleManager.addParticle(ParticleTypes.WITCH, pos.getX() + i, pos.getY() + i, pos.getZ() + i, i, i, i);
                    /*client.particleManager.addParticle(ModParticles.CHICKEN_PARTICLE,
                            pos.getX() + 0.5d, pos.getY() + 0.7d, pos.getZ() + 0.5d,
                            Math.cos(i) * 0.35d, 0.15d, Math.sin(i) * 0.35d);*/
                }
            }
        }
    }
}
