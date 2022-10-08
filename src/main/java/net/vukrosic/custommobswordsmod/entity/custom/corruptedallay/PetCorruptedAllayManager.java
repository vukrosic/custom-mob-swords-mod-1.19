package net.vukrosic.custommobswordsmod.entity.custom.corruptedallay;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.WorldGenerationProgressListener;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.World;
import net.minecraft.world.dimension.DimensionOptions;
import net.minecraft.world.level.ServerWorldProperties;
import net.minecraft.world.level.storage.LevelStorage;
import net.minecraft.world.spawner.Spawner;
import net.vukrosic.custommobswordsmod.command.SetHunterCommand;

import java.util.List;
import java.util.concurrent.Executor;

public class PetCorruptedAllayManager {
    public static boolean isBeingLookedAt = false;
    /*
    PlayerEntity speedrunner = SetHunterCommand.pray;
    public static CorruptedAllayAllayEntityGL allay;
    public static CorruptedAllayVexEntityGL vex;
    static boolean visible = false;
    static int experience = 0;

    public static void checkIfHuntersSee() {
        if (SetHunterCommand.hunters != null) {
            SetHunterCommand.hunters.forEach(hunter -> {
                if(hunter.canSee(allay)) {
                    visible = true;
                }
            });
        }
    }*/

}
