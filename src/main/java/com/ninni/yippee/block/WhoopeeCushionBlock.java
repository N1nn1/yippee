package com.ninni.yippee.block;

import com.ninni.yippee.init.YippeeSoundEvents;
import com.ninni.yippee.state.property.YippeeProperties;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.BasePressurePlateBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.SimpleWaterloggedBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

import java.util.List;

import static net.minecraft.world.level.block.state.properties.BlockStateProperties.WATERLOGGED;

@SuppressWarnings("deprecation")
public class WhoopeeCushionBlock extends BasePressurePlateBlock implements SimpleWaterloggedBlock {
    protected static final VoxelShape PRESSED_SHAPE = Block.box(3.0, 0.0, 3.0, 13.0, 1.0, 13.0);
    protected static final VoxelShape DEFAULT_SHAPE = Block.box(3.0, 0.0, 3.0, 13.0, 3.0, 13.0);
    public static final BooleanProperty PRESSED = YippeeProperties.PRESSED;
    public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;

    public WhoopeeCushionBlock(Properties settings) {
        super(settings);
        this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH).setValue(PRESSED, false).setValue(WATERLOGGED, false));
    }

    @Override
    public VoxelShape getShape(BlockState pState, BlockGetter world, BlockPos pPos, CollisionContext pContext) {
        return this.getSignalForState(pState) > 0 ? PRESSED_SHAPE : DEFAULT_SHAPE;
    }

    @Override
    protected void playOnSound(LevelAccessor world, BlockPos pPos) {
        world.playSound(null, pPos, YippeeSoundEvents.BLOCK_CUSHION_PRESSED.get(), SoundSource.BLOCKS, 0.5F, 1.0F);
    }

    @Override
    protected void playOffSound(LevelAccessor world, BlockPos pPos) {
        world.playSound(null, pPos, YippeeSoundEvents.BLOCK_CUSHION_UNPRESSED.get(), SoundSource.BLOCKS, 0.3F, 0.7F);
    }

    @Override
    protected int getSignalStrength(Level world, BlockPos pPos) {
        List<Entity> list = world.getEntities(null, TOUCH_AABB.move(pPos));
        if (!list.isEmpty()) {
            for (Entity entity : list) {
                if (!entity.isIgnoringBlockTriggers()) {
                    return 15;
                }
            }
        }
        return 0;
    }

    @Override
    protected int getSignalForState(BlockState pState) {
        return pState.getValue(PRESSED) ? 15 : 0;
    }

    @Override
    protected BlockState setSignalForState(BlockState pState, int pStrength) {
        return pState.setValue(PRESSED, pStrength > 0);
    }

    @Override
    protected void checkPressed(@Nullable Entity entity, Level world, BlockPos pos, BlockState state, int output) {
        int i = this.getSignalStrength(world, pos);
        boolean bl = output > 0;
        boolean bl2 = i > 0;
        if (output != i) {
            BlockState blockState = this.setSignalForState(state, i);
            world.setBlock(pos, blockState, 2);
            this.updateNeighbours(world, pos);
            world.setBlocksDirty(pos, state, blockState);
        }

        if (!bl2 && bl) {
            this.playOffSound(world, pos);
            world.gameEvent(entity, GameEvent.BLOCK_DEACTIVATE, pos);
        } else if (bl2 && !bl) {
            this.playOnSound(world, pos);
            if (world instanceof ServerLevel serverLevel && world.getFluidState(pos).getType() != Fluids.WATER) serverLevel.sendParticles(ParticleTypes.CAMPFIRE_SIGNAL_SMOKE, pos.getX() + 0.5D, pos.getY(), pos.getZ() + 0.5D, 3, 0.05D, 0.5D, 0.05D, 0.01);
            if (world instanceof ServerLevel serverWorld && world.getFluidState(pos).getType() == Fluids.WATER) serverWorld.sendParticles(ParticleTypes.BUBBLE_COLUMN_UP, pos.getX() + 0.5, pos.getY() + 0.2, pos.getZ() + 0.5, 15,0.05,0.5,0.05,0.2);
            world.gameEvent(entity, GameEvent.BLOCK_ACTIVATE, pos);
        }

        if (bl2) {
            world.scheduleTick(new BlockPos(pos), this, this.getPressedTime());
        }
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext pContext) {
        Level worldAccess = pContext.getLevel();
        BlockPos blockPos = pContext.getClickedPos();
        boolean bl = worldAccess.getFluidState(blockPos).getType() == Fluids.WATER;
        return this.defaultBlockState().setValue(WATERLOGGED, bl).setValue(FACING, pContext.getHorizontalDirection().getOpposite());
    }

    @Override
    public FluidState getFluidState(BlockState pState) {
        return pState.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(pState);
    }

    @Override
    public BlockState rotate(BlockState pState, Rotation pRotation) {
        return pState.setValue(FACING, pRotation.rotate(pState.getValue(FACING)));
    }

    @Override
    public BlockState mirror(BlockState pState, Mirror pMirror) {
        return pState.rotate(pMirror.getRotation(pState.getValue(FACING)));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        pBuilder.add(PRESSED, FACING, WATERLOGGED);
    }
}
