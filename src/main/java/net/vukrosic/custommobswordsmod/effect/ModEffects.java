package net.vukrosic.custommobswordsmod.effect;

import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.vukrosic.custommobswordsmod.CustomMobSwordsMod;
import net.vukrosic.custommobswordsmod.effect.custom.*;

public class ModEffects {


    public static StatusEffect SPONTANEOUSCOMBUSTION = Registry.register(Registry.STATUS_EFFECT, new Identifier(CustomMobSwordsMod.MOD_ID, "spontaneouscombustion"),
            new SpontaneousCompustionEffect(StatusEffectCategory.HARMFUL, 6644887));

    public static StatusEffect CHICKEN;

    public static StatusEffect SHIELDOFABSORBTION = Registry.register(Registry.STATUS_EFFECT, new Identifier(CustomMobSwordsMod.MOD_ID, "shieldofabsorbtion"),
            new ShieldOfAbsorbtionEffect(StatusEffectCategory.BENEFICIAL, 1644887));
    public static StatusEffect CARBONPOISONING;

    public static StatusEffect FIRE_ENDERMAN;
    public static StatusEffect ENDER_ZOGLIN;

    public static StatusEffect FROGKINGPOISON = Registry.register(Registry.STATUS_EFFECT, new Identifier(CustomMobSwordsMod.MOD_ID, "frogkingpoison"),
            new FrogKingPoisonEffect(StatusEffectCategory.HARMFUL, 8724287));


    private static StatusEffect registerCarbonPoisoningEffect(String name) {
        return Registry.register(Registry.STATUS_EFFECT, new Identifier(CustomMobSwordsMod.MOD_ID, name),
                new CarbonPoisoningEffect(StatusEffectCategory.HARMFUL, 3124687));
    }

    private static StatusEffect registerChickenEffect(String name) {
        return Registry.register(Registry.STATUS_EFFECT, new Identifier(CustomMobSwordsMod.MOD_ID, name),
                new ChickenEffect(StatusEffectCategory.HARMFUL, 6544887));

    }

    private static StatusEffect registerFireEndermanEffect(String name) {
        return Registry.register(Registry.STATUS_EFFECT, new Identifier(CustomMobSwordsMod.MOD_ID, name),
                new FireEndermanEffect(StatusEffectCategory.HARMFUL, 25512780));
    }

    private static StatusEffect registerEnderZoglinEffect(String name) {
        return Registry.register(Registry.STATUS_EFFECT, new Identifier(CustomMobSwordsMod.MOD_ID, name),
                new EnderZoglinEffect(StatusEffectCategory.BENEFICIAL, 61512780));
    }

    public static void registerEffects() {

        CARBONPOISONING = registerCarbonPoisoningEffect("carbonpoisoning");
        CHICKEN = registerChickenEffect("chicken");
        FIRE_ENDERMAN = registerFireEndermanEffect("fire_enderman");
        ENDER_ZOGLIN = registerEnderZoglinEffect("ender_zoglin");
        /*FROGKINGPOISON = registerStatusEffect("frogkingpoision");
        SHIELDOFABSORBTION = registerStatusEffect("shieldofabsorbtion");*/

    }


}
