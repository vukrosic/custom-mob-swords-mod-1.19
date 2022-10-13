package net.vukrosic.custommobswordsmod.command;

import com.mojang.brigadier.CommandDispatcher;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.command.CommandRegistryAccess;
import net.minecraft.command.argument.EntityArgumentType;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.vukrosic.custommobswordsmod.entity.custom.PlayerEntityExt;
import net.vukrosic.custommobswordsmod.entity.custom.chunken.ChunkenPhaseManager;

import java.util.Collection;

public class ResetChunkenCommand {
    public static void register(CommandDispatcher<ServerCommandSource> serverCommandSourceCommandDispatcher, CommandRegistryAccess commandRegistryAccess, CommandManager.RegistrationEnvironment registrationEnvironment) {
        serverCommandSourceCommandDispatcher.register(CommandManager.literal("chunken")
                .then(CommandManager.literal("reset")
                        .executes((context) -> {
                            return reset((ServerCommandSource)context.getSource()/*, EntityArgumentType.getEntities(context, "targets")*/);
                        }))
                .then(CommandManager.literal("removeEffect")
                        .executes((context) -> {
                            return removeEffect((ServerCommandSource)context.getSource()/*, EntityArgumentType.getEntities(context, "targets")*/);
                        }))
                .then(CommandManager.literal("giveEffect")
                        .executes((context) -> {
                            return giveEffect((ServerCommandSource)context.getSource()/*, EntityArgumentType.getEntities(context, "targets")*/);
                        }))
                .then(CommandManager.literal("setFinalPhase")
                        .executes((context) -> {
                            return setfinalphase((ServerCommandSource)context.getSource()/*, EntityArgumentType.getEntities(context, "targets")*/);
                        })));

    }



    private static int removeEffect(ServerCommandSource source/*, Collection<? extends Entity> targets*/) {
        // put speedrunner in creative mode
        ((PlayerEntityExt)source.getPlayer()).setChickenEffect(false);
        return 1;
    }

    private static int giveEffect(ServerCommandSource source/*, Collection<? extends Entity> targets*/) {
        // put speedrunner in creative mode
        ((PlayerEntityExt)source.getPlayer()).setChickenEffect(true);
        return 1;
    }
    private static int reset(ServerCommandSource source/*, Collection<? extends Entity> targets*/) {
        // put speedrunner in creative mode
        // for each hunter in hunters list
        for(PlayerEntity hunter : SetHunterCommand.hunters){
            ((PlayerEntityExt)hunter).setChickenEffect(false);
        }
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