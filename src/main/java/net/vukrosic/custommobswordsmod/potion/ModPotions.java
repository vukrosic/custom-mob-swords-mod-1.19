package net.vukrosic.custommobswordsmod.potion;

import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.potion.Potion;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.vukrosic.custommobswordsmod.CustomMobSwordsMod;
import net.vukrosic.custommobswordsmod.effect.ModEffects;


public class ModPotions {
    public static Potion FIRE_ENDERMAN_POTION;
    public static Potion ENDER_ZOGLIN_POTION;

    public static Potion registerFireEndermanPotion(String name) {
        return Registry.register(Registry.POTION, new Identifier(CustomMobSwordsMod.MOD_ID, name),
                new Potion(new StatusEffectInstance(ModEffects.FIRE_ENDERMAN, 2000, 0)));
    }

    public static Potion registerEnderZoglinPotion(String name) {
        return Registry.register(Registry.POTION, new Identifier(CustomMobSwordsMod.MOD_ID, name),
                new Potion(new StatusEffectInstance(ModEffects.ENDER_ZOGLIN, 2000, 0)));
    }

    public static void registerPotions() {

        FIRE_ENDERMAN_POTION = registerFireEndermanPotion("fire_enderman_potion");
        ENDER_ZOGLIN_POTION = registerEnderZoglinPotion("ender_zoglin_potion");
    }
}
