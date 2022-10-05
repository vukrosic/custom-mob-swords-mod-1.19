package net.vukrosic.custommobswordsmod.util;

import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.screen.slot.Slot;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.math.random.Random;
import net.vukrosic.custommobswordsmod.item.ModItems;

import java.util.ArrayList;
import java.util.List;

public class FireInfectedPlayers {


    public static ArrayList<PlayerEntity> players = new ArrayList<>();
    public static ArrayList<Integer> fireTicks = new ArrayList<>();

    static int maxFireTicks = 100;

    public static void addPlayer(PlayerEntity player) {
        System.out.println("Adding player to fire infected players list");
        if(!players.contains(player)) {
            players.add(player);
            fireTicks.add(maxFireTicks);
        }
    }


    public static boolean isPlayerInList(PlayerEntity player) {
        return players.contains(player);
    }

    public static void tick() {
        // decrement every player's fire ticks
        for(int i = 0; i < fireTicks.size(); i++) {
            fireTicks.set(i, fireTicks.get(i) - 1);
            // damage players' stools
            damageTools(players.get(i));
        }

        // remove players with 0 fire ticks
        for(int i = 0; i < fireTicks.size(); i++) {
            if(fireTicks.get(i) <= 0) {
                convertInventoryItems(players.get(i));
                players.remove(i);
                fireTicks.remove(i);
            }
        }

    }



    static void damageTools(PlayerEntity player) {
        for(int i = 0; i < player.getInventory().size(); i++) {
            if (!player.world.isClient) {
                ItemStack itemStack = player.getInventory().getStack(i);
                if(itemStack.isDamageable()){
                    if(itemStack.getItem() instanceof ToolItem) {
                        if(((ToolItem) itemStack.getItem()).getMaterial() == ToolMaterials.GOLD) {
                            player.getInventory().removeStack(i);
                        }
                        itemStack.setDamage(itemStack.getDamage() + itemStack.getMaxDamage() / maxFireTicks);
                    }
                }
            }

        }
    }



    public static void convertInventoryItems(PlayerEntity player) {
        for (int i = 0; i < player.getInventory().size(); i++) {
            ItemStack stack = player.getInventory().getStack(i);
            if (Items.COOKED_BEEF.equals(stack.getItem()) || Items.BEEF.equals(stack.getItem())) {
                player.getInventory().setStack(i, new ItemStack(ModItems.BURNT_BEEF, stack.getCount()));
            } else if (Items.COOKED_CHICKEN.equals(stack.getItem()) || Items.CHICKEN.equals(stack.getItem())) {
                player.getInventory().setStack(i, new ItemStack(ModItems.BURNT_CHICKEN, stack.getCount()));
            } else if (Items.COOKED_COD.equals(stack.getItem()) || Items.COD.equals(stack.getItem())) {
                player.getInventory().setStack(i, new ItemStack(ModItems.BURNT_COD, stack.getCount()));
            } else if (Items.COOKED_MUTTON.equals(stack.getItem()) || Items.MUTTON.equals(stack.getItem())) {
                player.getInventory().setStack(i, new ItemStack(ModItems.BURNT_MUTTON, stack.getCount()));
            } else if (Items.COOKED_PORKCHOP.equals(stack.getItem()) || Items.PORKCHOP.equals(stack.getItem())) {
                player.getInventory().setStack(i, new ItemStack(ModItems.BURNT_PORKCHOP, stack.getCount()));
            } else if (Items.COOKED_RABBIT.equals(stack.getItem()) || Items.RABBIT.equals(stack.getItem())) {
                player.getInventory().setStack(i, new ItemStack(ModItems.BURNT_RABBIT, stack.getCount()));
            } else if (Items.COOKED_SALMON.equals(stack.getItem()) || Items.SALMON.equals(stack.getItem())) {
                player.getInventory().setStack(i, new ItemStack(ModItems.BURNT_SALMON, stack.getCount()));
            } else if (Items.BREAD.equals(stack.getItem())) {
                player.getInventory().setStack(i, new ItemStack(ModItems.BURNT_BREAD, stack.getCount()));
            } else if (Items.APPLE.equals(stack.getItem())) {
                player.getInventory().setStack(i, new ItemStack(ModItems.BURNT_APPLE, stack.getCount()));
            } else if (Items.CARROT.equals(stack.getItem()) || Items.GOLDEN_CARROT.equals(stack.getItem())) {
                player.getInventory().setStack(i, new ItemStack(ModItems.BURNT_CARROT, stack.getCount()));
            } else if (Items.POTATO.equals(stack.getItem()) || Items.BAKED_POTATO.equals(stack.getItem())) {
                player.getInventory().setStack(i, new ItemStack(ModItems.BURNT_POTATO, stack.getCount()));
            } else if (Items.GOLDEN_APPLE.equals(stack.getItem())) {
                player.getInventory().setStack(i, new ItemStack(ModItems.BURNT_GOLDEN_APPLE, stack.getCount()));
            } else if (Items.MELON.equals(stack.getItem())) {
                player.getInventory().setStack(i, new ItemStack(ModItems.BURNT_MELON, stack.getCount()));
            } else if (Items.MUSHROOM_STEW.equals(stack.getItem())) {
                player.getInventory().setStack(i, new ItemStack(ModItems.BURNT_STEW, stack.getCount()));
            } else if (Items.SUSPICIOUS_STEW.equals(stack.getItem())) {
                player.getInventory().setStack(i, new ItemStack(ModItems.BURNT_STEW, stack.getCount()));
            } else if (Items.RABBIT_STEW.equals(stack.getItem())) {
                player.getInventory().setStack(i, new ItemStack(ModItems.BURNT_STEW, stack.getCount()));
            } else {
                player.getInventory().removeStack(i);
                //player.getInventory().setStack(i, new ItemStack(ModItems.BURNT_GOLDEN_APPLE, stack.getCount()));
            }



        }
    }
}
