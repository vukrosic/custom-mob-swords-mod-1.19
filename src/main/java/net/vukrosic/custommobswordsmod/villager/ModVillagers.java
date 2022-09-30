package net.vukrosic.custommobswordsmod.villager;

import com.google.common.collect.ImmutableSet;
import net.fabricmc.fabric.api.object.builder.v1.trade.TradeOfferHelper;
import net.fabricmc.fabric.api.object.builder.v1.villager.VillagerProfessionBuilder;
import net.fabricmc.fabric.api.object.builder.v1.world.poi.PointOfInterestHelper;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.village.TradeOffer;
import net.minecraft.village.VillagerProfession;
import net.minecraft.world.poi.PointOfInterestType;
import net.vukrosic.custommobswordsmod.CustomMobSwordsMod;
import net.vukrosic.custommobswordsmod.item.ModItems;

public class ModVillagers {
/*
    public static final PointOfInterestType INFINITY_POI = registerPOI("infinity_poi", Blocks.OAK_LOG);

    public static final VillagerProfession INFINITY_VILLAGER = registerProfession("infinity_villager",
            RegistryKey.of(Registry.POINT_OF_INTEREST_TYPE_KEY, new Identifier(CustomMobSwordsMod.MOD_ID, "infinity_poi")));

    public static VillagerProfession registerProfession(String name, RegistryKey<PointOfInterestType> type) {
        return Registry.register(Registry.VILLAGER_PROFESSION,
                new Identifier(CustomMobSwordsMod.MOD_ID, name), VillagerProfessionBuilder.create().id(new Identifier(CustomMobSwordsMod.MOD_ID, name))
                        .workstation(type).workSound(SoundEvents.ENTITY_VILLAGER_WORK_ARMORER).build());
    }


    public static PointOfInterestType registerPOI(String name, Block block){
        return PointOfInterestHelper.register(new Identifier(CustomMobSwordsMod.MOD_ID, name), 1, 1,
                ImmutableSet.copyOf(block.getStateManager().getStates()));
    }

    public static void registerVillagers(){
        CustomMobSwordsMod.LOGGER.debug("Registering Villagers for " + CustomMobSwordsMod.MOD_ID);
    }

    public static void registerTrades(){
        TradeOfferHelper.registerVillagerOffers(INFINITY_VILLAGER, 1,
                factories -> {
                    factories.add(((entity, random) -> new TradeOffer(
                            new ItemStack(Items.EMERALD, 3),
                            new ItemStack(ModItems.INFINITY_BOW, 1),
                            6,2,0.02f
                    )));
                });
    }
*/

}
