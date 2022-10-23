package com.ninni.yippee.block;

import com.ninni.yippee.init.YippeeBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;

public class RestingMysticalOakLogBLock extends AbstractMysticalLogBlock {

    public RestingMysticalOakLogBLock(Properties settings) {
        super(settings);
    }

    @Override
    public InteractionResult use(BlockState p_60503_, Level world, BlockPos pos, Player player, InteractionHand hand, BlockHitResult p_60508_) {
        ItemStack itemStack = player.getItemInHand(hand);
        RandomSource random = world.getRandom();
        if (itemStack.is(Items.BONE_MEAL)) {
            world.playSound(player, player.getX(), player.getY(), player.getZ(), SoundEvents.BONE_MEAL_USE, SoundSource.NEUTRAL, 1.0f, 1.0f);
            if (!player.getAbilities().instabuild) itemStack.shrink(1);
            if (!world.isClientSide && random.nextFloat() <= 0.25f) {
                world.setBlockAndUpdate(pos, YippeeBlocks.MYSTICAL_OAK_LOG.get().defaultBlockState().setValue(FACING, world.getBlockState(pos).getValue(FACING)));
            }
            return InteractionResult.SUCCESS;
        }
        return InteractionResult.PASS;
    }

    @Override
    public void animateTick(BlockState p_220827_, Level world, BlockPos pos, RandomSource random) {
    }

}