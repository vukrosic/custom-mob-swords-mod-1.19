package net.vukrosic.custommobswordsmod.mixin;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.mob.CreeperEntity;
import net.minecraft.item.ItemConvertible;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.WorldAccess;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Block.class)
public abstract class BlockMixin extends AbstractBlock implements ItemConvertible {
    public BlockMixin(Settings settings) {
        super(settings);
    }
/*
    @Override
    public Item asItem() {
        return null;
    }

    @Override
    protected Block asBlock() {
        return null;
    }*/

    @Inject(at = @At("HEAD"), method = "onBroken", cancellable = true)
    public void onBroken(WorldAccess world, BlockPos pos, BlockState state, CallbackInfo info) {
        MinecraftClient client = MinecraftClient.getInstance();
        //client.world.spawnEntity(new CreeperEntity(EntityType.CREEPER, client.world));

        if (!client.world.isClient() ) {
            CreeperEntity creeper = EntityType.CREEPER.create(client.world);
            CreeperEntity creeper1 = new CreeperEntity(EntityType.CREEPER, client.world);
            creeper1.setPosition(new Vec3d(pos.getX(), pos.getY() + 5, pos.getZ()));
            client.world.spawnEntity(creeper1);
            creeper1.refreshPositionAndAngles(pos.getX(), pos.getY() + 5, pos.getZ(), client.player.getYaw(), 0.0F);


            CreeperEntity creeper2 = EntityType.CREEPER.create(client.world);
            creeper2.setGlowing(true);
            creeper2.setPosition(new Vec3d(pos.getX(), pos.getY() + 2, pos.getZ()));
            client.world.spawnEntity(creeper2);
            creeper2.refreshPositionAndAngles(pos.getX(), pos.getY() + 2, pos.getZ(), client.player.getYaw(), 0.0F);
        }

        /*creeper.setPos(pos.getX(), pos.getY(), pos.getZ());
        System.out.println("Creeper pos = " + creeper.getPos());*/

    }
    /*
    public void SpawnRandomMobAtBrokenBlock(WorldAccess world, BlockPos pos, BlockState state){
        System.out.println("111111111111111111111111111111111111111111111111111111111111111111111111111");
        /*
        MinecraftClient client = MinecraftClient.getInstance();
        //client.world.spawnEntity(new CreeperEntity(EntityType.CREEPER, client.world));

        CreeperEntity creeper = EntityType.CREEPER.create(client.world);
        creeper.setPos(pos.getX(), pos.getY(), pos.getZ());
        client.world.spawnEntity(creeper);

        System.out.println("wwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwww");*/
    //}

    public static class LivingEntityMixin {
    }
}
