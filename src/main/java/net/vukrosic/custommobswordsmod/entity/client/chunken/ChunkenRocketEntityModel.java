package net.vukrosic.custommobswordsmod.entity.client.chunken;

import net.minecraft.util.Identifier;

import net.vukrosic.custommobswordsmod.CustomMobSwordsMod;
import net.vukrosic.custommobswordsmod.entity.custom.chunken.ChunkenRocketEntity;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class ChunkenRocketEntityModel extends AnimatedGeoModel<ChunkenRocketEntity>{
    @Override
    public Identifier getModelResource(ChunkenRocketEntity object) {
        return new Identifier(CustomMobSwordsMod.MOD_ID, "geo/rocket.geo.json");
    }

    @Override
    public Identifier getTextureResource(ChunkenRocketEntity object) {
        return new Identifier(CustomMobSwordsMod.MOD_ID, "textures/entity/rocket.png");
    }

    @Override
    public Identifier getAnimationResource(ChunkenRocketEntity animatable) {
        return new Identifier(CustomMobSwordsMod.MOD_ID, "animations/rocket.animation.json");
    }
}