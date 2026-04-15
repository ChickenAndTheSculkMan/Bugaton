package com.sculkman.bugaton;

import com.sculkman.bugaton.entity.BugatonEntities;
import com.sculkman.bugaton.entity.BugatonModelLayers;
import com.sculkman.bugaton.entity.models.DisturbedModel;
import com.sculkman.bugaton.entity.models.NightmareModel;
import com.sculkman.bugaton.entity.renderers.DisturbedRenderer;
import com.sculkman.bugaton.entity.renderers.NightmareRenderer;
import com.sculkman.bugaton.particle.BugatonParticles;
import com.sculkman.bugaton.particle.LocustParticle;
import com.sculkman.bugaton.particle.PhantomlingParticle;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;

public class BugatonClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        ParticleFactoryRegistry.getInstance().register(BugatonParticles.LOCUST, LocustParticle.Factory::new);
        ParticleFactoryRegistry.getInstance().register(BugatonParticles.PHANTOMLING, PhantomlingParticle.Factory::new);
        EntityModelLayerRegistry.registerModelLayer(BugatonModelLayers.NIGHTMARE, NightmareModel::getTexturedModelData);
        EntityRendererRegistry.register(BugatonEntities.NIGHTMARE, NightmareRenderer::new);
        EntityModelLayerRegistry.registerModelLayer(BugatonModelLayers.DISTURBED, DisturbedModel::getTexturedModelData);
        EntityRendererRegistry.register(BugatonEntities.DISTURBED, DisturbedRenderer::new);
    }
}
