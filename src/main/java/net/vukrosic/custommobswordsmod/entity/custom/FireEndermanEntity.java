package net.vukrosic.custommobswordsmod.entity.custom;

import net.minecraft.client.render.entity.EndermanEntityRenderer;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.*;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.mob.CreeperEntity;
import net.minecraft.entity.mob.EndermanEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.world.GameRules;
import net.minecraft.world.World;
import net.minecraft.world.explosion.Explosion;
import net.vukrosic.custommobswordsmod.item.ModItems;
import org.jetbrains.annotations.Nullable;


public class FireEndermanEntity extends EndermanEntity {

    int explosionRadius = 100;
    public FireEndermanEntity(EntityType<? extends EndermanEntity> entityType, World world) {
        super(entityType, world);

        NbtCompound nbt = new NbtCompound();
        nbt.putInt("ExplosionRadius", explosionRadius);
    }

    @Override
    public void setTarget(@Nullable LivingEntity target) {
        if (target instanceof LivingEntity) {
            target.setOnFireFor(5);
        }
        super.setTarget(target);
    }


    @Override
    public void setAngerTime(int angerTime) {
        super.setAngerTime(angerTime * 500);
    }

    @Override
    public void onDeath(DamageSource source) {
        if (!this.isRemoved() && !this.dead) {
            Entity entity = source.getAttacker();
            LivingEntity livingEntity = this.getPrimeAdversary();
            if (this.scoreAmount >= 0 && livingEntity != null) {
                livingEntity.updateKilledAdvancementCriterion(this, this.scoreAmount, source);
            }

            if (this.isSleeping()) {
                this.wakeUp();
            }



            this.dead = true;
            this.getDamageTracker().update();
            if (this.world instanceof ServerWorld) {
                if (entity != null) {
                    entity.onKilledOther((ServerWorld)this.world, this);
                }

                this.drop(source);
                this.onKilledBy(livingEntity);
            }

            this.world.sendEntityStatus(this, (byte)3);
            this.setPose(EntityPose.DYING);
        }
    }



    public ItemEntity dropStack() {
        // make a stack of 1 fire pearl item
        ItemStack firePearlStack = new ItemStack(ModItems.FIRE_PEARL, 1);
        return super.dropStack(firePearlStack, 0);
    }


    @Nullable
    @Override
    protected void drop(DamageSource source) {
        boolean bl;
        Entity entity = source.getAttacker();
        int i = entity instanceof PlayerEntity ? EnchantmentHelper.getLooting((LivingEntity)entity) : 0;
        boolean bl2 = bl = this.playerHitTimer > 0;
        if (this.shouldDropLoot() && this.world.getGameRules().getBoolean(GameRules.DO_MOB_LOOT)) {
            this.dropStack();
            this.dropEquipment(source, i, bl);
        }
        this.dropInventory();
        this.dropXp();
    }

    @Override
    public void onAttacking(Entity target) {
        if (target != null) {
            if (target instanceof LivingEntity) {
                target.setOnFireFor(5);
            }
            super.onAttacking(target);
        }
    }




    public static DefaultAttributeContainer.Builder setAttributes(){
        return EndermanEntity.createEndermanAttributes();
    }





}
