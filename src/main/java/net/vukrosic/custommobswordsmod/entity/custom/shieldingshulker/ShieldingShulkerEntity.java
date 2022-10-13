package net.vukrosic.custommobswordsmod.entity.custom.shieldingshulker;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.boss.ServerBossBar;
import net.minecraft.entity.mob.ShulkerEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;
import net.vukrosic.custommobswordsmod.command.SetHunterCommand;

public class ShieldingShulkerEntity extends ShulkerEntity {

    public PlayerEntity owner;
    ServerBossBar serverBossBar;
    //public float BeamProgress = 0;
    public ShieldingShulkerEntity(EntityType<? extends ShulkerEntity> entityType, World world) {
        super(entityType, world);
    }

    public static DefaultAttributeContainer.Builder setAttributes(){
        return ShulkerEntity.createShulkerAttributes();
    }


    @Override
    public void tick() {
        super.tick();
        if (this.world.isClient) {
            return;
        }
        this.setTarget(null);

        // if owner is more than 10 blocks away, teleport 2 blocks away from owner in a random direction
        if (this.owner != null && this.squaredDistanceTo(this.owner) > 100) {
            this.teleport(this.owner.getX() + (this.random.nextDouble() - 0.5) * 4, this.owner.getY(), this.owner.getZ() + (this.random.nextDouble() - 0.5) * 4);
        }
    }

    @Override
    protected boolean tryTeleport() {

        // if distance to owner is larger than 10 blocks
        if (this.owner != null && this.squaredDistanceTo(this.owner) > 100) {
            // teleport 2 blocks away from owner in a random direction

            if (!this.isAiDisabled() && this.isAlive()) {
                BlockPos blockPos = owner.getBlockPos();

                for (int i = 0; i < 50; ++i) {
                    BlockPos blockPos2 = blockPos.add(MathHelper.nextBetween(this.random, -8, 8), MathHelper.nextBetween(this.random, -8, 8), MathHelper.nextBetween(this.random, -8, 8));
                    if (blockPos2.getY() > this.world.getBottomY() && this.world.isAir(blockPos2) && this.world.getWorldBorder().contains(blockPos2) && this.world.isSpaceEmpty(this, (new Box(blockPos2)).contract(1.0E-6))) {
                        Direction direction = this.findAttachSide(blockPos2);
                        if (direction != null) {
                            this.detach();
                            this.setAttachedFace(direction);
                            this.playSound(SoundEvents.ENTITY_SHULKER_TELEPORT, 1.0F, 1.0F);
                            this.setPosition((double) blockPos2.getX() + 0.5, (double) blockPos2.getY(), (double) blockPos2.getZ() + 0.5);
                            this.world.emitGameEvent(GameEvent.TELEPORT, blockPos, GameEvent.Emitter.of(this));
                            this.dataTracker.set(PEEK_AMOUNT, (byte) 0);
                            this.setTarget((LivingEntity) null);
                            return true;
                        }
                    }
                }
            }

        } else {
            return false;
        }


        return super.tryTeleport();
    }


    private void setAttachedFace(Direction face) {
        this.dataTracker.set(ATTACHED_FACE, face);
    }

    /*
    @Override
    public boolean canTarget(LivingEntity target) {
        if(target instanceof PlayerEntity){
            if(target== owner){
                return false;
            }
        }
        return super.canTarget(target);
    }

    @Override
    public boolean tryAttack(Entity target) {
        return false;
    }

    @Override
    protected void attackLivingEntity(LivingEntity target) {
        return;
    }*/



    // ise tihs for hunters
    /*
    @Override
    public boolean isInAttackRange(LivingEntity entity) {
        return super.isInAttackRange(entity);
    }*/
}
