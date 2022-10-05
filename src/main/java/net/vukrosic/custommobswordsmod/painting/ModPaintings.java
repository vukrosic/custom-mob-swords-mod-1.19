package net.vukrosic.custommobswordsmod.painting;

import net.minecraft.entity.decoration.painting.PaintingVariant;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.vukrosic.custommobswordsmod.CustomMobSwordsMod;

public class ModPaintings {

    public static final PaintingVariant LewSubCount1 = registerPainting("lew_sub_count_1", new PaintingVariant(64, 64));
    public static final PaintingVariant LewSubCount2 = registerPainting("lew_sub_count_2", new PaintingVariant(64, 64));
    public static final PaintingVariant LewSubCountLower = registerPainting("lew_sub_count_lower", new PaintingVariant(64, 64));
    public static final PaintingVariant LewSubCountHigher = registerPainting("lew_sub_count_higher", new PaintingVariant(64, 64));
    public static final PaintingVariant LewSubCountLew = registerPainting("lew_sub_count_lew", new PaintingVariant(64, 64));
    public static final PaintingVariant LewSubCountNumber = registerPainting("lew_sub_count_number", new PaintingVariant(64, 64));

    private static PaintingVariant registerPainting(String name, PaintingVariant paintingVariant) {
        return Registry.register(Registry.PAINTING_VARIANT, new Identifier(CustomMobSwordsMod.MOD_ID, name), paintingVariant);
    }
    public static void registerPaintings() {
        CustomMobSwordsMod.LOGGER.debug("Registering paintings for " + CustomMobSwordsMod.MOD_ID);
    }
}
