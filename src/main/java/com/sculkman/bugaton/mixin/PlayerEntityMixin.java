package com.sculkman.bugaton.mixin;

import com.sculkman.bugaton.effect.BugatonEffect;
import com.sculkman.bugaton.particle.BugatonParticles;
import com.sculkman.bugaton.particle.LocustParticle;
import net.minecraft.client.render.WorldRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.mob.PhantomEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;


@Mixin(PlayerEntity.class)
public abstract class PlayerEntityMixin {
    @Inject(method = "tick", at = @At("HEAD"), cancellable = true)
    public void LocustTick(CallbackInfo ci) {
        PlayerEntity playerEntity = (PlayerEntity) (Object) this;
        BlockPos pos = playerEntity.getBlockPos();
        Biome biome = (Biome)playerEntity.getWorld().getBiome(pos).value();
        Biome.Precipitation precipitation = biome.getPrecipitation(pos);
        World world = playerEntity.getWorld();
        if (precipitation == Biome.Precipitation.NONE && playerEntity.getWorld().isSkyVisible(pos) && (playerEntity.getWorld().isRaining() || playerEntity.getWorld().isThundering())) {
            if (!playerEntity.getWorld().isClient) {
                ServerWorld serverWorld = (ServerWorld) world;
                serverWorld.spawnParticles(BugatonParticles.LOCUST, (double) pos.getX() + 0.5, (double) pos.getY() + 0.9, (double) pos.getZ() + 0.5, 100, 15.0, 8.0, 15.0, 0.2);
            }
        }
        if (playerEntity.getLastAttacker() instanceof PhantomEntity) {
            playerEntity.addStatusEffect(new StatusEffectInstance(BugatonEffect.PHANTOM_FEVER, -1));
        }
    }
}
