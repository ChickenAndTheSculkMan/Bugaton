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
    private static final Identifier TEXTURE_CRAZY = new Identifier(Bugaton.MOD_ID, "textures/entities/nightmare_crazy.png");
    private static final Identifier TEXTURE_COBBLE = new Identifier(Bugaton.MOD_ID, "textures/entities/nightmare_cobble.png");
    private static final Identifier TEXTURE_ZOID = new Identifier(Bugaton.MOD_ID, "textures/entities/nightmare_zoid.png");
    private static final Identifier TEXTURE_JAE = new Identifier(Bugaton.MOD_ID, "textures/entities/nightmare_jae.png");
    private static final Identifier TEXTURE_EYE = new Identifier(Bugaton.MOD_ID, "textures/entities/nightmare_eye.png");
    private static final Identifier TEXTURE_DUCK = new Identifier(Bugaton.MOD_ID, "textures/entities/nightmare_duck.png");
    private static final Identifier TEXTURE_SWITCHED = new Identifier(Bugaton.MOD_ID, "textures/entities/nightmare_switched.png");


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
            } else if (entity.getCustomName().contains(Text.of("Crazy")) || entity.getCustomName().contains(Text.of("Crazyplace"))) {
                return TEXTURE_CRAZY;
            } else if (entity.getCustomName().contains(Text.of("Cobblestone")) || entity.getCustomName().contains(Text.of("Cobble"))) {
                return TEXTURE_COBBLE;
            } else if (entity.getCustomName().contains(Text.of("Zoid")) || entity.getCustomName().contains(Text.of("zod"))) {
                return TEXTURE_ZOID;
            } else if (entity.getCustomName().contains(Text.of("Jae")) || entity.getCustomName().contains(Text.of("Salt Shower"))) {
                return TEXTURE_JAE;
            } else if (entity.getCustomName().contains(Text.of("Eye")) || entity.getCustomName().contains(Text.of("Yee"))) {
                return TEXTURE_EYE;
            } else if (entity.getCustomName().contains(Text.of("DuckLoaf")) || entity.getCustomName().contains(Text.of("duck"))) {
                return TEXTURE_DUCK;
            } else if (entity.getCustomName().contains(Text.of("SwitchedCube")) || entity.getCustomName().contains(Text.of("Switched"))) {
                return TEXTURE_SWITCHED;
            }
            else {
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