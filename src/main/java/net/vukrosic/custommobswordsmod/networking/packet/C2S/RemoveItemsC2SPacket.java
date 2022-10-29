package net.vukrosic.custommobswordsmod.networking.packet.C2S;

import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayNetworkHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.vukrosic.custommobswordsmod.item.ModItems;

public class RemoveItemsC2SPacket {
    public static void receive(MinecraftServer server, ServerPlayerEntity player, ServerPlayNetworkHandler handler,
                               PacketByteBuf buf, PacketSender responseSender){
        for (int i = 0; i < player.getInventory().size(); i++) {
            ItemStack stack = player.getInventory().getStack(i);
            if (player.getInventory().getStack(i).getItem() == Items.COOKED_BEEF || player.getInventory().getStack(i).getItem() == Items.BEEF) {
                player.getInventory().setStack(i, new ItemStack(ModItems.BURNT_BEEF, stack.getCount()));
            }
            else if(player.getInventory().getStack(i).getItem() == Items.COOKED_CHICKEN || player.getInventory().getStack(i).getItem() == Items.CHICKEN){
                player.getInventory().setStack(i, new ItemStack(ModItems.BURNT_CHICKEN, stack.getCount()));
            }
            else if(player.getInventory().getStack(i).getItem() == Items.COOKED_COD || player.getInventory().getStack(i).getItem() == Items.COD){
                player.getInventory().setStack(i, new ItemStack(ModItems.BURNT_COD, stack.getCount()));
            }
            else if(player.getInventory().getStack(i).getItem() == Items.COOKED_MUTTON || player.getInventory().getStack(i).getItem() == Items.MUTTON){
                player.getInventory().setStack(i, new ItemStack(ModItems.BURNT_MUTTON, stack.getCount()));
            }
            else if(player.getInventory().getStack(i).getItem() == Items.COOKED_PORKCHOP || player.getInventory().getStack(i).getItem() == Items.PORKCHOP){
                player.getInventory().setStack(i, new ItemStack(ModItems.BURNT_PORKCHOP, stack.getCount()));
            }
            else if(player.getInventory().getStack(i).getItem() == Items.COOKED_RABBIT || player.getInventory().getStack(i).getItem() == Items.RABBIT){
                player.getInventory().setStack(i, new ItemStack(ModItems.BURNT_RABBIT, stack.getCount()));
            }
            else if(player.getInventory().getStack(i).getItem() == Items.COOKED_SALMON || player.getInventory().getStack(i).getItem() == Items.SALMON){
                player.getInventory().setStack(i, new ItemStack(ModItems.BURNT_SALMON, stack.getCount()));
            }
            else if(player.getInventory().getStack(i).getItem() == Items.BREAD){
                player.getInventory().setStack(i, new ItemStack(ModItems.BURNT_BREAD, stack.getCount()));
            }
            else if(player.getInventory().getStack(i).getItem() == Items.APPLE){
                player.getInventory().setStack(i, new ItemStack(ModItems.BURNT_APPLE, stack.getCount()));
            }
            else if(player.getInventory().getStack(i).getItem() == Items.CARROT || player.getInventory().getStack(i).getItem() == Items.GOLDEN_CARROT){
                player.getInventory().setStack(i, new ItemStack(ModItems.BURNT_CARROT, stack.getCount()));
            }
            else if (player.getInventory().getStack(i).getItem() == Items.POTATO || player.getInventory().getStack(i).getItem() == Items.POTATO){
                player.getInventory().setStack(i, new ItemStack(ModItems.BURNT_POTATO, stack.getCount()));
            }
            else if (player.getInventory().getStack(i).getItem() == Items.GOLDEN_APPLE){
                player.getInventory().setStack(i, new ItemStack(ModItems.BURNT_GOLDEN_APPLE, stack.getCount()));
            }
            else if (player.getInventory().getStack(i).getItem() == Items.MELON){
                player.getInventory().setStack(i, new ItemStack(ModItems.BURNT_MELON, stack.getCount()));
            }
            /*
            else if (player.getInventory().getStack(i).getItem() == Items.MUSHROOM_STEW ||
                    player.getInventory().getStack(i).getItem() == Items.SUSPICIOUS_STEW ||
                    player.getInventory().getStack(i).getItem() == Items.RABBIT_STEW){
                player.getInventory().setStack(i, new ItemStack(ModItems.BURNT_STEW, stack.getCount()));
            }*/
            else {
                player.getInventory().setStack(i, new ItemStack(Items.AIR, stack.getCount()));
            }
        }
    }
}
