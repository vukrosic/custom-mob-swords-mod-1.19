package net.vukrosic.custommobswordsmod.entity.custom.summoner;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.entity.model.PlayerEntityModel;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.entity.passive.VillagerEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
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

import java.util.Random;


public class SummonerEntityGL extends PassiveEntity implements IAnimatable {

    public PlayerEntity controllingPlayer;
    private AnimationFactory factory = new AnimationFactory(this);
    int playerEatingTimer = 0;

    public int particleCountdown, maxParticleCountodown = 40;
    public boolean enableParticleCountdown = false;


    public SummonerEntityGL(EntityType<? extends PassiveEntity> entityType, World world) {
        super(entityType, world);

    }

    @Nullable
    @Override
    public PassiveEntity createChild(ServerWorld world, PassiveEntity entity) {
        return null;
    }



    public static DefaultAttributeContainer.Builder setAttributes(){
        return VillagerEntity.createVillagerAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 1000.0D);
    }

    @Override
    public boolean damage(DamageSource source, float amount) {
        return false;
    }

    @Override
    public void tickMovement() {
        if(controllingPlayer != null){
            //copy his movement
            this.setPos(controllingPlayer.getX() - 1, controllingPlayer.getY(), controllingPlayer.getZ());
            //this.setVelocity(controllingPlayer.getVelocity());
            this.setHeadYaw(controllingPlayer.getHeadYaw());
            this.setPitch(controllingPlayer.getPitch());
            this.setYaw(controllingPlayer.getYaw());
            this.setBodyYaw(controllingPlayer.getBodyYaw());
            this.setSneaking(controllingPlayer.isSneaking());
            this.setSprinting(controllingPlayer.isSprinting());
            this.setSwimming(controllingPlayer.isSwimming());
        }
        super.tickMovement();
    }

    private <E extends IAnimatable> PlayState predicate(AnimationEvent<E> event) {
        if (event.isMoving()) {
            if(controllingPlayer != null){
                // get controlling player animation
            }
            event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.geometry.pigzombie.walk", true));
            event.getController().setAnimationSpeed(3);
            return PlayState.CONTINUE;
        }


        event.getController().setAnimationSpeed(1);
        event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.geometry.pigzombie.idle", true));
        return PlayState.CONTINUE;


    }


    private PlayState attackPredicate(AnimationEvent event) {
        MinecraftClient client = MinecraftClient.getInstance();
        if(this.handSwinging && event.getController().getAnimationState().equals(AnimationState.Stopped)) {
            event.getController().markNeedsReload();
            event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.geometry.pigzombie.summon", false));
            event.getController().setAnimationSpeed(1);
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

    // disable hitbox


    @Override
    public void tick() {
        if(enableParticleCountdown){
            if(particleCountdown > 0){
                particleCountdown--;
            }else{
                enableParticleCountdown = false;
                particleCountdown = maxParticleCountodown;
                ServerWorld serverWorld = (ServerWorld) this.world;
                for (int i = 0; i < 50; i++) {
                    Random rand = new Random();
                    double x = this.getX() + (rand.nextDouble() - 0.5) * 2;
                    double y = this.getY() + (rand.nextDouble() - 0.5) * 2;
                    double z = this.getZ() + (rand.nextDouble() - 0.5) * 2;
                    serverWorld.spawnParticles(ParticleTypes.WITCH, x, y + 1, z, 1, 0, 0, 0, 1);
                }
            }
        }
        // get player model and make it invisible
        // disable collisions with controllingPlayer
        // disable collisions with other entities



        // give blindness to player
        for(PlayerEntity hunter : SetHunterCommand.hunters) {
            hunter.addStatusEffect(new StatusEffectInstance(StatusEffects.BLINDNESS, 20, 1, false, false));
        }
        if(SetHunterCommand.pray != null){
            SetHunterCommand.pray.addStatusEffect(new StatusEffectInstance(StatusEffects.BLINDNESS, 20, 1, false, false));
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
        this.dataTracker.set(SUMMONING, attacking);
    }

    @Override
    public boolean isAttacking() {
        return this.dataTracker.get(SUMMONING);
    }

    @Override
    protected void initDataTracker() {
        super.initDataTracker();
        this.dataTracker.startTracking(SUMMONING, false);
    }*/
}
