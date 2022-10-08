package net.vukrosic.custommobswordsmod.entity.client.chunken;

import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.ProjectileEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.vukrosic.custommobswordsmod.CustomMobSwordsMod;
import net.vukrosic.custommobswordsmod.entity.custom.chunken.ChunkenLaserEntity;
import net.vukrosic.custommobswordsmod.entity.custom.chunken.ChunkenLaserProjectileEntityGL;
import org.checkerframework.checker.units.qual.C;
import software.bernie.example.entity.RocketProjectile;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import software.bernie.geckolib3.renderers.geo.GeoProjectilesRenderer;

public class ChunkenLaserProjectileEntityRendererGL extends GeoProjectilesRenderer<ChunkenLaserProjectileEntityGL> {


    public ChunkenLaserProjectileEntityRendererGL(EntityRendererFactory.Context ctx) {
        super(ctx, new ChunkenLaserProjectileEntityModelGL());
    }

    protected int getBlockLight(RocketProjectile entityIn, BlockPos partialTicks) {
        return 15;
    }

    @Override
    public Identifier getTextureResource(ChunkenLaserProjectileEntityGL instance) {
        return new Identifier(CustomMobSwordsMod.MOD_ID, "textures/entity/chicken_robot_phase_1.png");
    }

    @Override
    public RenderLayer getRenderType(ChunkenLaserProjectileEntityGL animatable, float partialTicks, MatrixStack stack, VertexConsumerProvider renderTypeBuffer, VertexConsumer vertexBuilder, int packedLightIn, Identifier textureLocation) {
        return RenderLayer.getEntityTranslucent(getTextureResource(animatable));
    }



    private Identifier getTextureLocation(ChunkenLaserProjectileEntityGL animatable) {
        return new Identifier(CustomMobSwordsMod.MOD_ID, "textures/entity/chicken_robot_phase_1.png");
    }

}
