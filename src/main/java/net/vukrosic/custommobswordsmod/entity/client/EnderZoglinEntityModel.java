package net.vukrosic.custommobswordsmod.entity.client;

import net.minecraft.client.model.ModelPart;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.client.render.entity.model.PiglinEntityModel;
import net.minecraft.client.render.entity.model.ZombieEntityModel;
import net.minecraft.entity.mob.*;
import net.minecraft.util.Identifier;
import net.vukrosic.custommobswordsmod.CustomMobSwordsMod;

public class EnderZoglinEntityModel extends ZombieEntityModel {
    public static final EntityModelLayer LAYER_LOCATION = new EntityModelLayer(new Identifier(CustomMobSwordsMod.MOD_ID, "ender_piglin"), "main");


    public EnderZoglinEntityModel(ModelPart modelPart) {
        super(modelPart);
    }

    @Override
    public void setAngles(HostileEntity entity, float limbAngle, float limbDistance, float animationProgress, float headYaw, float headPitch) {
        super.setAngles(entity, limbAngle, limbDistance, animationProgress, headYaw, headPitch);
    }
}
