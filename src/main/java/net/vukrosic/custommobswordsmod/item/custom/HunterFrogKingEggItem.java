package net.vukrosic.custommobswordsmod.item.custom;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.EggItem;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import net.vukrosic.custommobswordsmod.entity.custom.chunken.HunterGoldEggEntity;
import net.vukrosic.custommobswordsmod.entity.custom.frogking.HunterFrogKingEggEntity;

public class HunterFrogKingEggItem extends EggItem {
    public HunterFrogKingEggItem(Settings settings) {
        super(settings);
    }

    public PlayerEntity trappedHunter = null;

    void SetTrappedHunter(PlayerEntity trappedHunter){
        this.trappedHunter = trappedHunter;
    }


    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack itemStack = user.getStackInHand(hand);
        world.playSound((PlayerEntity)null, user.getX(), user.getY(), user.getZ(), SoundEvents.ENTITY_ENDER_PEARL_THROW, SoundCategory.NEUTRAL, 0.5F, 0.4F / (world.getRandom().nextFloat() * 0.4F + 0.8F));
        user.getItemCooldownManager().set(this, 20);
        if (!world.isClient) {
            HunterFrogKingEggEntity hunterEggEntity = new HunterFrogKingEggEntity(world, user);
            hunterEggEntity.setItem(itemStack);
            hunterEggEntity.setVelocity(user, user.getPitch(), user.getYaw(), 0.0F, 1.5F, 1.0F);
            world.spawnEntity(hunterEggEntity);
        }
        user.incrementStat(Stats.USED.getOrCreateStat(this));
        if (!user.getAbilities().creativeMode) {
            itemStack.decrement(1);
        }

        return TypedActionResult.success(itemStack, world.isClient());
    }
}
