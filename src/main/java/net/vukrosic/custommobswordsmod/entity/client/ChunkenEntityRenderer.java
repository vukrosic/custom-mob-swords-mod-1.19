package net.vukrosic.custommobswordsmod.entity.client;

import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.vukrosic.custommobswordsmod.CustomMobSwordsMod;
import net.vukrosic.custommobswordsmod.entity.custom.ChunkenEntity;
import software.bernie.geckolib3.GeckoLib;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class ChunkenEntityRenderer extends GeoEntityRenderer<ChunkenEntity> {

    public ChunkenEntityRenderer(EntityRendererFactory.Context ctx) {
        super(ctx, new ChunkenEntityModel());
        this.shadowRadius = .8f;
    }

    @Override
    public Identifier getTextureResource(ChunkenEntity instance) {
        return new Identifier(CustomMobSwordsMod.MOD_ID, "textures/entity/chunken.png");
    }

    @Override
    public RenderLayer getRenderType(ChunkenEntity animatable, float partialTicks,
                                     MatrixStack stack, VertexConsumerProvider renderTypeBuffer,
                                     VertexConsumer vertexBuilder, int packedLightIn, Identifier textureLocation) {

        return super.getRenderType(animatable, partialTicks, stack, renderTypeBuffer, vertexBuilder, packedLightIn, textureLocation);
    }


}
