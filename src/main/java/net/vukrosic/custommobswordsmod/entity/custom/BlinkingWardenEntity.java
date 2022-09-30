package net.vukrosic.custommobswordsmod.entity.custom;

import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.brain.Brain;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffectUtil;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.mob.WardenEntity;
import net.minecraft.entity.mob.ZombifiedPiglinEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.packet.s2c.play.EntitySpawnS2CPacket;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.vukrosic.custommobswordsmod.command.SetHunterCommand;
import net.vukrosic.custommobswordsmod.effect.ModEffects;
import org.jetbrains.annotations.Nullable;

public class BlinkingWardenEntity extends WardenEntity {
    public BlinkingWardenEntity(EntityType<? extends HostileEntity> entityType, World world) {
        super(entityType, world);
    }

    // make warden not move and not take any actinos, just stand there

    public static DefaultAttributeContainer.Builder setAttributes(){
        return WardenEntity.createHostileAttributes().add(EntityAttributes.GENERIC_MAX_HEALTH, 500.0).add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.30000001192092896).add(EntityAttributes.GENERIC_KNOCKBACK_RESISTANCE, 1.0).add(EntityAttributes.GENERIC_ATTACK_KNOCKBACK, 1.5).add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 30.0);
    }



    // remove all goals

/*
    @Override
    protected void attackLivingEntity(LivingEntity target) {
        return;
    }*/

    @Override
    public void tick() {
        return;
    }

    @Override
    public boolean tryAttack(Entity target) {
        return false;
    }



    @Override
    public boolean shouldRender(double distance) {
        PlayerEntity player = MinecraftClient.getInstance().player;
        // if player has carbonposioning effect

        return true;
        /*
        player.sendMessage(Text.of("Contains darkness1 " + player.getStatusEffect(StatusEffects.DARKNESS)), false);
        if(player.getStatusEffect(StatusEffects.DARKNESS) != null) {
            return super.shouldRender(distance);
        }
        return false;*/
    }


}
