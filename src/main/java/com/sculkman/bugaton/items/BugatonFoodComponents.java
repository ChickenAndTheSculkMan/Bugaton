package com.sculkman.bugaton.items;

import com.sculkman.bugaton.effect.BugatonEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.item.FoodComponent;

public class BugatonFoodComponents {
    public static final FoodComponent NIGHTMANE_STEW = (new FoodComponent.Builder()).hunger(3).saturationModifier(0.0F).alwaysEdible().snack().meat().statusEffect(new StatusEffectInstance(BugatonEffect.FELLDOM, -1), 1.0F).build();
    public static final FoodComponent NIGHTMANE = (new FoodComponent.Builder().hunger(0).saturationModifier(0).alwaysEdible().meat().statusEffect(new StatusEffectInstance(BugatonEffect.FELLDOM, -1), 1.0F).build());

    public static void registerBugatonFoodComponents() {

    }
}
