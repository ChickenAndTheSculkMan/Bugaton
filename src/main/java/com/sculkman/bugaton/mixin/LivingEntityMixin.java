package com.sculkman.bugaton.mixin;

import com.sculkman.bugaton.entity.BugatonEntities;
import com.sculkman.bugaton.entity.NightmareEntity;
import com.sculkman.bugaton.items.BugatonItems;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.passive.MerchantEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import javax.management.MXBean;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin {
    @Shadow public abstract ItemStack getMainHandStack();

    @Shadow @Nullable protected PlayerEntity attackingPlayer;

    @Inject(method = "takeShieldHit", at = @At("HEAD"))
    public void PhantasmKill(LivingEntity attacker, CallbackInfo ci) {
        ItemStack stack = this.getMainHandStack();
        if (stack.isOf(BugatonItems.PHANTASM)) {
                attacker.damage(attacker.getDamageSources().sting(this.attackingPlayer), 15);
                stack.decrement(1);
                if (!attacker.getWorld().isClient) {
                    for (int i = 0; i < 2; i++) {
                        BlockPos blockPos = attacker.getBlockPos().add(0, 1, 0);
                        NightmareEntity nightmareEntity = (NightmareEntity) BugatonEntities.NIGHTMARE.create(attacker.getWorld());
                        if (nightmareEntity != null) {
                            nightmareEntity.refreshPositionAndAngles(blockPos, 0.0F, 0.0F);
                            nightmareEntity.initialize(((ServerWorld) attacker.getWorld()), attacker.getWorld().getLocalDifficulty(blockPos), SpawnReason.MOB_SUMMONED, null, null);
                            ((ServerWorld) attacker.getWorld()).spawnEntityAndPassengers(nightmareEntity);
                        }
                    }
                    Vec3d pos = attacker.getPos();
                    ((ServerWorld)attacker.getWorld()).spawnParticles(ParticleTypes.WHITE_ASH, (double) pos.getX(), (double) pos.getY() + 0.9, (double) pos.getZ(), 50, 1.0, 1.0, 1.0, 0.0);
                }
                attacker.playSound(SoundEvents.ENTITY_PHANTOM_AMBIENT, 1.3F, 0.8F);
        }
    }
}
