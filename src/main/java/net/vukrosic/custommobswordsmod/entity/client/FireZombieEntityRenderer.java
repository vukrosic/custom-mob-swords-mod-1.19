package net.vukrosic.custommobswordsmod.entity.client;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.render.entity.EndermanEntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.ZombieEntityRenderer;
import net.minecraft.entity.mob.EndermanEntity;
import net.minecraft.util.Identifier;
import net.vukrosic.custommobswordsmod.CustomMobSwordsMod;

@Environment(value= EnvType.CLIENT)
public class FireZombieEntityRenderer extends ZombieEntityRenderer {



    public FireZombieEntityRenderer(EntityRendererFactory.Context context) {
        super(context);
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
