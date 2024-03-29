package net.vukrosic.custommobswordsmod.entity.custom;

import net.minecraft.block.BlockState;
import net.minecraft.entity.EntityType;
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
import net.minecraft.item.Item;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.command.CommandManager;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
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


public class ChunkenEntity extends HostileEntity implements IAnimatable {

    private AnimationFactory factory = new AnimationFactory(this);
    int playerEatingTimer = 0;

    int scale = 0;
    boolean growing = false;

    private static final TrackedData<Boolean> ATTACKING =
            DataTracker.registerData(ChunkenEntity.class, TrackedDataHandlerRegistry.BOOLEAN);

    public ChunkenEntity(EntityType<? extends HostileEntity> entityType, World world) {
        super(entityType, world);
    }



    public void eatPlayer(PlayerEntity player) {
        // move him upwards
        //player.setVelocity(0, 1, 0);
        //player.setYaw(player.getYaw() + 90);



        //((ClientPlayerEntity)player).showsDeathScreen();
        // get player's screen
        // draw black texture over player's screen



        playerEatingTimer = 50;
        /*

        MinecraftClient.getInstance().openScreen(new Screen(null) {
            @Override
            public void render(MatrixStack matrices, int mouseX, int mouseY, float delta) {
                DrawableHelper.fill(matrices, 0, 0, this.width, this.height, 0xFF000000);
            }
        });*/

        // DrawableHelper.drawTexture(new MatrixStack(), 0, 0, 0, 0, 0, 0, 0, 0, 0);
    }







    /*
    *SET TARGET TO CLOSEST PLAYER
    */

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


    @Override
    public boolean damage(DamageSource source, float amount) {
        // if attacked by a player
        //get cow head item
        // drop it

        // check if on cliient
        if(!world.isClient() && scale < 10) {


            if (source.getAttacker() instanceof PlayerEntity n) {
                growing = true;
                PlayerEntity attacker = (PlayerEntity) source.getAttacker();

                MinecraftServer server = source.getSource().getServer();

                CommandManager commandManager = server.getPlayerManager().getServer().getCommandManager();


                commandManager.executeWithPrefix(attacker.getCommandSource(), "/scale add 0.3 @e[type=custommobswordsmod:chunken]");
                scale++;
            }
        }

        return super.damage(source, amount);

    }



    public static DefaultAttributeContainer.Builder setAttributes() {
        return HostileEntity.createMobAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 20.0D)
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 8.0f)
                .add(EntityAttributes.GENERIC_ATTACK_SPEED, 2.0f)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.25f);
    }






    private <E extends IAnimatable> PlayState predicate(AnimationEvent<E> event) {
        if (event.isMoving()) {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.chick.move", true));
            return PlayState.CONTINUE;
        }
        /*
        else if (event.isEating()) {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.the_chunken.eat", false));
        }*/

        event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.chick.general", true));
        return PlayState.CONTINUE;


    }


    private PlayState attackPredicate(AnimationEvent event) {
        if(this.handSwinging && event.getController().getAnimationState().equals(AnimationState.Stopped)) {
            event.getController().markNeedsReload();
            event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.chick.attack", false));
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
        this.goalSelector.add(2, new MeleeAttackGoal(this, 1.2D, false));
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
