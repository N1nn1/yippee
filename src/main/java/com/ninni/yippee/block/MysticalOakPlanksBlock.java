package com.ninni.yippee.block;

import com.ninni.yippee.sound.YippeeSoundEvents;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.FacingBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.sound.SoundCategory;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;

public class MysticalOakPlanksBlock extends FacingBlock {
    private int wisdomCooldownTicks;
    public static final DirectionProperty FACING = Properties.FACING;

    protected MysticalOakPlanksBlock(Settings properties) {
        super(properties);
        this.setDefaultState(((this.stateManager.getDefaultState()).with(FACING, Direction.NORTH)));
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (this.wisdomCooldownTicks == 0) {
            this.wisdomCooldownTicks = 40;
            world.playSound(pos.getX(), pos.getY(), pos.getZ(), YippeeSoundEvents.BLOCK_MYSTICAL_WOOD_SPEAK, SoundCategory.BLOCKS, 1.0f, 1.0f, false);
            return ActionResult.SUCCESS;
        }
        return ActionResult.PASS;
    }

    @Override
    public void randomDisplayTick(BlockState state, World world, BlockPos pos, Random random) {
        if (this.wisdomCooldownTicks > 0){
            this.wisdomCooldownTicks --;
        }
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

