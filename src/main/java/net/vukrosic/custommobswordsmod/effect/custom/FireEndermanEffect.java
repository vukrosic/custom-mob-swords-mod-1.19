package net.vukrosic.custommobswordsmod.effect.custom;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.AttributeContainer;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.text.Text;
import net.vukrosic.custommobswordsmod.entity.custom.PlayerEntityExt;
import org.jetbrains.annotations.Nullable;

public class FireEndermanEffect extends StatusEffect {
    public FireEndermanEffect(StatusEffectCategory statusEffectCategory, int color) {
        super(statusEffectCategory, color);
    }

    @Override
    public void onApplied(LivingEntity entity, AttributeContainer attributes, int amplifier) {
        if(entity instanceof PlayerEntity){
            PlayerEntity player = (PlayerEntity) entity;
            ((PlayerEntityExt) player).setFireEndermenEnabled(true);
        }
        super.onApplied(entity, attributes, amplifier);
    }
/*
    @Override
    public void applyInstantEffect(@Nullable Entity source, @Nullable Entity attacker, LivingEntity target, int amplifier, double proximity) {
        if(target instanceof PlayerEntity){
            PlayerEntity player = (PlayerEntity) target;
            ((PlayerEntityExt) player).setFireEndermenEnabled(true);
            player.sendMessage(Text.of("Fire Enderman enable1111111111111d applyInstantEffect"), false);
        }
        super.applyInstantEffect(source, attacker, target, amplifier, proximity);
    }

    @Override
    public void applyUpdateEffect(LivingEntity entity, int amplifier) {
        if(entity instanceof PlayerEntity){
            PlayerEntity player = (PlayerEntity) entity;
            ((PlayerEntityExt) player).setFireEndermenEnabled(true);
            player.sendMessage(Text.of("Fire Enderman enable1111111111111d applyUpdateEffect"), false);
        }
        super.applyUpdateEffect(entity, amplifier);
    }
*/
    @Override
    public void onRemoved(LivingEntity entity, AttributeContainer attributes, int amplifier) {
        if(entity instanceof PlayerEntity){
            PlayerEntity player = (PlayerEntity) entity;
            ((PlayerEntityExt) player).setFireEndermenEnabled(false);
        }
        super.onRemoved(entity, attributes, amplifier);
    }
}
