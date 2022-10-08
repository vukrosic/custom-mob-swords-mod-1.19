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
import net.vukrosic.custommobswordsmod.particle.ModParticles;
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
        player.sendMessage(Text.of("You just broke the block"), false);
        if(player.getStatusEffect(ModEffects.CHICKEN) != null){
            player.sendMessage(Text.of("Sound and particles should play"), false);
            client.getSoundManager().play(PositionedSoundInstance.master(SoundEvents.ENTITY_CHICKEN_AMBIENT, 1.0F, 15.0F));

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
