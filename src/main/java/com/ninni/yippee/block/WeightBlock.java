package com.ninni.yippee.block;

import com.ninni.yippee.entity.damage.YippeeDamageSource;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.FallingBlock;
import net.minecraft.block.ShapeContext;
import net.minecraft.entity.FallingBlockEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.util.function.BooleanBiFunction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;

public class WeightBlock extends FallingBlock {
    private static final VoxelShape SHAPE = VoxelShapes.combineAndSimplify(Block.createCuboidShape(6, 14, 6, 10, 16, 10), Block.createCuboidShape(0, 0, 0, 16, 14, 16), BooleanBiFunction.OR);

    public WeightBlock(Settings settings) {
        super(settings);
    }

    @SuppressWarnings("deprecation")
    @Override public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) { return SHAPE; }

    @Override
    protected void configureFallingBlockEntity(FallingBlockEntity entity) {
        entity.setHurtEntities(1.0f, 20);
    }

    @Override
    public DamageSource getDamageSource() {
        return YippeeDamageSource.WEIGHT;
    }
}
