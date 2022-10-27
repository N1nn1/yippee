package com.ninni.yippee.block;

import com.ninni.yippee.init.YippeeSoundEvents;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.Nullable;

@SuppressWarnings("deprecation")
public class AbstractMysticalLogBlock extends Block {
    private int wisdomCooldownTicks;
    public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;

    public AbstractMysticalLogBlock(Properties settings) {
        super(settings);
        this.registerDefaultState((this.stateDefinition.any()).setValue(FACING, Direction.NORTH));
    }

    @Override
    public InteractionResult use(BlockState p_60503_, Level world, BlockPos pos, Player p_60506_, InteractionHand p_60507_, BlockHitResult p_60508_) {
        if (this.wisdomCooldownTicks == 0) {
            this.wisdomCooldownTicks = 40;
            world.playLocalSound(pos.getX(), pos.getY(), pos.getZ(), YippeeSoundEvents.BLOCK_MYSTICAL_WOOD_SPEAK.get(), SoundSource.BLOCKS, 1.0f, 1.0f, false);
            return InteractionResult.SUCCESS;
        }
        return InteractionResult.PASS;
    }

    @Override
    public void animateTick(BlockState p_220827_, Level world, BlockPos pos, RandomSource random) {
        if (this.wisdomCooldownTicks > 0) {
            this.wisdomCooldownTicks --;
        }
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext ctx) {
        return this.defaultBlockState().setValue(FACING, ctx.getHorizontalDirection().getOpposite());
    }

    @Override
    public BlockState rotate(BlockState state, Rotation rotation) {
        return state.setValue(FACING, rotation.rotate(state.getValue(FACING)));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING);
    }

}