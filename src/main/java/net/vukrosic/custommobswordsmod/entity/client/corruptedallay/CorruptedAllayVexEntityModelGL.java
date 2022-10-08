package net.vukrosic.custommobswordsmod.entity.client.corruptedallay;

import net.minecraft.util.Identifier;
import net.vukrosic.custommobswordsmod.CustomMobSwordsMod;
import net.vukrosic.custommobswordsmod.entity.custom.corruptedallay.CorruptedAllayVexEntityGL;
import net.vukrosic.custommobswordsmod.entity.custom.corruptedallay.PetCorruptedAllayManager;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class CorruptedAllayVexEntityModelGL extends AnimatedGeoModel<CorruptedAllayVexEntityGL> {
    @Override
    public Identifier getModelResource(CorruptedAllayVexEntityGL object) {
        return new Identifier(CustomMobSwordsMod.MOD_ID, "geo/corrupted_allay.geo.json");
    }

    @Override
    public Identifier getTextureResource(CorruptedAllayVexEntityGL object) {
        if(PetCorruptedAllayManager.isBeingLookedAt)
            return new Identifier(CustomMobSwordsMod.MOD_ID, "textures/entity/corruptedallay-allay.png");
        else
            return new Identifier(CustomMobSwordsMod.MOD_ID, "textures/entity/corruptedallay-vex.png");
    }

    @Override
    public Identifier getAnimationResource(CorruptedAllayVexEntityGL animatable) {
        return new Identifier(CustomMobSwordsMod.MOD_ID, "animations/corrupted_allay.animation.json");
    }
}
