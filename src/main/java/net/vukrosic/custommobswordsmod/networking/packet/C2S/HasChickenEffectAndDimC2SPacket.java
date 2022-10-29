package net.vukrosic.custommobswordsmod.networking.packet.C2S;

import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.sound.PositionedSoundInstance;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayNetworkHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.vukrosic.custommobswordsmod.entity.custom.PlayerEntityExt;
import net.vukrosic.custommobswordsmod.networking.ModMessages;
import net.vukrosic.custommobswordsmod.particle.ModParticles;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;

import java.util.Random;

public class HasChickenEffectAndDimC2SPacket {

    public static void receive(MinecraftServer minecraftServer, ServerPlayerEntity serverPlayerEntity,
                               ServerPlayNetworkHandler handler, PacketByteBuf packetByteBuf, PacketSender packetSender) {

    }
}
