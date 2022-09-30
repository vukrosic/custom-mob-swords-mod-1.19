package net.vukrosic.custommobswordsmod.entity.client;

import net.minecraft.util.Identifier;
import net.vukrosic.custommobswordsmod.CustomMobSwordsMod;
import net.vukrosic.custommobswordsmod.entity.custom.ChunkenEntity;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class ChunkenEntityModel extends AnimatedGeoModel<ChunkenEntity> {
    @Override
    public Identifier getModelResource(ChunkenEntity object) {
        return new Identifier(CustomMobSwordsMod.MOD_ID, "geo/chunken_gl.geo.json");
    }

    @Override
    public Identifier getTextureResource(ChunkenEntity object) {
        return new Identifier(CustomMobSwordsMod.MOD_ID, "textures/entity/chunken.png");
    }

    @Override
    public Identifier getAnimationResource(ChunkenEntity animatable) {
        return new Identifier(CustomMobSwordsMod.MOD_ID, "animations/chunken.animation.json");
    }
}
