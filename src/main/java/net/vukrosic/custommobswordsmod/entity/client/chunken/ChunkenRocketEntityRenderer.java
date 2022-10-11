package net.vukrosic.custommobswordsmod.entity.client.chunken;

import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import software.bernie.example.client.renderer.entity.RocketRender;
import software.bernie.example.entity.RocketProjectile;

public class ChunkenRocketEntityRenderer extends RocketRender {
    public ChunkenRocketEntityRenderer(EntityRendererFactory.Context renderManagerIn) {
        super(renderManagerIn);
    }

    @Override
    public RenderLayer getRenderType(RocketProjectile animatable, float partialTicks, MatrixStack stack, VertexConsumerProvider renderTypeBuffer, VertexConsumer vertexBuilder, int packedLightIn, Identifier textureLocation) {
        stack.scale(2, 2, 2);
        return super.getRenderType(animatable, partialTicks, stack, renderTypeBuffer, vertexBuilder, packedLightIn, textureLocation);
    }
}
