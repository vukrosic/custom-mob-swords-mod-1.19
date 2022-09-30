package net.vukrosic.custommobswordsmod.item.custom;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import net.vukrosic.custommobswordsmod.util.CarbonPoisoningEffectManager;

public class BurntFoodItem extends Item {
    public BurntFoodItem(Settings settings) {
        super(settings);
    }

    // when it is eaten, add the player to the carbon poisoning effect manager


    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        CarbonPoisoningEffectManager.addPlayer(user);
        return super.use(world, user, hand);
    }
}
