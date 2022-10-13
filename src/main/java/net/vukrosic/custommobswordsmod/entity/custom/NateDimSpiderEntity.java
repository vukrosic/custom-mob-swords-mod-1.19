package net.vukrosic.custommobswordsmod.entity.custom;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.TargetPredicate;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.mob.SpiderEntity;
import net.minecraft.entity.mob.ZombifiedPiglinEntity;
import net.minecraft.entity.passive.PandaEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class NateDimSpiderEntity extends SpiderEntity {
    public NateDimSpiderEntity(EntityType<? extends SpiderEntity> entityType, World world) {
        super(entityType, world);
    }


    public static DefaultAttributeContainer.Builder setAttributes(){
        return SpiderEntity.createSpiderAttributes();
    }

    @Override
    public void setTarget(@Nullable LivingEntity target) {
        if(target != null && target instanceof PlayerEntity){
            return;
        }
        super.setTarget(target);
    }

    @Override
    public void tick() {
        // agro all mobs
        if(getTarget() == null){
            setTarget(world.getClosestEntity(PandaEntity.class, TargetPredicate.DEFAULT, this, getX(), getY(), getZ(), getBoundingBox().expand(16.0D, 16.0D, 16.0D)));
        }
        //PandaEntity panda = this.world.getClosestEntity(PandaEntity.class, this.getBoundingBox().expand(10), this);
        super.tick();
    }
}
