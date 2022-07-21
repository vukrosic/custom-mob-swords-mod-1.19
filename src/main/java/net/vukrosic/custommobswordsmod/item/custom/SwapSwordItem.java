package net.vukrosic.custommobswordsmod.item.custom;

import net.fabricmc.loader.impl.lib.sat4j.core.Vec;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.entity.BeaconBlockEntity;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.BufferBuilder;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.block.entity.BeaconBlockEntityRenderer;
import net.minecraft.client.render.debug.DebugRenderer;
import net.minecraft.client.render.entity.EnderDragonEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.decoration.ItemFrameEntity;
import net.minecraft.entity.passive.VillagerEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ProjectileUtil;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.particle.BlockStateParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.stat.Stats;
import net.minecraft.text.LiteralTextContent;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.*;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.RaycastContext;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;
import net.vukrosic.custommobswordsmod.particle.ModParticles;

import java.awt.*;

public class SwapSwordItem extends SwordItem {
    public SwapSwordItem(ToolMaterial toolMaterial, int attackDamage, float attackSpeed, Settings settings) {
        super(toolMaterial, attackDamage, attackSpeed, settings);
    }




    @Override
    public void usageTick(World world, LivingEntity user, ItemStack stack, int remainingUseTicks) {
        super.usageTick(world, user, stack, remainingUseTicks);
        VertexConsumerProvider vertexConsumers = MinecraftClient.getInstance().getBufferBuilders().getEffectVertexConsumers();
        MatrixStack matrixStack = new MatrixStack();
        float tickDelta = MinecraftClient.getInstance().getTickDelta();

        EnderDragonEntityRenderer.renderCrystalBeam((float)user.getX(), (float)user.getY(), (float)user.getZ(), tickDelta, 0, matrixStack, vertexConsumers, 0);

    }



    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {

        // Render crystal beam between user and target

        VertexConsumerProvider vertexConsumers = MinecraftClient.getInstance().getBufferBuilders().getEffectVertexConsumers();
        MatrixStack matrixStack = new MatrixStack();
        float tickDelta = MinecraftClient.getInstance().getTickDelta();

        EnderDragonEntityRenderer.renderCrystalBeam((float)user.getX(), (float)user.getY(), (float)user.getZ(), tickDelta, 0, matrixStack, vertexConsumers, 0);


        if (!world.isClient()) {
            super.use(world, user, hand);

            ItemStack itemStack = user.getStackInHand(hand);
            //HitResult hitResult = raycast(world, user, RaycastContext.FluidHandling.ANY);
            HitResult hitResult = user.raycast(64, 0, true);

            BlockState obsidian = Blocks.OBSIDIAN.getDefaultState();
            world.setBlockState(new BlockPos(hitResult.getPos()), obsidian);

            //spawnFoundParticles(world, new BlockPos(hitResult.getPos()));

            if (hitResult.getType() == HitResult.Type.MISS) {
                System.out.println("MISSED");
                return TypedActionResult.pass(itemStack);
            } else {
                if (hitResult.getType() == net.minecraft.util.hit.HitResult.Type.ENTITY) {
                    Vec3d pos = hitResult.getPos();

                    // make a list of random positions above and around in the air

                    




                    BlockState state = MinecraftClient.getInstance().world.getBlockState(new BlockPos(pos));
                    Block block = state.getBlock();
                    return TypedActionResult.success(itemStack, world.isClient());
                } else return TypedActionResult.pass(itemStack);
            }
        } else {
            return super.use(world, user, hand);
        }




    }



    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
        super.inventoryTick(stack, world, entity, slot, selected);

    }

    @Override
    public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {



        target.world.addParticle(ParticleTypes.EXPLOSION, target.getX(), target.getY() + 1, target.getZ(), 0, 0, 0);
        return super.postHit(stack, target, attacker);




        /*

        if (!target.world.isClient) {

            if (target instanceof VillagerEntity) {
                target.damage(DamageSource.MAGIC, 10);
                System.out.println("IT ENTERED SWAP POSTHIT FLAME PARTICLE");
                target.world.addParticle(ParticleTypes.FLAME, target.getX(), target.getY(), target.getZ(), 0, 0, 0);
            }

        }

        if (target.world.isClient) {

            if (target instanceof VillagerEntity) {
                System.out.println("IT ENTERED SWAP POSTHIT FLAME CLIENT");
                target.world.addParticle(ParticleTypes.FLAME, target.getX(), target.getY(), target.getZ(), 0, 0, 0);
                renderGlobal
            }

        }

        return super.postHit(stack, target, attacker);
*/











        //spawnFoundParticles(attacker.world, new BlockPos(target.getPos()));
/*
        Random random = Random.create();

        target.world.addParticle(ParticleTypes.EXPLOSION, target.getX(), target.getY() + 2.0, target.getZ(), 2, 2, 2);
*/

        /*
        for (int i = 0; i < 25; ++i) {
            double d = random.nextGaussian() * 0.02;
            double e = random.nextGaussian() * 0.02;
            double f = random.nextGaussian() * 0.02;

            d = 1;
            e = 5;
            f = 1;

            target.world.addParticle(ParticleTypes.ITEM_SNOWBALL, target.getX(), target.getY() + 3.0, target.getZ(), d, e, f);
            target.world.addParticle(ParticleTypes.ANGRY_VILLAGER, target.getX(), target.getY() + 3.0, target.getZ(), d, e, f);
            target.world.addParticle(ParticleTypes.CRIT, target.getX(), target.getY() + 3.0, target.getZ(), d, e, f);
            target.world.addParticle(ParticleTypes.FIREWORK, target.getX(), target.getY() + 2.0, target.getZ(), d, e, f);
            System.out.println("PARTICLES HOULSD BE SWPAUINED");

            BlockState blockState = Blocks.OBSIDIAN.getDefaultState();
            target.world.addParticle(
                    new BlockStateParticleEffect(ParticleTypes.BLOCK, blockState),
                    target.getX(),
                    target.getY() + 2,
                    target.getZ(),
                    0.0,
                    0.0,
                    0.0
            );
        }


        return super.postHit(stack, target, attacker);
    }*/

    /*

    private void spawnFoundParticles(World world, BlockPos positionClicked) {
        System.out.println("SPAWNFOUNDPARTICLES");
        for(int i = 0; i < 360; i++) {
            if(i % 20 == 0) {
                world.addParticle(ModParticles.CITRINE_PARTICLE,
                        positionClicked.getX() + 0.5d, positionClicked.getY() + 1, positionClicked.getZ() + 0.5d,
                        Math.cos(i) * 0.25d, 0.15d, Math.sin(i) * 0.25d);
            }
        }
    }
*/
    }

}

