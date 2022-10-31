package net.vukrosic.custommobswordsmod.mixin;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import com.mojang.datafixers.util.Pair;
import net.minecraft.block.Blocks;
import net.minecraft.entity.ai.brain.Activity;
import net.minecraft.entity.ai.brain.Brain;
import net.minecraft.entity.ai.brain.MemoryModuleState;
import net.minecraft.entity.ai.brain.MemoryModuleType;
import net.minecraft.entity.ai.brain.task.BiasedLongJumpTask;
import net.minecraft.entity.ai.brain.task.LeapingChargeTask;
import net.minecraft.entity.passive.FrogBrain;
import net.minecraft.entity.passive.FrogEntity;
import net.minecraft.sound.SoundEvents;
import net.minecraft.tag.BlockTags;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(FrogBrain.class)
public class FrogBrainMixin {

    @Inject (method = "addLongJumpActivities", at = @At("HEAD"), cancellable = true)
    private static void addLongJumpActivities(Brain<FrogEntity> brain, CallbackInfo ci) {
        ci.cancel();
    }
}
