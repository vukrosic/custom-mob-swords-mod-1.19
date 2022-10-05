package net.vukrosic.custommobswordsmod.entity.client;

import net.minecraft.util.Identifier;
import net.vukrosic.custommobswordsmod.CustomMobSwordsMod;
import net.vukrosic.custommobswordsmod.entity.custom.EnderZoglinEntityGL;
import net.vukrosic.custommobswordsmod.entity.custom.EnderZoglinEntityGL;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class EnderZoglinEntityModelGL extends AnimatedGeoModel<EnderZoglinEntityGL> {
    @Override
    public Identifier getModelResource(EnderZoglinEntityGL object) {
        return new Identifier(CustomMobSwordsMod.MOD_ID, "geo/ender_zoglin_faces.geo.json");
    }

    @Override
    public Identifier getTextureResource(EnderZoglinEntityGL object) {
        return new Identifier(CustomMobSwordsMod.MOD_ID, "textures/entity/ender_zoglin.png");
    }

    @Override
    public Identifier getAnimationResource(EnderZoglinEntityGL animatable) {
        return new Identifier(CustomMobSwordsMod.MOD_ID, "animations/ender_zoglin.animation.json");
    }
}
