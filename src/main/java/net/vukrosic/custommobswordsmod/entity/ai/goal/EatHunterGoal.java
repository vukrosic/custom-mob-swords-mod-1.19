package net.vukrosic.custommobswordsmod.entity.ai.goal;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.entity.mob.PathAwareEntity;
import net.vukrosic.custommobswordsmod.entity.custom.ChunkenEntity;

public class EatHunterGoal extends MeleeAttackGoal {

    private ChunkenEntity entity;
    private int animCounter = 0;
    private int animTickLength = 20;

    public EatHunterGoal(PathAwareEntity mob, double speed, boolean pauseWhenMobIdle) {
        super(mob, speed, pauseWhenMobIdle);
        entity = ((ChunkenEntity) mob);
    }


    @Override
    protected void attack(LivingEntity target, double squaredDistance) {
        if (squaredDistance <= this.getSquaredMaxAttackDistance(target) && this.getCooldown() <= 0) {
            if(entity != null) {
                entity.setAttacking(true);
                animCounter = 0;
            }
        }

        super.attack(target, squaredDistance);
    }

    @Override
    public void tick() {
        super.tick();
        if(entity.isAttacking()) {
            animCounter++;

            if(animCounter >= animTickLength) {
                animCounter = 0;
                entity.setAttacking(false);
            }
        }
    }

    @Override
    public void stop() {
        animCounter = 0;
        entity.setAttacking(false);
        super.stop();
    }
}
