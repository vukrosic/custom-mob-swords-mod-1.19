package net.vukrosic.custommobswordsmod.entity.client;

import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.mob.HoglinEntity;
import net.minecraft.util.Identifier;
import net.vukrosic.custommobswordsmod.CustomMobSwordsMod;
import net.vukrosic.custommobswordsmod.entity.custom.EnderZoglinEntityGL;
import net.vukrosic.custommobswordsmod.entity.custom.EnderZoglinEntityGL;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class EnderZoglinEntityRendererGL extends GeoEntityRenderer<EnderZoglinEntityGL> {

    public EnderZoglinEntityRendererGL(EntityRendererFactory.Context ctx) {
        super(ctx, new EnderZoglinEntityModelGL());
        this.shadowRadius = .8f;
    }

    @Override
    public Identifier getTextureResource(EnderZoglinEntityGL instance) {
        return new Identifier(CustomMobSwordsMod.MOD_ID, "textures/entity/ender_zoglin.png");

    }

    @Override
    public RenderLayer getRenderType(EnderZoglinEntityGL animatable, float partialTicks,
                                     MatrixStack stack, VertexConsumerProvider renderTypeBuffer,
                                     VertexConsumer vertexBuilder, int packedLightIn, Identifier textureLocation) {

        return super.getRenderType(animatable, partialTicks, stack, renderTypeBuffer, vertexBuilder, packedLightIn, textureLocation);
    }


    protected boolean isShaking(HoglinEntity hoglinEntity) {
        return true;
    }

}
