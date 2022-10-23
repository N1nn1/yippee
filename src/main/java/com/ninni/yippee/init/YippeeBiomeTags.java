package com.ninni.yippee.init;

import com.ninni.yippee.Yippee;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.biome.Biome;

public class YippeeBiomeTags {

    public static final TagKey<Biome> TREES_MYSTICAL_GENERATES = TagKey.create(Registry.BIOME_REGISTRY, new ResourceLocation(Yippee.MOD_ID, "trees_mystical_generates"));

}
