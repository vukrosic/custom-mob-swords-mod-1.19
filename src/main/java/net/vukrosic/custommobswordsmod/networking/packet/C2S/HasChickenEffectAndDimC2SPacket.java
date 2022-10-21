package net.vukrosic.custommobswordsmod.networking.packet.C2S;

import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayNetworkHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.vukrosic.custommobswordsmod.entity.custom.PlayerEntityExt;
import net.vukrosic.custommobswordsmod.networking.ModMessages;

public class HasChickenEffectAndDimC2SPacket {

    public static void receive(MinecraftServer minecraftServer, ServerPlayerEntity serverPlayerEntity, ServerPlayNetworkHandler handler, PacketByteBuf packetByteBuf, PacketSender packetSender) {
        boolean b = ((PlayerEntityExt)serverPlayerEntity).hasChickenEffect();
        // send b in a packet to the client
        PacketByteBuf buf = PacketByteBufs.create();
        buf.writeBoolean(b);
        ClientPlayNetworking.send(ModMessages.CHICKEN_EFFECT_POST_ID, buf);
    }
/*
    public static boolean isInNateDim(MinecraftServer minecraftServer, ServerPlayerEntity serverPlayerEntity, ServerPlayNetworkHandler handler, PacketByteBuf packetByteBuf, PacketSender packetSender) {
        return serverPlayerEntity.getWorld().getRegistryKey().getValue().toString() == "custommobswordsmod:nate_dim";
    }*/
}
