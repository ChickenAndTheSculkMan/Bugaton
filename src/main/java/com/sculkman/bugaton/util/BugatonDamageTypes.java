package com.sculkman.bugaton.util;

import net.minecraft.entity.damage.DamageType;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;

public class BugatonDamageTypes {
    public static final RegistryKey<DamageType> SWARMING_DAMAGE = RegistryKey.of(RegistryKeys.DAMAGE_TYPE,
            new Identifier("bugaton", "swarming"));

    public static void registerBugatonDamageTypes() {

    }
}
