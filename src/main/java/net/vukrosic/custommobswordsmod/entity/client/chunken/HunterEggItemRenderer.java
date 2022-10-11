package net.vukrosic.custommobswordsmod.entity.client.chunken;

import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.ItemEntityRenderer;
import net.minecraft.entity.ItemEntity;
import net.minecraft.util.Identifier;
import net.vukrosic.custommobswordsmod.entity.custom.chunken.ChunkenEntityRangedGL;
import net.vukrosic.custommobswordsmod.entity.custom.chunken.HunterEggEntity;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class HunterEggItemRenderer extends ItemEntityRenderer {

    public HunterEggItemRenderer(EntityRendererFactory.Context context) {
        super(context);
    }

    @Override
    public Identifier getTexture(ItemEntity itemEntity) {
        return super.getTexture(itemEntity);
    }
}
