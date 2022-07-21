package net.vukrosic.custommobswordsmod.util;


import net.minecraft.client.render.debug.DebugRenderer;
import net.minecraft.util.math.Box;
import net.vukrosic.custommobswordsmod.item.custom.DoublingPickaxeItem;
import net.vukrosic.custommobswordsmod.item.custom.InfinityBowItem;
import net.vukrosic.custommobswordsmod.item.custom.SwapSwordItem;

public class TickCounter {
    //MinecraftClient client = MinecraftClient.getInstance().world.getTime()

    static int tickCounter = 0;

    public static void incrementTickCounter(){
        tickCounter++;
    }

    public static int getTickCounter(){
        return tickCounter;
    }




    public static void calledEveryTick() {
/*
        // Stopwatch
        if(!Stopwatch.timePaused){
            Stopwatch.addOneTick();
        }
*/

        // CutdownTreesCommand breaking blocks every tick

        if(tickCounter % 10 == 0) {
            if (DoublingPickaxeItem.breaking && DoublingPickaxeItem.radius < 10) {
                DoublingPickaxeItem.radius += 1;
                DoublingPickaxeItem.breakNextBlock();
            }
            else
            {
                DoublingPickaxeItem.radius += 2;
                DoublingPickaxeItem.breaking = false;
            }
        }










    }


}
