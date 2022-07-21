package net.vukrosic.custommobswordsmod.entity.custom;

import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.TridentEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class InfinityTridentEntity extends TridentEntity {

    public InfinityTridentEntity(World world, LivingEntity owner, ItemStack stack) {
        super(world, owner, stack);
    }

    @Override
    protected void onEntityHit(EntityHitResult entityHitResult) {
        super.onEntityHit(entityHitResult);
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
                }
            }
        }
    }

}
