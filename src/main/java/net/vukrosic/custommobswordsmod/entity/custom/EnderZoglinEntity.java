package net.vukrosic.custommobswordsmod.entity.custom;

import com.eliotlash.mclib.math.functions.classic.Mod;
import net.minecraft.block.BlockState;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.mob.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvents;
import net.minecraft.tag.FluidTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;
import net.vukrosic.custommobswordsmod.command.SetHunterCommand;
import net.vukrosic.custommobswordsmod.entity.ModEntities;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Random;

public class EnderZoglinEntity extends ZombifiedPiglinEntity {


    public EnderZoglinEntity(EntityType<? extends ZombifiedPiglinEntity> entityType, World world) {
        super(entityType, world);
    }

    public static DefaultAttributeContainer.Builder setAttributes(){
        return ZombifiedPiglinEntity.createZombifiedPiglinAttributes().
                add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 3.0D);
    }
/*
    @Override
    public void setDancing(boolean dancing) {
        super.setDancing(true);
    }*/


    /*
    boolean teleportTo(Entity entity) {
        Vec3d vec3d = new Vec3d(this.getX() - entity.getX(), this.getBodyY(0.5) - entity.getEyeY(), this.getZ() - entity.getZ());
        vec3d = vec3d.normalize();
        double d = 16.0;
        double e = this.getX() + (this.random.nextDouble() - 0.5) * 8.0 - vec3d.x * 16.0;
        double f = this.getY() + (double)(this.random.nextInt(16) - 8) - vec3d.y * 16.0;
        double g = this.getZ() + (this.random.nextDouble() - 0.5) * 8.0 - vec3d.z * 16.0;
        return this.teleportTo(e, f, g);
    }*/

    /*
    @Nullable
    @Override
    public LivingEntity getTarget() {
        System.out
        return super.getTarget();
    }*/


    @Override
    public void tick() {
        if(getTarget() == null)
            aggroClosestHunter();
        double randomNumber = Math.random();
        if(super.getTarget() != null) {
            if (randomNumber > 0.80) {
                // if distance to target is greater than 10, teleport to target
                if (this.distanceTo(super.getTarget()) > 10) {
                    this.teleportRandomly();
                }
            }

            if (randomNumber > 0.9) {
                // break a random block in a 10 block radius
                int breakBlockRadius = 6;
                BlockPos pos = new BlockPos(world.getRandom().nextInt(breakBlockRadius), world.getRandom().nextInt(breakBlockRadius), world.getRandom().nextInt(breakBlockRadius));
                world.breakBlock(new BlockPos(super.getTarget().getX() + pos.getX() - breakBlockRadius/2, super.getTarget().getY() - pos.getY(), super.getTarget().getZ() + pos.getZ() - breakBlockRadius/2), false);
            }
        }

        super.tick();
    }


    void aggroClosestHunter(){
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
    }

/*
    @Override
    public void setHealth(float health) {
        if(super.getHealth() > health) {
            EnderZoglinEntity enderZoglinEntity = new EnderZoglinEntity(ModEntities.ENDER_ZOGLIN, world);
            enderZoglinEntity.refreshPositionAndAngles(this.getX(), this.getY(), this.getZ(), 0, 0);
            world.spawnEntity(enderZoglinEntity);
        }
        super.setHealth(health);
    }*/

    @Override
    public void setTarget(@Nullable LivingEntity target) {
        if(target == SetHunterCommand.pray){
            return;
        }
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

    /* SHIVERRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRR  GameEvent.TELEPORT */

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
}
