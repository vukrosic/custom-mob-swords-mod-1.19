package net.vukrosic.custommobswordsmod.entity.custom.chunken;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraft.world.explosion.Explosion;
import software.bernie.example.entity.RocketProjectile;

import java.util.List;

public class ChunkenRocketEntity extends RocketProjectile {


    public ChunkenRocketEntity(EntityType<? extends RocketProjectile> entityType, World world) {
        super(entityType, world);
    }

    @Override
    public void doDamage() {
        float q = 4.0F;
        int k = MathHelper.floor(this.getX() - (double) q - 1.0D);
        int l = MathHelper.floor(this.getX() + (double) q + 1.0D);
        int t = MathHelper.floor(this.getY() - (double) q - 1.0D);
        int u = MathHelper.floor(this.getY() + (double) q + 1.0D);
        int v = MathHelper.floor(this.getZ() - (double) q - 1.0D);
        int w = MathHelper.floor(this.getZ() + (double) q + 1.0D);
        List<Entity> list = this.world.getOtherEntities(this,
                new Box((double) k, (double) t, (double) v, (double) l, (double) u, (double) w));
        Vec3d vec3d = new Vec3d(this.getX(), this.getY(), this.getZ());
        for (int x = 0; x < list.size(); ++x) {
            Entity entity = (Entity) list.get(x);
            double y = (double) (MathHelper.sqrt((float) entity.squaredDistanceTo(vec3d)) / q);
            if (y <= 1.0D) {
                if (entity instanceof LivingEntity) {
                    if (this.getOwner() instanceof LivingEntity) {
                        entity.damage(DamageSource.mob((LivingEntity) this.getOwner()), 8);
                    }
                    this.world.createExplosion(this, this.getX(), this.getBodyY(0.0625D), this.getZ(), 0.0F,
                            Explosion.DestructionType.NONE);
                }
            }
        }
    }



}
