package com.sculkman.bugaton.particle;

import com.sculkman.bugaton.Bugaton;
import io.netty.util.Constant;
import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes;
import net.minecraft.client.particle.Particle;
import net.minecraft.particle.DefaultParticleType;
import net.minecraft.particle.ParticleType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class BugatonParticles {

    public static final DefaultParticleType LOCUST = FabricParticleTypes.simple();
    public static final DefaultParticleType PHANTOMLING = FabricParticleTypes.simple();

    //Improved variant of the Vesselage Particle registerer
    private static ParticleType<DefaultParticleType> registerParticleType(String name, ParticleType particleType) {
        return Registry.register(Registries.PARTICLE_TYPE, new Identifier(Bugaton.MOD_ID, name), particleType);
    }

    public static void registerBugatonParticles() {
        registerParticleType("locust", LOCUST);
        registerParticleType("phantomling", PHANTOMLING);
    }
}
