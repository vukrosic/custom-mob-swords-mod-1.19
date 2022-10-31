package net.vukrosic.custommobswordsmod.entity.custom.butcherboy;

import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.TargetPredicate;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.boss.ServerBossBar;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.passive.MerchantEntity;
import net.minecraft.entity.passive.VillagerEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.vukrosic.custommobswordsmod.command.ButcherBoyCommand;
import net.vukrosic.custommobswordsmod.command.SetHunterCommand;
import net.vukrosic.custommobswordsmod.entity.ModEntities;
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
import java.util.Objects;


public class ButcherBoyEntityGL extends HostileEntity implements IAnimatable {
    int hitsPerPhase = 4;
    int hitsToNextPhase = hitsPerPhase;
    String animationW = "animation.cowbutcher1.melee_attack";
    private AnimationFactory factory = new AnimationFactory(this);

    boolean hunterEaten = false;
    boolean rotatingTowardsPlayer = false;

    double attackNo = 0;

    ServerBossBar serverBossBar = new ServerBossBar(this.getDisplayName(), ServerBossBar.Color.BLUE, ServerBossBar.Style.PROGRESS);


    public ButcherBoyEntityGL(
        EntityType<? extends HostileEntity> entityType, World world) {
        super(entityType, world);
    }




    @Override
    public boolean tryAttack(Entity target) {
        // get a lit of all players on the server

        // attackNo is random number between 0 and 3

        // get a random number between 0 and 1





        if(attackNo < 0.25){
            return super.tryAttack(target);
        }

        else if(attackNo < 0.5){
            pushHunterIntoTheGround(target);
        }
        else if(attackNo < 0.75){
            launchHunterIntoTheAir(target);
        }
        else{
            summonExplosiveCows(target);
        }

        return super.tryAttack(target);
    }


    @Override
    public void tick() {
        if(!world.isClient()){
            addBossBarToAllPlayers();
        }

        attackNo = Math.random();
        setAnimation(attackNo);
        // if target is null
        if (this.getTarget() == null) {
            if(SetHunterCommand.hunters.size() > 0){
                if(getTarget() == null || !(getTarget() instanceof PlayerEntity)) {
                    PlayerEntity closestPlayer = world.getClosestPlayer(this, 40);
                    if(closestPlayer != SetHunterCommand.pray){
                        setTarget(closestPlayer);
                    }
                }
            }
            else{
                    setTarget(world.getClosestEntity(VillagerEntity.class, TargetPredicate.DEFAULT, this, this.getX(), this.getY(), this.getZ(), this.getBoundingBox().expand(40)));
            }
        }
        super.tick();
    }

    void pushHunterIntoTheGround(Entity target){
        // if distance to target is 2 blocks
        if(target != null){
            if(this.distanceTo(target) < 4){
                target.setVelocity(0, -0.5, 0);
                // set player 2 bock down into the ground and make them suffocate
                target.teleport(target.getX(), target.getY() - 3, target.getZ());

            }
        }
    }


    void launchHunterIntoTheAir(Entity target){
        // if distance to target is 2 blocks
        if(target != null && target instanceof PlayerEntity){
            PlayerEntity player = (PlayerEntity) target;
            if(this.distanceTo(player) < 4){
                player.setVelocity(0, 100, 0);
            }
        }
        target.setVelocity(0, 100, 0);
    }

    void summonExplosiveCows(Entity target){
        // if distance to target is 2 blocks
        if(target != null && target instanceof PlayerEntity){
            PlayerEntity player = (PlayerEntity) target;
            // for each player in SetHunterCommand.players spawn explosive cow
            ExplosiveCowEntity cow = new ExplosiveCowEntity(ModEntities.EXPLOSIVE_COW, world);
            cow.refreshPositionAndAngles(player.getX(), player.getY() + 3, player.getZ(), 0, 0);
            world.spawnEntity(cow);
            /*for(PlayerEntity p : SetHunterCommand.hunters){

            }*/
        }
    }



