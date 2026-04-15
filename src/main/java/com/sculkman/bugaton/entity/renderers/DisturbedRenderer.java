package com.sculkman.bugaton.entity.renderers;

import com.sculkman.bugaton.Bugaton;
import com.sculkman.bugaton.entity.BugatonModelLayers;
import com.sculkman.bugaton.entity.DisturbedEntity;
import com.sculkman.bugaton.entity.NightmareEntity;
import com.sculkman.bugaton.entity.models.DisturbedModel;
import com.sculkman.bugaton.entity.models.NightmareModel;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.util.Identifier;

public class DisturbedRenderer extends MobEntityRenderer<DisturbedEntity, DisturbedModel<DisturbedEntity>> {
    public DisturbedRenderer(EntityRendererFactory.Context context) {
        super(context, new DisturbedModel<>(context.getPart(BugatonModelLayers.DISTURBED)), 0.4f);
    }
    private static final Identifier TEXTURE = new Identifier(Bugaton.MOD_ID, "textures/entities/disturbed.png");

    @Override
    public Identifier getTexture(DisturbedEntity entity) {
        return TEXTURE;
    }
}
