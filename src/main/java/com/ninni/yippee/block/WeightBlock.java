package com.ninni.yippee.block;

import com.ninni.yippee.entity.damage.YippeeDamageSource;
import com.ninni.yippee.entity.effect.YippeeStatusEffects;
import net.minecraft.block.BlockState;
import net.minecraft.block.FallingBlock;
import net.minecraft.block.ShapeContext;
import net.minecraft.entity.FallingBlockEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;

@SuppressWarnings("deprecation")
public class WeightBlock extends FallingBlock {
    private static final VoxelShape SHAPE = VoxelShapes.union(
        createCuboidShape(6, 14, 6, 10, 16, 10),
        createCuboidShape(0, 0, 0, 16, 14, 16)
    );

    public WeightBlock(Settings settings) {
        super(settings);
    }

    @Override
    public DamageSource getDamageSource() {
        return YippeeDamageSource.WEIGHT;
    }

    @Override public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) { return SHAPE; }

    @Override
    protected void configureFallingBlockEntity(FallingBlockEntity entity) {
        entity.setHurtEntities(1.0f, 20);
    }

    public static void applyStatusEffect(LivingEntity entity) {
        entity.addStatusEffect(new StatusEffectInstance(YippeeStatusEffects.FLATTENED, 20 * 8, 0));
    }
}
