package net.vukrosic.custommobswordsmod.networking.packet.C2S;

import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayNetworkHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.vukrosic.custommobswordsmod.entity.custom.frogking.FrogKingEntity;

public class FrogKingJumpC2SPacket {
    public static void receive(MinecraftServer server, ServerPlayerEntity player, ServerPlayNetworkHandler handler,
                               PacketByteBuf buf, PacketSender responseSender){
        FrogKingEntity frogKingEntity = (FrogKingEntity) player.getVehicle();
        frogKingEntity.playerJumped = true;
        frogKingEntity.jump();
    }
}
