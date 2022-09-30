package net.vukrosic.custommobswordsmod.command;

import com.mojang.brigadier.CommandDispatcher;
import net.minecraft.command.CommandRegistryAccess;
import net.minecraft.command.argument.EntityArgumentType;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;

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
               System.out.println("Hunter set to " + pray);
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
