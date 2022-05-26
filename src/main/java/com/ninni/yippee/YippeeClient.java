package com.ninni.yippee;

import com.ninni.yippee.block.YippeeBlocks;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.minecraft.client.render.RenderLayer;

public class YippeeClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        BlockRenderLayerMap brlm = BlockRenderLayerMap.INSTANCE;
        brlm.putBlocks(RenderLayer.getCutout(),
            YippeeBlocks.WHOOPEE_CUSHION
        );
    }
}

