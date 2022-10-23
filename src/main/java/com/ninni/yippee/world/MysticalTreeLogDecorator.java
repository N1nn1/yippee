package com.ninni.yippee.world;

import com.mojang.serialization.Codec;
import com.ninni.yippee.init.YippeeBlocks;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecorator;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecoratorType;

public class MysticalTreeLogDecorator extends TreeDecorator {
    public static final MysticalTreeLogDecorator INSTANCE = new MysticalTreeLogDecorator();
    public static final Codec<MysticalTreeLogDecorator> CODEC = Codec.unit(() -> INSTANCE);

    @Override
    protected TreeDecoratorType<?> type() {
        return YippeeTreeDecorators.MYSTICAL_TREE_LOG.get();
    }

    @Override
    public void place(Context context) {
        context.setBlock(context.logs().get(2), YippeeBlocks.MYSTICAL_OAK_LOG.get().defaultBlockState().setValue(HorizontalDirectionalBlock.FACING, Direction.Plane.HORIZONTAL.getRandomDirection(context.random())));
    }
}