package com.ninni.yippee.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.FacingBlock;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.Direction;

public class MysticalOakPlanksBlock extends FacingBlock {
    public static final DirectionProperty FACING = Properties.FACING;

    protected MysticalOakPlanksBlock(Settings properties) {
        super(properties);
        this.setDefaultState(((this.stateManager.getDefaultState()).with(FACING, Direction.NORTH)));
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        super.appendProperties(builder);
        builder.add(FACING);
    }

    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        return this.getDefaultState().with(FACING, ctx.getPlayerLookDirection().getOpposite());
    }
}

