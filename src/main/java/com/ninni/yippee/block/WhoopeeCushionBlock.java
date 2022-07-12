package com.ninni.yippee.block;

import com.ninni.yippee.sound.YippeeSoundEvents;
import com.ninni.yippee.state.property.YippeeProperties;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.AbstractPressurePlateBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.block.Waterloggable;
import net.minecraft.entity.Entity;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.BlockMirror;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.event.GameEvent;
import org.jetbrains.annotations.Nullable;

import java.util.List;

import static net.minecraft.state.property.Properties.*;

@SuppressWarnings("deprecation")
public class WhoopeeCushionBlock extends AbstractPressurePlateBlock implements Waterloggable {
    protected static final VoxelShape PRESSED_SHAPE = Block.createCuboidShape(3.0, 0.0, 3.0, 13.0, 1.0, 13.0);
    protected static final VoxelShape DEFAULT_SHAPE = Block.createCuboidShape(3.0, 0.0, 3.0, 13.0, 3.0, 13.0);
    public static final BooleanProperty PRESSED = YippeeProperties.PRESSED;
    public static final DirectionProperty FACING = Properties.HORIZONTAL_FACING;

    protected WhoopeeCushionBlock(AbstractBlock.Settings settings) {
        super(settings);
        this.setDefaultState(((this.stateManager.getDefaultState()).with(FACING, Direction.NORTH)).with(PRESSED, false).with(WATERLOGGED, false));
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) { return this.getRedstoneOutput(state) > 0 ? PRESSED_SHAPE : DEFAULT_SHAPE; }

    @Override
    protected int getRedstoneOutput(BlockState state) { return state.get(PRESSED) ? 15 : 0; }
    @Override
    protected BlockState setRedstoneOutput(BlockState state, int rsOut) { return state.with(PRESSED, rsOut > 0); }

    @Override
    protected void playPressSound(WorldAccess world, BlockPos pos) { world.playSound(null, pos, YippeeSoundEvents.BLOCK_CUSHION_PRESSED, SoundCategory.BLOCKS, 0.5F, 1F); }
    @Override
    protected void playDepressSound(WorldAccess world, BlockPos pos) { world.playSound(null, pos, YippeeSoundEvents.BLOCK_CUSHION_UNPRESSED, SoundCategory.BLOCKS, 0.3F, 0.7F); }

    @Override
    protected void updatePlateState(@Nullable Entity entity, World world, BlockPos pos, BlockState state, int output) {
        int i = this.getRedstoneOutput(world, pos);
        boolean bl = output > 0;
        boolean bl2 = i > 0;
        if (output != i) {
            BlockState blockState = this.setRedstoneOutput(state, i);
            world.setBlockState(pos, blockState, 2);
            this.updateNeighbors(world, pos);
            world.scheduleBlockRerenderIfNeeded(pos, state, blockState);
        }

        if (!bl2 && bl) {
            this.playDepressSound(world, pos);
            world.emitGameEvent(entity, GameEvent.BLOCK_DEACTIVATE, pos);
        } else if (bl2 && !bl) {
            this.playPressSound(world, pos);
            if (world instanceof ServerWorld serverWorld && world.getFluidState(pos).getFluid() != Fluids.WATER) serverWorld.spawnParticles(ParticleTypes.CAMPFIRE_SIGNAL_SMOKE, pos.getX() + 0.5, pos.getY(), pos.getZ() + 0.5, 3,0.05,0.5,0.05,0.01);
            if (world instanceof ServerWorld serverWorld && world.getFluidState(pos).getFluid() == Fluids.WATER) serverWorld.spawnParticles(ParticleTypes.BUBBLE_COLUMN_UP, pos.getX() + 0.5, pos.getY() + 0.2, pos.getZ() + 0.5, 15,0.05,0.5,0.05,0.2);
            world.emitGameEvent(entity, GameEvent.BLOCK_ACTIVATE, pos);
        }

        if (bl2) world.createAndScheduleBlockTick(new BlockPos(pos), this, this.getTickRate());

    }

    @Override
    protected int getRedstoneOutput(World world, BlockPos pos) {
        List<Entity> list = world.getOtherEntities(null, BOX.offset(pos));
        if (!list.isEmpty()) for (Entity entity : list) if (!entity.canAvoidTraps()) return 15;
        return 0;
    }

    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        WorldAccess worldAccess = ctx.getWorld();
        BlockPos blockPos = ctx.getBlockPos();
        boolean bl = worldAccess.getFluidState(blockPos).getFluid() == Fluids.WATER;
        return this.getDefaultState().with(WATERLOGGED, bl).with(FACING, ctx.getPlayerFacing().getOpposite());
    }

    @Override
    public FluidState getFluidState(BlockState state) { return state.get(WATERLOGGED) ? Fluids.WATER.getStill(false) : super.getFluidState(state); }
    @Override
    public BlockState rotate(BlockState state, BlockRotation rotation) { return state.with(FACING, rotation.rotate(state.get(FACING))); }
    @Override
    public BlockState mirror(BlockState state, BlockMirror mirror) { return state.rotate(mirror.getRotation(state.get(FACING))); }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) { builder.add(PRESSED, FACING, WATERLOGGED); }
}
