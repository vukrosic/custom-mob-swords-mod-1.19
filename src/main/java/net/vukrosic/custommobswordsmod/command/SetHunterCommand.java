package net.vukrosic.custommobswordsmod.command;

import com.mojang.brigadier.CommandDispatcher;
import io.netty.buffer.Unpooled;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.fabricmc.loader.impl.lib.sat4j.core.Vec;
import net.minecraft.command.CommandRegistryAccess;
import net.minecraft.command.argument.EntityArgumentType;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.Vec3i;
import net.vukrosic.custommobswordsmod.networking.ModMessages;

import java.util.ArrayList;
import java.util.Collection;

public class SetHunterCommand {

    public static PlayerEntity pray = null;

    public static ArrayList<PlayerEntity> hunters = new ArrayList<>();


    public static void register(CommandDispatcher<ServerCommandSource> serverCommandSourceCommandDispatcher, CommandRegistryAccess commandRegistryAccess, CommandManager.RegistrationEnvironment registrationEnvironment) {
        serverCommandSourceCommandDispatcher.register(CommandManager.literal("manhunt")
                .then(CommandManager.literal("setpray")
                        .then(CommandManager.argument("targets", EntityArgumentType.entities())
                                .executes((context) -> {
                                    return setPray((ServerCommandSource)context.getSource(), EntityArgumentType.getEntities(context, "targets"));
                                })))
                .then(CommandManager.literal("sethunter")
                        .then(CommandManager.argument("targets", EntityArgumentType.entities())
                                .executes((context) -> {
                                    return setHunter((ServerCommandSource)context.getSource(), EntityArgumentType.getEntities(context, "targets"));
                                })))
                .then(CommandManager.literal("reset")
                        .executes((context) -> {
                                    return reset((ServerCommandSource)context.getSource(), EntityArgumentType.getEntities(context, "targets"));
                                })));

    }
    private static int setPray(ServerCommandSource source, Collection<? extends Entity> targets) {
        for (Entity entity : targets) {
            if (entity instanceof PlayerEntity) {
                pray = (PlayerEntity) entity;
                System.out.println("Prey set to " + pray);
                PacketByteBuf buf = new PacketByteBuf(Unpooled.buffer());
                // save player to buf
                buf.writeUuid(pray.getUuid());
                ServerPlayNetworking.send((ServerPlayerEntity) entity, ModMessages.SET_PREY, buf);
            }
        }
        return 1;
    }
    private static int setHunter(ServerCommandSource source, Collection<? extends Entity> targets) {
        for (Entity entity : targets) {
            if (entity instanceof PlayerEntity) {
                hunters.add((PlayerEntity) entity);
                System.out.println("Hunter set to " + pray);
            }
        }
        return 1;
    }

    private static int reset(ServerCommandSource source, Collection<? extends Entity> targets) {
        pray = null;
        hunters.clear();
        return 1;
    }

}
