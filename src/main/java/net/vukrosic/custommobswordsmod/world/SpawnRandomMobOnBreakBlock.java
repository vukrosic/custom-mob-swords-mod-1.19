package net.vukrosic.custommobswordsmod.world;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.TntEntity;
import net.minecraft.entity.mob.CreeperEntity;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.util.Formatting;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.dimension.DimensionType;

public class SpawnRandomMobOnBreakBlock extends Block {
    public SpawnRandomMobOnBreakBlock(Settings settings) {
        super(settings);
    }

    @Override
    public void onBroken(WorldAccess world, BlockPos pos, BlockState state) {
        super.onBroken(world, pos, state);
        MinecraftClient client = MinecraftClient.getInstance();
        //client.world.spawnEntity(new CreeperEntity(EntityType.CREEPER, client.world));

        CreeperEntity creeper = EntityType.CREEPER.create(client.world);
        creeper.setPos(pos.getX(), pos.getY(), pos.getZ());
        client.world.spawnEntity(creeper);

        System.out.println("wwwwwwwwwwwwwwwwwwwwwwwwwww");
    }
}
