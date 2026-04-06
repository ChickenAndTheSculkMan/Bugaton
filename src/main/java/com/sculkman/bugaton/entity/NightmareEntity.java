package com.sculkman.bugaton.entity;

import com.sculkman.bugaton.entity.goals.NightmareMeleeAttackGoal;
import com.sculkman.bugaton.entity.goals.UnPacifiedActiveTargetGoal;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.mob.AbstractSkeletonEntity;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.mob.PathAwareEntity;
import net.minecraft.entity.passive.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.DyeItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Arm;
import net.minecraft.util.DyeColor;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.EntityView;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

public class NightmareEntity extends TameableEntity {
    protected NightmareEntity(EntityType<? extends TameableEntity> entityType, World world) {
        super(entityType, world);
    }

    private boolean isPacified;
    private boolean songPlaying;
    @Nullable
    private BlockPos songSource;
    private static final TrackedData<Integer> COLLAR_COLOR = DataTracker.registerData(NightmareEntity.class, TrackedDataHandlerRegistry.INTEGER);

    public final AnimationState dancingAnimationState = new AnimationState();
    private int dancingAnimationTimeout = 0;

    private static final TrackedData<Boolean> SCUTTERING =
            DataTracker.registerData(NightmareEntity.class, TrackedDataHandlerRegistry.BOOLEAN);
    private static final TrackedData<Boolean> PACIFIED =
            DataTracker.registerData(NightmareEntity.class, TrackedDataHandlerRegistry.BOOLEAN);

    @Override
    protected void initGoals() {
        this.goalSelector.add(1, new SwimGoal(this));
        this.goalSelector.add(5, new NightmareMeleeAttackGoal(this, 8.0F, false));
        this.goalSelector.add(8, new WanderAroundFarGoal(this, 1.0));
        this.goalSelector.add(10, new LookAtEntityGoal(this, PlayerEntity.class, 8.0F));
        this.goalSelector.add(10, new LookAroundGoal(this));
        this.targetSelector.add(4, new UnPacifiedActiveTargetGoal<>(this, PlayerEntity.class, true, null));
    }

    public static DefaultAttributeContainer.Builder createNightmareAttributes() {
        return MobEntity.createMobAttributes()
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.2F)
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 16.0)
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 4.0)
                .add(EntityAttributes.GENERIC_FOLLOW_RANGE, 24.0);

    }

    private void setupAnimationStates() {
        if (isSongPlaying() && this.dancingAnimationTimeout <= 0) {
            this.dancingAnimationTimeout = 5;
            this.dancingAnimationState.start(this.age);
        } else if (!isSongPlaying()) {
            this.dancingAnimationTimeout--;
            this.dancingAnimationState.stop();
        } else {
            this.dancingAnimationTimeout--;
        }
    }

    @Override
    public void tick() {
        super.tick();
        if (this.random.nextInt(12000) == 1 && this.isPacified) {
            this.dropItem(Items.PHANTOM_MEMBRANE);
            this.playSound(SoundEvents.ENTITY_PHANTOM_AMBIENT, 0.6F, 2.0F);
        }
        setupAnimationStates();
    }

    @Override
    public void setNearbySongPlaying(BlockPos songPosition, boolean playing) {
        this.songSource = songPosition;
        this.songPlaying = playing;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource source) {
        return SoundEvents.ENTITY_PHANTOM_HURT;
    }

    @Override
    public SoundCategory getSoundCategory() {
        return SoundCategory.HOSTILE;
    }

    public boolean isSongPlaying() {
        return this.songPlaying;
    }

    @Override
    public @Nullable PassiveEntity createChild(ServerWorld world, PassiveEntity entity) {
        return null;
    }

    @Override
    protected void updateLimbs(float posDelta) {
        float f = this.getPose() == EntityPose.STANDING ? Math.min(posDelta * 6.0f, 1.0f) : 0.0f;
        this.limbAnimator.updateLimbs(f, 0.2f);
    }

    @Override
    protected void initDataTracker() {
        super.initDataTracker();
        this.dataTracker.startTracking(SCUTTERING, false);
        this.dataTracker.startTracking(COLLAR_COLOR, DyeColor.RED.getId());
        this.dataTracker.startTracking(PACIFIED, false);
    }

    @Override
    public void onAttacking(Entity target) {
        super.onAttacking(target);
    }

    public boolean isScuttering() {
        return this.dataTracker.get(SCUTTERING);
    }

    public void setScuttering(boolean scuttering) {
        this.dataTracker.set(SCUTTERING, scuttering);
    }

    public DyeColor getCollarColor() {
        return DyeColor.byId(this.dataTracker.get(COLLAR_COLOR));
    }

    public void setCollarColor(DyeColor color) {
        this.dataTracker.set(COLLAR_COLOR, color.getId());
    }

    @Override
    protected void playStepSound(BlockPos pos, BlockState state) {
        this.playSound(SoundEvents.ENTITY_PIG_STEP, 0.4F, 1.2F);
    }

    @Override
    protected @Nullable SoundEvent getDeathSound() {
        return SoundEvents.ENTITY_PHANTOM_DEATH;
    }

    @Override
    public void playAmbientSound() {
        this.playSound(SoundEvents.ENTITY_PHANTOM_AMBIENT, 1.2F, 1.6F);
    }

    @Override
    public EntityGroup getGroup() {
        return EntityGroup.UNDEAD;
    }

    @Override
    public ActionResult interactMob(PlayerEntity player, Hand hand) {
        ItemStack itemStack = player.getStackInHand(hand);
        Item item = itemStack.getItem();

        if (this.getWorld().isClient) {
            boolean bl = this.isPacified && itemStack.isOf(Items.PHANTOM_MEMBRANE);
            return bl ? ActionResult.CONSUME : ActionResult.PASS;
        }
        if (!this.isNightmarePacified() && itemStack.isOf(Items.PHANTOM_MEMBRANE)) {
            if (!player.getAbilities().creativeMode) {
                itemStack.decrement(1);
            }
            this.setPacified(true);
            this.getWorld().sendEntityStatus(this, EntityStatuses.ADD_POSITIVE_PLAYER_REACTION_PARTICLES);
        }
        if (item instanceof DyeItem dyeItem && this.isNightmarePacified()) {
            DyeColor dyeColor = dyeItem.getColor();
            if (dyeColor != this.getCollarColor()) {
                this.setCollarColor(dyeColor);
                if (!player.getAbilities().creativeMode) {
                    itemStack.decrement(1);
                }

                return ActionResult.SUCCESS;
            }
        }
        return super.interactMob(player, hand);
    }


    @Override
    public void writeCustomDataToNbt(NbtCompound nbt) {
        super.writeCustomDataToNbt(nbt);
        nbt.putBoolean("IsPacified", this.isPacified);
        nbt.putByte("CollarColor", (byte)this.getCollarColor().getId());
    }

    @Override
    public void readCustomDataFromNbt(NbtCompound nbt) {
        super.readCustomDataFromNbt(nbt);
        if (nbt.contains("CollarColor", NbtElement.NUMBER_TYPE)) {
            this.setCollarColor(DyeColor.byId(nbt.getInt("CollarColor")));
        }
        this.isPacified = nbt.getBoolean("IsPacified");
        this.setPacified(this.isPacified);
    }

    @Override
    public EntityView method_48926() {
        return null;
    }

    public boolean isNightmarePacified() {
        return this.dataTracker.get(PACIFIED);
    }

    public void setPacified(boolean pacified) {
        this.isPacified = pacified;
        this.dataTracker.set(PACIFIED, pacified);
    }
}
