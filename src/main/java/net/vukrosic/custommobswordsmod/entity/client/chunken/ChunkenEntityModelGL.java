package net.vukrosic.custommobswordsmod.entity.client.chunken;

import net.minecraft.util.Identifier;
import net.vukrosic.custommobswordsmod.CustomMobSwordsMod;
import net.vukrosic.custommobswordsmod.entity.custom.ChunkenEntity;
import net.vukrosic.custommobswordsmod.entity.custom.chunken.ChunkenEntityGL;
import net.vukrosic.custommobswordsmod.entity.custom.chunken.ChunkenPhaseManager;
import software.bernie.geckolib3.model.AnimatedGeoModel;

import java.util.ArrayList;

public class ChunkenEntityModelGL extends AnimatedGeoModel<ChunkenEntityGL> {

    public int phase = 0;
    public Identifier texture = new Identifier(CustomMobSwordsMod.MOD_ID, "textures/entity/chicken_robot_phase_1.png");
    // make an array of textures
    // ArrayList<Identifier> textures = new ArrayList<Identifier>();

    // add a texture to textures
    public ChunkenEntityModelGL() {
    }

    @Override
    public Identifier getModelResource(ChunkenEntityGL object) {
        return new Identifier(CustomMobSwordsMod.MOD_ID, "geo/chicken_robot.geo.json");
    }

    @Override
    public Identifier getTextureResource(ChunkenEntityGL object) {
        return ChunkenPhaseManager.getChunkenTexture();
        // return new Identifier(CustomMobSwordsMod.MOD_ID, "textures/entity/chicken_robot_phase_1.png");
    }

    @Override
    public Identifier getAnimationResource(ChunkenEntityGL animatable) {
        return new Identifier(CustomMobSwordsMod.MOD_ID, "animations/chicken_robot_no_scale.animation.json");
    }
}
