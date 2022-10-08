package net.vukrosic.custommobswordsmod.entity.client.chunken;

import net.minecraft.util.Identifier;
import net.vukrosic.custommobswordsmod.CustomMobSwordsMod;
import net.vukrosic.custommobswordsmod.entity.custom.chunken.ChunkenEntityGL;
import net.vukrosic.custommobswordsmod.entity.custom.chunken.ChunkenLaserEntityGL;
import net.vukrosic.custommobswordsmod.entity.custom.chunken.ChunkenPhaseManager;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class ChunkenLaserEntityModelGL extends AnimatedGeoModel<ChunkenLaserEntityGL> {

    public int phase = 0;
    public Identifier texture = new Identifier(CustomMobSwordsMod.MOD_ID, "textures/entity/chicken_robot_phase_1.png");
    // make an array of textures
    // ArrayList<Identifier> textures = new ArrayList<Identifier>();

    // add a texture to textures
    public ChunkenLaserEntityModelGL() {
    }

    @Override
    public Identifier getModelResource(ChunkenLaserEntityGL object) {
        return new Identifier(CustomMobSwordsMod.MOD_ID, "geo/chunken_lasergl.geo.json");
    }

    @Override
    public Identifier getTextureResource(ChunkenLaserEntityGL object) {
        return new Identifier(CustomMobSwordsMod.MOD_ID, "textures/entity/chicken_robot_phase_1.png");
    }

    @Override
    public Identifier getAnimationResource(ChunkenLaserEntityGL animatable) {
        return new Identifier(CustomMobSwordsMod.MOD_ID, "animations/chicken_robot_no_scale.animation.json");
    }
}
