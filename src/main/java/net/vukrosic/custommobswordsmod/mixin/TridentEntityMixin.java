package net.vukrosic.custommobswordsmod.mixin;

import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.entity.projectile.TridentEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(TridentEntity.class)
public abstract class TridentEntityMixin extends PersistentProjectileEntity {
    protected TridentEntityMixin(EntityType<? extends PersistentProjectileEntity> entityType, World world) {
        super(entityType, world);
    }

    @Override
    public ItemStack asItemStack() {
        return null;
    }

    @Inject(at = @At("HEAD"), method = "onEntityHit", cancellable = true)
    protected void onEntityHit(EntityHitResult entityHitResult, CallbackInfo info) {
        Entity entity = entityHitResult.getEntity();

                World world = entity.getEntityWorld();
                BlockPos pos = entity.getBlockPos();
                int x = pos.getX();
                int z = pos.getZ();
                int y = pos.getY();
                int chunkX = x >> 4;
                int chunkZ = z >> 4;
                for (int i = 0; i < 16; i++) {
                    for (int j = 0; j < 16; j++) {
                        for (int k = 0; k < 256; k++) {
                            if (world.getBlockState(new BlockPos(chunkX * 16 + i, k, chunkZ * 16 + j)) != Blocks.BEDROCK.getDefaultState()) {
                                world.setBlockState(new BlockPos(chunkX * 16 + i, k, chunkZ * 16 + j), Blocks.AIR.getDefaultState());
                            }
                            //world.setBlockState(new BlockPos(chunkX * 16 + i, k, chunkZ * 16 + j), Blocks.AIR.getDefaultState());
                        }
                    }
                }
    }
}



