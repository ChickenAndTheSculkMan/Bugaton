package com.sculkman.bugaton.effect;

import com.sculkman.bugaton.entity.BugatonEntities;
import com.sculkman.bugaton.entity.NightmareEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.attribute.AttributeContainer;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.mob.EvokerEntity;
import net.minecraft.entity.mob.VexEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import org.apache.logging.log4j.core.jmx.Server;

public class NeuronParasiteEffect extends StatusEffect {
    protected NeuronParasiteEffect(StatusEffectCategory category, int color) {
        super(category, color);
    }
    protected final Random random = Random.create();

    public boolean canApplyUpdateEffect(int duration, int amplifier) {
        return true;
    }

    @Override
    public void applyUpdateEffect(LivingEntity entity, int amplifier) {
        super.applyUpdateEffect(entity, amplifier);
        if (entity instanceof PlayerEntity && ((PlayerEntity) entity).getHungerManager().getFoodLevel() < 18 && entity.isAlive()) {
            if (!entity.getWorld().isClient) {
                for (int i = 0; i < 1; i++) {
                    BlockPos blockPos = entity.getBlockPos().add(-2 + random.nextInt(5), 1, -2 + random.nextInt(5));
                    NightmareEntity nightmareEntity = (NightmareEntity) BugatonEntities.NIGHTMARE.create(entity.getWorld());
                    if (nightmareEntity != null) {
                        nightmareEntity.refreshPositionAndAngles(blockPos, 0.0F, 0.0F);
                        nightmareEntity.initialize(((ServerWorld) entity.getWorld()), entity.getWorld().getLocalDifficulty(blockPos), SpawnReason.MOB_SUMMONED, null, null);
                        ((ServerWorld) entity.getWorld()).spawnEntityAndPassengers(nightmareEntity);
                    }
                }
            }
            entity.removeStatusEffect(BugatonEffect.PHANTOM_FEVER);
            entity.damage(entity.getDamageSources().starve(), 10F);
        }
    }

}
