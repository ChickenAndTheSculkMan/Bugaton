package com.sculkman.bugaton.entity.models;

import com.sculkman.bugaton.entity.NightmareEntity;
import com.sculkman.bugaton.entity.animations.NightmareAnim;
import net.minecraft.client.model.*;
import net.minecraft.client.render.entity.model.SinglePartEntityModel;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

// Made with Blockbench 5.1.2
// Exported for Minecraft version 1.17+ for Yarn
// Paste this class into your mod and generate all required imports
public class NightmareModel<T extends NightmareEntity> extends SinglePartEntityModel<T> {
	private final ModelPart root;
	private final ModelPart head;
	public NightmareModel(ModelPart root) {
		this.root = root.getChild("root");
		this.head = this.root.getChild("head");
	}
	public static TexturedModelData getTexturedModelData() {
		ModelData modelData = new ModelData();
		ModelPartData modelPartData = modelData.getRoot();
		ModelPartData root = modelPartData.addChild("root", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 24.0F, 0.0F));

		ModelPartData leg_2 = root.addChild("leg_2", ModelPartBuilder.create(), ModelTransform.pivot(5.0F, -4.0F, 5.0F));

		ModelPartData cube_r1 = leg_2.addChild("cube_r1", ModelPartBuilder.create().uv(8, 43).cuboid(-0.8939F, 0.6739F, -1.3787F, 2.0F, 4.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(-1.0F, 0.0F, 0.0F, 0.7777F, -0.1231F, -0.6614F));

		ModelPartData body_1 = root.addChild("body_1", ModelPartBuilder.create().uv(0, 0).cuboid(-5.0F, -9.0F, -6.0F, 10.0F, 7.0F, 12.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

		ModelPartData body_2 = root.addChild("body_2", ModelPartBuilder.create().uv(40, 19).cuboid(-4.0F, -8.0F, 6.0F, 8.0F, 5.0F, 5.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

		ModelPartData head = root.addChild("head", ModelPartBuilder.create().uv(40, 29).cuboid(-3.0F, -8.0F, -10.0F, 6.0F, 5.0F, 4.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

		ModelPartData pincer_1 = head.addChild("pincer_1", ModelPartBuilder.create().uv(0, 31).cuboid(-8.0F, -5.0F, -16.0F, 8.0F, 0.0F, 12.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

		ModelPartData pincer_2 = head.addChild("pincer_2", ModelPartBuilder.create().uv(0, 19).cuboid(0.0F, -5.0F, -16.0F, 8.0F, 0.0F, 12.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

		ModelPartData leg_1 = root.addChild("leg_1", ModelPartBuilder.create(), ModelTransform.pivot(-5.0F, -4.0F, 5.0F));

		ModelPartData cube_r2 = leg_1.addChild("cube_r2", ModelPartBuilder.create().uv(16, 43).cuboid(-1.0F, -4.0F, -1.0F, 2.0F, 4.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(-1.0F, 3.0F, 3.0F, 0.7777F, 0.1231F, 0.6614F));

		ModelPartData leg_3 = root.addChild("leg_3", ModelPartBuilder.create(), ModelTransform.pivot(-5.0F, -5.0F, -5.0F));

		ModelPartData cube_r3 = leg_3.addChild("cube_r3", ModelPartBuilder.create().uv(40, 38).cuboid(-1.0F, -7.0F, -1.0F, 2.0F, 5.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(-3.0F, 5.0F, -5.0F, -0.7777F, -0.1231F, 0.6614F));

		ModelPartData leg_4 = root.addChild("leg_4", ModelPartBuilder.create(), ModelTransform.pivot(5.0F, -5.0F, -5.0F));

		ModelPartData cube_r4 = leg_4.addChild("cube_r4", ModelPartBuilder.create().uv(0, 43).cuboid(-1.0F, -7.0F, -1.0F, 2.0F, 5.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(3.0F, 5.0F, -5.0F, -0.7777F, 0.1231F, -0.6614F));
		return TexturedModelData.of(modelData, 128, 128);
	}
	public void setAngles(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.getPart().traverse().forEach(ModelPart::resetTransform);
		this.setHeadAngles(netHeadYaw, headPitch);

		if (entity.isScuttering()) {
			this.animateMovement(NightmareAnim.walk, limbSwing, limbSwingAmount, 2f, 2.5f);
		} else {
			this.animateMovement(NightmareAnim.walk_slow, limbSwing, limbSwingAmount, 2f, 2.5f);
		}
		this.updateAnimation(entity.dancingAnimationState, NightmareAnim.dance, ageInTicks, 1f);
	}

	private void setHeadAngles(float headYaw, float headPitch) {
		headYaw = MathHelper.clamp(headYaw, -10.00f, 10.0f);
		headPitch = MathHelper.clamp(headPitch, -15.00f, 5.0f);

		this.head.yaw = headYaw * 0.017453292F;
		this.head.pitch = headPitch * 0.017453292F;
	}

	@Override
	public ModelPart getPart() {
		return root;
	}
}