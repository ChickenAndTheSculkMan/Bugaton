package com.sculkman.bugaton.mixin;

import com.sculkman.bugaton.effect.BugatonEffect;
import com.sculkman.bugaton.effect.FelldomEffect;
import com.sculkman.bugaton.particle.BugatonParticles;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.mob.PhantomEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.MilkBucketItem;
import net.minecraft.network.packet.s2c.play.EntityStatusEffectS2CPacket;
import net.minecraft.registry.Registries;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Iterator;
import java.util.Map;

//lobotomized from https://github.com/MattiDragon/no-magic-milk/blob/master/src/main/java/io/github/mattidragon/nomagicmilk/mixin/MilkBucketItemMixin.java
@Mixin(MilkBucketItem.class)
public class MilkBucketItemMixin {
    @Redirect(method = "finishUsing", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/LivingEntity;clearStatusEffects()Z"))
    public boolean injectEffects(LivingEntity entity) {
        Map<StatusEffect, StatusEffectInstance> map = entity.getActiveStatusEffects();

        boolean changed = false;

        for (Iterator<StatusEffect> iterator = map.keySet().iterator(); iterator.hasNext(); ) {
            StatusEffect effect = iterator.next();
            StatusEffectInstance instance = map.get(effect);

            boolean isAllowed = effect instanceof FelldomEffect;

            if (isAllowed) {
                map.put(effect, new StatusEffectInstance(effect,
                        (int) (instance.getDuration()),
                        (int) ((instance.getAmplifier())),
                        instance.isAmbient(),
                        instance.shouldShowParticles(),
                        instance.shouldShowIcon()));
                if (instance.equals(map.get(effect))) changed = true;
                if (entity instanceof ServerPlayerEntity player) {
                    player.networkHandler.sendPacket(new EntityStatusEffectS2CPacket(player.getId(), map.get(effect)));
                }
            } else {
                iterator.remove();
                ((LivingEntityAccess)entity).removeStatusEffect(instance);
                changed = true;
            }
        }

        return changed;
    }
}