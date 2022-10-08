package net.vukrosic.custommobswordsmod.entity.client.frogking;

import net.minecraft.util.Identifier;
import net.vukrosic.custommobswordsmod.CustomMobSwordsMod;
import net.vukrosic.custommobswordsmod.entity.custom.frogking.FrogKingEntity;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class FrogKingEntityModel extends AnimatedGeoModel<FrogKingEntity> {
    @Override
    public Identifier getModelResource(FrogKingEntity object) {
        return new Identifier(CustomMobSwordsMod.MOD_ID, "geo/frogking.geo.json");
    }

    @Override
    public Identifier getTextureResource(FrogKingEntity object) {
        return new Identifier(CustomMobSwordsMod.MOD_ID, "textures/entity/frogking.png");
    }

    @Override
    public Identifier getAnimationResource(FrogKingEntity animatable) {
        return new Identifier(CustomMobSwordsMod.MOD_ID, "animations/frog_king.animation.json");
    }

}
