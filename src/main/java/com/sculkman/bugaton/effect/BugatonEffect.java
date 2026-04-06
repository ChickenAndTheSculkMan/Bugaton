package com.sculkman.bugaton.effect;

import com.sculkman.bugaton.Bugaton;
import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.particle.DefaultParticleType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class BugatonEffect {

    public static final StatusEffect PHANTOM_FEVER = new NeuronParasiteEffect(StatusEffectCategory.HARMFUL, 8978176);
    public static final StatusEffect FELLDOM = new FelldomEffect(StatusEffectCategory.BENEFICIAL, 4411786);

    public static StatusEffect registerEffect(String name, StatusEffect effect) {
        return Registry.register(Registries.STATUS_EFFECT, new Identifier(Bugaton.MOD_ID, name),
                effect);
    }

    public static void registerBugatonEffects() {
        registerEffect("phantom_fever", PHANTOM_FEVER);
        registerEffect("felldom", FELLDOM);
    }
}
