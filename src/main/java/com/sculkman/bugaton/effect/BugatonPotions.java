package com.sculkman.bugaton.effect;

import com.sculkman.bugaton.Bugaton;
import com.sculkman.bugaton.items.BugatonItems;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.Items;
import net.minecraft.potion.Potion;
import net.minecraft.potion.Potions;
import net.minecraft.recipe.BrewingRecipeRegistry;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class BugatonPotions {
    public static final Potion PARASITIC_POTION =
            Registry.register(Registries.POTION, new Identifier(Bugaton.MOD_ID, "parasitic_potion"),
                    new Potion(new StatusEffectInstance(BugatonEffect.PHANTOM_FEVER, 3600, 0)));
    public static final Potion LONGER_PARASITIC_POTION =
            Registry.register(Registries.POTION, new Identifier(Bugaton.MOD_ID, "stronger_parasitic_potion"),
                    new Potion(new StatusEffectInstance(BugatonEffect.PHANTOM_FEVER, 7200, 1)));
    public static final Potion LONGEST_PARASITIC_POTION =
            Registry.register(Registries.POTION, new Identifier(Bugaton.MOD_ID, "strongest_parasitic_potion"),
                    new Potion(new StatusEffectInstance(BugatonEffect.PHANTOM_FEVER, 24000, 2)));
    public static final Potion ETERNAL_PARASITIC_POTION =
            Registry.register(Registries.POTION, new Identifier(Bugaton.MOD_ID, "eternal_parasitic_potion"),
                    new Potion(new StatusEffectInstance(BugatonEffect.PHANTOM_FEVER, -1, 3)));

    public static void registerPotions(){
        registerPotionsRecipes();
    }

    public static void registerPotionsRecipes(){
        BrewingRecipeRegistry.registerPotionRecipe(Potions.AWKWARD, BugatonItems.NIGHTMANE, PARASITIC_POTION);
        BrewingRecipeRegistry.registerPotionRecipe(BugatonPotions.PARASITIC_POTION, BugatonItems.NIGHTMANE, LONGER_PARASITIC_POTION);
        BrewingRecipeRegistry.registerPotionRecipe(BugatonPotions.LONGER_PARASITIC_POTION, BugatonItems.NIGHTMANE, LONGEST_PARASITIC_POTION);
        BrewingRecipeRegistry.registerPotionRecipe(BugatonPotions.LONGEST_PARASITIC_POTION, BugatonItems.NIGHTMANE, ETERNAL_PARASITIC_POTION);
    }
}
