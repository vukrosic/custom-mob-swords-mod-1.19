package net.vukrosic.custommobswordsmod.entity.client;

import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.WardenEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.mob.WardenEntity;
import net.minecraft.util.Identifier;
import net.vukrosic.custommobswordsmod.command.SetHunterCommand;

public class BlinkingWardenEntityRenderer extends WardenEntityRenderer {
    public BlinkingWardenEntityRenderer(EntityRendererFactory.Context context) {
        super(context);
    }

    @Override
    public Identifier getTexture(WardenEntity wardenEntity) {
        return super.getTexture(wardenEntity);
    }
}
