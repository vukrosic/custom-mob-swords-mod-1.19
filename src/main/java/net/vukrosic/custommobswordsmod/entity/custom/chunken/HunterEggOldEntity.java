package net.vukrosic.custommobswordsmod.entity.custom.chunken;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.thrown.EggEntity;
import net.minecraft.util.hit.HitResult;
import net.minecraft.world.World;

public class HunterEggOldEntity extends EggEntity {
    public HunterEggOldEntity(EntityType<? extends EggEntity> entityType, World world) {
        super(entityType, world);
    }

    PlayerEntity trappedHunter = null;

    public void SetTrappedHunter(PlayerEntity trappedHunter){
        this.trappedHunter = trappedHunter;
    }

    @Override
    protected void onCollision(HitResult hitResult) {
        super.onCollision(hitResult);
        if (!this.world.isClient) {
            //teleport hunter to chicken dimension
            if(trappedHunter != null){
                trappedHunter.teleport(hitResult.getPos().getX(), hitResult.getPos().getY(), hitResult.getPos().getZ());
            }

        }
        this.world.sendEntityStatus(this, (byte)3);
        this.discard();
    }


}
