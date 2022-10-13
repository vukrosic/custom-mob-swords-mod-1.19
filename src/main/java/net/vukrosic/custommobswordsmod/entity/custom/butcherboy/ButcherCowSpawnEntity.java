package net.vukrosic.custommobswordsmod.entity.custom.butcherboy;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.passive.CowEntity;
import net.minecraft.entity.passive.FrogEntity;
import net.minecraft.world.World;
import net.vukrosic.custommobswordsmod.entity.ModEntities;

public class ButcherCowSpawnEntity extends CowEntity {
    public ButcherCowSpawnEntity(EntityType<? extends CowEntity> entityType, World world) {
        super(entityType, world);
    }

    @Override
    public void onDeath(DamageSource damageSource) {
        ButcherBoyEntityGL butcherBoyEntityGL = new ButcherBoyEntityGL(ModEntities.BUTCHER_BOY, this.world);
        butcherBoyEntityGL.refreshPositionAndAngles(this.getX(), this.getY(), this.getZ(), this.getYaw(), this.getPitch());
        this.world.spawnEntity(butcherBoyEntityGL);
        super.onDeath(damageSource);
    }

    public static DefaultAttributeContainer.Builder setAttributes() {
        return CowEntity.createCowAttributes();
    }
}
