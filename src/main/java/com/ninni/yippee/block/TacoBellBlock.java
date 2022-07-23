package com.ninni.yippee.block;

import com.ninni.yippee.block.blockentities.TacoBellBlockEntity;
import com.ninni.yippee.init.YippeeBlockEntityTypes;
import com.ninni.yippee.init.YippeeSoundEvents;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.FaceAttachedHorizontalDirectionalBlock;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BellAttachType;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.material.PushReaction;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

import java.util.stream.Stream;

@SuppressWarnings("deprecation, unused")
public class TacoBellBlock extends BaseEntityBlock {
    public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;
    public static final EnumProperty<BellAttachType> ATTACHMENT = BlockStateProperties.BELL_ATTACHMENT;
    public static final BooleanProperty POWERED = BlockStateProperties.POWERED;
    private static final VoxelShape NORTH_SOUTH_SHAPE = Block.box(0.0, 0.0, 4.0, 16.0, 16.0, 12.0);
    private static final VoxelShape EAST_WEST_SHAPE = Block.box(4.0, 0.0, 0.0, 12.0, 16.0, 16.0);
    private static final VoxelShape BELL_SHAPE = Stream.of(
            Block.box(5, 7, 5, 11, 12, 11),
            Block.box(7, 12, 7, 9, 13, 9),
            Block.box(7, 4, 7, 9, 5, 9),
            Block.box(3, 5, 2.999999999999999, 13, 7, 13)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    private static final VoxelShape NORTH_SOUTH_WALLS_SHAPE = Shapes.or(BELL_SHAPE, Block.box(7.0, 13.0, 0.0, 9.0, 15.0, 16.0));
    private static final VoxelShape EAST_WEST_WALLS_SHAPE = Shapes.or(BELL_SHAPE, Block.box(0.0, 13.0, 7.0, 16.0, 15.0, 9.0));
    private static final VoxelShape WEST_WALL_SHAPE = Shapes.or(BELL_SHAPE, Block.box(0.0, 13.0, 7.0, 13.0, 15.0, 9.0));
    private static final VoxelShape EAST_WALL_SHAPE = Shapes.or(BELL_SHAPE, Block.box(3.0, 13.0, 7.0, 16.0, 15.0, 9.0));
    private static final VoxelShape NORTH_WALL_SHAPE = Shapes.or(BELL_SHAPE, Block.box(7.0, 13.0, 0.0, 9.0, 15.0, 13.0));
    private static final VoxelShape SOUTH_WALL_SHAPE = Shapes.or(BELL_SHAPE, Block.box(7.0, 13.0, 3.0, 9.0, 15.0, 16.0));
    private static final VoxelShape HANGING_SHAPE = Shapes.or(BELL_SHAPE, Block.box(7.0, 13.0, 7.0, 9.0, 16.0, 9.0));

    public TacoBellBlock(Properties properties) { super(properties); }

    @Override
    public void neighborChanged(BlockState state, Level world, BlockPos pos, Block p_60512_, BlockPos p_60513_, boolean p_60514_) {
        boolean bl = world.hasNeighborSignal(pos);
        if (bl != state.getValue(POWERED)) {
            if (bl) this.ring(world, pos, null);
            world.setBlock(pos, state.setValue(POWERED, bl), 3);
        }
    }

    @Override
    public void onProjectileHit(Level world, BlockState state, BlockHitResult hit, Projectile projectile) {
        Entity entity = projectile.getOwner();
        this.ring(world, state, hit, entity instanceof Player ? (Player)entity : null, true);
    }

    @Override
    public InteractionResult use(BlockState state, Level world, BlockPos pos, Player player, InteractionHand hand, BlockHitResult result) {
        return this.ring(world, state, result, player, true) ? InteractionResult.sidedSuccess(world.isClientSide) : InteractionResult.PASS;
    }

    public boolean ring(Level world, BlockState state, BlockHitResult hitResult, @Nullable Player player, boolean bl) {
        boolean bl2;
        Direction direction = hitResult.getDirection();
        BlockPos blockPos = hitResult.getBlockPos();
        boolean bl3 = bl2 = !bl || this.isProperHit(state, direction, hitResult.getLocation().y - (double)blockPos.getY());
        if (bl2) {
            boolean bl32 = this.ring(player, world, blockPos, direction);
            if (bl32 && player != null) player.awardStat(Stats.BELL_RING);
            return true;
        }
        return false;
    }

    private boolean isProperHit(BlockState state, Direction side, double y) {
        if (side.getAxis() != Direction.Axis.Y && !(y > (double)0.8124F)) {
            Direction direction = state.getValue(FACING);
            BellAttachType bellattachtype = state.getValue(ATTACHMENT);
            return switch (bellattachtype) {
                case FLOOR -> direction.getAxis() == side.getAxis();
                case SINGLE_WALL, DOUBLE_WALL -> direction.getAxis() != side.getAxis();
                case CEILING -> true;
            };
        } else {
            return false;
        }
    }

    public void ring(Level world, BlockPos pos, @Nullable Direction direction) {this.ring(null, world, pos, direction);}

    public boolean ring(@Nullable Entity entity, Level world, BlockPos pos, @Nullable Direction direction) {
        BlockEntity blockEntity = world.getBlockEntity(pos);
        if (!world.isClientSide && blockEntity instanceof TacoBellBlockEntity tacoBellBlockEntity) {
            if (direction == null) direction = world.getBlockState(pos).getValue(FACING);
            tacoBellBlockEntity.activate(direction);
            world.playSound(null, pos, YippeeSoundEvents.BLOCK_TACO_BELL_RING.get(), SoundSource.BLOCKS, 2.0f, 1.0f);
            world.gameEvent(entity, GameEvent.BLOCK_CHANGE, pos);
            return true;
        }
        return false;
    }

    private VoxelShape getShape(BlockState state) {
        Direction direction = state.getValue(FACING);
        BellAttachType attachment = state.getValue(ATTACHMENT);

        if (attachment == BellAttachType.FLOOR) {
            if (direction == Direction.NORTH || direction == Direction.SOUTH) return NORTH_SOUTH_SHAPE;
            return EAST_WEST_SHAPE;
        }
        if (attachment == BellAttachType.CEILING) return HANGING_SHAPE;
        if (attachment == BellAttachType.DOUBLE_WALL) {
            if (direction == Direction.NORTH || direction == Direction.SOUTH) return NORTH_SOUTH_WALLS_SHAPE;
            return EAST_WEST_WALLS_SHAPE;
        }
        if (direction == Direction.NORTH) return NORTH_WALL_SHAPE;
        if (direction == Direction.SOUTH) return SOUTH_WALL_SHAPE;
        if (direction == Direction.EAST) return EAST_WALL_SHAPE;
        return WEST_WALL_SHAPE;
    }

    @Override public VoxelShape getCollisionShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) { return this.getShape(state); }
    @Override public VoxelShape getShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) { return this.getShape(state); }
    @Override public RenderShape getRenderShape(BlockState p_49232_) { return RenderShape.MODEL; }

    @Override
    @Nullable
    public BlockState getStateForPlacement(BlockPlaceContext ctx) {
        Direction direction = ctx.getClickedFace();
        BlockPos blockPos = ctx.getClickedPos();
        Level world = ctx.getLevel();

        Direction.Axis axis = direction.getAxis();
        if (axis == Direction.Axis.Y) {
            BlockState blockState = (this.defaultBlockState().setValue(ATTACHMENT, direction == Direction.DOWN ? BellAttachType.CEILING : BellAttachType.FLOOR)).setValue(FACING, ctx.getHorizontalDirection());

            if (blockState.canSurvive(ctx.getLevel(), blockPos)) return blockState;
        } else {
            boolean bl = axis == Direction.Axis.X && world.getBlockState(blockPos.west()).isFaceSturdy(world, blockPos.west(), Direction.EAST) && world.getBlockState(blockPos.east()).isFaceSturdy(world, blockPos.east(), Direction.WEST) || axis == Direction.Axis.Z && world.getBlockState(blockPos.north()).isFaceSturdy(world, blockPos.north(), Direction.SOUTH) && world.getBlockState(blockPos.south()).isFaceSturdy(world, blockPos.south(), Direction.NORTH);
            BlockState blockState = (this.defaultBlockState().setValue(FACING, direction.getOpposite())).setValue(ATTACHMENT, bl ? BellAttachType.DOUBLE_WALL : BellAttachType.SINGLE_WALL);
            if (blockState.canSurvive(ctx.getLevel(), ctx.getClickedPos())) return blockState;
            boolean bl2 = world.getBlockState(blockPos.below()).isFaceSturdy(world, blockPos.below(), Direction.UP);
            if ((blockState = blockState.setValue(ATTACHMENT, bl2 ? BellAttachType.FLOOR : BellAttachType.CEILING)).canSurvive(ctx.getLevel(), ctx.getClickedPos())) return blockState;
        }
        return null;
    }

    @Override
    public BlockState updateShape(BlockState state, Direction direction, BlockState neighborState, LevelAccessor world, BlockPos pos, BlockPos neighborPos) {
        BellAttachType attachment = state.getValue(ATTACHMENT);
        Direction direction2 = TacoBellBlock.getPlacementSide(state).getOpposite();
        if (direction2 == direction && !state.canSurvive(world, pos) && attachment != BellAttachType.DOUBLE_WALL) return Blocks.AIR.defaultBlockState();
        if (direction.getAxis() == state.getValue(FACING).getAxis()) {
            if (attachment == BellAttachType.DOUBLE_WALL && !neighborState.isFaceSturdy(world, neighborPos, direction)) return (state.setValue(ATTACHMENT, BellAttachType.SINGLE_WALL)).setValue(FACING, direction.getOpposite());
            if (attachment == BellAttachType.SINGLE_WALL && direction2.getOpposite() == direction && neighborState.isFaceSturdy(world, neighborPos, state.getValue(FACING)))  return state.setValue(ATTACHMENT, BellAttachType.DOUBLE_WALL);
        }
        return super.updateShape(state, direction, neighborState, world, pos, neighborPos);
    }

    @Override
    public boolean canSurvive(BlockState state, LevelReader world, BlockPos pos) {
        Direction direction = TacoBellBlock.getPlacementSide(state).getOpposite();
        if (direction == Direction.UP) return Block.canSupportCenter(world, pos.above(), Direction.DOWN);
        return FaceAttachedHorizontalDirectionalBlock.canAttach(world, pos, direction);
    }

    private static Direction getPlacementSide(BlockState state) {
        return switch ((BellAttachType) state.getValue(ATTACHMENT)) {
            case FLOOR -> Direction.UP;
            case CEILING -> Direction.DOWN;
            default -> state.getValue(FACING).getOpposite();
        };
    }

    @Override
    public PushReaction getPistonPushReaction(BlockState p_60584_) {
        return PushReaction.DESTROY;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING, ATTACHMENT, POWERED);
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new TacoBellBlockEntity(pos, state);
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level world, BlockState p_153213_, BlockEntityType<T> type) {
        return TacoBellBlock.createTickerHelper(type, YippeeBlockEntityTypes.TACO_BELL.get(), world.isClientSide ? TacoBellBlockEntity::clientTick : TacoBellBlockEntity::serverTick);
    }

    @Override
    public boolean isPathfindable(BlockState p_60475_, BlockGetter p_60476_, BlockPos p_60477_, PathComputationType p_60478_) {
        return false;
    }

}