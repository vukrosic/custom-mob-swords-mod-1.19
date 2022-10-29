package net.vukrosic.custommobswordsmod.entity.custom.frogking;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.passive.HorseColor;
import net.minecraft.entity.passive.HorseEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.text.Text;
import net.vukrosic.custommobswordsmod.entity.custom.PlayerEntityExt;

import java.util.ArrayList;

public class EatenMobsByFrogKing {
    public static ArrayList<LivingEntity> EatenLivingEntities = new ArrayList<LivingEntity>();
    // make a list of entity types
    public static ArrayList<EntityType> EatenEntityTypes = new ArrayList<EntityType>();
    // make a list that saves entity attributes so I can spawn the same entity

    public static ArrayList<PlayerEntity> EatenPlayers = new ArrayList<PlayerEntity>();
    /*
    public static void ShootMob(PlayerEntity player){
        // get vehicle
        if(player.getVehicle() != null){
            if(player.getVehicle() instanceof FrogKingEntity){
                FrogKingEntity frog = (FrogKingEntity) player.getVehicle();
                frog.ShootEatenEntity();
            }
        }
    }*/
}
