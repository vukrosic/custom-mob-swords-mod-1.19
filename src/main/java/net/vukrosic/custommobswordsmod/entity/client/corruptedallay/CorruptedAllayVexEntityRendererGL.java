package net.vukrosic.custommobswordsmod.entity.client.corruptedallay;

import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.util.Identifier;
import net.vukrosic.custommobswordsmod.CustomMobSwordsMod;
import net.vukrosic.custommobswordsmod.entity.custom.corruptedallay.CorruptedAllayVexEntityGL;
import net.vukrosic.custommobswordsmod.entity.custom.corruptedallay.PetCorruptedAllayManager;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class CorruptedAllayVexEntityRendererGL extends GeoEntityRenderer<CorruptedAllayVexEntityGL> {
    public CorruptedAllayVexEntityRendererGL(EntityRendererFactory.Context ctx) {
        super(ctx, new CorruptedAllayVexEntityModelGL());
    }

    @Override
    public Identifier getTextureResource(CorruptedAllayVexEntityGL instance) {
        if(PetCorruptedAllayManager.isBeingLookedAt)
            return new Identifier(CustomMobSwordsMod.MOD_ID, "textures/entity/corruptedallay-allay.png");
        else
            return new Identifier(CustomMobSwordsMod.MOD_ID, "textures/entity/corruptedallay-vex.png");
    }
}
