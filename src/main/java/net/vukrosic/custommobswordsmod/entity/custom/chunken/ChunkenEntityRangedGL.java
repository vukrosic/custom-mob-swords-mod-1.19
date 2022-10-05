package net.vukrosic.custommobswordsmod.entity.custom.chunken;

import net.minecraft.block.BlockState;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.RangedAttackMob;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.passive.MerchantEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.entity.projectile.ProjectileUtil;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.command.CommandManager;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import software.bernie.geckolib3.core.AnimationState;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;


public class ChunkenEntityRangedGL extends HostileEntity implements IAnimatable, RangedAttackMob {

    private AnimationFactory factory = new AnimationFactory(this);




    public ChunkenEntityRangedGL(
        EntityType<? extends HostileEntity> entityType, World world) {
        super(entityType, world);
    }




    @Override
    public void tick() {




/*
        if(playerEatingTimer > 0) {
            playerEatingTimer--;
            if(playerEatingTimer == 0) {
                GettingEatenByChunkenManager.setPlayer(null);
            }
        }*/
        if(getTarget() == null) {
            setTarget(world.getClosestPlayer(this, 40));
        }
        // if distance to target is less than 10, eat him
       /* if (getTarget() != null && getTarget().distanceTo(this) < 4) {
            eatPlayer((PlayerEntity) getTarget());
        }*/
        super.tick();
    }



    public static DefaultAttributeContainer.Builder setAttributes() {
        return HostileEntity.createMobAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 2000.0D)
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 8.0f)
                .add(EntityAttributes.GENERIC_ATTACK_SPEED, 2.0f)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.25f);
    }






    private <E extends IAnimatable> PlayState predicate(AnimationEvent<E> event) {
        if (event.isMoving()) {
            event.getController().setAnimation(new AnimationBuilder().addAnimation(ChunkenPhaseManager.getWalkAnimation(), true));
            return PlayState.CONTINUE;
        }
        /*
        else if (event.isEating()) {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.the_chunken.eat", false));
        }*/

        event.getController().setAnimation(new AnimationBuilder().addAnimation(ChunkenPhaseManager.getIdleAnimation(), true));
        return PlayState.CONTINUE;


    }



    private PlayState attackPredicate(AnimationEvent event) {
        if(this.handSwinging && event.getController().getAnimationState().equals(AnimationState.Stopped)) {
            event.getController().markNeedsReload();
            event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.geometry.chicken.phase5_laser_second_part", false));
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

    @Override
    protected void initGoals() {
        this.goalSelector.add(1, new SwimGoal(this));
        this.goalSelector.add(2, new MeleeAttackGoal(this, 2.2D, false));
        // add ranged attack goal
        this.goalSelector.add(3, new WanderAroundFarGoal(this, 0.75f, 1));
        this.goalSelector.add(4, new LookAroundGoal(this));
/*
        this.targetSelector.add(2, new ActiveTargetGoal(this, PlayerEntity.class, true));
        this.targetSelector.add(2, new ActiveTargetGoal<>(this, PlayerEntity.class, true));
        this.targetSelector.add(2, new ActiveTargetGoal<>(this, MerchantEntity.class, true));*/
    }

    @Override
    public AnimationFactory getFactory() {
        return factory;
    }

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
    }

    @Override
    public void attack(LivingEntity target, float pullProgress) {
        ItemStack itemStack = this.getArrowType(this.getStackInHand(ProjectileUtil.getHandPossiblyHolding(this, Items.BOW)));
        PersistentProjectileEntity persistentProjectileEntity = this.createArrowProjectile(itemStack, pullProgress);
        double d = target.getX() - this.getX();
        double e = target.getBodyY(0.3333333333333333) - persistentProjectileEntity.getY();
        double f = target.getZ() - this.getZ();
        double g = Math.sqrt(d * d + f * f);
        persistentProjectileEntity.setVelocity(d, e + g * 0.20000000298023224, f, 1.6F, (float)(14 - this.world.getDifficulty().getId() * 4));
        this.playSound(SoundEvents.ENTITY_SKELETON_SHOOT, 1.0F, 1.0F / (this.getRandom().nextFloat() * 0.4F + 0.8F));
        this.world.spawnEntity(persistentProjectileEntity);
    }

    protected PersistentProjectileEntity createArrowProjectile(ItemStack arrow, float damageModifier) {
        return ProjectileUtil.createArrowProjectile(this, arrow, damageModifier);
    }
}
