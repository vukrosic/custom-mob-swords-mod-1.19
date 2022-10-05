package net.vukrosic.custommobswordsmod.entity.client.fireenderman;

import net.minecraft.util.Identifier;
import net.vukrosic.custommobswordsmod.CustomMobSwordsMod;
import net.vukrosic.custommobswordsmod.entity.custom.EnderZoglinEntityGL;
import net.vukrosic.custommobswordsmod.entity.custom.fireenderman.FireEndermanEntityGL;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class FireEndermanEntityModelGL extends AnimatedGeoModel<FireEndermanEntityGL> {
    @Override
    public Identifier getModelResource(FireEndermanEntityGL object) {
        return new Identifier(CustomMobSwordsMod.MOD_ID, "geo/fire_enderman.geo.json");
    }

    @Override
    public Identifier getTextureResource(FireEndermanEntityGL object) {
        return new Identifier(CustomMobSwordsMod.MOD_ID, "textures/entity/fire_enderman.png");
    }

    @Override
    public Identifier getAnimationResource(FireEndermanEntityGL animatable) {
        return new Identifier(CustomMobSwordsMod.MOD_ID, "animations/fire_enderman.animation.json");
    }
}
