package net.vukrosic.custommobswordsmod.entity.client.chunken;

import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.vukrosic.custommobswordsmod.CustomMobSwordsMod;
import net.vukrosic.custommobswordsmod.entity.custom.chunken.ChunkenEntityGL;
import net.vukrosic.custommobswordsmod.entity.custom.chunken.ChunkenLaserEntityGL;
import net.vukrosic.custommobswordsmod.entity.custom.chunken.ChunkenPhaseManager;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class ChunkenLaserEntityRendererGL extends GeoEntityRenderer<ChunkenLaserEntityGL> {


    public ChunkenLaserEntityRendererGL(EntityRendererFactory.Context ctx) {
        super(ctx, new ChunkenLaserEntityModelGL());
        this.shadowRadius = .8f;

        ChunkenPhaseManager.createChunkenPhases();
    }

    @Override
    public Identifier getTextureResource(ChunkenLaserEntityGL instance) {
        return new Identifier(CustomMobSwordsMod.MOD_ID, "textures/entity/chicken_robot_phase_1.png");
    }

    @Override
    public RenderLayer getRenderType(ChunkenLaserEntityGL animatable, float partialTicks,
                                     MatrixStack stack, VertexConsumerProvider renderTypeBuffer,
                                     VertexConsumer vertexBuilder, int packedLightIn, Identifier textureLocation) {

        return super.getRenderType(animatable, partialTicks, stack, renderTypeBuffer, vertexBuilder, packedLightIn, textureLocation);
    }


}
