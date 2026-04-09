package com.sculkman.bugaton;

import com.sculkman.bugaton.effect.BugatonEffect;
import com.sculkman.bugaton.effect.BugatonPotions;
import com.sculkman.bugaton.entity.BugatonEntities;
import com.sculkman.bugaton.entity.BugatonModelLayers;
import com.sculkman.bugaton.items.BugatonFoodComponents;
import com.sculkman.bugaton.items.BugatonItemGroups;
import com.sculkman.bugaton.items.BugatonItems;
import com.sculkman.bugaton.particle.BugatonParticles;
import com.sculkman.bugaton.util.BugatonDamageTypes;
import net.fabricmc.api.ModInitializer;

import net.minecraft.potion.PotionUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Bugaton implements ModInitializer {
	public static final String MOD_ID = "bugaton";

	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.
		BugatonParticles.registerBugatonParticles();
		BugatonEntities.registerModEntites();
		BugatonEffect.registerBugatonEffects();
		BugatonItems.registerModItems();
		BugatonItemGroups.registerItemGroups();
		BugatonPotions.registerPotions();
		BugatonFoodComponents.registerBugatonFoodComponents();
		BugatonDamageTypes.registerBugatonDamageTypes();
		LOGGER.info("Bugging a ton!");
	}
}