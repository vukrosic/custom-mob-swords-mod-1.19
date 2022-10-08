package net.vukrosic.custommobswordsmod.entity.client.butcherboy;

import net.minecraft.util.Identifier;
import net.vukrosic.custommobswordsmod.CustomMobSwordsMod;
import net.vukrosic.custommobswordsmod.entity.custom.butcherboy.ButcherBoyEntityGL;
import net.vukrosic.custommobswordsmod.entity.custom.corruptedallay.CorruptedAllayVexEntityGL;
import net.vukrosic.custommobswordsmod.entity.custom.corruptedallay.PetCorruptedAllayManager;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class ButcherBoyEntityModelGL extends AnimatedGeoModel<ButcherBoyEntityGL> {
    @Override
    public Identifier getModelResource(ButcherBoyEntityGL object) {
        return new Identifier(CustomMobSwordsMod.MOD_ID, "geo/cow_butcher.geo.json");
    }

    @Override
    public Identifier getTextureResource(ButcherBoyEntityGL object) {
        return new Identifier(CustomMobSwordsMod.MOD_ID, "textures/entity/cow_butcher.png");
    }

    @Override
    public Identifier getAnimationResource(ButcherBoyEntityGL animatable) {
        return new Identifier(CustomMobSwordsMod.MOD_ID, "animations/cow_butcher.animation.json");
    }
}
