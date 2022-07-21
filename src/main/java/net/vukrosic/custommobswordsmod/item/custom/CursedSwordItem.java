package net.vukrosic.custommobswordsmod.item.custom;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterial;


public class CursedSwordItem extends SwordItem {
    public CursedSwordItem(ToolMaterial toolMaterial, int attackDamage, float attackSpeed, Settings settings) {
        super(toolMaterial, attackDamage, attackSpeed, settings);
    }

    public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {

        if (attacker instanceof PlayerEntity) {

            PlayerEntity player = (PlayerEntity) attacker;

            if (player.world.random.nextFloat() < 0.1F) {

                player.damage(DamageSource.GENERIC, 1.0F);

            }

        }

        return true;

    }

}

