package net.vukrosic.custommobswordsmod.entity.custom;

import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.*;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.mob.EndermanEntity;
import net.minecraft.entity.mob.ZombieEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.GameRules;
import net.minecraft.world.World;
import net.vukrosic.custommobswordsmod.item.ModItems;
import net.vukrosic.custommobswordsmod.util.FireInfectedPlayers;
import org.jetbrains.annotations.Nullable;


public class FireZombieEntity extends ZombieEntity {


    public FireZombieEntity(EntityType<? extends ZombieEntity> entityType, World world) {
        super(entityType, world);
        this.setOnFireFor(9999);
    }
/*
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
*/

    @Override
    public boolean damage(DamageSource source, float amount) {
        if(source.isFire()){
            return false;
        }
        return super.damage(source, amount);
    }
/*
    public ItemEntity dropStack() {
        // make a stack of 1 fire pearl item
        ItemStack firePearlStack = new ItemStack(ModItems.FIRE_PEARL, 1);
        return super.dropStack(firePearlStack, 0);
    }
*/
/*
    @Nullable
    @Override
    protected void drop(DamageSource source) {
        boolean bl;
        Entity entity = source.getAttacker();
        int i = entity instanceof PlayerEntity ? EnchantmentHelper.getLooting((LivingEntity)entity) : 0;
        boolean bl2 = bl = this.playerHitTimer > 0;
        if (this.shouldDropLoot() && this.world.getGameRules().getBoolean(GameRules.DO_MOB_LOOT)) {
            this.dropStack();
            System.out.println("DROPPPPPPPED");
            this.dropEquipment(source, i, bl);
        }
        this.dropInventory();
        this.dropXp();
    }
*/
    @Override
    public void onAttacking(Entity target) {
        if (target != null) {
            if (target instanceof LivingEntity) {
                target.setOnFireFor(5);
                // set target's inventory on fire
                PlayerEntity playerEntity = (PlayerEntity) target;

                FireInfectedPlayers.addPlayer(playerEntity);
                /*((PlayerEntityExt) target).setFireInfected(true);
                ((PlayerEntityExt) livingEntity).setFireInfected(true);*/

                //System.out.println("FireZombieEntity = " + ((PlayerEntityExt) livingEntity).fireInfected);



            }
            this.setOnFireFor(9999);
            super.onAttacking(target);
        }
    }

    @Override
    public void tick() {
        setTarget(world.getClosestPlayer(this, 150));
        super.tick();
    }

    public static DefaultAttributeContainer.Builder setAttributes(){
        return ZombieEntity.createZombieAttributes();
    }



}
