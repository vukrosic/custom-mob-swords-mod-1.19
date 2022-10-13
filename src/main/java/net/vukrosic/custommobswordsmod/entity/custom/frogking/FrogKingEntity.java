package net.vukrosic.custommobswordsmod.entity.custom.frogking;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.FrogEntity;
import net.minecraft.entity.passive.HorseEntity;
import net.minecraft.entity.player.PlayerEntity;

import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.vukrosic.custommobswordsmod.command.SetHunterCommand;
import net.vukrosic.custommobswordsmod.entity.custom.PlayerEntityExt;
import net.vukrosic.custommobswordsmod.item.ModItems;
import software.bernie.geckolib3.core.AnimationState;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;


public class FrogKingEntity extends FrogEntity implements IAnimatable {

    private AnimationFactory factory = new AnimationFactory(this);
    //private FrogKingTongueEntity tongue;

    public int MobPullCounter = 0;
    public int MobPullMaxCounter = 6;
    public LivingEntity EatingEntity;

// make a list of eaten mobs
    

    protected float jumpStrength = 3;
    protected boolean inAir;
    float flyingSpeed = 0;
    public FrogKingEntity(EntityType<? extends AnimalEntity> entityType, World world) {

        super(entityType, world);
        //tongue = new FrogKingTongueEntity(ModEntities.FROG_KING_TONGUE, world);
        //tongue.setOwner(this);
        //tongue.createTongueProjectile();
    }



    /*
     * TONGUE
     */



    // get mounted entity



    /*
     * TONGUE ATTACK
     */

    @Override
    public void tick() {

        if(EatingEntity != null && MobPullCounter > 0) {
            MobPullCounter--;
            if (MobPullCounter <= 0) {
                Vec3d entityPos = EatingEntity.getPos();
                Vec3d playerPos = this.getPos();
                Vec3d direction = playerPos.subtract(entityPos).normalize();
                EatingEntity.setVelocity(direction.multiply(10));
                //EatingEntity = null;
            }
        }
        // check distance to EatingEntity
        if(!this.world.isClient()) {
            float distance = 0;
            if (EatingEntity != null) {
                distance = EatingEntity.distanceTo(this);
                if (distance < 2) {
                    if (EatingEntity instanceof PlayerEntity) {
                        PlayerEntity playerEntity = (PlayerEntity) EatingEntity;
                        EatenMobsByFrogKing.EatenPlayers.add(playerEntity);
                        if(getMainPassenger() != null) {
                            ((PlayerEntity) getMainPassenger()).sendMessage(Text.of("EATEN PLAYER"), false);
                        }
                    } else {
                        EatenMobsByFrogKing.EatenLivingEntities.add((LivingEntity) EatingEntity);
                        if(getMainPassenger() != null) {
                            ((PlayerEntity) getMainPassenger()).sendMessage(Text.of("EATEN MOB"), false);
                            ((PlayerEntity) getMainPassenger()).sendMessage(Text.of("EatenMobsByFrogKing.EatenLivingEntities.size " + EatenMobsByFrogKing.EatenLivingEntities.size()), false);
                        }
                        //                                                                                                  <============= discards the mob
                        //EatingEntity.discard();
                    }

                }
            }
        }
        super.tick();
    }
    
    
    public void ShootEatenEntity(){
        if(getMainPassenger() != null) {
            ((PlayerEntity) this.getMainPassenger()).sendMessage(Text.of("size(): " + EatenMobsByFrogKing.EatenLivingEntities.size()), false);
        }
        if(!this.world.isClient() && EatenMobsByFrogKing.EatenLivingEntities.size() > 0){
            if(getMainPassenger() != null) {
                this.getMainPassenger().sendMessage(Text.of("SHOOTING EATEN MOB"));
            }
            if(EatenMobsByFrogKing.EatenLivingEntities.size() > 0 && getMainPassenger() != null && getMainPassenger() instanceof PlayerEntity) {
                PlayerEntity playerEntity = (PlayerEntity) getMainPassenger();
                LivingEntity entity = EatenMobsByFrogKing.EatenLivingEntities.get(0);
                //EatenMobsByFrogKing.EatenLivingEntities.remove(0);
                entity.refreshPositionAndAngles(this.getX(), this.getY(), this.getZ(), 0, 0);
                // set velocity forward from frog
                Vec3d entityPos = entity.getPos();
                // get where frog is looking at
                Vec3d lookRotation = this.getMainPassenger().getRotationVector();
                Vec3d cameraPos = playerEntity.getCameraPosVec(0);
                // get what player's cross is pointing to
                Vec3d crossPos = cameraPos.add(lookRotation.multiply(100));
                // get direction from frog to cross
                Vec3d direction = crossPos.subtract(entityPos).normalize();
                entity.setVelocity(direction.multiply(7));
                EatenMobsByFrogKing.EatenLivingEntities.remove(0);
            }
        }
    }
    
    
    
    

