package com.sculkman.bugaton.entity;

import com.sculkman.bugaton.entity.goals.DisturbedMeleeAttackGoal;
import com.sculkman.bugaton.entity.goals.NightmareMeleeAttackGoal;
import com.sculkman.bugaton.entity.goals.UnPacifiedActiveTargetGoal;
import net.minecraft.entity.AnimationState;
import net.minecraft.entity.EntityPose;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.EntityView;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class DisturbedEntity extends TameableEntity {
    protected DisturbedEntity(EntityType<? extends TameableEntity> entityType, World world) {
        super(entityType, world);
    }

    private static final TrackedData<Boolean> ATTACKING =
            DataTracker.registerData(DisturbedEntity.class, TrackedDataHandlerRegistry.BOOLEAN);

    public final AnimationState idleAnimationState = new AnimationState();
    private int idleAnimationTimeout = 0;
    public final AnimationState attackingAnimationState = new AnimationState();
    public int attackingAnimationTimeout = 0;
    public final AnimationState attackingAnimationState2 = new AnimationState();

    private void setupAnimationStates() {
        if (this.idleAnimationTimeout <= 0) {
            this.idleAnimationTimeout = this.random.nextInt(30) + 4;
            this.idleAnimationState.start(this.age);
        } else {
            this.idleAnimationTimeout--;
        }
        if(this.isAttacking() && attackingAnimationTimeout <= 0) {
            if (random.nextInt(2) == 1) {
                attackingAnimationTimeout = 22;
                attackingAnimationState.start(this.age);
            } else {
                attackingAnimationTimeout = 22;
                attackingAnimationState2.start(this.age);
            }
        } else {
            --this.attackingAnimationTimeout;
        }

        if(!this.isAttacking()) {
            attackingAnimationState.stop();
            attackingAnimationState2.stop();
        }
    }

    @Override
    public void tick() {
        super.tick();
        setupAnimationStates();
    }

    @Override
    protected void updateLimbs(float posDelta) {
        float f = this.getPose() == EntityPose.STANDING ? Math.min(posDelta * 6.0f, 1.0f) : 0.0f;
        this.limbAnimator.updateLimbs(f, 0.2f);
    }

    @Override
    protected void initGoals() {
        this.goalSelector.add(1, new SwimGoal(this));
        this.goalSelector.add(1, new DisturbedMeleeAttackGoal(this, 1d, true));
        this.goalSelector.add(8, new WanderAroundFarGoal(this, 1.0));
        this.goalSelector.add(10, new LookAtEntityGoal(this, PlayerEntity.class, 8.0F));
        this.goalSelector.add(10, new LookAroundGoal(this));
        this.targetSelector.add(1, new ActiveTargetGoal<>(this, PlayerEntity.class, true));
    }

    public static DefaultAttributeContainer.Builder createDisturbedAttributes() {
        return MobEntity.createMobAttributes()
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.25F)
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 80.0)
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 12.0)
                .add(EntityAttributes.GENERIC_FOLLOW_RANGE, 24.0);

    }

    @Override
    public @Nullable PassiveEntity createChild(ServerWorld world, PassiveEntity entity) {
        return null;
    }

    @Override
    public EntityView method_48926() {
        return null;
    }

    @Override
    protected void initDataTracker() {
        super.initDataTracker();
        this.dataTracker.startTracking(ATTACKING, false);
    }

    @Override
    public boolean isAttacking() {
        return this.dataTracker.get(ATTACKING);
    }
    public void setAttacking(boolean attacking) {
        this.dataTracker.set(ATTACKING, attacking);
    }

}
