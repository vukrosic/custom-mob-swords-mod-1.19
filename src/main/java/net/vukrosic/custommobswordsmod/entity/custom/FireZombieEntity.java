package net.vukrosic.custommobswordsmod.entity.custom;

import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.*;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
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


    @Override
    public boolean damage(DamageSource source, float amount) {
        if(source.isFire()){
            return false;
        }
        return super.damage(source, amount);
    }

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
        return ZombieEntity.createZombieAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 50.0D)
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 2.5D);
    }



}
