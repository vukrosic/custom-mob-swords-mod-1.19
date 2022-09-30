package net.vukrosic.custommobswordsmod.entity.custom.frogking;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ArrowEntity;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class FrogKingTongueProjectileEntity extends ArrowEntity {

    public boolean shooting = false;
    public int endCrystalAge;

    public FrogKingTongueProjectileEntity(EntityType<? extends ArrowEntity> entityType, World world) {
        super(entityType, world);
        this.endCrystalAge = this.random.nextInt(100000);
    }











/*
    @Override
    protected ItemStack asItemStack() {
        return null;
    }

    // if the projectile hits a living entity, it should kill it


    @Override
    protected void onEntityHit(EntityHitResult entityHitResult) {
        System.out.println("hit11111111111111111111111111: " + entityHitResult.getEntity().toString());
        super.onEntityHit(entityHitResult);
        entityHitResult.getEntity().kill();
    }*/

    @Override
    public void tick() {
        /*
        if((PlayerEntity)this.getOwner() != null){
            ((PlayerEntity) this.getOwner()).sendMessage(Text.of("tongue projectile ticking pleaseeeeee"), true);
            if (this.getOwner() instanceof PlayerEntity) {
                if (!world.isClient() && shooting && Math.random() < 0.07) {

                    PlayerEntity playerEntity = (PlayerEntity) this.getOwner();
                    playerEntity.sendMessage(Text.of("TICKING PROJECTILE SHOULD SPAWN ARROWS"), false);
                    ArrowEntity arrowEntity = new ArrowEntity(world, playerEntity);
                    arrowEntity.refreshPositionAndAngles(playerEntity.getX(), playerEntity.getY(), playerEntity.getZ(), 0, 0);
                    world.spawnEntity(arrowEntity);
                }
            }
        }*/
        super.tick();
    }


    public BlockPos getBeamTarget() {
        if(this.getOwner() != null){
            return this.getOwner().getBlockPos();
        }
        return null;
    }
}
