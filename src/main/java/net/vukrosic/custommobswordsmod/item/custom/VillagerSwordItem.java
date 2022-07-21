package net.vukrosic.custommobswordsmod.item.custom;

import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterial;

public class VillagerSwordItem extends SwordItem {
    public VillagerSwordItem(ToolMaterial toolMaterial, int attackDamage, float attackSpeed, Settings settings) {
        super(toolMaterial, attackDamage, attackSpeed, settings);
    }

    @Override
    public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        //stack.damage(stack.getDamage()*2, attacker, (p) -> p.sendToolBreakStatus(attacker.getActiveHand()));



        if(stack.getDamage() < 1000000)
            stack.setDamage(stack.getDamage() * 2);
        return true;
    }
}
