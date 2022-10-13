package net.vukrosic.custommobswordsmod.entity.custom.frogking;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.text.Text;
import net.vukrosic.custommobswordsmod.entity.custom.PlayerEntityExt;

import java.util.ArrayList;

public class EatenMobsByFrogKing {
    public static ArrayList<LivingEntity> EatenLivingEntities = new ArrayList<LivingEntity>();
    public static ArrayList<PlayerEntity> EatenPlayers = new ArrayList<PlayerEntity>();
    public static void ShootMob(PlayerEntity player){
        // get vehicle
        if(player.getVehicle() != null){
            if(player.getVehicle() instanceof FrogKingEntity){
                FrogKingEntity frog = (FrogKingEntity) player.getVehicle();
                frog.ShootEatenEntity();
            }
        }
    }
}
