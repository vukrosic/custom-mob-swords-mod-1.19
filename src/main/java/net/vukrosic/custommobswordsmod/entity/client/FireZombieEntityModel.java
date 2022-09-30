package net.vukrosic.custommobswordsmod.entity.client;

import net.minecraft.client.model.ModelPart;
import net.minecraft.client.render.entity.model.EndermanEntityModel;
import net.minecraft.client.render.entity.model.ZombieEntityModel;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.mob.HostileEntity;

public class FireZombieEntityModel extends ZombieEntityModel {
    public FireZombieEntityModel(ModelPart root) {
        super(root);
    }


    @Override
    public void setAngles(HostileEntity entity, float limbAngle, float limbDistance, float animationProgress, float headYaw, float headPitch) {
        super.setAngles(entity, limbAngle, limbDistance, animationProgress, headYaw, headPitch);
    }


}
