package net.vukrosic.custommobswordsmod.mixin;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.sound.PositionedSoundInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.network.ServerPlayerInteractionManager;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.vukrosic.custommobswordsmod.entity.custom.PlayerEntityExt;
import net.vukrosic.custommobswordsmod.particle.ModParticles;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Random;

@Mixin(ServerPlayerInteractionManager.class)
public class ServerPlayerInteractionManagerMixin {
    @Inject(method = "interactBlock", at = @At("HEAD"), cancellable = true)
    private void interactBlock(ServerPlayerEntity player, World world, ItemStack stack, Hand hand, BlockHitResult hitResult, CallbackInfoReturnable<ActionResult> cir) {
        PlayerEntity playerEntity = (PlayerEntity) player;
        boolean isInChickenDimention = ((PlayerEntityExt) playerEntity).isInChickenDimention();
        if (isInChickenDimention) {
            cir.setReturnValue(ActionResult.FAIL);
        }
    }


    @Inject(method = "tryBreakBlock", at = @At("HEAD"), cancellable = true)
    public void tryBreakBlock(BlockPos pos, CallbackInfoReturnable info) {
        MinecraftServer server = MinecraftClient.getInstance().getServer();
        ServerPlayerEntity player = (ServerPlayerEntity) server.getPlayerManager().getPlayer(MinecraftClient.getInstance().player.getUuid());
        if(((PlayerEntityExt)player).hasChickenEffect()){
            ServerWorld serverWorld = (ServerWorld) player.world;
            for (int i = 0; i < 50; i++) {
                Random rand = new Random();
                double x = pos.getX() + (rand.nextDouble() - 0.5) * 2;
                double y = pos.getY() + (rand.nextDouble() - 0.5) * 2;
                double z = pos.getZ() + (rand.nextDouble() - 0.5) * 2;
                serverWorld.spawnParticles(ModParticles.FEATHER_PARTICLE, x, y + 1, z, 1, 0, 0, 0, 1);
                //serverWorld.spawnParticles(ParticleTypes.ANGRY_VILLAGER, x, y + 1, z, 1, 0, 0, 0, 1);
            }

        }
    }
}
