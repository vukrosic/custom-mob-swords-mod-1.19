package net.vukrosic.custommobswordsmod.item;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.item.ToolMaterials;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.vukrosic.custommobswordsmod.CustomMobSwordsMod;
import net.vukrosic.custommobswordsmod.entity.ModEntities;
import net.vukrosic.custommobswordsmod.item.custom.*;


public class ModItems {

    public static final Item INFINITY_CREEPER_SPAWN_EGG = registerItem("infinity_creeper_spawn_egg",
            new SpawnEggItem(ModEntities.INFINITY_CREEPER, 0x948e8d, 0x3b3635,
                    new FabricItemSettings().group(ItemGroup.MISC)));

    public static final Item INFINITY_TRIDENT = registerItem("infinity_trident",
            new InfinityTridentItem(new FabricItemSettings().group(ItemGroup.COMBAT)));

    public static final Item INFINITY_ARROW = registerItem("infinity_arrow",
            new InfinityArrowItem(new FabricItemSettings().group(ItemGroup.COMBAT)));

    public static final Item INFINITY_BOW = registerItem("infinity_bow",
            new InfinityBowItem(new FabricItemSettings().group(ItemGroup.COMBAT)));

    public static final Item DOUBLING_PICKAXE = registerItem("doubling_pickaxe",
            new DoublingPickaxeItem(ToolMaterials.DIAMOND, 5, 1, new FabricItemSettings().group(ItemGroup.TOOLS)));



    /*=======
     Swords
     =======*/

    public static final Item CREEPER_SWORD = registerItem("creeper_sword",
            new CreeperSwordItem(ToolMaterials.DIAMOND, 5, 1, new FabricItemSettings().group(ItemGroup.COMBAT)));

    public static final Item LIFE_STEAL_SWORD = registerItem("life_steal_sword",
            new LifeStealSwordItem(ToolMaterials.DIAMOND, 4, 1, new FabricItemSettings().group(ItemGroup.COMBAT)));

    public static final Item SWAP_SWORD = registerItem("swap_sword",
            new SwapSwordItem(ToolMaterials.DIAMOND, 3, 1, new FabricItemSettings().group(ItemGroup.COMBAT)));

    public static final Item PULL_SWORD = registerItem("pull_sword",
            new PullSwordItem(ToolMaterials.DIAMOND, 3, 1, new FabricItemSettings().group(ItemGroup.COMBAT)));

    public static final Item BACKSTAB_SWORD = registerItem("backstab_sword",
            new BackstabSwordItem(ToolMaterials.DIAMOND, 5, 2f, new FabricItemSettings().group(ItemGroup.COMBAT)));

    public static final Item PAPER_SWORD = registerItem("paper_sword",
            new PaperSwordItem(ToolMaterials.DIAMOND, 0, 2f, new FabricItemSettings().group(ItemGroup.COMBAT)));

    public static final Item CURSED_SWORD = registerItem("cursed_sword",
            new CursedSwordItem(ToolMaterials.DIAMOND, 10, 2f, new FabricItemSettings().group(ItemGroup.COMBAT)));
    public static final Item UNDYING_SWORD = registerItem("undying_sword",
            new UndyingSwordItem(ToolMaterials.DIAMOND, 8, 2f, new FabricItemSettings().group(ItemGroup.COMBAT)));

    public static final Item VILLAGER_SWORD = registerItem("villager_sword",
            new VillagerSwordItem(ToolMaterials.DIAMOND, 1, 2f, new FabricItemSettings().group(ItemGroup.COMBAT)));




    private static Item registerItem(String name, Item item) {
        return Registry.register(Registry.ITEM, new Identifier(CustomMobSwordsMod.MOD_ID, name), item);
    }
    public static void registerModItems(){
        CustomMobSwordsMod.LOGGER.info("Registering Mod Items for " + CustomMobSwordsMod.MOD_ID);
    }
}
