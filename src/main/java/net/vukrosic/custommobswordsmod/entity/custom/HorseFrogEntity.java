package net.vukrosic.custommobswordsmod.entity.custom;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.passive.FrogEntity;
import net.minecraft.entity.passive.HorseEntity;
import net.minecraft.world.World;

public class HorseFrogEntity extends HorseEntity {
    public HorseFrogEntity(EntityType<? extends HorseEntity> entityType, World world) {
        super(entityType, world);
    }

    public static DefaultAttributeContainer.Builder setAttributes(){
        return HorseEntity.createBaseHorseAttributes();
    }
}
