package com.ninni.yippee.events;

import com.ninni.yippee.Yippee;
import com.ninni.yippee.block.YippeeBlocks;
import com.ninni.yippee.client.render.block.TacoBellBlockEntityRenderer;
import com.ninni.yippee.init.YippeeBlockEntityTypes;
import com.ninni.yippee.init.YippeeModelLayers;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.client.renderer.texture.TextureAtlas;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(modid = Yippee.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientEvents {

    @SubscribeEvent
    public static void onClientSetup(final FMLClientSetupEvent event) {
        ItemBlockRenderTypes.setRenderLayer(YippeeBlocks.WHOOPEE_CUSHION.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(YippeeBlocks.WEIGHT.get(), RenderType.cutout());
    }

    @SubscribeEvent
    public static void registerLayerDefinition(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(YippeeModelLayers.TACO_BELL, TacoBellBlockEntityRenderer::createBodyLayer);
    }

    @SubscribeEvent
    public static void registerEntityRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerBlockEntityRenderer(YippeeBlockEntityTypes.TACO_BELL.get(), TacoBellBlockEntityRenderer::new);
    }

    @SubscribeEvent
    public static void onTexturesStitch(TextureStitchEvent.Pre event) {
        event.addSprite(TacoBellBlockEntityRenderer.BELL_BODY_TEXTURE);
    }

}
