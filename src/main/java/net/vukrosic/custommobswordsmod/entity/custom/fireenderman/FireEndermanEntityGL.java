package net.vukrosic.custommobswordsmod.entity.custom.fireenderman;

import net.minecraft.block.BlockState;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.decoration.painting.PaintingVariants;
import net.minecraft.entity.mob.EndermanEntity;
import net.minecraft.entity.mob.ZombifiedPiglinEntity;
import net.minecraft.sound.SoundEvents;
import net.minecraft.tag.FluidTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
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


public class FireEndermanEntityGL extends EndermanEntity implements IAnimatable {

    private AnimationFactory factory = new AnimationFactory(this);
    int playerEatingTimer = 0;

    boolean growing = false;

    private static final TrackedData<Boolean> ATTACKING =
            DataTracker.registerData(FireEndermanEntityGL.class, TrackedDataHandlerRegistry.BOOLEAN);

    public FireEndermanEntityGL(EntityType<? extends EndermanEntity> entityType, World world) {
        super(entityType, world);
    }



    public static DefaultAttributeContainer.Builder setAttributes(){
        return EndermanEntity.createEndermanAttributes();
    }





    private <E extends IAnimatable> PlayState predicate(AnimationEvent<E> event) {
        if (event.isMoving()) {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.fireenderman.walk", true));
            return PlayState.CONTINUE;
        }
        /*
        else if (event.isEating()) {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.the_chunken.eat", false));
        }*/

        event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.fireenderman.idle", true));
        return PlayState.CONTINUE;


    }


    private PlayState attackPredicate(AnimationEvent event) {
        if(this.handSwinging && event.getController().getAnimationState().equals(AnimationState.Stopped)) {
            event.getController().markNeedsReload();
            event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.fireenderman.attack", false));
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
    public void setTarget(@Nullable LivingEntity target) {
        if(target == SetHunterCommand.pray){
            return;
        }
        super.setTarget(target);
    }


    @Override
    public AnimationFactory getFactory() {
        return factory;
    }

}
