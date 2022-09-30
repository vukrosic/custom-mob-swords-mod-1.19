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
    public boolean isFireImmune = true;

    public ItemEntityMixin(EntityType<?> type, World world) {
        super(type, world);
    }

    @Inject(method = "tick", at = @At("HEAD"), cancellable = true)
    private void countTicks(CallbackInfo info){
        System.out.println("getFireTicks = " + super.getFireTicks());
    }





    public boolean getFireImmune(){
        return isFireImmune;
    }
*/

    @Overwrite
    public boolean damage(DamageSource source, float amount) {
        return false;
    }

    // destroy item if it is on fire for too long
    @Inject(method = "tick", at = @At("HEAD"), cancellable = true)
    private void burn(CallbackInfo info) {

        if (!this.world.isClient()) {
            if (FireInfectedPlayers.isPlayerInList(thrownBy)) {
                int burningTicksLeft = FireInfectedPlayers.fireTicks.get(FireInfectedPlayers.players.indexOf(thrownBy));
                System.out.println("burningTicksLeft = " + burningTicksLeft);

                ServerWorld serverWorld = (ServerWorld) this.world;
                MinecraftClient client = MinecraftClient.getInstance();

                // if fire ticks == 0, destroy item
                if (FireInfectedPlayers.fireTicks.get(FireInfectedPlayers.players.indexOf(thrownBy)) <= 5) {
                    System.out.println("spawn particlessssssssssssssss 1111111111111");
                    Random rand = new Random();
                    double x = this.getX() /*+ (rand.nextDouble() - 0.5) * 2*/;
                    double y = this.getY() /*+ (rand.nextDouble() - 0.5) * 2*/;
                    double z = this.getZ() /*+ (rand.nextDouble() - 0.5) * 2*/;
                    serverWorld.spawnParticles(ParticleTypes.WITCH, x, y + 1, z, 50, 0, 0, 0, 1);
                    //this.kill();
                    if(serverWorld.getPlayers().size() > 0){
                        serverWorld.spawnParticles(serverWorld.getPlayers().get(0), ParticleTypes.WITCH, true, x, y + 1, z, 50, 0, 0, 0, 1);
                    }
                    this.kill();
                }
            /*
            for (int i = 0; i < 2; i++) {
                System.out.println("spawn particlessssssssssssssss");
                Random rand = new Random();
                double x = this.getX() /*+ (rand.nextDouble() - 0.5) * 2*/
                ;
                double y = this.getY() /*+ (rand.nextDouble() - 0.5) * 2*/;
                double z = this.getZ() /*+ (rand.nextDouble() - 0.5) * 2*/;
                /*client.particleManager.addParticle(ParticleTypes.WITCH, x, y + 1, z, 0, 0, 0);
                client.world.addParticle(ParticleTypes.WITCH, x, y + 1, z, 0, 0, 0);*/
                //serverWorld.spawnParticles(ParticleTypes.WITCH, x, y + 1, z, 10, 0, 0, 0, 1);
                /*System.out.println("Number of server players is " + serverWorld.getPlayers().size());
                if(serverWorld.getPlayers().size() > 0){
                    serverWorld.spawnParticles(serverWorld.getPlayers().get(0), ParticleTypes.EXPLOSION_EMITTER, true, x, y + 1, z, 10, 0, 0, 0, 1);
                }*/


            }/*
            burntimer--;
            if(burntimer <= 0){
                for (int i = 0; i < 50; i++) {
                    Random rand = new Random();
                    double x = this.getX() + (rand.nextDouble() - 0.5) * 2;
                    double y = this.getY() + (rand.nextDouble() - 0.5) * 2;
                    double z = this.getZ() + (rand.nextDouble() - 0.5) * 2;
                    this.getServer().getWorld(this.getEntityWorld().getRegistryKey()).spawnParticles(ParticleTypes.WITCH, x, y + 1, z, 1, 0, 0, 0, 1);
                    //this.getServer().getWorld(this.getEntityWorld().getRegistryKey()).spawnParticles(ParticleTypes.EXPLOSION_EMITTER, x, y + 1, z, 1, 0, 0, 0, 1);
                }
                //this.kill();
            }*/
            }
        }

    }



/*
    @Inject(at = @At("HEAD"), method = "damage" )
    public void damage(DamageSource source, float amount, CallbackInfoReturnable ci){
        System.out.println("source.isFire() = " + source.isFire());
        /*if(getFireImmune() && source.isFire()){
            System.out.println("Ain't gonna do damage to this item.");
            return;
        }*/
    /*}*/


/*

    @Override
    public void setFireTicks(int fireTicks) {
        super.setFireTicks(super.getFireTicks()/50);
        System.out.println("fireTicks = " + super.getFireTicks()/50);
    }

    /*
    @Override
    public void setFireTicks(int fireTicks) {
        super.setFireTicks(fireTicks);
        System.out.println("OVERRIDDDDDDING");
    }

    @Inject(at = @At("HEAD"), method = "setFireTicks" )
    public void setFireTicks(int fireTicks, CallbackInfo ci) {
        System.out.println("fireTicks " + " ----------------------------------------------------- " + fireTicks);
        fireTicks =  fireTicks / 20;
    }
*/





//}
