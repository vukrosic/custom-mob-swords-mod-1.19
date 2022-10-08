package net.vukrosic.custommobswordsmod.entity.custom.chunken;

import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.passive.CowEntity;
import net.minecraft.entity.passive.MerchantEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ArrowEntity;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.command.CommandManager;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.vukrosic.custommobswordsmod.entity.ModEntities;
import net.vukrosic.custommobswordsmod.entity.custom.frogking.FrogKingTongueProjectileEntity;
import software.bernie.geckolib3.core.AnimationState;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;


public class ChunkenEntityGL extends HostileEntity implements IAnimatable {
    int hitsPerPhase = 4;
    int hitsToNextPhase = hitsPerPhase;
    private AnimationFactory factory = new AnimationFactory(this);

    boolean hunterEaten = false;
    boolean rotatingTowardsPlayer = false;



    public ChunkenEntityGL(
        EntityType<? extends HostileEntity> entityType, World world) {
        super(entityType, world);
        ChunkenPhaseManager.resetChunkenPhase();
    }



    @Override
    public void tick() {
        if(getTarget() == null) {
            setTarget(world.getClosestPlayer(this, 40));
        }
        if(ChunkenPhaseManager.chunkenPhase == 4) {
            // if distance to target is 2 blocks
            if(this.getTarget() != null && this.getTarget() instanceof PlayerEntity){
                PlayerEntity player = (PlayerEntity) this.getTarget();
            }
        }
        super.tick();
    }




    @Override
    public boolean damage(DamageSource source, float amount) {
        if(!world.isClient() && ChunkenPhaseManager.chunkenPhase < 5) {
            if (source.getAttacker() instanceof PlayerEntity n) {
                PlayerEntity attacker = (PlayerEntity) source.getAttacker();
                MinecraftServer server = source.getSource().getServer();
                CommandManager commandManager = server.getPlayerManager().getServer().getCommandManager();
                commandManager.executeWithPrefix(attacker.getCommandSource(), "/scale add 0.1 @e[type=custommobswordsmod:chunkengl]");
                hitsToNextPhase--;
                if(hitsToNextPhase == 0 && ChunkenPhaseManager.chunkenPhase < 4) {
                    ChunkenPhaseManager.chunkenPhase++;
                    hitsToNextPhase = hitsPerPhase;
                    world.getPlayers().get(0).sendMessage(Text.of("going to the next phase = " + ChunkenPhaseManager.chunkenPhase), false);
                }
            }
        }
        return super.damage(source, amount);
    }

    @Override
    public boolean tryAttack(Entity target) {
        if(distanceTo(target) < 2.5) {
            return false;
        }
        this.lookAtEntity(this.getTarget(), 360, 360);
        // this.getTarget().setInvisible(true);
        if(ChunkenPhaseManager.chunkenPhase == 4) {
            rotatingTowardsPlayer = true;
            FrogKingTongueProjectileEntity projectile = new FrogKingTongueProjectileEntity(ModEntities.FROG_KING_TONGUE_PROJECTILE, world);
            projectile.refreshPositionAndAngles(this.getX(), this.getY() + 1.5, this.getZ(), this.getYaw(), this.getPitch());
            projectile.setPosition(this.getX(), this.getY() + 3.5, this.getZ());
            projectile.setVelocity(projectile.getRotationVector().multiply(3));
            projectile.setNoGravity(true);
            world.spawnEntity(projectile);
        }
        return super.tryAttack(target);
    }




    /*===================================================
    =================================================*/

    public static DefaultAttributeContainer.Builder setAttributes() {
        return HostileEntity.createMobAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 2000.0D)
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 2.5f)
                .add(EntityAttributes.GENERIC_ATTACK_SPEED, 2.0f)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.15f);
    }






    private <E extends IAnimatable> PlayState predicate(AnimationEvent<E> event) {
        if (event.isMoving()) {
            event.getController().setAnimation(new AnimationBuilder().addAnimation(ChunkenPhaseManager.getWalkAnimation(), true));
            return PlayState.CONTINUE;
        }
        else {
            event.getController().setAnimation(new AnimationBuilder().addAnimation(ChunkenPhaseManager.getIdleAnimation(), true));
            return PlayState.CONTINUE;
        }
    }



    private PlayState attackPredicate(AnimationEvent event) {
        if(this.handSwinging && event.getController().getAnimationState().equals(AnimationState.Stopped)) {



            event.getController().markNeedsReload();
            if(ChunkenPhaseManager.chunkenPhase < 4) {
                event.getController().setAnimation(new AnimationBuilder().addAnimation(ChunkenPhaseManager.getAttackAnimation(), false));
                event.getController().setAnimationSpeed(2);
            }
            else {
                if(!hunterEaten){
                    event.getController().setAnimationSpeed(1);
                    event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.geometry.chicken.phase5_skills_attack", false));
                    hunterEaten = true;
                }
                else{
                    event.getController().setAnimationSpeed(1);
                    event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.geometry.chicken.phase5_laser_second_part", false));
                    /*
                    ChunkenLaserEntityGL laser = new ChunkenLaserEntityGL(ModEntities.CHUNKEN_LASERGL, world);
                    laser.refreshPositionAndAngles(this.getX(), this.getY(), this.getZ(), 0, 0);
                    laser.setPos(this.getX(), this.getY() + 1.5, this.getZ());
                    world.spawnEntity(laser);
                    // set velocity towards player
                    Vec3d dir = this.getTarget().getPos().subtract(this.getPos()).normalize();
                    laser.setVelocity(dir.multiply(2));*/
                }

            }


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
        this.goalSelector.add(3, new WanderAroundFarGoal(this, 0.75f, 1));
        this.goalSelector.add(4, new LookAroundGoal(this));

        this.targetSelector.add(2, new ActiveTargetGoal<>(this, PlayerEntity.class, true));
        this.targetSelector.add(2, new ActiveTargetGoal<>(this, MerchantEntity.class, true));
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
}
