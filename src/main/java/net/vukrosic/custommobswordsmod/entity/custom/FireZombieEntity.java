package net.vukrosic.custommobswordsmod.entity.custom;

import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.*;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.mob.EndermanEntity;
import net.minecraft.entity.mob.ZombieEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.world.GameRules;
import net.minecraft.world.World;
import net.vukrosic.custommobswordsmod.command.SetHunterCommand;
import net.vukrosic.custommobswordsmod.item.ModItems;
import net.vukrosic.custommobswordsmod.networking.ModMessages;
import net.vukrosic.custommobswordsmod.util.FireInfectedPlayers;
import net.vukrosic.custommobswordsmod.util.custom.HandledScreenExt;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;


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
            if (target instanceof PlayerEntity) {
                PlayerEntity playerEntity = (PlayerEntity) target;
                //FireInfectedPlayers.addPlayer(playerEntity);
                target.setOnFireFor(5);
                ServerPlayNetworking.send(((ServerPlayerEntity) playerEntity), ModMessages.FIRE_INFECTED_ID, PacketByteBufs.create());
                FireInfectedPlayers.addPlayer(playerEntity);
            }
            this.setOnFireFor(9999);
            super.onAttacking(target);
        }
    }

    @Override
    public void tick() {
        setTarget(world.getClosestPlayer(this, 150));
        aggroClosestHunter();
        super.tick();
    }

    public static DefaultAttributeContainer.Builder setAttributes(){
        return ZombieEntity.createZombieAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 50.0D)
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 2.5D);
    }

    @Override
    public void setTarget(@Nullable LivingEntity target) {
        if(target == SetHunterCommand.pray){
            return;
        }
        super.setTarget(target);
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
}
