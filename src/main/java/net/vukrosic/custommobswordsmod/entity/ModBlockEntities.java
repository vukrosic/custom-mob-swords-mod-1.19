package net.vukrosic.custommobswordsmod.entity;

import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.vukrosic.custommobswordsmod.CustomMobSwordsMod;
import net.vukrosic.custommobswordsmod.block.ModBlocks;

public class ModBlockEntities {

    //public static BlockEntityType<ItemCombinerBlockEntity> ITEM_COMBINER;
    
    public static void registerAllBlockEntities(){
        /*ITEM_COMBINER = Registry.register(Registry.BLOCK_ENTITY_TYPE,
                new Identifier(CustomMobSwordsMod.MOD_ID, "item_combiner"),
                FabricBlockEntityTypeBuilder.create(ItemCombinerBlockEntity::new,
                        ModBlocks.ITEM_COMBINER).build(null));*/
    }
}
