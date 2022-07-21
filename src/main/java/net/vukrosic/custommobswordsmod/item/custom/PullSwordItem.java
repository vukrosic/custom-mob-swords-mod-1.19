package net.vukrosic.custommobswordsmod.item.custom;

import net.fabricmc.loader.impl.lib.sat4j.core.Vec;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.mob.ZombieEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.vukrosic.custommobswordsmod.util.IZombieEntityMixin;

public class PullSwordItem extends SwordItem {
    public PullSwordItem(ToolMaterial toolMaterial, int attackDamage, float attackSpeed, Settings settings) {
        super(toolMaterial, attackDamage, attackSpeed, settings);
    }


    @Override
    public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {

        if(target instanceof ZombieEntity) {
            System.out.println("11111111111111111111112222222222222222222");


            PlayerEntity player = (PlayerEntity) attacker;
            World world = player.world;

            MinecraftClient client = MinecraftClient.getInstance();
            float tickDelta = MinecraftClient.getInstance().getTickDelta();
            Vec3d cameraDirection = client.cameraEntity.getRotationVec(tickDelta);


            target.setVelocity(cameraDirection.multiply(15));
/*
            target.teleport(target.getX() + cameraDirection.normalize().getX() * 6,
                    target.getY() + cameraDirection.normalize().getY(),
                    target.getZ() + cameraDirection.normalize().getZ() * 6,
                    true);*/

            System.out.println("target.getVelocity().getX() = " + target.getVelocity().getX());
            if(target.getVelocity().getX() < 8 && target.getVelocity().getZ() < 8){
                target.kill();
                System.out.println("KILLED");
            }
            ((IZombieEntityMixin)target).setEviscereting(true);





        }

/*
        if (attacker instanceof PlayerEntity) {
            PlayerEntity player = (PlayerEntity) attacker;
            World world = player.world;
            if (!world.isClient) {
                double x = player.getX();
                double y = player.getY();
                double z = player.getZ();
                world.playSound(null, x, y, z, SoundEvents.ENTITY_ARROW_SHOOT, SoundCategory.PLAYERS, 1.0F, 1.0F / (0.05F * 0.4F + 1.2F) + 0.5F);
                if (target instanceof LivingEntity) {
                    LivingEntity livingEntity = (LivingEntity) target;
                    livingEntity.setVelocity(player.getRotationVec(1.0F).multiply(2.0F));
                    livingEntity.damage(DamageSource.player(player), 1.0F);
                }
            }
        }*/

        System.out.println("evisceratiiiiiiiiiiiiiiiiiiiing");

        return super.postHit(stack, target, attacker);
    }
}
