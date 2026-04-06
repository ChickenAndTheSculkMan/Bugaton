package com.sculkman.bugaton.entity.renderers;

import com.sculkman.bugaton.Bugaton;
import com.sculkman.bugaton.entity.NightmareEntity;
import com.sculkman.bugaton.entity.models.NightmareModel;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.feature.FeatureRenderer;
import net.minecraft.client.render.entity.feature.FeatureRendererContext;
import net.minecraft.client.render.entity.model.WolfEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.passive.WolfEntity;
import net.minecraft.util.Identifier;

public class NightmareCollarFeatureRenderer extends FeatureRenderer<NightmareEntity, NightmareModel<NightmareEntity>> {
    private static final Identifier SKIN = new Identifier(Bugaton.MOD_ID,"textures/entities/nightmare_collar.png");

    public NightmareCollarFeatureRenderer(FeatureRendererContext<NightmareEntity, NightmareModel<NightmareEntity>> featureRendererContext) {
        super(featureRendererContext);
    }

    public void render(
            MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i, NightmareEntity nightmareEntity, float f, float g, float h, float j, float k, float l
    ) {
        if (nightmareEntity.isNightmarePacified() && !nightmareEntity.isInvisible()) {
            float[] fs = nightmareEntity.getCollarColor().getColorComponents();
            renderModel(this.getContextModel(), SKIN, matrixStack, vertexConsumerProvider, i, nightmareEntity, fs[0], fs[1], fs[2]);
        }
    }
}