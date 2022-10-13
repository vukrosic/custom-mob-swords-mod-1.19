package net.vukrosic.custommobswordsmod.entity.client.shieldingshulker;

import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.vukrosic.custommobswordsmod.CustomMobSwordsMod;
import net.vukrosic.custommobswordsmod.entity.client.chunken.ChunkenEntityModelGL;
import net.vukrosic.custommobswordsmod.entity.custom.chunken.ChunkenEntityGL;
import net.vukrosic.custommobswordsmod.entity.custom.chunken.ChunkenPhaseManager;
import net.vukrosic.custommobswordsmod.entity.custom.shieldingshulker.ShieldingShulkerEntityGL;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class ShieldingShulkerEntityRendererGL extends GeoEntityRenderer<ShieldingShulkerEntityGL> {

    public ShieldingShulkerEntityRendererGL(EntityRendererFactory.Context ctx) {
        super(ctx, new ShieldingShulkerEntityModelGL());
        this.shadowRadius = .8f;
    }

    @Override
    public Identifier getTextureResource(ShieldingShulkerEntityGL instance) {
        return new Identifier(CustomMobSwordsMod.MOD_ID, "textures/entity/familiar_shulker.png");
    }


}
