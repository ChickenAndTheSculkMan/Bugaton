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
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public class NightmaneItem extends Item {
    public NightmaneItem(Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack itemStack = user.getStackInHand(hand);
        itemStack.decrement(1);
        user.heal(2);
        user.addStatusEffect(new StatusEffectInstance(BugatonEffect.FELLDOM, -1));
        StatusEffect i = user.getStatusEffects().iterator().next().getEffectType();
        int a = user.getStatusEffects().iterator().next().getAmplifier();
        int b = user.getStatusEffects().iterator().next().getDuration();
        if (i instanceof FelldomEffect) {
            if (a != 0) {
                user.setStatusEffect(new StatusEffectInstance(i, b, a - 1), user);
            }
        }
        user.playSound(SoundEvents.ENTITY_PHANTOM_AMBIENT, 1.1F, 1.6F);
        return TypedActionResult.consume(itemStack);
    }
}
