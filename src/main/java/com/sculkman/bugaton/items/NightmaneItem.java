package com.sculkman.bugaton.items;

import com.sculkman.bugaton.effect.BugatonEffect;
import com.sculkman.bugaton.effect.FelldomEffect;
import net.minecraft.client.gui.screen.option.KeybindsScreen;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class NightmaneItem extends Item {
    public NightmaneItem(Settings settings) {
        super(settings);
    }

    @Override
    public ItemStack finishUsing(ItemStack stack, World world, LivingEntity user) {
        if (stack.getNbt() != null && stack.getNbt().getInt("NightmanePotency") != 0) {
            user.heal(3 + stack.getNbt().getInt("NightmanePotency"));
        } else {
            user.heal(3);
        }
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
        user.playSound(SoundEvents.ENTITY_PHANTOM_AMBIENT, 1.1F, 1.6F);
        return super.finishUsing(stack, world, user);
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        super.appendTooltip(stack, world, tooltip, context);
        if (KeybindsScreen.hasShiftDown()) {
            tooltip.add(1, Text.translatable("tooltips.bugaton.nightmane.shifting"));
            tooltip.add(2, Text.translatable("tooltips.bugaton.nightmane.shifting1"));
        } else {
            tooltip.add(Text.translatable("tooltips.bugaton.not_shifting"));
        }
    }
}
