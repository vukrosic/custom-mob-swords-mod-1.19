package net.vukrosic.custommobswordsmod.entity.client;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.render.entity.*;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.client.render.entity.model.EntityModelLayers;
import net.minecraft.entity.mob.*;
import net.minecraft.util.Identifier;
import net.vukrosic.custommobswordsmod.CustomMobSwordsMod;

@Environment(value= EnvType.CLIENT)
public class EnderZoglinEntityRenderer extends ZombieEntityRenderer {



    /*
    public EnderPiglinEntityRenderer(EntityRendererFactory.Context context) {
        super(context, mainLayer, innerArmorLayer, outerArmorLayer, zombie);
    }
    */

    private static final Identifier TEXTURE = new Identifier(CustomMobSwordsMod.MOD_ID, "textures/entity/ender_zoglin.png");

    public EnderZoglinEntityRenderer(EntityRendererFactory.Context context) {
        super(context);
    }


    @Override
    public TextRenderer getTextRenderer() {
        return super.getTextRenderer();
    }

    @Override
    public Identifier getTexture(ZombieEntity zombieEntity) {
        return TEXTURE;
    }
}
