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
import net.vukrosic.custommobswordsmod.entity.custom.chunken.ChunkenPhaseManager;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class ChunkenEntityRendererGL extends GeoEntityRenderer<ChunkenEntityGL> {

    public ChunkenEntityRendererGL(EntityRendererFactory.Context ctx) {
        super(ctx, new ChunkenEntityModelGL());
        this.shadowRadius = .3f;

        ChunkenPhaseManager.createChunkenPhases();
    }

    @Override
    public Identifier getTextureResource(ChunkenEntityGL instance) {
        return ChunkenPhaseManager.getChunkenTexture();
        // return new Identifier(CustomMobSwordsMod.MOD_ID, "textures/entity/chicken_robot_phase_1.png");
    }

    @Override
    public RenderLayer getRenderType(ChunkenEntityGL animatable, float partialTicks,
                                     MatrixStack stack, VertexConsumerProvider renderTypeBuffer,
                                     VertexConsumer vertexBuilder, int packedLightIn, Identifier textureLocation) {

        stack.scale(0.5f, 0.5f, 0.5f);
        return super.getRenderType(animatable, partialTicks, stack, renderTypeBuffer, vertexBuilder, packedLightIn, textureLocation);
    }


}
