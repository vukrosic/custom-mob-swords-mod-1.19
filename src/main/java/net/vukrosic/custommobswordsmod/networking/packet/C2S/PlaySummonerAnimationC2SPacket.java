package net.vukrosic.custommobswordsmod.networking.packet.C2S;

import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayNetworkHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Hand;
import net.vukrosic.custommobswordsmod.entity.custom.PlayerEntityExt;

public class PlaySummonerAnimationC2SPacket {
    public static void receive(MinecraftServer server, ServerPlayerEntity player, ServerPlayNetworkHandler handler,
                               PacketByteBuf buf, PacketSender responseSender){
        if(((PlayerEntityExt)player).getSummonerEntityGL() != null){
            ((PlayerEntityExt)player).getSummonerEntityGL().swingHand(Hand.MAIN_HAND);
            ((PlayerEntityExt)player).getSummonerEntityGL().enableParticleCountdown = true;

        }
    }
}
