package net.vukrosic.custommobswordsmod.effect.custom;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.text.Text;

public class ChickenEffect extends StatusEffect {


    public ChickenEffect(StatusEffectCategory statusEffectCategory, int color) {
        super(statusEffectCategory, color);
    }

    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier) {
        return true;
    }

    @Override
    public void applyUpdateEffect(LivingEntity entity, int amplifier) {
        /*
        entity.getWorld().breakBlock()
        if(((PlayerEntity)entity).getWorld().isClient()){
            ((PlayerEntity) entity).sendMessage(Text.of("UPDATING CHICKEN EFFECT"), true);
            // on break block play chicken sound
            entity
        }*/
        super.applyUpdateEffect(entity, amplifier);
    }
}
