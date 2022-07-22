package com.ninni.yippee.client.render.block;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.ninni.yippee.Yippee;
import com.ninni.yippee.block.blockentities.TacoBellBlockEntity;
import com.ninni.yippee.init.YippeeModelLayers;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.texture.TextureAtlas;
import net.minecraft.client.resources.model.Material;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class TacoBellBlockEntityRenderer implements BlockEntityRenderer<TacoBellBlockEntity> {
    public static final ResourceLocation BELL_BODY_TEXTURE = new ResourceLocation(Yippee.MOD_ID, "entity/taco_bell/taco_bell_body");
    @SuppressWarnings("deprecation")
    public static final Material BELL_BODY_SPRITE_IDENTIFIER = new Material(TextureAtlas.LOCATION_BLOCKS, BELL_BODY_TEXTURE);
    private static final String BELL_BODY = "bell_body";
    private final ModelPart bellBody;

    public TacoBellBlockEntityRenderer(BlockEntityRendererProvider.Context ctx) {
        ModelPart modelPart = ctx.bakeLayer(YippeeModelLayers.TACO_BELL);
        this.bellBody = modelPart.getChild(BELL_BODY);
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition data = new MeshDefinition();
        PartDefinition root = data.getRoot();

        PartDefinition bellBody = root.addOrReplaceChild(
                BELL_BODY,
                CubeListBuilder.create()
                        .texOffs(0, 0)
                        .mirror(true)
                        .addBox(-3.0F, -5.0F, -3.0F, 6.0F, 5.0F, 6.0F)
                        .texOffs(18, 3)
                        .mirror(false)
                        .addBox(-1.0F, 0.0F, -1.0F, 2.0F, 1.0F, 2.0F)
                        .texOffs(18, 0)
                        .mirror(false)
                        .addBox(-1.0F, -8.0F, -1.0F, 2.0F, 1.0F, 2.0F)
                        .texOffs(0, 11)
                        .mirror(false)
                        .addBox(-5.0F, -7.0F, -5.0F, 10.0F, 2.0F, 10.0F),
                PartPose.offsetAndRotation(0.0F, 12.0F, 0.0F, 3.1416F, 0.0F, 0.0F)
        );
        return LayerDefinition.create(data, 48, 32);
    }

    @Override
    public void render(TacoBellBlockEntity entity, float tickDelta, PoseStack matrices, MultiBufferSource vertexConsumers, int light, int overlay) {
        float g = (float)entity.ringTicks + tickDelta;
        float h = 0.0f;
        float k = 0.0f;
        if (entity.ringing) {
            float l = Mth.sin(g / (float)Math.PI) / (4.0f + g / 3.0f);
            if (entity.lastSideHit == Direction.NORTH) h = -l;
            else if (entity.lastSideHit == Direction.SOUTH) h = l;
            else if (entity.lastSideHit == Direction.EAST) k = -l;
            else if (entity.lastSideHit == Direction.WEST) k = l;
        }
        this.bellBody.xRot = h;
        this.bellBody.zRot = k;
        matrices.translate(0.5, 0, 0.5);
        VertexConsumer vertexConsumer = BELL_BODY_SPRITE_IDENTIFIER.buffer(vertexConsumers, RenderType::entitySolid);
        this.bellBody.render(matrices, vertexConsumer, light, overlay);
    }
}