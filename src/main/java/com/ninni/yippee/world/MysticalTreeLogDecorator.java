package com.ninni.yippee.world;

import com.mojang.serialization.Codec;
import com.ninni.yippee.block.YippeeBlocks;
import net.minecraft.block.HorizontalFacingBlock;
import net.minecraft.util.math.Direction;
import net.minecraft.world.gen.treedecorator.TreeDecorator;
import net.minecraft.world.gen.treedecorator.TreeDecoratorType;

public class MysticalTreeLogDecorator extends TreeDecorator {
    public static final MysticalTreeLogDecorator INSTANCE = new MysticalTreeLogDecorator();
    public static final Codec<MysticalTreeLogDecorator> CODEC = Codec.unit(() -> INSTANCE);

    @Override
    protected TreeDecoratorType<?> getType() {
        return YippeeTreeDecorators.MYSTICAL_TREE_LOG;
    }

    @Override
    public void generate(Generator generator) {
        generator.replace(generator.getLogPositions().get(2), YippeeBlocks.MYSTICAL_OAK_LOG.getDefaultState().with(HorizontalFacingBlock.FACING, Direction.Type.HORIZONTAL.random(generator.getRandom())));
    }

}
