package net.vukrosic.custommobswordsmod.entity.client;

import net.minecraft.client.render.entity.AbstractHorseEntityRenderer;
import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.HorseEntityRenderer;
import net.minecraft.client.render.entity.model.HorseEntityModel;
import net.minecraft.entity.Entity;
import net.minecraft.entity.passive.HorseEntity;
import net.minecraft.util.Identifier;


public class HorseFrogEntityRenderer extends AbstractHorseEntityRenderer<HorseEntity, HorseEntityModel<HorseEntity>> {

    public HorseFrogEntityRenderer(EntityRendererFactory.Context ctx, HorseEntityModel model, float scale) {
        super(ctx, model, scale);
    }


    @Override
    public Identifier getTexture(HorseEntity entity) {
        return new Identifier("textures/entity/horse/horse_white.png");
    }
}
