package com.sculkman.bugaton.entity.renderers;

import com.sculkman.bugaton.Bugaton;
import com.sculkman.bugaton.entity.BugatonModelLayers;
import com.sculkman.bugaton.entity.NightmareEntity;
import com.sculkman.bugaton.entity.models.NightmareModel;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.render.entity.feature.PhantomEyesFeatureRenderer;
import net.minecraft.client.render.entity.feature.WolfCollarFeatureRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import java.util.Objects;

public class NightmareRenderer extends MobEntityRenderer<NightmareEntity, NightmareModel<NightmareEntity>> {
    private static final Identifier TEXTURE = new Identifier(Bugaton.MOD_ID, "textures/entities/nightmare.png");
    private static final Identifier TEXTURE_SALMON = new Identifier(Bugaton.MOD_ID, "textures/entities/nightmare_salmon.png");


    public NightmareRenderer(EntityRendererFactory.Context context) {
        super(context, new NightmareModel<>(context.getPart(BugatonModelLayers.NIGHTMARE)), 0.4f);
        this.addFeature(new NightmareEyesFeatureRenderer<>(this));
        this.addFeature(new NightmareCollarFeatureRenderer(this));
    }

    @Override
    public Identifier getTexture(NightmareEntity entity) {
        if (entity.getCustomName() != null) {
            if (entity.getCustomName().contains(Text.of("Salmon")) || entity.getCustomName().contains(Text.of("salmon"))) {
                return TEXTURE_SALMON;
            } else {
                return TEXTURE;
            }
        } else {
            return TEXTURE;
        }
    }

    @Override
    public void render(NightmareEntity mobEntity, float f, float g, MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i) {
        super.render(mobEntity, f, g, matrixStack, vertexConsumerProvider, i);
    }
}