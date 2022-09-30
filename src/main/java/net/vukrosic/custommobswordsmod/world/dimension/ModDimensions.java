package net.vukrosic.custommobswordsmod.world.dimension;

import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.World;
import net.minecraft.world.dimension.DimensionType;
import net.vukrosic.custommobswordsmod.CustomMobSwordsMod;

public class ModDimensions {

/*
    public static final RegistryKey<World> NATEDIM_DIMENSION_KEY = RegistryKey.of(Registry.WORLD_KEY,
            new Identifier(CustomMobSwordsMod.MOD_ID, "natedim"));
    public static final RegistryKey<DimensionType> NATEDIM_TYPE_KEY = RegistryKey.of(Registry.DIMENSION_TYPE_KEY,
            NATEDIM_DIMENSION_KEY.getValue());

    public static final RegistryKey<World> GOLDDIM_DIMENSION_KEY = RegistryKey.of(Registry.WORLD_KEY,
            new Identifier(CustomMobSwordsMod.MOD_ID, "golddim"));
    public static final RegistryKey<DimensionType> GOLDDIM_TYPE_KEY = RegistryKey.of(Registry.DIMENSION_TYPE_KEY,
            GOLDDIM_DIMENSION_KEY.getValue());

    public static final RegistryKey<World> LEWDIM_DIMENSION_KEY = RegistryKey.of(Registry.WORLD_KEY,
            new Identifier(CustomMobSwordsMod.MOD_ID, "lewdim"));
    public static final RegistryKey<DimensionType> LEWDIM_TYPE_KEY = RegistryKey.of(Registry.DIMENSION_TYPE_KEY,
            LEWDIM_DIMENSION_KEY.getValue());
*/

/*
    public static final RegistryKey<World> CHICKENDIM_DIMENSION_KEY = RegistryKey.of(Registry.WORLD_KEY,
            new Identifier(CustomMobSwordsMod.MOD_ID, "chickendim"));
    public static final RegistryKey<DimensionType> CHICKENDIM_TYPE_KEY = RegistryKey.of(Registry.DIMENSION_TYPE_KEY,
            CHICKENDIM_DIMENSION_KEY.getValue());
*/
/*
    public static final RegistryKey<World> KJDIM_DIMENSION_KEY = RegistryKey.of(Registry.WORLD_KEY,
            new Identifier(CustomMobSwordsMod.MOD_ID, "kjdim"));
    public static final RegistryKey<DimensionType> KJDIM_TYPE_KEY = RegistryKey.of(Registry.DIMENSION_TYPE_KEY,
            KJDIM_DIMENSION_KEY.getValue());
*/
    public static void register() {
        CustomMobSwordsMod.LOGGER.debug("Registering ModDimensions for " + CustomMobSwordsMod.MOD_ID);
    }
}
