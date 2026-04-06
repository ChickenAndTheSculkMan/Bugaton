package com.sculkman.bugaton.particle;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.particle.*;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.particle.DefaultParticleType;
import net.minecraft.util.math.random.Random;

public class LocustParticle extends AnimatedParticle {
    protected LocustParticle(ClientWorld world, double x, double y, double z, SpriteProvider spriteProvider, float upwardsAcceleration) {
        super(world, x, y, z, spriteProvider, upwardsAcceleration);
    }
    protected LocustParticle(ClientWorld world, double x, double y, double z, float scaleMultiplier, SpriteProvider spriteProvider) {
        super(world, x, y, z, spriteProvider, 0);
        this.red = 1.0F;
        this.green = 1.0F;
        this.blue = 1.0F;
        this.scale *= 0.75F;
        this.maxAge = 20;
        this.setSpriteForAge(spriteProvider);
    }

    @Override
    public void tick() {
        super.tick();
        this.setVelocity(1.0, 0.0, 0.2);
    }

    public ParticleTextureSheet getType() {
        return ParticleTextureSheet.PARTICLE_SHEET_TRANSLUCENT;
    }

    @Environment(EnvType.CLIENT)
    public static class Factory implements ParticleFactory<DefaultParticleType> {
        private final SpriteProvider spriteProvider;

        public Factory(SpriteProvider spriteProvider) {
            this.spriteProvider = spriteProvider;
        }

        public Particle createParticle(DefaultParticleType defaultParticleType, ClientWorld clientWorld, double d, double e, double f, double g, double h, double i) {
            return new LocustParticle(clientWorld, d, e, f, 1.0F, this.spriteProvider);
        }
    }
}
