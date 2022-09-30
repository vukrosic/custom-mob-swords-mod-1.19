package net.vukrosic.custommobswordsmod.entity.custom;

import com.google.common.collect.UnmodifiableIterator;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.*;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.mob.ZombifiedPiglinEntity;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.CowEntity;
import net.minecraft.entity.passive.FrogEntity;
import net.minecraft.entity.passive.HorseEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.predicate.entity.EntityPredicates;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Arm;
import net.minecraft.util.Hand;
import net.minecraft.util.math.*;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class BigFrogEntity extends FrogEntity {

    protected float jumpStrength = 3;
    protected boolean inAir;
    public BigFrogEntity(EntityType<? extends AnimalEntity> entityType, World world) {
        super(entityType, world);
    }

    public static DefaultAttributeContainer.Builder setAttributes(){
        return FrogEntity.createFrogAttributes();
    }

    float flyingSpeed = 0;


    public void updatePassengerPosition(Entity passenger) {
        super.updatePassengerPosition(passenger);
        if (passenger instanceof MobEntity) {
            MobEntity mobEntity = (MobEntity) passenger;
            this.bodyYaw = mobEntity.bodyYaw;
        }
        passenger.setPosition(this.getX(), this.getY() + this.getMountedHeightOffset() + passenger.getHeightOffset(), this.getZ());
        if (passenger instanceof LivingEntity) {
            ((LivingEntity) passenger).bodyYaw = this.bodyYaw;
        }
    }

    @Override
    public void tickMovement() {
        // set target to closest cow
        if (this.getTarget() == null) {
            this.world.getEntitiesByClass(CowEntity.class, this.getBoundingBox().expand(10), EntityPredicates.EXCEPT_SPECTATOR).forEach((cow) -> {
                System.out.println("cow found88888888888888888888888888");
                this.setTarget(cow);
            });
        }
        /*
        LivingEntity livingEntity = getMainPassenger();
        if (livingEntity != null){
            setVelocity(getVelocity().add(0,(livingEntity.getPitch()/200)*-1,0));
        }*/
        super.tickMovement();
    }

    private LivingEntity getMainPassenger(){
        LivingEntity livingEntity = null;
        for (Entity entity : getPassengerList()){
            if (entity instanceof LivingEntity){
                livingEntity = (LivingEntity) entity;
            }
        }
        return livingEntity;
    }


/*
    protected void playStepSound(BlockPos pos, BlockState state) {
        if (!state.getMaterial().isLiquid()) {
            BlockState blockState = this.world.getBlockState(pos.up());
            BlockSoundGroup blockSoundGroup = state.getSoundGroup();
            if (blockState.isOf(Blocks.SNOW)) {
                blockSoundGroup = blockState.getSoundGroup();
            }

            if (this.hasPassengers() && this.playExtraHorseSounds) {
                ++this.soundTicks;
                if (this.soundTicks > 5 && this.soundTicks % 3 == 0) {
                    this.playWalkSound(blockSoundGroup);
                } else if (this.soundTicks <= 5) {
                    this.playSound(SoundEvents.ENTITY_HORSE_STEP_WOOD, blockSoundGroup.getVolume() * 0.15F, blockSoundGroup.getPitch());
                }
            } else if (blockSoundGroup == BlockSoundGroup.WOOD) {
                this.playSound(SoundEvents.ENTITY_HORSE_STEP_WOOD, blockSoundGroup.getVolume() * 0.15F, blockSoundGroup.getPitch());
            } else {
                this.playSound(SoundEvents.ENTITY_HORSE_STEP, blockSoundGroup.getVolume() * 0.15F, blockSoundGroup.getPitch());
            }

        }
    }*/
/*
    public void travel(Vec3d movementInput) {
        if (this.isAlive()) {
            LivingEntity livingEntity = (LivingEntity) this.getPrimaryPassenger();
            if (this.hasPassengers() && livingEntity != null) {
                this.setYaw(livingEntity.getYaw());
                this.prevYaw = this.getYaw();
                this.setPitch(livingEntity.getPitch() * 0.5F);
                this.setRotation(this.getYaw(), this.getPitch());
                this.bodyYaw = this.getYaw();
                this.headYaw = this.bodyYaw;
                float f = livingEntity.sidewaysSpeed * 0.5F;
                float g = livingEntity.forwardSpeed;
                if (g <= 0.0F) {
                    g *= 0.25F;
                    //this.soundTicks = 0;
                }

                if (this.onGround && this.jumpStrength == 0.0F && !this.jumping) {
                    f = 0.0F;
                    g = 0.0F;
                }

                if (this.jumpStrength > 0.0F && !this.isInAir() && this.onGround) {
                    double d = this.getJumpStrength() * (double)this.jumpStrength * (double)this.getJumpVelocityMultiplier();
                    double e = d + this.getJumpBoostVelocityModifier();
                    Vec3d vec3d = this.getVelocity();
                    this.setVelocity(vec3d.x, e, vec3d.z);
                    this.setInAir(true);
                    this.velocityDirty = true;
                    if (g > 0.0F) {
                        float h = MathHelper.sin(this.getYaw() * 0.017453292F);
                        float i = MathHelper.cos(this.getYaw() * 0.017453292F);
                        this.setVelocity(this.getVelocity().add((double)(-0.4F * h * this.jumpStrength), 0.0, (double)(0.4F * i * this.jumpStrength)));
                    }

                    this.jumpStrength = 0.0F;
                }

                this.airStrafingSpeed = this.getMovementSpeed() * 0.1F;
                if (this.isLogicalSideForUpdatingMovement()) {
                    this.setMovementSpeed((float)this.getAttributeValue(EntityAttributes.GENERIC_MOVEMENT_SPEED));
                    super.travel(new Vec3d((double)f, movementInput.y, (double)g));
                } else if (livingEntity instanceof PlayerEntity) {
                    this.setVelocity(Vec3d.ZERO);
                }

                if (this.onGround) {
                    this.jumpStrength = 0.0F;
                    this.setInAir(false);
                }

                this.updateLimbs(this, false);
                this.tryCheckBlockCollision();
            } else {
                this.airStrafingSpeed = 0.02F;
                super.travel(movementInput);
            }
        }
    }*/


    public void travel(Vec3d movementInput) {
        if (this.isAlive()) {
            if (this.hasPassengers()) {
                LivingEntity livingEntity = getMainPassenger();

                if (livingEntity != null){
                    this.setBodyYaw(livingEntity.getYaw());
                    this.prevYaw = this.getYaw();
                    this.setPitch(livingEntity.getPitch() * 0.5F);
                    this.setRotation(this.bodyYaw, this.getPitch());
                    this.bodyYaw = this.getYaw();
                    this.headYaw = this.bodyYaw;
                    float f = livingEntity.sidewaysSpeed * 0.5F;
                    float g = livingEntity.forwardSpeed;

                    this.flyingSpeed = this.getMovementSpeed() * 0.1F;
                    this.setMovementSpeed((float) this.getAttributeValue(EntityAttributes.GENERIC_MOVEMENT_SPEED));
                    double finalYTravel = movementInput.y;
                    super.travel(new Vec3d((double) f, finalYTravel, (double) g));

                    this.updateLimbs(this, false);
                    this.tryCheckBlockCollision();
                }
            }
            else {
                this.flyingSpeed = 0.02F;
                super.travel(movementInput);
            }
        }
    }


    // make this frog eat a cow





    public void setInAir(boolean inAir) {
        this.inAir = inAir;
    }

    public boolean isInAir() {
        return inAir;
    }

    private double getJumpStrength() {
        return jumpStrength;
    }


    public boolean canBeControlledByRider() {
        return this.getPrimaryPassenger() instanceof LivingEntity;
    }

    @Override
    public ActionResult interactMob(PlayerEntity player, Hand hand) {
        putPlayerOnBack(player);
        return super.interactMob(player, hand);
    }

    protected void putPlayerOnBack(PlayerEntity player) {
        if (!this.world.isClient) {
            player.setYaw(this.getYaw());
            player.setPitch(this.getPitch());
            player.startRiding(this);
        }
    }





/*
    public void travel(Vec3d movementInput) {
        if (this.isAlive()) {
            LivingEntity livingEntity = this.getPrimaryPassenger();
            if (this.hasPassengers() && livingEntity != null) {
                this.setYaw(livingEntity.getYaw());
                this.prevYaw = this.getYaw();
                this.setPitch(livingEntity.getPitch() * 0.5F);
                this.setRotation(this.getYaw(), this.getPitch());
                this.bodyYaw = this.getYaw();
                this.headYaw = this.bodyYaw;
                float f = livingEntity.sidewaysSpeed * 0.5F;
                float g = livingEntity.forwardSpeed;
                if (g <= 0.0F) {
                    g *= 0.25F;
                    this.soundTicks = 0;
                }

                if (this.onGround && this.jumpStrength == 0.0F && this.isAngry() && !this.jumping) {
                    f = 0.0F;
                    g = 0.0F;
                }

                if (this.jumpStrength > 0.0F && !this.isInAir() && this.onGround) {
                    double d = this.getJumpStrength() * (double)this.jumpStrength * (double)this.getJumpVelocityMultiplier();
                    double e = d + this.getJumpBoostVelocityModifier();
                    Vec3d vec3d = this.getVelocity();
                    this.setVelocity(vec3d.x, e, vec3d.z);
                    this.setInAir(true);
                    this.velocityDirty = true;
                    if (g > 0.0F) {
                        float h = MathHelper.sin(this.getYaw() * 0.017453292F);
                        float i = MathHelper.cos(this.getYaw() * 0.017453292F);
                        this.setVelocity(this.getVelocity().add((double)(-0.4F * h * this.jumpStrength), 0.0, (double)(0.4F * i * this.jumpStrength)));
                    }

                    this.jumpStrength = 0.0F;
                }

                this.airStrafingSpeed = this.getMovementSpeed() * 0.1F;
                if (this.isLogicalSideForUpdatingMovement()) {
                    this.setMovementSpeed((float)this.getAttributeValue(EntityAttributes.GENERIC_MOVEMENT_SPEED));
                    super.travel(new Vec3d((double)f, movementInput.y, (double)g));
                } else if (livingEntity instanceof PlayerEntity) {
                    this.setVelocity(Vec3d.ZERO);
                }

                if (this.onGround) {
                    this.jumpStrength = 0.0F;
                    this.setInAir(false);
                }

                this.updateLimbs(this, false);
                this.tryCheckBlockCollision();
            } else {
                this.airStrafingSpeed = 0.02F;
                super.travel(movementInput);
            }
        }
    }*/

    /*
    @Nullable
    public LivingEntity getPrimaryPassenger() {
        if (this.isSaddled()) {
            Entity var2 = this.getFirstPassenger();
            if (var2 instanceof LivingEntity) {
                LivingEntity livingEntity = (LivingEntity)var2;
                return livingEntity;
            }
        }

        return null;
    }*/

    protected void playJumpSound() {
        this.playSound(SoundEvents.ENTITY_HORSE_JUMP, 0.4F, 1.0F);
    }

/*
    @Nullable
    private Vec3d locateSafeDismountingPos(Vec3d offset, LivingEntity passenger) {
        double d = this.getX() + offset.x;
        double e = this.getBoundingBox().minY;
        double f = this.getZ() + offset.z;
        BlockPos.Mutable mutable = new BlockPos.Mutable();
        UnmodifiableIterator var10 = passenger.getPoses().iterator();

        while(var10.hasNext()) {
            EntityPose entityPose = (EntityPose)var10.next();
            mutable.set(d, e, f);
            double g = this.getBoundingBox().maxY + 0.75;

            while(true) {
                double h = this.world.getDismountHeight(mutable);
                if ((double)mutable.getY() + h > g) {
                    break;
                }

                if (Dismounting.canDismountInBlock(h)) {
                    Box box = passenger.getBoundingBox(entityPose);
                    Vec3d vec3d = new Vec3d(d, (double)mutable.getY() + h, f);
                    if (Dismounting.canPlaceEntityAt(this.world, passenger, box.offset(vec3d))) {
                        passenger.setPose(entityPose);
                        return vec3d;
                    }
                }

                mutable.move(Direction.UP);
                if (!((double)mutable.getY() < g)) {
                    break;
                }
            }
        }

        return null;
    }

    public Vec3d updatePassengerForDismount(LivingEntity passenger) {
        Vec3d vec3d = getPassengerDismountOffset((double)this.getWidth(), (double)passenger.getWidth(), this.getYaw() + (passenger.getMainArm() == Arm.RIGHT ? 90.0F : -90.0F));
        Vec3d vec3d2 = this.locateSafeDismountingPos(vec3d, passenger);
        if (vec3d2 != null) {
            return vec3d2;
        } else {
            Vec3d vec3d3 = getPassengerDismountOffset((double)this.getWidth(), (double)passenger.getWidth(), this.getYaw() + (passenger.getMainArm() == Arm.LEFT ? 90.0F : -90.0F));
            Vec3d vec3d4 = this.locateSafeDismountingPos(vec3d3, passenger);
            return vec3d4 != null ? vec3d4 : this.getPos();
        }
    }*/
}
