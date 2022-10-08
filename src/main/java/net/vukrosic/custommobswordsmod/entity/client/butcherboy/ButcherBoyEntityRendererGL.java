package net.vukrosic.custommobswordsmod.entity.client.butcherboy;

import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.vukrosic.custommobswordsmod.CustomMobSwordsMod;
import net.vukrosic.custommobswordsmod.entity.client.chunken.ChunkenEntityModelGL;
import net.vukrosic.custommobswordsmod.entity.custom.butcherboy.ButcherBoyEntityGL;
import net.vukrosic.custommobswordsmod.entity.custom.chunken.ChunkenEntityGL;
import net.vukrosic.custommobswordsmod.entity.custom.chunken.ChunkenPhaseManager;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class ButcherBoyEntityRendererGL extends GeoEntityRenderer<ButcherBoyEntityGL> {

    public ButcherBoyEntityRendererGL(EntityRendererFactory.Context ctx) {
        super(ctx, new ButcherBoyEntityModelGL());
        this.shadowRadius = .8f;

        ChunkenPhaseManager.createChunkenPhases();
    }

    @Override
    public Identifier getTextureResource(ButcherBoyEntityGL instance) {
        return new Identifier(CustomMobSwordsMod.MOD_ID, "textures/entity/cow_butcher.png");
    }
}
