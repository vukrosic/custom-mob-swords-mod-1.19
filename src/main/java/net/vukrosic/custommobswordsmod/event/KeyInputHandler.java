package net.vukrosic.custommobswordsmod.event;

import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.util.InputUtil;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.AnimationState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.mob.SkeletonEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ArrowEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.entity.projectile.ProjectileUtil;
import net.minecraft.item.ArrowItem;
import net.minecraft.item.Items;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.*;
import net.minecraft.world.RaycastContext;
import net.minecraft.world.World;
import net.vukrosic.custommobswordsmod.command.SetHunterCommand;
import net.vukrosic.custommobswordsmod.entity.custom.PlayerEntityExt;
import net.vukrosic.custommobswordsmod.entity.custom.ShieldingShulkerEntity;
import net.vukrosic.custommobswordsmod.entity.custom.frogking.FrogKingEntity;
import net.vukrosic.custommobswordsmod.screen.BlackScreen;
import net.vukrosic.custommobswordsmod.screen.BlackScreenHandler;
import org.lwjgl.glfw.GLFW;

public class KeyInputHandler {
    public static final String KEY_CATEGORY_CUSTOMMOBS = "key.category.custommobswordsmod.custommobs";
    public static final String KEY_SHOOT_TONGUE = "key.custommobswordsmod.shoot_tongue";
    public static final String KEY_SHOOT_BEAM = "key.custommobswordsmod.shoot_beam";
    public static final String KEY_SUMMON_SHULKER = "key.custommobswordsmod.summon_shulker";

    public static KeyBinding shootTongue;
    public static KeyBinding shootBeam;
    public static KeyBinding summonShulker;

    public static void registerKeyInputs() {
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (shootTongue.wasPressed()) {
                ServerWorld serverWorld = client.getServer().getWorld(client.player.world.getRegistryKey());
                ArrowEntity arrowEntity = new ArrowEntity(client.world, client.player);
                PlayerEntity player = serverWorld.getPlayerByUuid(client.player.getUuid());

                float raycastDistance = 500;

                Vec3d cameraPos = player.getCameraPosVec(0);
                Vec3d cameraDirection = client.cameraEntity.getRotationVec(0);
                Vec3d vec3d3 = cameraPos.add(cameraDirection.multiply(raycastDistance));
                Box box = player
                        .getBoundingBox()
                        .stretch(player.getRotationVec(1.0F).multiply(raycastDistance))
                        .expand(1.0D, 1.0D, 1.0D);

                EntityHitResult entityHitResult = ProjectileUtil.raycast(
                        player,
                        cameraPos,
                        vec3d3,
                        box,
                        (entityx) -> !entityx.isSpectator(),
                        raycastDistance
                );

                if (entityHitResult != null && entityHitResult.getEntity() instanceof LivingEntity) {
                    if(player.getVehicle() != null && player.getVehicle() instanceof FrogKingEntity){
                        FrogKingEntity frogKingEntity = (FrogKingEntity) player.getVehicle();
                        frogKingEntity.swingHand(Hand.MAIN_HAND);
                        //frogKingEntity.tryAttack(entityHitResult.getEntity());

                        frogKingEntity.EatingEntity = (LivingEntity) entityHitResult.getEntity();
                        frogKingEntity.MobPullCounter = frogKingEntity.MobPullMaxCounter;
                    }

                }
                }

            if(shootBeam.wasPressed()){
                client.setScreen(new BlackScreen(new BlackScreenHandler(0, client.player.getInventory()), client.player.getInventory(), Text.of("")));
            }

            if (summonShulker.wasPressed()) {
                if(SetHunterCommand.pray != null){
                    ((PlayerEntityExt)SetHunterCommand.pray).SummonShieldingShulker();
                }

            }
        });
    }






    public static void register() {
            shootTongue = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                    KEY_SHOOT_TONGUE,
                    InputUtil.Type.KEYSYM,
                    GLFW.GLFW_KEY_G,
                    KEY_CATEGORY_CUSTOMMOBS
            ));

            shootBeam = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                    KEY_SHOOT_BEAM,
                    InputUtil.Type.KEYSYM,
                    GLFW.GLFW_KEY_H,
                    KEY_CATEGORY_CUSTOMMOBS
            ));

            summonShulker = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                    KEY_SUMMON_SHULKER,
                    InputUtil.Type.KEYSYM,
                    GLFW.GLFW_KEY_P,
                    KEY_CATEGORY_CUSTOMMOBS
            ));

            registerKeyInputs();
        }
}
