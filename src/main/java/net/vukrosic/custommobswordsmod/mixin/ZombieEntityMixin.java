package net.vukrosic.custommobswordsmod.mixin;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.mob.ZombieEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;
import net.vukrosic.custommobswordsmod.util.IZombieEntityMixin;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ZombieEntity.class)
public abstract class ZombieEntityMixin extends HostileEntity implements IZombieEntityMixin {
    protected ZombieEntityMixin(EntityType<? extends HostileEntity> entityType, World world) {
        super(entityType, world);
    }



    public Boolean eviscereting = false;

    @Inject(method = "tickMovement", at = @At("HEAD"))
    private void tickMovement(CallbackInfo ci) {
        //entityEntity entity = (entityEntity) (Object) this;

        if(eviscereting) {


            LivingEntity entity = (LivingEntity) (Object) this;

            if (entity.world.isClient) return;

            int evisceratingRadius = 5;
            BlockPos pos = entity.getBlockPos();
            for (int x = -evisceratingRadius; x <= evisceratingRadius; x++) {
                if(x == evisceratingRadius){
                    java.util.Random rand = new java.util.Random();
                    if(rand.nextFloat() < 0.5)
                        continue;
                }
                for (int y = -evisceratingRadius; y <= evisceratingRadius; y++) {
                    if(x == evisceratingRadius){
                        java.util.Random rand = new java.util.Random();
                        if(rand.nextFloat() < 0.5)
                            continue;
                    }
                    for (int z = -evisceratingRadius; z <= evisceratingRadius; z++) {
                        if(x == evisceratingRadius){
                            java.util.Random rand = new java.util.Random();
                            if(rand.nextFloat() < 0.5)
                                continue;
                        }
                        BlockPos newPos = pos.add(x, y, z);
                        BlockState state = entity.world.getBlockState(newPos);
                        Block block = state.getBlock();
                        if (block != Blocks.BEDROCK) {
                            entity.world.breakBlock(newPos, false);
                        }
                    }
                }
            }
        }
    }

    @Override
    public void setEviscereting(boolean value) {

        eviscereting = value;

    }








/*
    public Boolean evisorating = false;

    @Inject(method = "tickMovement", at = @At("HEAD"))
    private void tickMovement(CallbackInfo ci) {
        ZombieEntity zombie = (ZombieEntity) (Object) this;
        if (zombie.world.isClient) return;
        BlockPos pos = zombie.getBlockPos();
        for (int x = -10; x <= 10; x++) {
            for (int y = -10; y <= 10; y++) {
                for (int z = -10; z <= 10; z++) {
                    BlockPos newPos = pos.add(x, y, z);
                    BlockState state = zombie.world.getBlockState(newPos);
                    Block block = state.getBlock();
                    if (block != Blocks.BEDROCK) {
                        zombie.world.breakBlock(newPos, false);
                    }
                }
            }
        }
    }*/
}