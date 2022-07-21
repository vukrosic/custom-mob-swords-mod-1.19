package net.vukrosic.custommobswordsmod;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.vukrosic.custommobswordsmod.entity.ModEntities;
import net.vukrosic.custommobswordsmod.entity.custom.InfinityCreeperEntity;
import net.vukrosic.custommobswordsmod.item.ModItems;
import net.vukrosic.custommobswordsmod.particle.ModParticles;
import net.vukrosic.custommobswordsmod.util.ModRegistries;
import net.vukrosic.custommobswordsmod.villager.ModVillagers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CustomMobSwordsMod implements ModInitializer {
	public static final String MOD_ID = "custommobswordsmod";

	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);





	@Override
	public void onInitialize() {

		ModItems.registerModItems();
		ModEntities.registerModEntities();
		ModVillagers.registerVillagers();
		ModVillagers.registerTrades();
		ModRegistries.registerModStuffs();
		ModParticles.registerParticles();
	}
}
