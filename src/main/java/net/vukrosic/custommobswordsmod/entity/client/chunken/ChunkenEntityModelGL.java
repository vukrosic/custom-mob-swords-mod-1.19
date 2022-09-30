package net.vukrosic.custommobswordsmod.entity.client.chunken;

import net.minecraft.util.Identifier;
import net.vukrosic.custommobswordsmod.CustomMobSwordsMod;
import net.vukrosic.custommobswordsmod.entity.custom.ChunkenEntity;
import net.vukrosic.custommobswordsmod.entity.custom.chunken.ChunkenEntityGL;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class ChunkenEntityModelGL extends AnimatedGeoModel<ChunkenEntityGL> {
    @Override
    public Identifier getModelResource(ChunkenEntityGL object) {
        return new Identifier(CustomMobSwordsMod.MOD_ID, "geo/chunkenn.geo.json");
    }

    @Override
    public Identifier getTextureResource(ChunkenEntityGL object) {
        return new Identifier(CustomMobSwordsMod.MOD_ID, "textures/entity/the_chunken.png");
    }

    @Override
    public Identifier getAnimationResource(ChunkenEntityGL animatable) {
        return new Identifier(CustomMobSwordsMod.MOD_ID, "animations/chunkenn.animation.json");
    }
}
