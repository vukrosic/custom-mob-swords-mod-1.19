package net.vukrosic.custommobswordsmod.entity.client.frogking;

import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.ArrowEntityRenderer;
import net.minecraft.client.render.entity.EnderDragonEntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.projectile.ArrowEntity;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.vukrosic.custommobswordsmod.entity.custom.frogking.FrogKingTongueProjectileEntity;

public class FrogKingTongueProjectileEntityRenderer extends ArrowEntityRenderer {
    public FrogKingTongueProjectileEntityRenderer(EntityRendererFactory.Context context) {
        super(context);
    }


    @Override
    public void render(ArrowEntity persistentProjectileEntity, float f, float g, MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i) {
        FrogKingTongueProjectileEntity frogKingTongueProjectileEntity = (FrogKingTongueProjectileEntity) persistentProjectileEntity;
        BlockPos blockPos = frogKingTongueProjectileEntity.getBeamTarget();
        if (blockPos != null) {
            float m = (float)blockPos.getX() + 0.5F;
            float n = (float)blockPos.getY() + 0.5F;
            float o = (float)blockPos.getZ() + 0.5F;
            float p = (float)((double)m - frogKingTongueProjectileEntity.getX());
            float q = (float)((double)n - frogKingTongueProjectileEntity.getY());
            float r = (float)((double)o - frogKingTongueProjectileEntity.getZ());
            matrixStack.translate((double)p, (double)q, (double)r);
            EnderDragonEntityRenderer.renderCrystalBeam(-p, -q, -r, g, frogKingTongueProjectileEntity.endCrystalAge, matrixStack, vertexConsumerProvider, i);
        }

        super.render(persistentProjectileEntity, f, g, matrixStack, vertexConsumerProvider, i);
    }

    @Override
    public Identifier getTexture(ArrowEntity arrowEntity) {
        return super.getTexture(arrowEntity);
    }
}
