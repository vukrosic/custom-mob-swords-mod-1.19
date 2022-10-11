package net.vukrosic.custommobswordsmod.mixin;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.mob.Angerable;
import net.minecraft.entity.passive.GolemEntity;
import net.minecraft.entity.passive.IronGolemEntity;
import net.minecraft.sound.SoundEvents;
import net.minecraft.world.World;
import net.vukrosic.custommobswordsmod.entity.custom.chunken.ChunkenEntityGL;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(IronGolemEntity.class)
public abstract class IronGolemEntityMixin extends GolemEntity implements Angerable {
    @Shadow
    private int attackTicksLeft;

    public IronGolemEntityMixin(EntityType<? extends GolemEntity> entityType, World world) {
        super(entityType, world);
    }

    @Inject(method = "tryAttack", at = @At("HEAD"))
    public void tryAttack(Entity target, CallbackInfoReturnable<Boolean> cir) {
        if(target instanceof ChunkenEntityGL) {
            //return;
        }
    }


    @Override
    public void setTarget(@Nullable LivingEntity target) {
        if(!(target instanceof ChunkenEntityGL)) {
            super.setTarget(target);
        }
    }

    /**
     * @author
     * @reason
     */
    @Overwrite
    public boolean tryAttack(Entity target) {
        if(target instanceof ChunkenEntityGL) {
            return false;
        }
        this.attackTicksLeft = 10;
        this.world.sendEntityStatus(this, (byte)4);
        float f = this.getAttackDamage();
        float g = (int)f > 0 ? f / 2.0F + (float)this.random.nextInt((int)f) : f;
        boolean bl = target.damage(DamageSource.mob(this), g);
        if (bl) {
            double var10000;
            if (target instanceof LivingEntity) {
                LivingEntity livingEntity = (LivingEntity)target;
                var10000 = livingEntity.getAttributeValue(EntityAttributes.GENERIC_KNOCKBACK_RESISTANCE);
            } else {
                var10000 = 0.0;
            }

            double d = var10000;
            double e = Math.max(0.0, 1.0 - d);
            target.setVelocity(target.getVelocity().add(0.0, 0.4000000059604645 * e, 0.0));
            this.applyDamageEffects(this, target);
        }

        this.playSound(SoundEvents.ENTITY_IRON_GOLEM_ATTACK, 1.0F, 1.0F);
        return bl;
    }

    @Shadow
    private float getAttackDamage() {
        return 1;
    }
}
