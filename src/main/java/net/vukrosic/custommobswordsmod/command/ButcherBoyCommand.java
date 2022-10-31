package net.vukrosic.custommobswordsmod.command;

import com.mojang.brigadier.CommandDispatcher;
import net.minecraft.command.CommandRegistryAccess;
import net.minecraft.entity.boss.ServerBossBar;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class ButcherBoyCommand {

    public static ArrayList<ServerBossBar> serverBossBars = new ArrayList();

    public static void register(CommandDispatcher<ServerCommandSource> serverCommandSourceCommandDispatcher, CommandRegistryAccess commandRegistryAccess, CommandManager.RegistrationEnvironment registrationEnvironment) {
        serverCommandSourceCommandDispatcher.register(CommandManager.literal("butcherboy")
                .then(CommandManager.literal("removeBars")
                        .executes((context) -> {
                            return removeBars((ServerCommandSource) context.getSource()/*, EntityArgumentType.getEntities(context, "targets")*/);
                        })));

    }
    private static int removeBars (ServerCommandSource source){
        // remove all serverbars
        for (int i = 0; i < serverBossBars.size(); i++) {
            serverBossBars.get(i).clearPlayers();
        }
        return 1;
    }
}