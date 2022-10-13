package net.vukrosic.custommobswordsmod.entity.custom.chunken;

import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.passive.MerchantEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Items;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.vukrosic.custommobswordsmod.command.SetHunterCommand;
import net.vukrosic.custommobswordsmod.entity.ModEntities;
import net.vukrosic.custommobswordsmod.entity.custom.HunterEggEntity;
import net.vukrosic.custommobswordsmod.entity.custom.PlayerEntityExt;
import net.vukrosic.custommobswordsmod.item.ModItems;
import net.vukrosic.custommobswordsmod.item.custom.HunterEggItem;
import net.vukrosic.custommobswordsmod.particle.ModParticles;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib3.core.AnimationState;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

import java.util.ArrayList;
import java.util.Random;


public class ChunkenEntityGL extends HostileEntity implements IAnimatable {
    int hitsToNextPhase = ChunkenPhaseManager.hitsPerPhase;
    private AnimationFactory factory = new AnimationFactory(this);

    int attackTimer, maxAttackTimer = 10;
    boolean canAttack = false;


    int poopEggTimer = 40;
    boolean hasEggToPoop = false;

    public ChunkenEntityGL(
        EntityType<? extends HostileEntity> entityType, World world) {
        super(entityType, world);
    }



    @Override
    public void tick() {
        if(hasEggToPoop){
            poopEggTimer--;
            if(poopEggTimer <= 0){
                hasEggToPoop = false;
                poopEggTimer = 40;
                // drop item
                this.dropItem(ModItems.HUNTER_EGG_ITEM);
            }
        }

        if(getTarget() == null && SetHunterCommand.hunters.size() > 0){
            ArrayList<Float> distances = new ArrayList<>();
            for(PlayerEntity hunter : SetHunterCommand.hunters){
                float distance = this.distanceTo(hunter);
                distances.add(distance);
            }
            // get index of closest hunter
            int index = distances.indexOf(distances.stream().min(Float::compare).get());
            setTarget(SetHunterCommand.hunters.get(index));
        }
        if(ChunkenPhaseManager.chunkenPhase == 4) {
            attackTimer--;
            if(attackTimer <= 5 && canAttack) {
                ShootRocket();
                if(attackTimer <= 0) {
                    attackTimer = maxAttackTimer;
                    canAttack = false;
                }
            }
        }
        super.tick();
    }


    @Override
    public void setTarget(@Nullable LivingEntity target) {
        if(target != null && target instanceof PlayerEntity) {
            PlayerEntity player = (PlayerEntity) target;
            if(SetHunterCommand.pray.getUuid() == player.getUuid()) {
                return;
            }
        }
        if(ChunkenPhaseManager.chunkenPhase == 3) {
            for(PlayerEntity player : world.getPlayers()) {
                if(player.getName().getString() == "Goldactual") {
                    super.setTarget(player);
                }
            }
        }
        else {
            if (target instanceof PlayerEntity) {
                super.setTarget(target);
            } else {
                // get closest player
                PlayerEntity closestPlayer = world.getClosestPlayer(this, 40);
                super.setTarget(closestPlayer);
            }
        }
    }

    @Override
    public boolean damage(DamageSource source, float amount) {
        if(!world.isClient() && ChunkenPhaseManager.chunkenPhase <= 2) {
            if (source.getAttacker() instanceof PlayerEntity n) {
                PlayerEntity attacker = (PlayerEntity) source.getAttacker();
                MinecraftServer server = source.getSource().getServer();
                CommandManager commandManager = server.getPlayerManager().getServer().getCommandManager();
                float scale = ChunkenPhaseManager.scalePerPhase / ChunkenPhaseManager.hitsPerPhase;
                String scaleString = String.valueOf(scale);
                String command = "/scale add " + scaleString + " @e[type=custommobswordsmod:chunkengl]";
                /*attacker.sendMessage(Text.of("IS IT THE SAME: " + command.equals("/scale add 2 @e[type=custommobswordsmod:chunkengl]")), false);
                attacker.sendMessage(Text.of(("/scale add 2 @e[type=custommobswordsmod:chunkengl]")), false);*/
                commandManager.executeWithPrefix(attacker.getCommandSource(), command);
                hitsToNextPhase--;
                if(hitsToNextPhase <= 0 && ChunkenPhaseManager.chunkenPhase < 3) {
                    ChunkenPhaseManager.chunkenPhase++;
                    hitsToNextPhase = ChunkenPhaseManager.hitsPerPhase;
                }
            }
        }
        return super.damage(source, amount);
    }

