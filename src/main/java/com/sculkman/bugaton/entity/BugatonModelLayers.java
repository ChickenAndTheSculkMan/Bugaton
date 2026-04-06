package com.sculkman.bugaton.entity;

import com.sculkman.bugaton.Bugaton;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.util.Identifier;

public class BugatonModelLayers {
    public static final EntityModelLayer NIGHTMARE =
            new EntityModelLayer(new Identifier(Bugaton.MOD_ID, "nightmare"), "main");
}
