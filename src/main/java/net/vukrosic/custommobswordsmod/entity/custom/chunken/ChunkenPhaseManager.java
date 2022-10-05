package net.vukrosic.custommobswordsmod.entity.custom.chunken;

import net.minecraft.util.Identifier;
import net.vukrosic.custommobswordsmod.CustomMobSwordsMod;

import java.util.ArrayList;

public class ChunkenPhaseManager {
    public static int chunkenPhase = 0;

    static ArrayList<Identifier> textures = new ArrayList<Identifier>();

    static ArrayList<String> idleAnimation = new ArrayList<>();
    static ArrayList<String> walkAnimation = new ArrayList<>();
    static ArrayList<String> attackAnimation = new ArrayList<>();


    public static void resetChunkenPhase() {
        chunkenPhase = 0;
    }


    public static void createChunkenPhases(){
        textures.add(new Identifier(CustomMobSwordsMod.MOD_ID, "textures/entity/chicken_robot_phase_1.png"));
        textures.add(new Identifier(CustomMobSwordsMod.MOD_ID, "textures/entity/chicken_robot_phase_2.png"));
        textures.add(new Identifier(CustomMobSwordsMod.MOD_ID, "textures/entity/chicken_robot_phase_3.png"));
        textures.add(new Identifier(CustomMobSwordsMod.MOD_ID, "textures/entity/chicken_robot_phase_4.png"));
        textures.add(new Identifier(CustomMobSwordsMod.MOD_ID, "textures/entity/chicken_robot_phase_5_gold.png"));


        idleAnimation.add("animation.geometry.chicken.phase1_idle");
        walkAnimation.add("animation.geometry.chicken.phase1_walk");
        attackAnimation.add("animation.geometry.chicken.phase1_attack");

        idleAnimation.add("animation.geometry.chicken.phase2_idle");
        walkAnimation.add("animation.geometry.chicken.phase2_walk");
        attackAnimation.add("animation.geometry.chicken.phase2_attack");

        idleAnimation.add("animation.geometry.chicken.phase3_idle");
        walkAnimation.add("animation.geometry.chicken.phase3_walk");
        attackAnimation.add("animation.geometry.chicken.phase3_attack");

        idleAnimation.add("animation.geometry.chicken.phase4_idle");
        walkAnimation.add("animation.geometry.chicken.phase4_walk");
        attackAnimation.add("animation.geometry.chicken.phase4_attack");

        idleAnimation.add("animation.geometry.chicken.phase5_idle");
        walkAnimation.add("animation.geometry.chicken.phase5_walk");
        attackAnimation.add("animation.geometry.chicken.phase5_attack");

    }

    public static String getIdleAnimation(){
        return idleAnimation.get(chunkenPhase);
    }

    public static String getWalkAnimation(){
        return walkAnimation.get(chunkenPhase);
    }

    public static String getAttackAnimation(){
        return attackAnimation.get(chunkenPhase);
    }

    public static Identifier getChunkenTexture(){
        return textures.get(chunkenPhase);
    }
}
