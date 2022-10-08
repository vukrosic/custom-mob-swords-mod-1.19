package net.vukrosic.custommobswordsmod.entity.client.chunken;

import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.vukrosic.custommobswordsmod.CustomMobSwordsMod;
import net.vukrosic.custommobswordsmod.entity.custom.chunken.ChunkenLaserEntityGL;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class ChunkenLaserEntityRenderer extends GeoEntityRenderer<ChunkenLaserEntityGL> {


    public ChunkenLaserEntityRenderer(EntityRendererFactory.Context ctx, AnimatedGeoModel<ChunkenLaserEntityGL> modelProvider) {
        super(ctx, modelProvider);
    }

    @Override
    public Identifier getTextureResource(ChunkenLaserEntityGL instance) {
        return new Identifier(CustomMobSwordsMod.MOD_ID, "textures/entity/chicken_robot_phase_1.png");
    }

    @Override
    public RenderLayer getRenderType(ChunkenLaserEntityGL animatable, float partialTicks, MatrixStack stack, VertexConsumerProvider renderTypeBuffer, VertexConsumer vertexBuilder, int packedLightIn, Identifier textureLocation) {
        return super.getRenderType(animatable, partialTicks, stack, renderTypeBuffer, vertexBuilder, packedLightIn, textureLocation);
    }

    /*
    @Override
    public Identifier getTexture(ChunkenLaserEntity entity) {
        return new Identifier(CustomMobSwordsMod.MOD_ID, "textures/entity/chicken_robot_phase_5_gold.png");
    }*/

}
