package com.sculkman.bugaton.mixin;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

//snatched from https://github.com/MattiDragon/no-magic-milk/blob/master/src/main/java/io/github/mattidragon/nomagicmilk/
@Mixin(LivingEntity.class)
public interface LivingEntityAccess {
    @Invoker("onStatusEffectRemoved")
    void removeStatusEffect(StatusEffectInstance effect);
}