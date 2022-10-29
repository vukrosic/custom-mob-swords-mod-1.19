package net.vukrosic.custommobswordsmod;

import net.fabricmc.api.ModInitializer;
import net.vukrosic.custommobswordsmod.block.ModBlocks;
import net.vukrosic.custommobswordsmod.effect.ModEffects;
import net.vukrosic.custommobswordsmod.entity.ModEntities;
import net.vukrosic.custommobswordsmod.item.ModItems;
import net.vukrosic.custommobswordsmod.networking.ModMessages;
import net.vukrosic.custommobswordsmod.painting.ModPaintings;
import net.vukrosic.custommobswordsmod.particle.ModParticles;
import net.vukrosic.custommobswordsmod.potion.ModPotions;
import net.vukrosic.custommobswordsmod.screen.ModScreenHandlers;
import net.vukrosic.custommobswordsmod.util.ModRegistries;
import net.vukrosic.custommobswordsmod.world.dimension.ModDimensions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import software.bernie.geckolib3.GeckoLib;

public class CustomMobSwordsMod implements ModInitializer {
	public static final String MOD_ID = "custommobswordsmod";

	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);





	@Override
	public void onInitialize() {

		ModItems.registerModItems();
		ModEntities.registerModEntities();
		ModMessages.registerC2SPacket();
		//ModVillagers.registerVillagers();
		//ModVillagers.registerTrades();
		ModRegistries.registerModStuffs();
		//ModDimensions.register();
        GeckoLib.initialize();
		ModEffects.registerEffects();
		ModParticles.registerParticles();
		ModScreenHandlers.registerAllScreenHandlers();
		ModPaintings.registerPaintings();
		ModPotions.registerPotions();
	}
}
