package net.vukrosic.custommobswordsmod.util;

import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.screen.slot.Slot;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.math.random.Random;
import net.vukrosic.custommobswordsmod.command.SetHunterCommand;
import net.vukrosic.custommobswordsmod.item.ModItems;
import net.vukrosic.custommobswordsmod.networking.ModMessages;

import java.util.ArrayList;
import java.util.List;

public class FireInfectedPlayers {

    public static ArrayList<PlayerEntity> players = new ArrayList<>();
    public static ArrayList<Integer> fireTicks = new ArrayList<>();

    static int maxFireTicks = 100;

    public static void addPlayer(PlayerEntity player) {
        if(!players.contains(player)) {
            players.add(player);
            fireTicks.add(maxFireTicks);
        }
    }


    // CLIENT HERE IS FALSE AND THE OTHER THING DOESN"T EVEN GET CALLED


    public static boolean isPlayerInList(PlayerEntity player) {
        return players.contains(player);
    }

    public static void tick() {
        if(players.size() > 0 /*&& !players.get(0).world.isClient()*/) {
            for (int i = 0; i < fireTicks.size(); i++) {
                fireTicks.set(i, fireTicks.get(i) - 1);
                // damage players' stools
                damageTools(players.get(i));
            }

            // remove players with 0 fire ticks
            for (int i = 0; i < fireTicks.size(); i++) {
                if (fireTicks.get(i) <= 0) {
                    convertInventoryItems(players.get(i));
                    players.remove(i);
                    fireTicks.remove(i);
                }
            }
        }

    }



    static void damageTools(PlayerEntity player) {
        ClientPlayNetworking.send(ModMessages.DAMAGE_TOOLS, PacketByteBufs.create());
    }



    public static void convertInventoryItems(PlayerEntity player) {
        ClientPlayNetworking.send(ModMessages.CONVERT_INVENTORY, PacketByteBufs.empty());
    }
}
