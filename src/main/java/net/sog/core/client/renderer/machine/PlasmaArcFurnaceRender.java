package net.sog.core.client.renderer.machine;

import com.gregtechceu.gtceu.api.machine.multiblock.WorkableElectricMultiblockMachine;
import com.gregtechceu.gtceu.client.renderer.machine.DynamicRender;
import com.gregtechceu.gtceu.client.renderer.machine.DynamicRenderType;
import com.gregtechceu.gtceu.client.util.ModelUtils;

import net.minecraft.client.renderer.LightTexture;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.RandomSource;
import net.minecraft.world.phys.AABB;
import net.minecraftforge.client.model.data.ModelData;
import net.sog.core.sogcore;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.serialization.Codec;
import org.joml.Quaternionf;

import java.util.List;

public class PlasmaArcFurnaceRender extends DynamicRender<WorkableElectricMultiblockMachine, PlasmaArcFurnaceRender> {

    public static final PlasmaArcFurnaceRender INSTANCE = new PlasmaArcFurnaceRender();
    public static final Codec<PlasmaArcFurnaceRender> CODEC = Codec.unit(PlasmaArcFurnaceRender.INSTANCE);
    public static final DynamicRenderType<WorkableElectricMultiblockMachine, PlasmaArcFurnaceRender> TYPE = new DynamicRenderType<>(
            PlasmaArcFurnaceRender.CODEC);

    // CHANGE: Load two different models
    public static final ResourceLocation SPHERE_MODEL_RL = sogcore.id("obj/blue_star"); // Or a new model for the
                                                                                            // sphere
    public static final ResourceLocation RINGS_MODEL_RL = sogcore.id("obj/rings");

    private static BakedModel sphereModel;
    private static BakedModel ringsModel;
    private static final RandomSource random = RandomSource.create();

    // --- CONSTANTS ADDED FOR ROTATION CONTROL ---
    private static final float ROTATION_SPEED = 0.025F; // Base speed of movement
    private static final float MAX_ROTATION_ANGLE = 30.0F; // Maximum amplitude of the main movement
    private static final float RINGS_ROTATION_SPEED = 0.5F; // New constant for rings rotation speed

    private PlasmaArcFurnaceRender() {
        ModelUtils.registerBakeEventListener(true, event -> {
            sphereModel = event.getModels().get(SPHERE_MODEL_RL);
            ringsModel = event.getModels().get(RINGS_MODEL_RL);
        });
    }

    @Override
    public DynamicRenderType<WorkableElectricMultiblockMachine, PlasmaArcFurnaceRender> getType() {
        return TYPE;
    }

    @Override
    public void render(WorkableElectricMultiblockMachine machine, float partialTick, PoseStack poseStack,
                       MultiBufferSource buffer, int packedLight, int packedOverlay) {
        if (!machine.isFormed() || !machine.getRecipeLogic().isActive()) {
            return;
        }

        float tick = (machine.getOffsetTimer() + partialTick);
        // Center of the multiblock
        double x = 0.5;
        double y = 2.5; // a little above the controller block
        double z = 0.5;

        // Offset in front of the facing direction by 5 blocks
        switch (machine.getFrontFacing()) {
            case NORTH -> z -= 20.0;
            case SOUTH -> z += 20.0;
            case WEST -> x -= 20.0;
            case EAST -> x += 20.0;
        }

        poseStack.pushPose();
        poseStack.translate(x, y, z);

        // Render the sphere and rings
        renderCelestialObject(tick, poseStack, buffer, packedOverlay);

        poseStack.popPose();
    }

    private void renderCelestialObject(float tick, PoseStack poseStack, MultiBufferSource buffer, int packedOverlay) {
        if (sphereModel == null || ringsModel == null) return;
        poseStack.pushPose();

        // Overall rotation of the entire object (sphere + rings)
        float angleY = MAX_ROTATION_ANGLE * (float) Math.sin(tick * ROTATION_SPEED);
        float angleX = (MAX_ROTATION_ANGLE / 2.0f) * (float) Math.sin(tick * ROTATION_SPEED * 2.0f);
        poseStack.mulPose(new Quaternionf().fromAxisAngleDeg(0.0F, 1.0F, 0.0F, angleY));
        poseStack.mulPose(new Quaternionf().fromAxisAngleDeg(1.0F, 0.0F, 0.0F, angleX));

        // 1. Renders the main star (internal, SOLID and SMALLER)
        poseStack.pushPose();
        // CHANGED: Scale reduced to 0.40F
        poseStack.scale(0.1F, 0.1F, 0.1F);
        // CHANGED: Using solidBlockSheet to ensure it is opaque
        renderModel(poseStack, buffer.getBuffer(Sheets.solidBlockSheet()), sphereModel, 1.0F, 1.0F, 1.0F, 1.0f,
                LightTexture.FULL_BRIGHT, packedOverlay);
        poseStack.popPose();

        // 2. Renders the glow aura (external, SMALLER and semi-transparent)
        poseStack.pushPose();

        // ADDED: Rotate the rings on a different axis and with a different speed
        float ringsAngle = (tick * RINGS_ROTATION_SPEED) % 360;
        poseStack.mulPose(new Quaternionf().fromAxisAngleDeg(0.0F, 1.0F, 0.0F, ringsAngle));

        poseStack.scale(6.4F, 6.4F, 6.4F); // Adjust scale as needed
        renderModel(poseStack, buffer.getBuffer(Sheets.solidBlockSheet()), ringsModel, 1.0F, 1.0F, 1.0F, 0.6F,
                LightTexture.FULL_BRIGHT, packedOverlay);
        poseStack.popPose();

        poseStack.popPose();
    }

    private void renderModel(PoseStack poseStack, VertexConsumer consumer, BakedModel model, float r, float g, float b,
                             float a, int light, int overlay) {
        if (model == null) return;
        PoseStack.Pose pose = poseStack.last();
        List<BakedQuad> quads = model.getQuads(null, null, random, ModelData.EMPTY, null);
        for (BakedQuad quad : quads) {
            consumer.putBulkData(pose, quad, r, g, b, a, light, overlay, false);
        }
    }

    @Override
    public int getViewDistance() {
        return 256;
    }

    @Override
    public boolean shouldRenderOffScreen(WorkableElectricMultiblockMachine machine) {
        return true;
    }

    @Override
    public AABB getRenderBoundingBox(WorkableElectricMultiblockMachine machine) {
        return new AABB(machine.getPos()).inflate(getViewDistance());
    }
}
