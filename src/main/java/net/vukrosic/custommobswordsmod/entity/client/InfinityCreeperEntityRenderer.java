package net.vukrosic.custommobswordsmod.entity.client;

import net.minecraft.client.render.entity.CreeperEntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.render.entity.feature.CreeperChargeFeatureRenderer;
import net.minecraft.client.render.entity.model.CreeperEntityModel;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.render.entity.model.EntityModelLayers;
import net.minecraft.entity.Entity;
import net.minecraft.entity.mob.CreeperEntity;
import net.minecraft.util.Identifier;
import net.vukrosic.custommobswordsmod.entity.custom.InfinityCreeperEntity;

public class InfinityCreeperEntityRenderer extends MobEntityRenderer<InfinityCreeperEntity, CreeperEntityModel<InfinityCreeperEntity>> {

    private static final Identifier TEXTURE = new Identifier("textures/entity/cow/cow.png");

    public InfinityCreeperEntityRenderer(EntityRendererFactory.Context context, CreeperEntityModel<InfinityCreeperEntity> entityModel, float f) {
        super(context, entityModel, f);
    }

    @Override
    public Identifier getTexture(InfinityCreeperEntity entity) {
        return TEXTURE;
    }




    /*

    private static final Identifier TEXTURE = new Identifier("textures/entity/cow/cow.png");


    public InfinityCreeperEntityRenderer(EntityRendererFactory.Context context) {

        super(context);
        System.out.println("ENTTTTTTTTERED InfinityCreeperEntityRenderer");
    }



    @Override
    public Identifier getTexture(CreeperEntity creeperEntity) {
        return TEXTURE;



    }*/
}
