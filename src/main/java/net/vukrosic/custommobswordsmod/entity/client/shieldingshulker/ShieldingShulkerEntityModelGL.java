package net.vukrosic.custommobswordsmod.entity.client.shieldingshulker;

import net.minecraft.util.Identifier;
import net.vukrosic.custommobswordsmod.CustomMobSwordsMod;
import net.vukrosic.custommobswordsmod.entity.custom.chunken.ChunkenEntityGL;
import net.vukrosic.custommobswordsmod.entity.custom.chunken.ChunkenPhaseManager;
import net.vukrosic.custommobswordsmod.entity.custom.shieldingshulker.ShieldingShulkerEntityGL;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class ShieldingShulkerEntityModelGL extends AnimatedGeoModel<ShieldingShulkerEntityGL> {

    public int phase = 0;
    public Identifier texture = new Identifier(CustomMobSwordsMod.MOD_ID, "textures/entity/chicken_robot_phase_1.png");
    // make an array of textures
    // ArrayList<Identifier> textures = new ArrayList<Identifier>();

    // add a texture to textures
    public ShieldingShulkerEntityModelGL() {
    }

    @Override
    public Identifier getModelResource(ShieldingShulkerEntityGL object) {
        return new Identifier(CustomMobSwordsMod.MOD_ID, "geo/familiar_shulker.geo.json");
    }

    @Override
    public Identifier getTextureResource(ShieldingShulkerEntityGL object) {
        return new Identifier(CustomMobSwordsMod.MOD_ID, "textures/entity/familiar_shulker.png");
    }

    @Override
    public Identifier getAnimationResource(ShieldingShulkerEntityGL animatable) {
        return new Identifier(CustomMobSwordsMod.MOD_ID, "animations/familiar_shulker.animation.json");
    }
}
