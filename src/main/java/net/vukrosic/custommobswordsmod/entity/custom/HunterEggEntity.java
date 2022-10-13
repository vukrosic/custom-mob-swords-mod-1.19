package net.vukrosic.custommobswordsmod.entity.custom;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.thrown.EnderPearlEntity;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.world.World;
import net.minecraft.world.explosion.Explosion;
import net.vukrosic.custommobswordsmod.entity.ModEntities;
import software.bernie.example.entity.RocketProjectile;

import java.util.Random;

public class HunterEggEntity extends EnderPearlEntity {
    public LivingEntity hunter;

    public HunterEggEntity(World world, LivingEntity owner) {
        super(world, owner);
    }


    @Override
    protected void onCollision(HitResult hitResult) {
        ServerWorld serverWorld = (ServerWorld)this.world;
        /*
        if(hunter == null)
            hunter = (LivingEntity)this.getOwner();
        if(hunter != null){
            serverWorld.getServer().getCommandManager().executeWithPrefix(serverWorld.getServer().getCommandSource(), "execute in minecraft:overworld run teleport " + hunter.getName().getString() + " " + hitResult.getPos().getX() + " " + hitResult.getPos().getY() + " " + hitResult.getPos().getZ());
        }*/
        particles();
        this.discard();
    }

    // on hit create explosion

    @Override
    protected void onBlockHit(BlockHitResult blockHitResult) {
        //super.onBlockHit(blockHitResult);
        //particles();
    }


    @Override
    protected void onEntityHit(EntityHitResult entityHitResult) {
        //this.world.createExplosion(this, this.getX(), this.getY(), this.getZ(), 2, Explosion.DestructionType.BREAK);
        //super.onEntityHit(entityHitResult);
        //particles();
    }

    void particles(){
        ServerWorld serverWorld = (ServerWorld) this.world;
        for (int i = 0; i < 50; i++) {
            Random rand = new Random();
            double x = this.getX() + (rand.nextDouble() - 0.5) * 2;
            double y = this.getY() + (rand.nextDouble() - 0.5) * 2;
            double z = this.getZ() + (rand.nextDouble() - 0.5) * 2;
            serverWorld.spawnParticles(ParticleTypes.WITCH, x, y + 1, z, 1, 0, 0, 0, 1);
            serverWorld.spawnParticles(ParticleTypes.ENCHANT, x, y + 1, z, 1, 0, 0, 0, 1);
        }
        //thrower.kill();
    }
}
