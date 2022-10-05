package net.vukrosic.custommobswordsmod.effect.custom;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.entity.player.PlayerEntity;

public class SpontaneousCompustionEffect extends StatusEffect {

    public SpontaneousCompustionEffect(StatusEffectCategory statusEffectCategory, int color) {
        super(statusEffectCategory, color);
    }

    @Override
    public void applyUpdateEffect(LivingEntity entity, int amplifier) {
        if(!entity.world.isClient()) {
            //randomly set on fire
            if(entity.world.random.nextInt(100) < 4) {
                entity.setOnFireFor(5);
            }
            if(entity instanceof PlayerEntity){
                PlayerEntity player = (PlayerEntity) entity;
                if(player.getHungerManager().getFoodLevel() == 20) {
                    player.getHungerManager().setFoodLevel(19);
                    entity.setOnFireFor(5);
                }
            }
        }
        super.applyUpdateEffect(entity, amplifier);
    }
}
