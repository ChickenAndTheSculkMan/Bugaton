package com.sculkman.bugaton.entity.goals;

import com.sculkman.bugaton.entity.NightmareEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.ActiveTargetGoal;
import net.minecraft.entity.player.PlayerEntity;
import org.jetbrains.annotations.Nullable;

import java.util.function.Predicate;

public class UnPacifiedActiveTargetGoal<T extends LivingEntity> extends ActiveTargetGoal<T> {
    private final NightmareEntity pacifiable;

    public UnPacifiedActiveTargetGoal(NightmareEntity pacifiable, Class<T> targetClass, boolean checkVisibility, @Nullable Predicate<LivingEntity> targetPredicate) {
        super(pacifiable, targetClass, 10, checkVisibility, true, targetPredicate);
        this.pacifiable = pacifiable;
    }

    @Override
    public boolean canStart() {
        return !this.pacifiable.isNightmarePacified() && super.canStart();
    }

    @Override
    public boolean shouldContinue() {
        return this.targetPredicate != null ? this.targetPredicate.test(this.mob, this.targetEntity) : super.shouldContinue();
    }
}
