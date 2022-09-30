package net.vukrosic.custommobswordsmod.entity.client;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.render.entity.CreeperEntityRenderer;
import net.minecraft.client.render.entity.EndermanEntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.entity.mob.CreeperEntity;
import net.minecraft.entity.mob.EndermanEntity;
import net.minecraft.util.Identifier;
import net.vukrosic.custommobswordsmod.CustomMobSwordsMod;

@Environment(value= EnvType.CLIENT)
public class FireEndermanEntityRenderer extends EndermanEntityRenderer {

    private static final Identifier TEXTURE = new Identifier(CustomMobSwordsMod.MOD_ID, "textures/entity/fire_enderman.png");

    public FireEndermanEntityRenderer(EntityRendererFactory.Context context) {
        super(context);
    }

    @Override
    protected float getAnimationCounter(EndermanEntity entity, float tickDelta) {
        return super.getAnimationCounter(entity, tickDelta);
    }

    @Override
    public TextRenderer getTextRenderer() {
        return super.getTextRenderer();
    }


    @Override
    public Identifier getTexture(EndermanEntity endermanEntity) {
        return TEXTURE;
    }

    /*

    private static final Identifier TEXTURE = new Identifier("textures/entity/cow/cow.png");


    public InfinityCreeperEntityRenderer(EntityRendererFactory.Context context) {

        super(context);
        System.out.println("ENTTTTTTTTERED InfinityCreeperEntityRenderer");
    }



    @Override
    public Identifier getTexture(CreeperEntity creeperEntity) {
        return TEXTURE;



    }*/
}
