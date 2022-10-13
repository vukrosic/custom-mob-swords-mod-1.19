package net.vukrosic.custommobswordsmod.entity.client.frogking;

import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.vukrosic.custommobswordsmod.CustomMobSwordsMod;
import net.vukrosic.custommobswordsmod.entity.custom.frogking.FrogKingEntity;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class FrogKingEntityRenderer extends GeoEntityRenderer<FrogKingEntity> {
    public FrogKingEntityRenderer(EntityRendererFactory.Context ctx) {
        super(ctx, new FrogKingEntityModel());
        this.shadowRadius = 2;
    }

    @Override
    public Identifier getTextureResource(FrogKingEntity instance) {
        return new Identifier(CustomMobSwordsMod.MOD_ID, "textures/entity/frogking.png");
    }

    @Override
    public RenderLayer getRenderType(FrogKingEntity animatable, float partialTicks,
                                     MatrixStack stack, VertexConsumerProvider renderTypeBuffer,
                                     VertexConsumer vertexBuilder, int packedLightIn, Identifier textureLocation) {
        float scale = 2F;
        stack.scale(scale, scale, scale);
        return super.getRenderType(animatable, partialTicks, stack, renderTypeBuffer, vertexBuilder, packedLightIn, textureLocation);
    }
}
