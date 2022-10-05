package net.vukrosic.custommobswordsmod.entity.client.chunken;

import net.minecraft.util.Identifier;
import net.vukrosic.custommobswordsmod.CustomMobSwordsMod;
import net.vukrosic.custommobswordsmod.entity.custom.chunken.ChunkenEntityGL;
import net.vukrosic.custommobswordsmod.entity.custom.chunken.ChunkenEntityRangedGL;
import net.vukrosic.custommobswordsmod.entity.custom.chunken.ChunkenPhaseManager;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class ChunkenEntityRangedModelGL extends AnimatedGeoModel<ChunkenEntityRangedGL> {

    @Override
    public Identifier getModelResource(ChunkenEntityRangedGL object) {
        return new Identifier(CustomMobSwordsMod.MOD_ID, "geo/chicken_robot.geo.json");
    }

    @Override
    public Identifier getTextureResource(ChunkenEntityRangedGL object) {
        return new Identifier(CustomMobSwordsMod.MOD_ID, "textures/entity/chicken_robot_phase_5_gold.png");
    }

    @Override
    public Identifier getAnimationResource(ChunkenEntityRangedGL animatable) {
        return new Identifier(CustomMobSwordsMod.MOD_ID, "animations/chicken_robot_no_scale.animation.json");
    }
}
