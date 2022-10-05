package net.vukrosic.custommobswordsmod.entity.client.fireenderman;

import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.mob.HoglinEntity;
import net.minecraft.util.Identifier;
import net.vukrosic.custommobswordsmod.CustomMobSwordsMod;
import net.vukrosic.custommobswordsmod.entity.client.EnderZoglinEntityModelGL;
import net.vukrosic.custommobswordsmod.entity.custom.EnderZoglinEntityGL;
import net.vukrosic.custommobswordsmod.entity.custom.fireenderman.FireEndermanEntityGL;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class FireEndermanEntityRendererGL extends GeoEntityRenderer<FireEndermanEntityGL> {

    public FireEndermanEntityRendererGL(EntityRendererFactory.Context ctx) {
        super(ctx, new FireEndermanEntityModelGL());
        this.shadowRadius = .8f;
    }

    @Override
    public Identifier getTextureResource(FireEndermanEntityGL instance) {
        return new Identifier(CustomMobSwordsMod.MOD_ID, "textures/entity/fire_enderman.png");

    }

    @Override
    public RenderLayer getRenderType(FireEndermanEntityGL animatable, float partialTicks,
                                     MatrixStack stack, VertexConsumerProvider renderTypeBuffer,
                                     VertexConsumer vertexBuilder, int packedLightIn, Identifier textureLocation) {

        return super.getRenderType(animatable, partialTicks, stack, renderTypeBuffer, vertexBuilder, packedLightIn, textureLocation);
    }


}
