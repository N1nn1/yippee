package com.ninni.yippee;

import com.google.common.reflect.Reflection;
import com.ninni.yippee.block.YippeeBlocks;
import com.ninni.yippee.block.entity.YippeeBlockEntityType;
import com.ninni.yippee.entity.effect.client.YippeeEntityModelLayers;
import com.ninni.yippee.entity.effect.client.render.block.TacoBellBlockEntityRenderer;
import com.ninni.yippee.entity.LivingEntityAccess;
import com.ninni.yippee.util.YippeePacketIdentifiers;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.client.rendering.v1.BlockEntityRendererRegistry;
import net.fabricmc.fabric.api.event.client.ClientSpriteRegistryCallback;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.texture.SpriteAtlasTexture;
import net.minecraft.entity.LivingEntity;

import java.util.Optional;

public class YippeeClient implements ClientModInitializer {

    @SuppressWarnings("UnstableApiUsage, deprecation")
    @Override
    public void onInitializeClient() {
        Reflection.initialize(YippeeEntityModelLayers.class);
        ClientSpriteRegistryCallback.event(SpriteAtlasTexture.BLOCK_ATLAS_TEXTURE).register((atlasTexture, registry) -> registry.register(TacoBellBlockEntityRenderer.BELL_BODY_TEXTURE));
        BlockEntityRendererRegistry.register(YippeeBlockEntityType.TACO_BELL, TacoBellBlockEntityRenderer::new);

        BlockRenderLayerMap brlm = BlockRenderLayerMap.INSTANCE;
        brlm.putBlocks(RenderLayer.getCutout(),
            YippeeBlocks.WHOOPEE_CUSHION,
            YippeeBlocks.WEIGHT
        );

        ClientPlayNetworking.registerGlobalReceiver(YippeePacketIdentifiers.FLATTEN, (client, handler, buf, sender) -> Optional.ofNullable(client.world).ifPresent(world -> {
            int id = buf.readInt();
            Optional.ofNullable(client.world.getEntityById(id))
                    .filter(LivingEntity.class::isInstance)
                    .map(LivingEntityAccess.class::cast)
                    .ifPresent(entity -> {
                        boolean flattened = buf.readBoolean();
                        entity.setFlattened(flattened);
                    });
        }));
    }
}
