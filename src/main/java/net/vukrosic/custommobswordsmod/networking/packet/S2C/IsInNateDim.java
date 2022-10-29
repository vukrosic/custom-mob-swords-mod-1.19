package net.vukrosic.custommobswordsmod.networking.packet.S2C;

import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayNetworkHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.vukrosic.custommobswordsmod.entity.custom.PlayerEntityExt;
import net.vukrosic.custommobswordsmod.networking.ModMessages;

public class IsInNateDim {
    public static void receive(MinecraftClient minecraftClient, ClientPlayNetworkHandler clientPlayNetworkHandler, PacketByteBuf packetByteBuf, PacketSender packetSender) {
        ClientPlayerEntity player = minecraftClient.player;
        ((PlayerEntityExt)player).setChickenEffect(packetByteBuf.readBoolean());
    }
}
