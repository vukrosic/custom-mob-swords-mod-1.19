package net.vukrosic.custommobswordsmod.event;

import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.block.Blocks;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ArrowEntity;
import net.minecraft.entity.projectile.ProjectileUtil;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.*;
//import net.vukrosic.custommobswordsmod.command.SetHunterCommand;
import net.vukrosic.custommobswordsmod.command.SetHunterCommand;
import net.vukrosic.custommobswordsmod.entity.ModEntities;
import net.vukrosic.custommobswordsmod.entity.custom.PlayerEntityExt;
import net.vukrosic.custommobswordsmod.entity.custom.chunken.ChunkenPhaseManager;
import net.vukrosic.custommobswordsmod.entity.custom.frogking.EatenMobsByFrogKing;
import net.vukrosic.custommobswordsmod.entity.custom.frogking.FrogKingEntity;
import net.vukrosic.custommobswordsmod.entity.custom.summoner.SummonerEntityGL;
import net.vukrosic.custommobswordsmod.screen.BlackScreen;
import net.vukrosic.custommobswordsmod.screen.BlackScreenHandler;
import org.lwjgl.glfw.GLFW;

public class KeyInputHandler {
    public static final String KEY_CATEGORY_CUSTOMMOBS = "key.category.custommobswordsmod.custommobs";
    public static final String KEY_SHOOT_TONGUE = "key.custommobswordsmod.shoot_tongue";
    public static final String KEY_SHOOT_BEAM = "key.custommobswordsmod.shoot_beam";
    public static final String KEY_SUMMON_SHULKER = "key.custommobswordsmod.summon_shulker";
    public static final String KEY_SHOOT_MOB = "key.custommobswordsmod.shoot_mob";
    public static final String KEY_SUMMON_SUMMONER_MOB = "key.custommobswordsmod.spawn_summoner_mob";
    public static final String KEY_ACTIVATE_CHUNKEN_4 = "key.custommobswordsmod.set_chunken_4";

    public static KeyBinding shootTongue;
    public static KeyBinding blackScreen;
    public static KeyBinding summonShulker;
    public static KeyBinding shootMob;
    public static KeyBinding summonSummonerMob;
    public static KeyBinding setChunkenTo4;

    public static void registerKeyInputs() {
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (shootTongue.wasPressed()) {
                if(client.player.getVehicle() != null) {

                    ServerWorld serverWorld = client.getServer().getWorld(client.player.world.getRegistryKey());
                    PlayerEntity player = serverWorld.getPlayerByUuid(client.player.getUuid());

                    float raycastDistance = 500;

                    Vec3d cameraPos = player.getCameraPosVec(0);
                    Vec3d cameraDirection = client.cameraEntity.getRotationVec(0);
                    Vec3d vec3d3 = cameraPos.add(cameraDirection.multiply(raycastDistance));
                    Box box = player
                            .getBoundingBox()
                            .stretch(player.getRotationVec(1.0F).multiply(raycastDistance))
                            .expand(100.0D, 100.0D, 100.0D);

                    EntityHitResult entityHitResult = ProjectileUtil.raycast(
                            player,
                            cameraPos,
                            vec3d3,
                            box,
                            (entityx) -> !entityx.isSpectator(),
                            raycastDistance
                    );
/*
                    HitResult hitResult = ProjectileUtil.raycast(
                            player,
                            cameraPos,
                            vec3d3,
                            box,
                            (entityx) -> !entityx.isSpectator(),
                            raycastDistance
                    );
                    // cast a 10x10x10 box and see if there is an entity in it
                    if(hitResult != null)
                        serverWorld.setBlockState(new BlockPos(hitResult.getPos()), Blocks.DIAMOND_BLOCK.getDefaultState());
*/
                    FrogKingEntity frogKingEntity = (FrogKingEntity) player.getVehicle();
                    frogKingEntity.swingHand(Hand.MAIN_HAND);

                    if (entityHitResult != null && entityHitResult.getEntity() instanceof LivingEntity) {
                        if (player.getVehicle() != null && player.getVehicle() instanceof FrogKingEntity) {
                            frogKingEntity.EatingEntity = (LivingEntity) entityHitResult.getEntity();
                            frogKingEntity.MobPullCounter = frogKingEntity.MobPullMaxCounter;
                        }
                    }
                }
            }

            if(blackScreen.wasPressed()){
                //client.setScreen(new BlackScreen(new BlackScreenHandler(0, client.player.getInventory()), client.player.getInventory(), Text.of("")));
            }

            if (summonShulker.wasPressed()) {
                if(SetHunterCommand.pray != null){
                    ((PlayerEntityExt)SetHunterCommand.pray).SummonShieldingShulker();
                }
            }

            if(shootMob.wasPressed()){
                // get if player is mounted
                if(client.player.getVehicle() != null && client.player.getVehicle() instanceof FrogKingEntity){
                    FrogKingEntity frogKingEntity = (FrogKingEntity) client.player.getVehicle();
                    //frogKingEntity.ShootEatenEntity();
                    ServerWorld serverWorld = client.getServer().getWorld(client.player.world.getRegistryKey());
                    PlayerEntity player = serverWorld.getPlayerByUuid(client.player.getUuid());
                    EatenMobsByFrogKing.ShootMob(player);
                    // get server player
                }
            }

            if(summonSummonerMob.wasPressed()){
                ServerWorld serverWorld = client.getServer().getWorld(client.player.world.getRegistryKey());
                PlayerEntity player = serverWorld.getPlayerByUuid(client.player.getUuid());
                if(((PlayerEntityExt)player).getSummonerEntityGL() != null){
                    ((PlayerEntityExt)player).getSummonerEntityGL().swingHand(Hand.MAIN_HAND);
                    ((PlayerEntityExt)player).getSummonerEntityGL().enableParticleCountdown = true;

                }
                //get all entities of type SummonerEntityGL
                /*
                serverWorld.getEntitiesByClass(SummonerEntityGL.class, new Box(-1,-1,-1,1,1,1), (entity) -> true).forEach(entity -> {
                    entity.swingHand(Hand.MAIN_HAND);
                });*/

            }

            if(setChunkenTo4.wasPressed()){
                ChunkenPhaseManager.set4Phase();
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

            blackScreen = KeyBindingHelper.registerKeyBinding(new KeyBinding(
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

            shootMob = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                    KEY_SHOOT_MOB,
                    InputUtil.Type.KEYSYM,
                    GLFW.GLFW_KEY_L,
                    KEY_CATEGORY_CUSTOMMOBS
            ));

            summonSummonerMob = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                    KEY_SUMMON_SUMMONER_MOB,
                    InputUtil.Type.KEYSYM,
                    GLFW.GLFW_KEY_K,
                    KEY_CATEGORY_CUSTOMMOBS
            ));

            setChunkenTo4 = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                    KEY_ACTIVATE_CHUNKEN_4,
                    InputUtil.Type.KEYSYM,
                    GLFW.GLFW_KEY_N,
                    KEY_CATEGORY_CUSTOMMOBS
            ));

            registerKeyInputs();
        }
}
