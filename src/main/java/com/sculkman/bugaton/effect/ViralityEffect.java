package com.sculkman.bugaton.effect;

import com.sculkman.bugaton.Bugaton;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.util.math.Box;

import java.util.*;
import java.util.function.Consumer;

public class ViralityEffect extends StatusEffect {
    protected ViralityEffect(StatusEffectCategory category, int color) {
        super(category, color);
    }

    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier) {
        return true;
    }

    @Override
    public void applyUpdateEffect(LivingEntity entity, int amplifier) {
        super.applyUpdateEffect(entity, amplifier);
        if (!entity.getWorld().isClient) {
            World world = entity.getWorld();
            BlockPos pos = entity.getBlockPos();
            Box box = (new Box(pos)).expand(3).stretch((double) 0.0F, (double) world.getHeight(), (double) 0.0F);
            List<Entity> list = world.getOtherEntities(entity, box);
            for (Entity target : list) {
                    if (target instanceof LivingEntity && target != entity && !((LivingEntity) target).hasStatusEffect(BugatonEffect.VIRALITY)) {
                        Collection<StatusEffectInstance> i = entity.getStatusEffects();
                        ListIterator<StatusEffectInstance> ia = i.stream().toList().listIterator();
                        for (int q = 1; q < 4 + amplifier; q++) {
                            if (ia.hasNext()) {
                                ((LivingEntity) target).addStatusEffect(ia.next());
                            }
                        }
                    }
            }
        }
    }
}


