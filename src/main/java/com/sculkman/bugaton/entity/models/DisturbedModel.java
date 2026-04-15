package com.sculkman.bugaton.entity.models;

import com.sculkman.bugaton.entity.DisturbedEntity;
import com.sculkman.bugaton.entity.NightmareEntity;
import com.sculkman.bugaton.entity.animations.DisturbedAnim;
import com.sculkman.bugaton.entity.animations.NightmareAnim;
import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.render.entity.model.SinglePartEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

// Made with Blockbench 5.1.3
// Exported for Minecraft version 1.17+ for Yarn
// Paste this class into your mod and generate all required imports
public class DisturbedModel<T extends DisturbedEntity> extends SinglePartEntityModel<T> {
	private final ModelPart root;
	private final ModelPart head;
	public DisturbedModel(ModelPart root) {
		this.root = root.getChild("root");
		this.head = this.root.getChild("head");
	}
	public static TexturedModelData getTexturedModelData() {
		ModelData modelData = new ModelData();
		ModelPartData modelPartData = modelData.getRoot();
		ModelPartData root = modelPartData.addChild("root", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 24.0F, 0.0F));

		ModelPartData body = root.addChild("body", ModelPartBuilder.create().uv(0, 0).cuboid(-5.0F, 0.0F, -3.0F, 10.0F, 15.0F, 6.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, -31.0F, 0.0F));

		ModelPartData leg_1 = root.addChild("leg_1", ModelPartBuilder.create().uv(42, 30).cuboid(-2.0F, 0.0F, -2.0F, 4.0F, 16.0F, 4.0F, new Dilation(0.0F)), ModelTransform.pivot(-2.0F, -16.0F, 0.0F));

		ModelPartData leg_2 = root.addChild("leg_2", ModelPartBuilder.create().uv(0, 50).cuboid(-2.0F, 0.0F, -2.0F, 4.0F, 16.0F, 4.0F, new Dilation(0.0F)), ModelTransform.pivot(2.0F, -16.0F, 0.0F));

		ModelPartData head = root.addChild("head", ModelPartBuilder.create().uv(0, 21).cuboid(-4.0F, -25.0F, -2.0F, 8.0F, 25.0F, 4.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, -31.0F, 0.0F));

		ModelPartData arm_2 = root.addChild("arm_2", ModelPartBuilder.create().uv(42, 0).cuboid(-2.0F, 0.0F, -2.0F, 4.0F, 25.0F, 5.0F, new Dilation(0.0F)), ModelTransform.pivot(-6.0F, -30.0F, 1.0F));

		ModelPartData arm_1 = root.addChild("arm_1", ModelPartBuilder.create().uv(24, 21).cuboid(-2.0F, 0.0F, -2.0F, 4.0F, 25.0F, 5.0F, new Dilation(0.0F)), ModelTransform.pivot(6.0F, -30.0F, 1.0F));
		return TexturedModelData.of(modelData, 128, 128);
	}
	@Override
	public void setAngles(DisturbedEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.getPart().traverse().forEach(ModelPart::resetTransform);
		this.setHeadAngles(netHeadYaw, headPitch);

		this.animateMovement(DisturbedAnim.walk, limbSwing, limbSwingAmount, 2f, 2.5f);
		this.updateAnimation(entity.idleAnimationState, DisturbedAnim.idle1, ageInTicks, 1f);
		this.updateAnimation(entity.attackingAnimationState, DisturbedAnim.swing1, ageInTicks, 1f);
		this.updateAnimation(entity.attackingAnimationState2, DisturbedAnim.swing2, ageInTicks, 1f);
	}

	private void setHeadAngles(float headYaw, float headPitch) {
		headYaw = MathHelper.clamp(headYaw, -10.00f, 10.0f);
		headPitch = MathHelper.clamp(headPitch, -15.00f, 5.0f);

		this.head.yaw = headYaw * 0.017453292F;
		this.head.pitch = headPitch * 0.017453292F;
	}

	@Override
	public void render(MatrixStack matrices, VertexConsumer vertexConsumer, int light, int overlay, float red, float green, float blue, float alpha) {
		root.render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha);
	}

	@Override
	public ModelPart getPart() {
		return root;
	}
}