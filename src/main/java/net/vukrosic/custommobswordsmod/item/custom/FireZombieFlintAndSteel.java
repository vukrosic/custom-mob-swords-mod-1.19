package net.vukrosic.custommobswordsmod.item.custom;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.FlintAndSteelItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.vukrosic.custommobswordsmod.entity.ModEntities;
import net.vukrosic.custommobswordsmod.entity.custom.FireZombieEntity;

public class FireZombieFlintAndSteel extends FlintAndSteelItem {
    public FireZombieFlintAndSteel(Settings settings) {
        super(settings);
    }



    @Override
    public ActionResult useOnEntity(ItemStack stack, PlayerEntity user, LivingEntity entity, Hand hand) {
        BlockPos pos = new BlockPos(entity.getX(), entity.getY(), entity.getZ());

        FireZombieEntity fireZombie = new FireZombieEntity(ModEntities.FIRE_ZOMBIE, entity.world);
        fireZombie.refreshPositionAndAngles(pos, 0, 0);
        fireZombie.setPosition(pos.getX(), pos.getY(), pos.getZ());
        user.world.spawnEntity(fireZombie);

        entity.discard();
        return super.useOnEntity(stack, user, entity, hand);
    }
}
