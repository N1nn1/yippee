package com.ninni.yippee.client.render.block;

import com.ninni.yippee.block.entity.TacoBellBlockEntity;
import com.ninni.yippee.client.YippeeEntityModelLayers;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.model.ModelData;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.model.ModelPartBuilder;
import net.minecraft.client.model.ModelPartData;
import net.minecraft.client.model.ModelTransform;
import net.minecraft.client.model.TexturedModelData;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;
import net.minecraft.client.texture.SpriteAtlasTexture;
import net.minecraft.client.util.SpriteIdentifier;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.MathHelper;

import static com.ninni.yippee.Yippee.*;

@Environment(EnvType.CLIENT)
public class TacoBellBlockEntityRenderer implements BlockEntityRenderer<TacoBellBlockEntity> {
    public static final Identifier BELL_BODY_TEXTURE = new Identifier(MOD_ID, "entity/taco_bell/taco_bell_body");
    @SuppressWarnings("deprecation") public static final SpriteIdentifier BELL_BODY_SPRITE_IDENTIFIER = new SpriteIdentifier(SpriteAtlasTexture.BLOCK_ATLAS_TEXTURE, BELL_BODY_TEXTURE);
    private static final String BELL_BODY = "bell_body";
    private final ModelPart bellBody;

    public TacoBellBlockEntityRenderer(BlockEntityRendererFactory.Context ctx) {
        ModelPart modelPart = ctx.getLayerModelPart(YippeeEntityModelLayers.TACO_BELL);
        this.bellBody = modelPart.getChild(BELL_BODY);
    }

    public static TexturedModelData getTexturedModelData() {
        ModelData data = new ModelData();
        ModelPartData root = data.getRoot();

        ModelPartData bellBody = root.addChild(
            BELL_BODY,
            ModelPartBuilder.create()
                            .uv(0, 0)
                            .mirrored(true)
                            .cuboid(-3.0F, -5.0F, -3.0F, 6.0F, 5.0F, 6.0F)
                            .uv(18, 3)
                            .mirrored(false)
                            .cuboid(-1.0F, 0.0F, -1.0F, 2.0F, 1.0F, 2.0F)
                            .uv(18, 0)
                            .mirrored(false)
                            .cuboid(-1.0F, -8.0F, -1.0F, 2.0F, 1.0F, 2.0F)
                            .uv(0, 11)
                            .mirrored(false)
                            .cuboid(-5.0F, -7.0F, -5.0F, 10.0F, 2.0F, 10.0F),
            ModelTransform.of(0.0F, 12.0F, 0.0F, 3.1416F, 0.0F, 0.0F)
        );
        return TexturedModelData.of(data, 48, 32);
    }

    @Override
    public void render(TacoBellBlockEntity entity, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay) {
        float g = (float)entity.ringTicks + tickDelta;
        float h = 0.0f;
        float k = 0.0f;
        if (entity.ringing) {
            float l = MathHelper.sin(g / (float)Math.PI) / (4.0f + g / 3.0f);
            if (entity.lastSideHit == Direction.NORTH) h = -l;
            else if (entity.lastSideHit == Direction.SOUTH) h = l;
            else if (entity.lastSideHit == Direction.EAST) k = -l;
            else if (entity.lastSideHit == Direction.WEST) k = l;
        }
        this.bellBody.pitch = h;
        this.bellBody.roll = k;
        matrices.translate(0.5, 0, 0.5);
        VertexConsumer vertexConsumer = BELL_BODY_SPRITE_IDENTIFIER.getVertexConsumer(vertexConsumers, RenderLayer::getEntitySolid);
        this.bellBody.render(matrices, vertexConsumer, light, overlay);
    }
}
