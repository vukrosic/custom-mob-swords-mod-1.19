package net.vukrosic.custommobswordsmod.item.custom;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.PickaxeItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class DoublingPickaxeItem extends PickaxeItem {
    public DoublingPickaxeItem(ToolMaterial material, int attackDamage, float attackSpeed, Settings settings) {
        super(material, attackDamage, attackSpeed, settings);
    }

    public static Boolean breaking = false;

    public static int radius = 2;
    public static int maxRadius = 15;
    static int x;
    static int y;
    static int z;
    static int i;
    static int j;
    static int k;

    @Override
    public boolean postMine(ItemStack stack, World world, BlockState state, BlockPos pos, LivingEntity miner) {

        x = pos.getX();
        y = pos.getY();
        z = pos.getZ();

        breaking = true;
        radius = 2;

        breakNextBlock();
        System.out.println("11111 ");
        return super.postMine(stack, world, state, pos, miner);


    }





    public static void breakNextBlock(){
        World world = MinecraftClient.getInstance().world;
        /*if (!world.isClient) {*/
            int radiusSquared = radius * radius;
            for(int i = -radius; i <= radius; i++) {
                for(int j = -radius; j <= radius; j++) {
                    for(int k = -radius; k <= radius; k++) {
                        if (i * i + j * j + k * k <= radiusSquared) {
                            BlockPos newPos = new BlockPos(x + i, y + j, z + k);
                            BlockState newState = world.getBlockState(newPos);
                            if (newState.getBlock() != Blocks.AIR) {
                                world.breakBlock(newPos, true);
                            }


                        }



                    }
                }
            /*}*/

        }



    }





}
