package net.vukrosic.custommobswordsmod.entity.client.chunken;

import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.vukrosic.custommobswordsmod.CustomMobSwordsMod;
import net.vukrosic.custommobswordsmod.entity.custom.ChunkenEntity;
import net.vukrosic.custommobswordsmod.entity.custom.chunken.ChunkenEntityGL;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class ChunkenEntityRendererGL extends GeoEntityRenderer<ChunkenEntityGL> {

    public ChunkenEntityRendererGL(EntityRendererFactory.Context ctx) {
        super(ctx, new ChunkenEntityModelGL());
        this.shadowRadius = .8f;
    }

    @Override
    public Identifier getTextureResource(ChunkenEntityGL instance) {
        return new Identifier(CustomMobSwordsMod.MOD_ID, "textures/entity/the_chunken.png");

    }

    @Override
    public RenderLayer getRenderType(ChunkenEntityGL animatable, float partialTicks,
                                     MatrixStack stack, VertexConsumerProvider renderTypeBuffer,
                                     VertexConsumer vertexBuilder, int packedLightIn, Identifier textureLocation) {

        return super.getRenderType(animatable, partialTicks, stack, renderTypeBuffer, vertexBuilder, packedLightIn, textureLocation);
    }


}