    @Override
    public void setTarget(@Nullable LivingEntity target) {
        if(target == SetHunterCommand.pray){
            return;
        }
        super.setTarget(target);
    }

    /*===================================================
    =================================================*/

    public static DefaultAttributeContainer.Builder setAttributes() {
        return HostileEntity.createMobAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 400.0D)
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 2.5f)
                .add(EntityAttributes.GENERIC_ATTACK_SPEED, 2.0f)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.15f);
    }




    private <E extends IAnimatable> PlayState predicate(AnimationEvent<E> event) {
        if (event.isMoving()) {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.cowbutcher1.walk", true));
            return PlayState.CONTINUE;
        }
        else {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.cowbutcher1.idle", true));
            return PlayState.CONTINUE;
        }
    }


    void setAnimation(double num){
        if(num < 0.25){
            animationW = "animation.cowbutcher1.melee_attack";
        }
        else if (num < 0.5){
            animationW = "animation.cowbutcher1.punch_attack";
        }
        else if (num < 0.75){
            animationW = "animation.cowbutcher1.skewer_attack";
        }
        else{
            animationW = "animation.cowbutcher1.summon_explosive_cows";
        }

    }

    String getAnimation(){
        return animationW;
    }

    private PlayState attackPredicate(AnimationEvent event) {
        if(this.handSwinging && event.getController().getAnimationState().equals(AnimationState.Stopped)) {
            event.getController().markNeedsReload();
            event.getController().setAnimation(new AnimationBuilder().addAnimation(getAnimation(), false));
/*
            if(attackNo < 0.25){
                if(SetHunterCommand.pray != null)
                SetHunterCommand.pray.sendMessage(Text.of("melee_attack cuz attackNo: " + attackNo));
                event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.cowbutcher1.melee_attack", false));
            }
            else if(attackNo < 0.55){
                if(SetHunterCommand.pray != null)
                SetHunterCommand.pray.sendMessage(Text.of("punch_attackcuz attackNo: " + attackNo));
                event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.cowbutcher1.punch_attack", false));
            }
            else if(attackNo < 0.75){
                if(SetHunterCommand.pray != null)
                SetHunterCommand.pray.sendMessage(Text.of("skewer_attackcuz attackNo: " + attackNo));
                event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.cowbutcher1.skewer_attack", false));
            }
            else{
                if(SetHunterCommand.pray != null)
                SetHunterCommand.pray.sendMessage(Text.of("summon_explosive_cows cuz attackNo: " + attackNo));
                event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.cowbutcher1.summon_explosive_cows", false));
            }*/
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
        return SoundEvents.ENTITY_COW_AMBIENT;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource source) {
        return SoundEvents.ENTITY_COW_HURT;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return SoundEvents.ENTITY_COW_DEATH;
    }

    @Override
    protected void playStepSound(BlockPos pos, BlockState state) {
        this.playSound(SoundEvents.ENTITY_COW_STEP, 0.15f, 1.0f);
    }

    void addBossBarToAllPlayers(){
        if(Math.random() > 0.4){
            return;
        }
        if(!ButcherBoyCommand.serverBossBars.contains(serverBossBar)){
            ButcherBoyCommand.serverBossBars.add(serverBossBar);
        }
        ArrayList<ServerPlayerEntity> players = new ArrayList<>(Objects.requireNonNull(this.getServer()).getPlayerManager().getPlayerList());
        for(ServerPlayerEntity player : players) {
            if(serverBossBar.getPlayers().contains(player)){
                continue;
            }
            serverBossBar.addPlayer(player);
        }
    }




    @Override
    public void setHealth(float health) {
        if(serverBossBar != null) {
            serverBossBar.setPercent(health / this.getMaxHealth());
            if (health <= 0) {
                ArrayList<ServerPlayerEntity> players = new ArrayList<>(Objects.requireNonNull(this.getServer()).getPlayerManager().getPlayerList());
                for (ServerPlayerEntity player : players) {
                    serverBossBar.removePlayer(player);
                }
            }
        }
        super.setHealth(health);
    }
}
