package com.sculkman.bugaton.effect;

import com.sculkman.bugaton.entity.BugatonEntities;
import com.sculkman.bugaton.entity.NightmareEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;

public class FelldomEffect extends StatusEffect {
    protected FelldomEffect(StatusEffectCategory category, int color) {
        super(category, color);
    }
    protected final Random random = Random.create();

    public boolean canApplyUpdateEffect(int duration, int amplifier) {
        return true;
    }

    @Override
    public void applyUpdateEffect(LivingEntity entity, int amplifier) {
        super.applyUpdateEffect(entity, amplifier);
        if (((PlayerEntity) entity).getHealth() < (entity.getMaxHealth() / 3) && entity.isAlive()) {
            entity.kill();
        }
    }

}
