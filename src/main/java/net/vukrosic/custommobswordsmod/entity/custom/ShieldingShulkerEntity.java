package net.vukrosic.custommobswordsmod.entity.custom;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.boss.ServerBossBar;
import net.minecraft.entity.mob.ShulkerEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.world.World;
import net.vukrosic.custommobswordsmod.command.SetHunterCommand;

public class ShieldingShulkerEntity extends ShulkerEntity {

    PlayerEntity owner;
    ServerBossBar serverBossBar;
    //public float BeamProgress = 0;
    public ShieldingShulkerEntity(EntityType<? extends ShulkerEntity> entityType, World world) {
        super(entityType, world);
        if(world.isClient() && SetHunterCommand.pray != null) {
            //this.owner = SetHunterCommand.pray;
            //owner.sendMessage(Text.of("You are now the owner of the Shielding Shulker NEW BOSS BAR ADDED"), false);
            /*
            serverBossBar = new ServerBossBar(this.getDisplayName(), ServerBossBar.Color.BLUE, ServerBossBar.Style.PROGRESS);
            serverBossBar.addPlayer((ServerPlayerEntity) owner);
            serverBossBar.setPercent(BeamProgress);*/
            //((PlayerEntityExt)owner).setShieldingShulkerEntity(this);

        }

    }
/*
    public void addBeamProgress(float beamProgress) {
        this.BeamProgress += beamProgress;
        serverBossBar.setPercent(BeamProgress);
        owner.sendMessage(Text.of("Beam progress: " + BeamProgress), false);
    }*/

    public static DefaultAttributeContainer.Builder setAttributes(){
        return ShulkerEntity.createShulkerAttributes();
    }



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
    }

    // ise tihs for hunters
    /*
    @Override
    public boolean isInAttackRange(LivingEntity entity) {
        return super.isInAttackRange(entity);
    }*/
}
