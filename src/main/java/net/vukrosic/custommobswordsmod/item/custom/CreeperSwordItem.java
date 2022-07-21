package net.vukrosic.custommobswordsmod.item.custom;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.util.math.Box;
import net.minecraft.world.World;
import net.minecraft.world.explosion.Explosion;

import java.util.ArrayList;
import java.util.List;

public class CreeperSwordItem extends SwordItem {
    public CreeperSwordItem(ToolMaterial toolMaterial, int attackDamage, float attackSpeed, Settings settings) {
        super(toolMaterial, attackDamage, attackSpeed, settings);
    }

    @Override
    public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        if(!target.getWorld().isClient()){
            int radius = 8;
            World world = attacker.world;
            List<LivingEntity> list = new ArrayList<LivingEntity>();
            list = world.getEntitiesByClass(LivingEntity.class, new Box(attacker.getBlockPos().add(-radius, -radius, -radius), attacker.getBlockPos().add(radius, radius, radius)), (livingEntity) -> {

                return true;

            });

            //list = world.getEntities(this, new AxisAlignedBB(this.posX - 50, this.posY - 50, this.posZ - 50, this.posX + 50, this.posY + 50, this.posZ + 50));
            for(LivingEntity entity : list){
                if(entity instanceof LivingEntity){
                    System.out.println(entity.getName().getString());
                    System.out.println("Exploded: " + entity.getX() + " " + entity.getY() + " " + entity.getZ());
                    if(entity != target)
                        target.world.createExplosion(attacker, entity.getX(), entity.getY(), entity.getZ(), 5, Explosion.DestructionType.BREAK);
                }
            }


        }


        return super.postHit(stack, target, attacker);
    }
}
