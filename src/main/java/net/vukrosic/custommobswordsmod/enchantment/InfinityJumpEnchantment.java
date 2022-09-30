package net.vukrosic.custommobswordsmod.enchantment;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.SpawnReason;

public class InfinityJumpEnchantment extends Enchantment {
    public InfinityJumpEnchantment(Rarity weight, EnchantmentTarget type, EquipmentSlot... slotTypes) {
        super(weight, type, slotTypes);
        //EntityType.LIGHTNING_BOLT.spawn(serverWrodl, null, null, null, position, SpawnReason.TRIGGERED, true, true);
    }




}
