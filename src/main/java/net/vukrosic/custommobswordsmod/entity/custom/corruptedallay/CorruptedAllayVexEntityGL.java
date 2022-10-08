package net.vukrosic.custommobswordsmod.entity.custom.corruptedallay;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.TargetPredicate;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.mob.PathAwareEntity;
import net.minecraft.entity.mob.VexEntity;
import net.minecraft.entity.passive.AllayEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.raid.RaiderEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.vukrosic.custommobswordsmod.command.SetHunterCommand;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib3.core.AnimationState;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

public class CorruptedAllayVexEntityGL extends VexEntity implements IAnimatable {
    private AnimationFactory factory = new AnimationFactory(this);
    boolean visible = false;
    PlayerEntity ownerPlayer;
    int xp = 0;
    public CorruptedAllayVexEntityGL(EntityType<? extends VexEntity> entityType, World world) {
        super(entityType, world);
        if(SetHunterCommand.pray != null) {
            ownerPlayer = SetHunterCommand.pray;
        }
    }

/*
    @Override
    protected void initGoals() {

        this.goalSelector.add(0, new SwimGoal(this));
        this.goalSelector.add(4, new MeleeAttackGoal(this, 2.2D, false));
        this.goalSelector.add(9, new LookAtEntityGoal(this, PlayerEntity.class, 3.0F, 1.0F));
        this.goalSelector.add(10, new LookAtEntityGoal(this, MobEntity.class, 8.0F));
        this.targetSelector.add(1, (new RevengeGoal(this, new Class[]{RaiderEntity.class})).setGroupRevenge(new Class[0]));
        this.targetSelector.add(2, new TrackOwnerTargetGoal(this));
        this.targetSelector.add(3, new ActiveTargetGoal(this, PlayerEntity.class, true));
    }*/

    @Override
    public void tick() {
        // if somebody is attacking ownerPlayer, then this entity will attack them
        if(ownerPlayer != null) {
            if(ownerPlayer.getAttacker() != null) {
                this.setTarget(ownerPlayer.getAttacker());
            }
            else if(ownerPlayer.getAttacking() != null) {
                this.setTarget(ownerPlayer.getAttacking());
            }
            else {
                this.setTarget(null);
            }
        }

        LivingEntity livingEntity = this.getTarget();
        if (livingEntity != null) {
            if (this.getBoundingBox().intersects(livingEntity.getBoundingBox())) {
                this.tryAttack(livingEntity);
                this.setCharging(false);
            } else {
                double d = this.squaredDistanceTo(livingEntity);
                if (d < 9.0) {
                    Vec3d vec3d = livingEntity.getEyePos();
                    this.moveControl.moveTo(vec3d.x, vec3d.y, vec3d.z, 14.0);
                }
            }

        }


        checkIfHuntersSee();
        super.tick();
    }

    @Override
    public void setCharging(boolean charging) {
        return;
        //super.setCharging(charging);
    }

    @Override
    public boolean onKilledOther(ServerWorld world, LivingEntity other) {
        xp += other.getXpToDrop();
        if(xp >= 10){
            //send message into chat
            world.getPlayers().get(0).sendMessage(Text.of("You gained " + other.getXpToDrop() + " xp corrupted allay entities!"), false);
            this.addStatusEffect(new StatusEffectInstance(StatusEffects.GLOWING, 1000000, 0, false, false));
        }
        return super.onKilledOther(world, other);
    }

    void checkIfHuntersSee() {
        PetCorruptedAllayManager.isBeingLookedAt = false;
        if (SetHunterCommand.hunters != null) {
            SetHunterCommand.hunters.forEach(hunter -> {
                if(hunter.canSee(this)) {
                    PetCorruptedAllayManager.isBeingLookedAt = true;
                }
            });
        }
    }


    class TrackOwnerTargetGoal extends TrackTargetGoal {
        private final TargetPredicate targetPredicate = TargetPredicate.createNonAttackable().ignoreVisibility().ignoreDistanceScalingFactor();

        public TrackOwnerTargetGoal(PathAwareEntity mob) {
            super(mob, false);
        }

        public boolean canStart() {
            return ownerPlayer != null && ownerPlayer.getAttacking() != null && this.canTrack(ownerPlayer.getAttacking(), this.targetPredicate);
        }

        public void start() {
            CorruptedAllayVexEntityGL.this.setTarget(CorruptedAllayVexEntityGL.this.ownerPlayer.getAttacking());
            super.start();
        }
    }

    /*==================*/

    private <E extends IAnimatable> PlayState predicate(AnimationEvent<E> event) {
        if (event.isMoving()) {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.currptedallay.fly", true));
            return PlayState.CONTINUE;
        }
        else {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.currptedallay.fly", true));
            return PlayState.CONTINUE;
        }
    }

    private PlayState attackPredicate(AnimationEvent event) {
        if(this.handSwinging && event.getController().getAnimationState().equals(AnimationState.Stopped)) {
            event.getController().markNeedsReload();
            event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.currptedallay.attack", false));
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
    public AnimationFactory getFactory() {
        return factory;
    }

    public static DefaultAttributeContainer.Builder setAttributes() {
        return VexEntity.createVexAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 2000.0D);
    }



}
