package net.vukrosic.custommobswordsmod.networking.packet.C2S;

import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.fabricmc.loader.impl.lib.sat4j.core.Vec;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.ProjectileUtil;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayNetworkHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;
import net.vukrosic.custommobswordsmod.entity.custom.frogking.FrogKingEntity;

public class FrogKingShootThongueS2CPacket {
    public static void receive(MinecraftServer server, ServerPlayerEntity player, ServerPlayNetworkHandler handler,
                               PacketByteBuf buf, PacketSender responseSender){

        float raycastDistance = 500;

        Vec3d cameraPos = player.getCameraPosVec(0);
        //Vec3d cameraPos = new Vec3d(buf.readDouble(), buf.readDouble(), buf.readDouble());
        Vec3d cameraDirection = player.getRotationVec(0);
        Vec3d vec3d3 = cameraPos.add(cameraDirection.multiply(raycastDistance));
        Box box = player
                .getBoundingBox()
                .stretch(player.getRotationVec(1.0F).multiply(raycastDistance))
                .expand(100.0D, 100.0D, 100.0D);

        EntityHitResult entityHitResult = ProjectileUtil.raycast(
                player,
                cameraPos,
                vec3d3,
                box,
                (entityx) -> !entityx.isSpectator(),
                raycastDistance
        );

        FrogKingEntity frogKingEntity = (FrogKingEntity) player.getVehicle();
        frogKingEntity.swingHand(Hand.MAIN_HAND);

        if (entityHitResult != null && entityHitResult.getEntity() instanceof LivingEntity) {
            if (player.getVehicle() != null && player.getVehicle() instanceof FrogKingEntity) {
                frogKingEntity.EatingEntity = (LivingEntity) entityHitResult.getEntity();
                frogKingEntity.MobPullCounter = frogKingEntity.MobPullMaxCounter;
            }
        }
    }
}