    private static final TrackedData<Boolean> ATTACKING =
            DataTracker.registerData(FrogKingEntity.class, TrackedDataHandlerRegistry.BOOLEAN);

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
    }

    /*
    above this is testing
     */

    private PlayState attackPredicate(AnimationEvent event) {
        if (this.handSwinging && event.getController().getAnimationState().equals(AnimationState.Stopped)) {
            event.getController().markNeedsReload();
            event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.frog_king.tongue", false));
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
     * TURNING INTO ITEM
     */

    @Override
    public boolean damage(DamageSource source, float amount) {
        if (this.isInvulnerableTo(source)) {
            return false;
        } else if (!this.world.isClient && !this.isRemoved()) {
            if(source.getAttacker() == SetHunterCommand.pray){
                this.dropItem(ModItems.FROG_KING_ITEM);
                this.discard();
            }
            return true;
        } else {
            return true;
        }
    }



    /*
    * MOUNTING
     */


    @Override
    public double getMountedHeightOffset() {
        return (double)4.4F;
    }

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


    private LivingEntity getMainPassenger(){
        LivingEntity livingEntity = null;
        for (Entity entity : getPassengerList()){
            if (entity instanceof LivingEntity){
                livingEntity = (LivingEntity) entity;
            }
        }
        return livingEntity;
    }


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
                    this.setMovementSpeed((float) this.getAttributeValue(EntityAttributes.GENERIC_MOVEMENT_SPEED)/5);
                    double finalYTravel = movementInput.y;
                    super.travel(new Vec3d((double) f, finalYTravel, (double) g));

                    this.updateLimbs(this, false);
                    this.tryCheckBlockCollision();
                }
            }
            else {
                this.flyingSpeed = 0.02F;
                //super.travel(movementInput);
            }
        }
        //super.travel(movementInput);
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
            ((PlayerEntityExt)player).setFrogKingEntity(this);
            player.setYaw(this.getYaw());
            player.setPitch(this.getPitch());
            player.startRiding(this);
        }
    }



    public static DefaultAttributeContainer.Builder setAttributes() {
        return FrogEntity.createFrogAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 200.0D);
    }


    private <E extends IAnimatable> PlayState predicate(AnimationEvent<E> event) {
        if (event.isMoving()) {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.frog_king.walk", true));
            return PlayState.CONTINUE;
        }
        /*
        else if (event.isEating()) {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.the_chunken.eat", false));
        }*/

        event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.frog_king.croak", true));
        return PlayState.CONTINUE;


    }





    @Override
    public AnimationFactory getFactory() {
        return factory;
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return SoundEvents.ENTITY_FROG_AMBIENT;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource source) {
        return SoundEvents.ENTITY_FROG_HURT;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return SoundEvents.ENTITY_FROG_DEATH;
    }

    @Override
    protected void playStepSound(BlockPos pos, BlockState state) {
        this.playSound(SoundEvents.ENTITY_FROG_STEP, 0.15f, 1.0f);
    }
}
