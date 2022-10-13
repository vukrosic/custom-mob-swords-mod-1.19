package net.vukrosic.custommobswordsmod.entity.custom.fireenderman;

import net.minecraft.entity.player.PlayerEntity;
import net.vukrosic.custommobswordsmod.entity.custom.PlayerEntityExt;

import java.util.ArrayList;

public class CombustometerManager {
    public static ArrayList <PlayerEntity> players = new ArrayList<PlayerEntity>();

    public static void AddPlayer(PlayerEntity player){
        players.add(player);
    }


    public static void RemovePlayer(PlayerEntity player) {
        players.remove(player);
    }
}
