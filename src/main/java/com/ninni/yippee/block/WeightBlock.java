package com.ninni.yippee.block;

import com.ninni.yippee.init.YippeeMobEffects;
import com.ninni.yippee.entities.damagesource.YippeeDamageSource;
import com.ninni.yippee.init.YippeeSoundEvents;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.FallingBlockEntity;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.FallingBlock;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

@SuppressWarnings("deprecation")
public class WeightBlock extends FallingBlock {
    public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;
    private static final VoxelShape SHAPE = Shapes.or(
            box(6, 14, 6, 10, 16, 10),
            box(0, 0, 0, 16, 14, 16)
    );

    public WeightBlock(Properties settings) {
        super(settings);
        this.registerDefaultState((this.stateDefinition.any()).setValue(FACING, Direction.NORTH));
    }

    @Override public BlockState getStateForPlacement(BlockPlaceContext ctx) { return this.defaultBlockState().setValue(FACING, ctx.getHorizontalDirection().getOpposite()); }

    @Override
    public DamageSource getFallDamageSource() {
        return YippeeDamageSource.WEIGHT;
    }

    @Override
    public VoxelShape getShape(BlockState p_60555_, BlockGetter p_60556_, BlockPos p_60557_, CollisionContext p_60558_) {
        return SHAPE;
    }

    @Override
    public void onLand(Level p_153220_, BlockPos p_153221_, BlockState p_153222_, BlockState p_153223_, FallingBlockEntity fallingBlockEntity) {
        if (!fallingBlockEntity.isSilent()) {
            fallingBlockEntity.playSound(YippeeSoundEvents.BLOCK_WEIGHT_FALL.get(), 1, 1);
        }
    }

    @Override
    public BlockState rotate(BlockState state, Rotation rotation) {
        return state.setValue(FACING, rotation.rotate(state.getValue(FACING)));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(FACING);
    }

    @Override
    protected void falling(FallingBlockEntity entity) {
        entity.setHurtsEntities(8.0F, 20);
    }

    public static void applyStatusEffect(LivingEntity entity) {
        entity.addEffect(new MobEffectInstance(YippeeMobEffects.FLATTENED.get(), 20 * 8, 0, false, false, true));
    }
}