package com.ninni.yippee.block;

import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;

public class RestingMysticalOakLogBLock extends AbstractMysticalLogBlock {

    public RestingMysticalOakLogBLock(Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        ItemStack itemStack = player.getStackInHand(hand);
        Random random = world.getRandom();
        if (itemStack.isOf(Items.BONE_MEAL)) {
            world.playSound(player, player.getX(), player.getY(), player.getZ(), SoundEvents.ITEM_BONE_MEAL_USE, SoundCategory.NEUTRAL, 1.0f, 1.0f);
            if (!player.getAbilities().creativeMode) itemStack.decrement(1);
            if (!world.isClient && random.nextFloat() <= 0.25f) {
                world.setBlockState(pos, YippeeBlocks.MYSTICAL_OAK_LOG.getDefaultState().with(FACING, world.getBlockState(pos).get(FACING)));
            }
            return ActionResult.SUCCESS;
        }
        return ActionResult.PASS;
    }

    @Override
    public void randomDisplayTick(BlockState state, World world, BlockPos pos, Random random) {}
}
