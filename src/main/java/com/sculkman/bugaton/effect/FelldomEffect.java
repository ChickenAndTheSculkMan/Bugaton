package com.sculkman.bugaton.effect;

import com.sculkman.bugaton.entity.BugatonEntities;
import com.sculkman.bugaton.entity.NightmareEntity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
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
        int a = (entity).getStatusEffects().iterator().next().getAmplifier();
        if (amplifier != 2 && random.nextInt(12000) == 1) {
            entity.setStatusEffect(new StatusEffectInstance(BugatonEffect.FELLDOM, -1, a + 1), entity);
        } else if (amplifier == 2) {
            // entity.timeUntilRegen = 2;
            entity.damage(entity.getDamageSources().dryOut(), 1F);
        }
        ItemStack stack = entity.getStackInHand(entity.getActiveHand());
        if (stack.isFood() && !(stack.hasNbt() && stack.getNbt().getBoolean("LacedByNightmane"))) {
            stack.getOrCreateNbt().putBoolean("LacedByNightmane", true);
        }
    }

}
