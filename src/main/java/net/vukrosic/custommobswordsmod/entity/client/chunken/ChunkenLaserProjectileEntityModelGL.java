package net.vukrosic.custommobswordsmod.entity.client.chunken;

import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.data.client.Model;
import net.minecraft.data.client.TextureKey;
import net.minecraft.util.Identifier;
import net.vukrosic.custommobswordsmod.CustomMobSwordsMod;
import net.vukrosic.custommobswordsmod.entity.custom.chunken.ChunkenLaserProjectileEntityGL;
import software.bernie.geckolib3.GeckoLib;
import software.bernie.geckolib3.model.AnimatedGeoModel;


public class ChunkenLaserProjectileEntityModelGL extends AnimatedGeoModel<ChunkenLaserProjectileEntityGL> {

    @Override
    public Identifier getModelResource(ChunkenLaserProjectileEntityGL object) {
        return new Identifier(CustomMobSwordsMod.MOD_ID, "geo/chunkenlaser.geo.json");
    }

    @Override
    public Identifier getTextureResource(ChunkenLaserProjectileEntityGL object) {
        return new Identifier(CustomMobSwordsMod.MOD_ID, "textures/entity/chicken_robot_phase_1.png");
    }

    @Override
    public Identifier getAnimationResource(ChunkenLaserProjectileEntityGL animatable) {
         return new Identifier(CustomMobSwordsMod.MOD_ID, "animations/chicken_robot.animation.json");
    }
}
