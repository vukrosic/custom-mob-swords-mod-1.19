package net.vukrosic.custommobswordsmod.entity.client;

import net.minecraft.client.model.ModelPart;
import net.minecraft.client.render.entity.model.CreeperEntityModel;
import net.minecraft.entity.Entity;

public class InfinityCreeperEntityModel extends CreeperEntityModel {
    public InfinityCreeperEntityModel(ModelPart root) {

        super(root);
    }


    @Override
    public void setAngles(Entity entity, float limbAngle, float limbDistance, float animationProgress, float headYaw, float headPitch) {
        super.setAngles(entity, limbAngle, limbDistance, animationProgress, headYaw, headPitch);
    }


}
