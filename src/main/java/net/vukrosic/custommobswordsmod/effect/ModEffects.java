package net.vukrosic.custommobswordsmod.effect;

import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.vukrosic.custommobswordsmod.CustomMobSwordsMod;
import net.vukrosic.custommobswordsmod.effect.custom.CarbonPoisoningEffect;
import net.vukrosic.custommobswordsmod.effect.custom.ChickenEffect;
import net.vukrosic.custommobswordsmod.effect.custom.FrogKingPoisonEffect;
import net.vukrosic.custommobswordsmod.effect.custom.ShieldOfAbsorbtionEffect;

public class ModEffects {

    public static StatusEffect CHICKEN;
    /*= Registry.register(Registry.STATUS_EFFECT, new Identifier(CustomMobSwordsMod.MOD_ID, "chicken"),
            new ChickenEffect(StatusEffectCategory.HARMFUL, 6544887));*/

    public static StatusEffect SHIELDOFABSORBTION = Registry.register(Registry.STATUS_EFFECT, new Identifier(CustomMobSwordsMod.MOD_ID, "shieldofabsorbtion"),
            new ShieldOfAbsorbtionEffect(StatusEffectCategory.BENEFICIAL, 1644887));
    public static StatusEffect CARBONPOISONING;
    /*= Registry.register(Registry.STATUS_EFFECT, new Identifier(CustomMobSwordsMod.MOD_ID, "carbonpoisoning"),
                new CarbonPoisoningEffect(StatusEffectCategory.HARMFUL, 3124687));
*/
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

    public static void registerEffects() {

        CARBONPOISONING = registerCarbonPoisoningEffect("carbonpoisoning");
        CHICKEN = registerChickenEffect("chicken");
        /*FROGKINGPOISON = registerStatusEffect("frogkingpoision");
        SHIELDOFABSORBTION = registerStatusEffect("shieldofabsorbtion");
        */
    }


}
