package net.vukrosic.custommobswordsmod.item.custom;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.CompassItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.GlobalPos;
import net.minecraft.world.World;
import net.vukrosic.custommobswordsmod.command.SetHunterCommand;
import org.jetbrains.annotations.Nullable;

public class TrackingCompassItem extends CompassItem {
    PlayerEntity player = SetHunterCommand.pray;
    public TrackingCompassItem(Settings settings) {
        super(settings);
    }


    // point compass towards player
    public void pointCompass(PlayerEntity player){
        this.player = player;
    }


    @Override
    public boolean hasGlint(ItemStack stack) {
        return super.hasGlint(stack);
    }
}
