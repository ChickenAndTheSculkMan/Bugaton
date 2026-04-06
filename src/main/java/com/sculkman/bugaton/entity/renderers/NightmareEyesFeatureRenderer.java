package com.sculkman.bugaton.entity.renderers;

import com.sculkman.bugaton.Bugaton;
import com.sculkman.bugaton.entity.NightmareEntity;
import com.sculkman.bugaton.entity.models.NightmareModel;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.entity.feature.EyesFeatureRenderer;
import net.minecraft.client.render.entity.feature.FeatureRendererContext;
import net.minecraft.client.render.entity.model.PhantomEntityModel;
import net.minecraft.entity.mob.PhantomEntity;
import net.minecraft.util.Identifier;

public class NightmareEyesFeatureRenderer<T extends NightmareEntity> extends EyesFeatureRenderer<T, NightmareModel<T>> {
    private static final RenderLayer SKIN = RenderLayer.getEyes(new Identifier(Bugaton.MOD_ID,"textures/entities/nightmare_eyes.png"));

    public NightmareEyesFeatureRenderer(FeatureRendererContext<T, NightmareModel<T>> featureRendererContext) {
        super(featureRendererContext);
    }

    @Override
    public RenderLayer getEyesTexture() {
        return SKIN;
    }
}