package net.vukrosic.custommobswordsmod.entity.client;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EndCrystalEntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.model.EntityModelLayers;
import net.minecraft.client.render.entity.model.EntityModelPartNames;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.decoration.EndCrystalEntity;
import net.minecraft.util.Identifier;

@Environment(value= EnvType.CLIENT)
public class InfinityBlockEntityRenderer extends EndCrystalEntityRenderer {



    private static final Identifier TEXTURE = new Identifier("textures/block/crying_obsidian.png");
    private static final RenderLayer END_CRYSTAL = RenderLayer.getEntityCutoutNoCull(TEXTURE);
    private static final float SINE_45_DEGREES = (float)Math.sin(0.7853981633974483);
    private static final String GLASS = "glass";
    private static final String BASE = "base";
    private final ModelPart core;
    private final ModelPart frame;
    private final ModelPart bottom;


    public InfinityBlockEntityRenderer(EntityRendererFactory.Context ctx) {
        super(ctx);
        this.shadowRadius = 0.5f;
        ModelPart modelPart = ctx.getPart(EntityModelLayers.END_CRYSTAL);
        this.frame = modelPart.getChild(GLASS);
        this.core = modelPart.getChild(EntityModelPartNames.CUBE);
        this.bottom = modelPart.getChild(BASE);
    }


    @Override
    public void render(EndCrystalEntity endCrystalEntity, float f, float g, MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i) {
        /*
        matrixStack.push();
        float h = EndCrystalEntityRenderer.getYOffset(endCrystalEntity, g);
        float j = ((float)endCrystalEntity.endCrystalAge + g) * 3.0f;
        VertexConsumer vertexConsumer = vertexConsumerProvider.getBuffer(END_CRYSTAL);
        matrixStack.push();
        matrixStack.scale(2.0f, 2.0f, 2.0f);
        matrixStack.translate(0.0, -0.5, 0.0);
        int k = OverlayTexture.DEFAULT_UV;
        if (endCrystalEntity.shouldShowBottom()) {
            //this.bottom.render(matrixStack, vertexConsumer, i, k);
        }
        matrixStack.multiply(Vec3f.POSITIVE_Y.getDegreesQuaternion(j));
        matrixStack.translate(0.0, 1.5f + h / 2.0f, 0.0);
        matrixStack.multiply(new Quaternion(new Vec3f(SINE_45_DEGREES, 0.0f, SINE_45_DEGREES), 60.0f, true));
        this.frame.render(matrixStack, vertexConsumer, i, k);

        /*
        float l = 0.875f;
        matrixStack.scale(0.875f, 0.875f, 0.875f);
        matrixStack.multiply(new Quaternion(new Vec3f(SINE_45_DEGREES, 0.0f, SINE_45_DEGREES), 60.0f, true));
        matrixStack.multiply(Vec3f.POSITIVE_Y.getDegreesQuaternion(j));
        //this.frame.render(matrixStack, vertexConsumer, i, k);
        matrixStack.scale(0.875f, 0.875f, 0.875f);
        matrixStack.multiply(new Quaternion(new Vec3f(SINE_45_DEGREES, 0.0f, SINE_45_DEGREES), 60.0f, true));
        matrixStack.multiply(Vec3f.POSITIVE_Y.getDegreesQuaternion(j));
        //this.core.render(matrixStack, vertexConsumer, i, k);*/
/*
        matrixStack.pop();
        matrixStack.pop();
        BlockPos blockPos = endCrystalEntity.getBeamTarget();
        if (blockPos != null) {
            float m = (float)blockPos.getX() + 0.5f;
            float n = (float)blockPos.getY() + 0.5f;
            float o = (float)blockPos.getZ() + 0.5f;
            float p = (float)((double)m - endCrystalEntity.getX());
            float q = (float)((double)n - endCrystalEntity.getY());
            float r = (float)((double)o - endCrystalEntity.getZ());
            matrixStack.translate(p, q, r);
            EnderDragonEntityRenderer.renderCrystalBeam(-p, -q + h, -r, g, endCrystalEntity.endCrystalAge, matrixStack, vertexConsumerProvider, i);
        }

*/


    }





    @Override
    public Identifier getTexture(EndCrystalEntity endCrystalEntity) {
        System.out.println("IT GOT INTO RENDERER");
        return TEXTURE;
    }

}
