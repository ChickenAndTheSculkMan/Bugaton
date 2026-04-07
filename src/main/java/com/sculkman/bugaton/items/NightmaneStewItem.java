package com.sculkman.bugaton.items;

import com.sculkman.bugaton.effect.BugatonEffect;
import com.sculkman.bugaton.effect.FelldomEffect;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundEvents;
import net.minecraft.world.World;

public class NightmaneStewItem extends Item {
    public NightmaneStewItem(Settings settings) {
        super(settings);
    }
    @Override
    public ItemStack finishUsing(ItemStack stack, World world, LivingEntity user) {
        user.heal(8);
        if (!user.getWorld().isClient && user instanceof PlayerEntity && user.isAlive() && user.hasStatusEffect(BugatonEffect.FELLDOM)) {
            StatusEffect i = user.getStatusEffects().iterator().next().getEffectType();
            int a = user.getStatusEffects().iterator().next().getAmplifier();
            int b = user.getStatusEffects().iterator().next().getDuration();
            if (i instanceof FelldomEffect) {
                if (a != 0) {
                    user.setStatusEffect(new StatusEffectInstance(i, b, a - 1), user);
                }
            }
        }
        user.playSound(SoundEvents.ENTITY_PHANTOM_AMBIENT, 1.1F, 0.8F);
        return super.finishUsing(stack, world, user);
    }
}
