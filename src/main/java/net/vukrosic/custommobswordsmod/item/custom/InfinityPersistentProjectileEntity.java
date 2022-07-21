package net.vukrosic.custommobswordsmod.item.custom;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.world.World;

public class InfinityPersistentProjectileEntity extends PersistentProjectileEntity {
    protected InfinityPersistentProjectileEntity(EntityType<? extends PersistentProjectileEntity> entityType, World world) {
        super(entityType, world);
    }

    @Override
    protected ItemStack asItemStack() {
        return null;
    }

    @Override
    protected void onEntityHit(EntityHitResult entityHitResult) {
        System.out.println("NAME = " + entityHitResult.getEntity().getEntityName());
        super.onEntityHit(entityHitResult);
    }
}
