package net.vukrosic.custommobswordsmod.mixin;

import net.minecraft.block.Block;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.item.CompassAnglePredicateProvider;
import net.minecraft.client.item.UnclampedModelPredicateProvider;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.Entity;
import net.minecraft.entity.passive.SheepEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.math.*;
import net.minecraft.world.Heightmap;
import net.minecraft.world.World;
import net.minecraft.world.dimension.DimensionType;
import net.minecraft.world.dimension.DimensionTypes;
import net.vukrosic.custommobswordsmod.command.SetHunterCommand;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;

@Mixin(CompassAnglePredicateProvider.class)
public abstract class CompassAnglePredicateProviderMixin implements UnclampedModelPredicateProvider {
    boolean trackingPrey = true;

    BlockPos netherPortalPos = null;

    BlockPos OverworldPreyPos = new BlockPos(0, 0, 0);
    BlockPos NetherPreyPos = new BlockPos(0, 0, 0);;
    BlockPos EndPreyPos = new BlockPos(0, 0, 0);;
    boolean spinningCrazily = false;



    @Inject(method = "getAngleTo", at = @At("HEAD"), cancellable = true)
    private void getAngleTo(Entity entity, long time, BlockPos pos, CallbackInfoReturnable<Float> cir) {
    }

    /**
     * @author
     * @reason
     */
    @Overwrite
    private double getAngleTo(Entity entity, BlockPos pos) {
        if(SetHunterCommand.pray != null && !spinningCrazily) {
            if (SetHunterCommand.pray.getWorld().getRegistryKey().getValue().toString().equals("minecraft:overworld")) {
                OverworldPreyPos = SetHunterCommand.pray.getBlockPos();
            } else if (SetHunterCommand.pray.getWorld().getRegistryKey().getValue().toString().equals("minecraft:the_nether")) {
                //SetHunterCommand.pray.sendMessage(Text.of("Saving Nether prey position"), false);
                NetherPreyPos = SetHunterCommand.pray.getBlockPos();
            } else if (SetHunterCommand.pray.getWorld().getRegistryKey().getValue().toString().equals("minecraft:the_end")) {
                //SetHunterCommand.pray.sendMessage(Text.of("Saving End prey position"), false);
                EndPreyPos = SetHunterCommand.pray.getBlockPos();
            }



            if (entity.world.getRegistryKey().getValue().toString().equals("minecraft:overworld")) {
                Vec3d vec3d = Vec3d.ofCenter(OverworldPreyPos);
                //SetHunterCommand.pray.sendMessage(Text.of("Compass points to OverworldPreyPos"), false);
                return Math.atan2(vec3d.getZ() - entity.getZ(), vec3d.getX() - entity.getX()) / 6.2831854820251465;
            } else if (entity.world.getRegistryKey().getValue().toString().equals("minecraft:the_nether")) {
                Vec3d vec3d = Vec3d.ofCenter(NetherPreyPos);
                //SetHunterCommand.pray.sendMessage(Text.of("Compass points to NetherPreyPos"), false);
                return Math.atan2(vec3d.getZ() - entity.getZ(), vec3d.getX() - entity.getX()) / 6.2831854820251465;
            } else if (entity.world.getRegistryKey().getValue().toString().equals("minecraft:the_end")) {
                Vec3d vec3d = Vec3d.ofCenter(EndPreyPos);
                //SetHunterCommand.pray.sendMessage(Text.of("Compass points to EndPreyPos"), false);
                return Math.atan2(vec3d.getZ() - entity.getZ(), vec3d.getX() - entity.getX()) / 6.2831854820251465;
            } else {
                // get name of the dimension
                //SetHunterCommand.pray.sendMessage(Text.of("The entity is in get value to string:   " + entity.world.getRegistryKey().getValue().toString()), false);
                return Math.random();
            }
        }
        else if (SetHunterCommand.pray != null && spinningCrazily){
            return Math.random();
        }
        else {
            Vec3d vec3d = Vec3d.ofCenter(pos);
            return Math.atan2(vec3d.getZ() - entity.getZ(), vec3d.getX() - entity.getX()) / 6.2831854820251465;
        }
    }
            // get nether portal block pos


    @Inject(method = "canPointTo", at = @At("RETURN"), cancellable = true)
    private void canPointTo(Entity entity, GlobalPos pos, CallbackInfoReturnable<Boolean> cir) {
        if (spinningCrazily) {
            cir.setReturnValue(false);
        }
    }
}
