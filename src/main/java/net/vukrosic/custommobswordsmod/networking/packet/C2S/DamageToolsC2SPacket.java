package net.vukrosic.custommobswordsmod.networking.packet.C2S;

import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ToolItem;
import net.minecraft.item.ToolMaterials;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayNetworkHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;

public class DamageToolsC2SPacket {
    static int maxFireTicks = 100;
    public static void receive(MinecraftServer server, ServerPlayerEntity player, ServerPlayNetworkHandler handler,
                               PacketByteBuf buf, PacketSender responseSender) {
        for (int i = 0; i < player.getInventory().size(); i++) {
            ItemStack itemStack = player.getInventory().getStack(i);
            if (itemStack.isDamageable()) {
                if (itemStack.getItem() instanceof ToolItem) {
                    if (((ToolItem) itemStack.getItem()).getMaterial() == ToolMaterials.GOLD) {
                        player.getInventory().removeStack(i);
                    }
                    itemStack.setDamage(itemStack.getDamage() + itemStack.getMaxDamage() / maxFireTicks);
                }
            }
        }
    }
}
