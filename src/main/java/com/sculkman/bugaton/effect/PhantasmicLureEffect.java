package com.sculkman.bugaton.effect;

import com.sculkman.bugaton.particle.BugatonParticles;
import com.sculkman.bugaton.util.BugatonDamageTypes;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.Vec3d;

public class PhantasmicLureEffect extends StatusEffect {
    protected PhantasmicLureEffect(StatusEffectCategory category, int color) {
        super(category, color);
    }

    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier) {
        return true;
    }

    @Override
    public void applyUpdateEffect(LivingEntity entity, int amplifier) {
        super.applyUpdateEffect(entity, amplifier);
        Vec3d pos = entity.getPos();
        if (entity.isOnGround() && !entity.isSubmergedInWater()) {
            entity.damage(new DamageSource(entity.getWorld().getRegistryManager().get(RegistryKeys.DAMAGE_TYPE).entryOf(BugatonDamageTypes.SWARMING_DAMAGE)), 1 + amplifier);
            entity.playSound(SoundEvents.ENTITY_PHANTOM_BITE, 0.3f, 1.8f);
            if (!entity.getWorld().isClient) {
                ServerWorld serverWorld = (ServerWorld) entity.getWorld();
                serverWorld.spawnParticles(BugatonParticles.PHANTOMLING, (double) pos.getX() - 0.6, (double) pos.getY() + 0.2, (double) pos.getZ(), 50, 2, 0.1, 2, 0.0);
            }
        }
    }
}
