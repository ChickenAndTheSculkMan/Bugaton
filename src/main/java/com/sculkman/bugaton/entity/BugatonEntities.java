package com.sculkman.bugaton.entity;

import com.sculkman.bugaton.Bugaton;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class BugatonEntities {
    public static final EntityType<NightmareEntity> NIGHTMARE = Registry.register(Registries.ENTITY_TYPE,
            new Identifier(Bugaton.MOD_ID, "nightmare"),
            FabricEntityTypeBuilder.create(SpawnGroup.MISC, NightmareEntity::new).dimensions(EntityDimensions.fixed(0.8f, 0.8f))
                    .build());

    public static void registerModEntites() {
        FabricDefaultAttributeRegistry.register(NIGHTMARE, NightmareEntity.createNightmareAttributes());
    }
}