    @Override
    public boolean tryAttack(Entity target) {
        if(target != null && !(target instanceof PlayerEntity)) {
            return false;
        }

        if (ChunkenPhaseManager.chunkenPhase == 0 || ChunkenPhaseManager.chunkenPhase == 1) {
            if (distanceTo(target) > 2.5) {
                return false;
            }
        }

        if (ChunkenPhaseManager.chunkenPhase == 2 || ChunkenPhaseManager.chunkenPhase == 3) {
            if (distanceTo(target) > 3.5) {
                return false;
            }
        }

        if (ChunkenPhaseManager.chunkenPhase == 4) {
            if (distanceTo(target) > 15) {
                return false;
            }
        }

        if (ChunkenPhaseManager.chunkenPhase == 0 ||
            ChunkenPhaseManager.chunkenPhase == 1 ||
            ChunkenPhaseManager.chunkenPhase == 2) {
            return super.tryAttack(target);
        }

        if (ChunkenPhaseManager.chunkenPhase == 3) {
            this.lookAtEntity(this.getTarget(), 360, 360);
            eatHunter(target);
            ChunkenPhaseManager.chunkenPhase = 4;
            return super.tryAttack(target);
        }

        if (ChunkenPhaseManager.chunkenPhase == 4) {
            if (!world.isClient) {
                attackTimer = maxAttackTimer;
                canAttack = true;
            }
            return super.tryAttack(target);
        } else {
            return super.tryAttack(target);
        }


    }

    void ShootRocket(){
        if(this.getTarget() != null) {
            ChunkenRocketEntity abstractarrowentity = new ChunkenRocketEntity(ModEntities.CHUNKEN_ROCKET, world);
            // get direction towards target
            /*Vec3d direction = this.getTarget().getPos().subtract(this.getPos()).normalize();
            abstractarrowentity.setVelocity(direction.multiply(2));*/
            abstractarrowentity.setVelocity(this, this.getPitch(), this.getYaw(), 0.0F,
                0.25F * 3.0F, 0);
            abstractarrowentity.refreshPositionAndAngles(this.getX(), this.getBodyY(0.8F),
                    this.getZ(), 0, 0);
            abstractarrowentity.setDamage(7);
            abstractarrowentity.hasNoGravity();
            world.spawnEntity(abstractarrowentity);
        }
    }



    void eatHunter(Entity player){
        ChunkenPhaseManager.eatenPlayerPos = player.getPos();
        player.getServer().getCommandManager().executeWithPrefix(player.getCommandSource(), "/execute in custommobswordsmod:chickendim run teleport ~ 160 ~");
        ChunkenPhaseManager.eatenPlayer = (PlayerEntity) player;
        ((PlayerEntityExt)player).setInChickenDimention(true);
        ((PlayerEntityExt)player).setChickenEffect(true);
        ChunkenPhaseManager.eatenPlayer = (PlayerEntity) player;

        hasEggToPoop = true;
        //player.getServer().getCommandManager().executeWithPrefix(player.getCommandSource(), "/gamemode adventure");
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
            // set animatino speed to 5x
            event.getController().setAnimationSpeed(5);
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
            if(ChunkenPhaseManager.chunkenPhase < 3) {
                event.getController().setAnimation(new AnimationBuilder().addAnimation(ChunkenPhaseManager.getAttackAnimation(), false));
                event.getController().setAnimationSpeed(3);
            }
            else if(ChunkenPhaseManager.chunkenPhase == 3){
                event.getController().setAnimationSpeed(1);
                event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.geometry.chicken.phase5_skills_attack", false));
            }
            else{
                event.getController().setAnimationSpeed(1.75f);
                event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.geometry.chicken.phase5_laser_first_part", false));
                /*
                ChunkenLaserEntityGL laser = new ChunkenLaserEntityGL(ModEntities.CHUNKEN_LASERGL, world);
                laser.refreshPositionAndAngles(this.getX(), this.getY(), this.getZ(), 0, 0);
                laser.setPos(this.getX(), this.getY() + 1.5, this.getZ());
                world.spawnEntity(laser);
                // set velocity towards player
                Vec3d dir = this.getTarget().getPos().subtract(this.getPos()).normalize();
                laser.setVelocity(dir.multiply(2));*/
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
        this.goalSelector.add(2, new MeleeAttackGoal(this, 1.5D, false));
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
