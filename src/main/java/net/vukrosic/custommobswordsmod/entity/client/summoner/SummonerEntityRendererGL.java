package net.vukrosic.custommobswordsmod.entity.client.summoner;

import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.util.Identifier;
import net.vukrosic.custommobswordsmod.CustomMobSwordsMod;
import net.vukrosic.custommobswordsmod.entity.client.corruptedallay.CorruptedAllayVexEntityModelGL;
import net.vukrosic.custommobswordsmod.entity.custom.corruptedallay.CorruptedAllayVexEntityGL;
import net.vukrosic.custommobswordsmod.entity.custom.corruptedallay.PetCorruptedAllayManager;
import net.vukrosic.custommobswordsmod.entity.custom.summoner.SummonerEntityGL;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class SummonerEntityRendererGL extends GeoEntityRenderer<SummonerEntityGL> {
    public SummonerEntityRendererGL(EntityRendererFactory.Context ctx) {
        super(ctx, new SummonerEntityModelGL());
    }

    @Override
    public Identifier getTextureResource(SummonerEntityGL instance) {
            return new Identifier(CustomMobSwordsMod.MOD_ID, "textures/entity/summoner.png");
    }
}
