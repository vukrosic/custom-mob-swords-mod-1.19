package net.vukrosic.custommobswordsmod.entity.client.chunken;

import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.vukrosic.custommobswordsmod.CustomMobSwordsMod;
import net.vukrosic.custommobswordsmod.entity.custom.ChunkenEntity;
import net.vukrosic.custommobswordsmod.entity.custom.chunken.ChunkenRocketEntity;
import software.bernie.geckolib3.renderers.geo.GeoProjectilesRenderer;


public class ChunkenRocketEntityRenderer extends GeoProjectilesRenderer<ChunkenRocketEntity> {
    public ChunkenRocketEntityRenderer(EntityRendererFactory.Context renderManagerIn) {
        super(renderManagerIn, new ChunkenRocketEntityModel());
    }

    public Identifier getTextureResource(ChunkenEntity instance) {
        return new Identifier(CustomMobSwordsMod.MOD_ID, "textures/entity/rocket.png");
    }
/*
    @Override
    public RenderLayer getRenderType(ChunkenRocketEntity animatable, float partialTicks, MatrixStack stack,
                                     VertexConsumerProvider renderTypeBuffer, VertexConsumer vertexBuilder,
                                     int packedLightIn, Identifier textureLocation) {
        stack.scale(2, 2, 2);
        return RenderLayer.getEntityTranslucent(getTextureResource(animatable));
        //return super.getRenderType(animatable, partialTicks, stack, renderTypeBuffer, vertexBuilder, packedLightIn, textureLocation);
    }*/
}

/*
public class RocketRender extends GeoProjectilesRenderer<RocketProjectile> {

    public RocketRender(EntityRendererFactory.Context renderManagerIn) {
        super(renderManagerIn, new RocketModel());
    }

    protected int getBlockLight(RocketProjectile entityIn, BlockPos partialTicks) {
        return 15;
    }

    @Override
    public RenderLayer getRenderType(RocketProjectile animatable, float partialTicks, MatrixStack stack,
                                     VertexConsumerProvider renderTypeBuffer, VertexConsumer vertexBuilder, int packedLightIn,
                                     Identifier textureLocation) {
        return RenderLayer.getEntityTranslucent(getTextureResource(animatable));
    }
}
*/