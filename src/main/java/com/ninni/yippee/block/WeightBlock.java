package com.ninni.yippee.block;

import com.ninni.yippee.entity.damage.YippeeDamageSource;
import com.ninni.yippee.entity.effect.YippeeStatusEffects;
import com.ninni.yippee.sound.YippeeSoundEvents;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.FallingBlock;
import net.minecraft.block.HorizontalFacingBlock;
import net.minecraft.block.ShapeContext;
import net.minecraft.entity.FallingBlockEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;

@SuppressWarnings("deprecation")
public class WeightBlock extends FallingBlock {
    public static final DirectionProperty FACING = HorizontalFacingBlock.FACING;
    private static final VoxelShape SHAPE = VoxelShapes.union(
        createCuboidShape(6, 14, 6, 10, 16, 10),
        createCuboidShape(0, 0, 0, 16, 14, 16)
    );

    public WeightBlock(Settings settings) {
        super(settings);
        this.setDefaultState((this.stateManager.getDefaultState()).with(FACING, Direction.NORTH));
    }

    @Override public BlockState getPlacementState(ItemPlacementContext ctx) { return this.getDefaultState().with(FACING, ctx.getPlayerFacing().getOpposite()); }

    @Override public DamageSource getDamageSource() { return YippeeDamageSource.WEIGHT; }

    @Override public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) { return SHAPE; }

    @Override
    public void onLanding(World world, BlockPos pos, BlockState fallingBlockState, BlockState currentStateInPos, FallingBlockEntity fallingBlockEntity) {
        if (!fallingBlockEntity.isSilent()) {
            fallingBlockEntity.playSound(YippeeSoundEvents.BLOCK_WEIGHT_FALL, 1, 1);
        }
    }

    @Override public BlockState rotate(BlockState state, BlockRotation rotation) { return state.with(FACING, rotation.rotate(state.get(FACING))); }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        super.appendProperties(builder);
        builder.add(FACING);
    }

    @Override protected void configureFallingBlockEntity(FallingBlockEntity entity) { entity.setHurtEntities(8.0f, 20); }

    public static void applyStatusEffect(LivingEntity entity) {
        entity.addStatusEffect(new StatusEffectInstance(YippeeStatusEffects.FLATTENED, 20 * 8, 0, false, false, true));
    }
}
