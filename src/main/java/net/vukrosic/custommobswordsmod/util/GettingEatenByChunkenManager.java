package net.vukrosic.custommobswordsmod.util;

import net.minecraft.entity.player.PlayerEntity;
import org.jetbrains.annotations.Nullable;

public class GettingEatenByChunkenManager {
    public static PlayerEntity player = null;

    public static void setPlayer(@Nullable PlayerEntity player) {
        GettingEatenByChunkenManager.player = player;
    }
}
