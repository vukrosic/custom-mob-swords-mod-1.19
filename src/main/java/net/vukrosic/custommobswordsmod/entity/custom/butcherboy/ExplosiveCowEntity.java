package net.vukrosic.custommobswordsmod.entity.custom.butcherboy;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.mob.WardenEntity;
import net.minecraft.entity.passive.CowEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.loot.function.SetAttributesLootFunction;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraft.world.explosion.Explosion;
import net.vukrosic.custommobswordsmod.command.SetHunterCommand;

public class ExplosiveCowEntity extends CowEntity {

    int explosionMaxTimer = 30;
    int explosionTimer = explosionMaxTimer;
    public ExplosiveCowEntity(EntityType<? extends CowEntity> entityType, World world) {
        super(entityType, world);
    }

    // if it gets close to a playerentity, it explodes


    @Override
    public void tick() {
        // get distance to hunter
        // if distance is less than 2 blocks, explode
        // if no target, set target to closest player
        if(getTarget() == null || !(getTarget() instanceof PlayerEntity)) {
            PlayerEntity closestPlayer = world.getClosestPlayer(this, 40);
            if(closestPlayer != SetHunterCommand.pray){
                setTarget(closestPlayer);
            }
        }
        if(getTarget() != null) {
            if(getTarget() instanceof PlayerEntity) {
                PlayerEntity player = (PlayerEntity) getTarget();
                Vec3d targetPos = player.getPos();
                Vec3d myPos = getPos();
                Vec3d direction = targetPos.subtract(myPos);
                direction = direction.normalize();
                setVelocity(direction.multiply(2));


            }
        }
        explosionTimer--;
        // explode
        if(explosionTimer <= 0) {
            world.createExplosion(this, getX(), getY(), getZ(), 2, true, Explosion.DestructionType.DESTROY);
            this.discard();
        }
        super.tick();
    }

    public static DefaultAttributeContainer.Builder setAttributes(){
        return CowEntity.createCowAttributes();
    }
}
