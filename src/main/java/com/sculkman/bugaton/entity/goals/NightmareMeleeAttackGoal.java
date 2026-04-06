package com.sculkman.bugaton.entity.goals;

import com.sculkman.bugaton.entity.NightmareEntity;
import net.minecraft.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.entity.mob.PathAwareEntity;

public class NightmareMeleeAttackGoal extends MeleeAttackGoal {
    public NightmareMeleeAttackGoal(NightmareEntity mob, double speed, boolean pauseWhenMobIdle) {
        super(mob, speed, pauseWhenMobIdle);
    }

    @Override
    public void start() {
        super.start();
        ((NightmareEntity) mob).setScuttering(true);
    }

    @Override
    public void stop() {
        super.stop();
        ((NightmareEntity) mob).setScuttering(false);
    }
}
