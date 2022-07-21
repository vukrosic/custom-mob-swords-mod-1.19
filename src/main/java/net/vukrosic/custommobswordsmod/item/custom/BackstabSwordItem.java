package net.vukrosic.custommobswordsmod.item.custom;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.stat.Stats;
import net.minecraft.util.math.Vec3d;

public class BackstabSwordItem extends SwordItem {
    public BackstabSwordItem(ToolMaterial toolMaterial, int attackDamage, float attackSpeed, Settings settings) {
        super(toolMaterial, attackDamage, attackSpeed, settings);
    }

    @Override
    public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        if (attacker instanceof PlayerEntity) {
            PlayerEntity player = (PlayerEntity) attacker;
            Vec3d vec3d = player.getRotationVec(1.0F);
            Vec3d vec3d2 = target.getPos().subtract(player.getPos()).normalize();
            double d = vec3d.dotProduct(vec3d2);
            if (d > 1.0D - 0.025D / vec3d2.length()) {
                target.damage(DamageSource.player(player), (float) (this.getAttackDamage() + player.getAttributeInstance(EntityAttributes.GENERIC_ATTACK_DAMAGE).getValue()));
                player.incrementStat(Stats.USED.getOrCreateStat(this));

                return true;
            }
        }
        return false;
    }
}
