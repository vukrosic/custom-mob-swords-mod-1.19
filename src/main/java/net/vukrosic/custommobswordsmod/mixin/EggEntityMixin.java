package net.vukrosic.custommobswordsmod.mixin;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.passive.ChickenEntity;
import net.minecraft.entity.projectile.ArrowEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.entity.projectile.thrown.EggEntity;
import net.minecraft.entity.projectile.thrown.ThrownItemEntity;
import net.minecraft.util.hit.HitResult;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(EggEntity.class)
public abstract class EggEntityMixin extends ThrownItemEntity {


    public EggEntityMixin(EntityType<? extends ThrownItemEntity> entityType, World world) {
        super(entityType, world);
    }

    @Inject(at = @At("HEAD"), method = "onCollision", cancellable = true)
    protected void onCollision(HitResult hitResult, CallbackInfo info) {

        for(int ik = 0; ik < 100; ik++){
                if (!this.world.isClient) {

                        ChickenEntity chickenEntity = (ChickenEntity)EntityType.CHICKEN.create(this.world);
                        chickenEntity.setBreedingAge(-24000);
                        chickenEntity.refreshPositionAndAngles(this.getX(), this.getY() + (float)ik / 100f, this.getZ() + (float)ik / 100f, this.getYaw(), 0.0F);
                        this.world.spawnEntity(chickenEntity);

                }

                this.world.sendEntityStatus(this, (byte)3);
                this.discard();
        }
    }
}

