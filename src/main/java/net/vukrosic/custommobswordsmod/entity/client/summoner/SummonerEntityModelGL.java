package net.vukrosic.custommobswordsmod.entity.client.summoner;

import net.minecraft.util.Identifier;
import net.vukrosic.custommobswordsmod.CustomMobSwordsMod;
import net.vukrosic.custommobswordsmod.entity.custom.corruptedallay.CorruptedAllayVexEntityGL;
import net.vukrosic.custommobswordsmod.entity.custom.corruptedallay.PetCorruptedAllayManager;
import net.vukrosic.custommobswordsmod.entity.custom.summoner.SummonerEntityGL;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class SummonerEntityModelGL extends AnimatedGeoModel<SummonerEntityGL> {
    @Override
    public Identifier getModelResource(SummonerEntityGL object) {
        return new Identifier(CustomMobSwordsMod.MOD_ID, "geo/summoner.geo.json");
    }

    @Override
    public Identifier getTextureResource(SummonerEntityGL object) {
        return new Identifier(CustomMobSwordsMod.MOD_ID, "textures/entity/summoner.png");
    }

    @Override
    public Identifier getAnimationResource(SummonerEntityGL animatable) {
        return new Identifier(CustomMobSwordsMod.MOD_ID, "animations/summoner.animation.json");
    }
}
