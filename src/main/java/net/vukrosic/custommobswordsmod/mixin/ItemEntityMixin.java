package net.vukrosic.custommobswordsmod.mixin;

import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.World;
import net.vukrosic.custommobswordsmod.item.custom.ItemEntityMixinExt;
import net.vukrosic.custommobswordsmod.util.FireInfectedPlayers;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Random;

@Mixin(ItemEntity.class)
public abstract class ItemEntityMixin extends Entity implements ItemEntityMixinExt {
    public ItemEntityMixin(EntityType<?> type, World world) {
        super(type, world);
    }


    public int burntimer = 1;
    public PlayerEntity thrownBy;

    @Override
    public void setBurnTimer(int burntimer) {
        this.burntimer = burntimer;
    }

    @Override
    public void setThrownBy(PlayerEntity player){
        this.thrownBy = player;
    }


/*
    @Overwrite
    public boolean damage(DamageSource source, float amount) {
        if(source.isFire()){
            this.burntimer = 100;
        }
        return false;
    }*/


    @Inject(method = "tick", at = @At("HEAD"), cancellable = true)
    private void burn(CallbackInfo info) {

        if (!this.world.isClient()) {
            if (FireInfectedPlayers.isPlayerInList(thrownBy)) {

                ServerWorld serverWorld = (ServerWorld) this.world;

                if (FireInfectedPlayers.fireTicks.get(FireInfectedPlayers.players.indexOf(thrownBy)) <= 5) {
                    double x = this.getX();
                    double y = this.getY();
                    double z = this.getZ();
                    serverWorld.spawnParticles(ParticleTypes.WITCH, x, y + 1, z, 50, 0, 0, 0, 1);
                    //this.kill();
                    if(serverWorld.getPlayers().size() > 0){
                        serverWorld.spawnParticles(serverWorld.getPlayers().get(0), ParticleTypes.WITCH, true, x, y + 1, z, 50, 0, 0, 0, 1);
                    }
                    this.kill();
                }
            }
            }
        }

    }

