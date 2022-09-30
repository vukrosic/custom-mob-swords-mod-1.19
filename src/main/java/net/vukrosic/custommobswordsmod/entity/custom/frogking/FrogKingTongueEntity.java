package net.vukrosic.custommobswordsmod.entity.custom.frogking;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.decoration.EndCrystalEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.vukrosic.custommobswordsmod.entity.ModEntities;
import net.vukrosic.custommobswordsmod.entity.custom.FireEndermanEntity;
import org.jetbrains.annotations.Nullable;

public class FrogKingTongueEntity extends EndCrystalEntity {
    LivingEntity owner;
    public FrogKingTongueProjectileEntity frogKingTongueProjectileEntity;

    public FrogKingTongueEntity(EntityType<? extends EndCrystalEntity> entityType, World world) {
        super(entityType, world);
    }


    public void setOwner(LivingEntity owner) {
        this.owner = owner;
    }

    public void createTongueProjectile(){
        if(!world.isClient()){
/*
            FrogKingTongueProjectileEntity frogKingTongueProjectileEntity = new FrogKingTongueProjectileEntity(ModEntities.FROG_KING_TONGUE_PROJECTILE, world);
            frogKingTongueProjectileEntity.refreshPositionAndAngles(this.getX(), this.getY(), this.getZ(), 0, 0);
            frogKingTongueProjectileEntity.setOwner(this.owner);
            world.spawnEntity(frogKingTongueProjectileEntity);
            this.frogKingTongueProjectileEntity = frogKingTongueProjectileEntity;
*/
        }
    }

    @Override
    public void tick() {
        setBeamTarget(this.frogKingTongueProjectileEntity.getBlockPos());
        super.tick();
    }

    @Override
    public void setBeamTarget(@Nullable BlockPos beamTarget) {
        super.setBeamTarget(beamTarget);
    }
}
