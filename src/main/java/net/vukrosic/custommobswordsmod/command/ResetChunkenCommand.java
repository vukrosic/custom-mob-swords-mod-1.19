package net.vukrosic.custommobswordsmod.command;

import com.mojang.brigadier.CommandDispatcher;
import net.minecraft.command.CommandRegistryAccess;
import net.minecraft.command.argument.EntityArgumentType;
import net.minecraft.entity.Entity;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.vukrosic.custommobswordsmod.entity.custom.chunken.ChunkenPhaseManager;

import java.util.Collection;

public class ResetChunkenCommand {
    public static void register(CommandDispatcher<ServerCommandSource> serverCommandSourceCommandDispatcher, CommandRegistryAccess commandRegistryAccess, CommandManager.RegistrationEnvironment registrationEnvironment) {
        serverCommandSourceCommandDispatcher.register(CommandManager.literal("chunken")
                .then(CommandManager.literal("reset")
                        .executes((context) -> {
                            return reset((ServerCommandSource)context.getSource()/*, EntityArgumentType.getEntities(context, "targets")*/);
                        }))
                .then(CommandManager.literal("setfinalphase")
                        .executes((context) -> {
                            return setfinalphase((ServerCommandSource)context.getSource()/*, EntityArgumentType.getEntities(context, "targets")*/);
                        })));

    }



    private static int reset(ServerCommandSource source/*, Collection<? extends Entity> targets*/) {
        // put speedrunner in creative mode

        ChunkenPhaseManager.resetChunkenPhase();
        source.getServer().getCommandManager().executeWithPrefix(source, "/scale set 1 @e[type=custommobswordsmod:chunkengl]");
        return 1;
    }

    private static int setfinalphase(ServerCommandSource source/*, Collection<? extends Entity> targets*/) {
        ChunkenPhaseManager.chunkenPhase = 4;
        source.getServer().getCommandManager().executeWithPrefix(source, "/scale set 4.2 @e[type=custommobswordsmod:chunkengl]");
        return 1;
    }

}
