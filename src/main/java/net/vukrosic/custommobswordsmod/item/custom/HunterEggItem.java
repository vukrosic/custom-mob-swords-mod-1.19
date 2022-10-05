package net.vukrosic.custommobswordsmod.item.custom;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.util.ActionResult;

public class HunterEggItem extends Item {
    public HunterEggItem(Settings settings) {
        super(settings);
    }

    PlayerEntity trappedHunter = null;

    void SetTrappedHunter(PlayerEntity trappedHunter){
        this.trappedHunter = trappedHunter;
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        if(!context.getWorld().isClient()){
            trappedHunter.teleport(context.getBlockPos().getX(), context.getBlockPos().getY(), context.getBlockPos().getZ());
        }
        return super.useOnBlock(context);
    }
}
