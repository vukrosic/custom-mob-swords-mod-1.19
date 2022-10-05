package net.vukrosic.custommobswordsmod.entity.custom;

import net.minecraft.block.BlockState;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.mob.ZombifiedPiglinEntity;
import net.minecraft.entity.passive.MerchantEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.command.CommandManager;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.tag.FluidTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib3.core.AnimationState;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;


public class EnderZoglinEntityGL extends ZombifiedPiglinEntity implements IAnimatable {

    private AnimationFactory factory = new AnimationFactory(this);
    int playerEatingTimer = 0;

    boolean growing = false;

    private static final TrackedData<Boolean> ATTACKING =
            DataTracker.registerData(EnderZoglinEntityGL.class, TrackedDataHandlerRegistry.BOOLEAN);

    public EnderZoglinEntityGL(EntityType<? extends ZombifiedPiglinEntity> entityType, World world) {
        super(entityType, world);

    }


/*

    public static DefaultAttributeContainer.Builder setAttributes() {
        return HostileEntity.createMobAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 20.0D)
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 8.0f)
                .add(EntityAttributes.GENERIC_ATTACK_SPEED, 2.0f)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.25f);
    }*/
    public static DefaultAttributeContainer.Builder setAttributes(){
        return ZombifiedPiglinEntity.createZombifiedPiglinAttributes();
    }





    private <E extends IAnimatable> PlayState predicate(AnimationEvent<E> event) {
        if (event.isMoving()) {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.enderzoglin.walk", true));
            return PlayState.CONTINUE;
        }
        /*
        else if (event.isEating()) {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.the_chunken.eat", false));
        }*/

        event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.enderzoglin.idle", true));
        return PlayState.CONTINUE;


    }


    private PlayState attackPredicate(AnimationEvent event) {
        if(this.handSwinging && event.getController().getAnimationState().equals(AnimationState.Stopped)) {
            event.getController().markNeedsReload();
            event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.enderzoglin.attack", false));
            this.handSwinging = false;
        }

        return PlayState.CONTINUE;
    }

    @Override
    public void registerControllers(AnimationData animationData) {
        animationData.addAnimationController(new AnimationController(this, "controller",
                0, this::predicate));

        animationData.addAnimationController(new AnimationController(this, "attackController",
                0, this::attackPredicate));
    }


/*
    @Override
    protected void initGoals() {
        this.goalSelector.add(1, new SwimGoal(this));
        this.goalSelector.add(2, new MeleeAttackGoal(this, 1.2D, false));
        this.goalSelector.add(3, new WanderAroundFarGoal(this, 0.75f, 1));
        this.goalSelector.add(4, new LookAroundGoal(this));

        this.targetSelector.add(2, new ActiveTargetGoal<>(this, PlayerEntity.class, true));
        this.targetSelector.add(2, new ActiveTargetGoal<>(this, MerchantEntity.class, true));
    }*/


    @Override
    public void tick() {
        double randomNumber = Math.random();
        if(super.getTarget() != null) {
            if (randomNumber > 0.80) {
                // if distance to target is greater than 10, teleport to target
                if (this.distanceTo(super.getTarget()) > 10) {
                    this.teleportRandomly();
                }
            }

            /*if (randomNumber > 0.6) {*/
                // break a random block in a 10 block radius
                int breakBlockRadius = 6;
                BlockPos pos = new BlockPos(world.getRandom().nextInt(breakBlockRadius), world.getRandom().nextInt(breakBlockRadius), world.getRandom().nextInt(breakBlockRadius));
                world.breakBlock(new BlockPos(super.getTarget().getX() + pos.getX() - breakBlockRadius/2, super.getTarget().getY() - pos.getY(), super.getTarget().getZ() + pos.getZ() - breakBlockRadius/2), false);
                BlockPos pos1 = new BlockPos(world.getRandom().nextInt(breakBlockRadius), world.getRandom().nextInt(breakBlockRadius), world.getRandom().nextInt(breakBlockRadius));
                world.breakBlock(new BlockPos(super.getTarget().getX() + pos1.getX() - breakBlockRadius/2, super.getTarget().getY() - pos1.getY(), super.getTarget().getZ() + pos1.getZ() - breakBlockRadius/2), false);
            //}
        }

        super.tick();
    }


    @Override
    public AnimationFactory getFactory() {
        return factory;
    }


    @Override
    public void setTarget(@Nullable LivingEntity target) {
        super.setTarget(target);
    }


    boolean teleportRandomly() {
        if (this.world.isClient() || !this.isAlive()) {
            return false;
        }
        int Radius = 8;
        double d = super.getTarget().getX() + (this.random.nextDouble() - 0.5) * Radius;
        double e = super.getTarget().getY() + (double)(this.random.nextInt(Radius) - Radius/2);
        double f = super.getTarget().getZ() + (this.random.nextDouble() - 0.5) * Radius;
        return this.teleportTo(d, e, f);
    }



    private boolean teleportTo(double x, double y, double z) {
        BlockPos.Mutable mutable = new BlockPos.Mutable(x, y, z);
        while (mutable.getY() > this.world.getBottomY() && !this.world.getBlockState(mutable).getMaterial().blocksMovement()) {
            mutable.move(Direction.DOWN);
        }
        BlockState blockState = this.world.getBlockState(mutable);
        boolean bl = blockState.getMaterial().blocksMovement();
        boolean bl2 = blockState.getFluidState().isIn(FluidTags.WATER);
        if (!bl || bl2) {
            return false;
        }
        Vec3d vec3d = this.getPos();
        boolean bl3 = this.teleport(x, y, z, true);
        if (bl3) {
            //this.world.emitGameEvent(GameEvent.TELEPORT, vec3d, GameEvent.Emitter.of(this));
            if (!this.isSilent()) {
                this.world.playSound(null, this.prevX, this.prevY, this.prevZ, SoundEvents.ENTITY_ENDERMAN_TELEPORT, this.getSoundCategory(), 1.0f, 1.0f);
                this.playSound(SoundEvents.ENTITY_ENDERMAN_TELEPORT, 1.0f, 1.0f);
            }
        }
        return bl3;
    }
/*
    @Override
    protected SoundEvent getAmbientSound() {
        return SoundEvents.ENTITY_DOLPHIN_AMBIENT;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource source) {
        return SoundEvents.ENTITY_CHICKEN_HURT;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return SoundEvents.ENTITY_CHICKEN_DEATH;
    }

    @Override
    protected void playStepSound(BlockPos pos, BlockState state) {
        this.playSound(SoundEvents.ENTITY_CHICKEN_STEP, 0.15f, 1.0f);
    }*/

/*
    @Override
    public void setAttacking(boolean attacking) {
        this.dataTracker.set(ATTACKING, attacking);
    }

    @Override
    public boolean isAttacking() {
        return this.dataTracker.get(ATTACKING);
    }

    @Override
    protected void initDataTracker() {
        super.initDataTracker();
        this.dataTracker.startTracking(ATTACKING, false);
    }*/
}
