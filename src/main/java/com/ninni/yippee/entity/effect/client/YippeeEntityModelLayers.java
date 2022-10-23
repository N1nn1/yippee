package com.ninni.yippee.entity.effect.client;

import com.ninni.yippee.entity.effect.client.render.block.TacoBellBlockEntityRenderer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.util.Identifier;

import static com.ninni.yippee.Yippee.MOD_ID;

@Environment(EnvType.CLIENT)
public interface YippeeEntityModelLayers {

    EntityModelLayer TACO_BELL = main("taco_bell", TacoBellBlockEntityRenderer::getTexturedModelData);

    private static EntityModelLayer register(String id, String name, EntityModelLayerRegistry.TexturedModelDataProvider provider) {
        EntityModelLayer layer = new EntityModelLayer(new Identifier(MOD_ID, id), name);
        EntityModelLayerRegistry.registerModelLayer(layer, provider);
        return layer;
    }

    private static EntityModelLayer main(String id, EntityModelLayerRegistry.TexturedModelDataProvider provider) { return register(id, "main", provider); }
}

