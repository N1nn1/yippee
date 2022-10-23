package com.ninni.yippee.block;

import com.ninni.yippee.item.YippeeItems;
import net.minecraft.advancement.criterion.Criteria;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.AxeItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;

public class MysticalOakLogBlock extends AbstractMysticalLogBlock {

    public MysticalOakLogBlock(Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        ItemStack itemStack = player.getStackInHand(hand);
        if (itemStack.isOf(Items.GLASS_BOTTLE)) {
            if (!player.getAbilities().creativeMode) itemStack.decrement(1);
            world.playSound(player, player.getX(), player.getY(), player.getZ(), SoundEvents.ITEM_BOTTLE_FILL, SoundCategory.NEUTRAL, 1.0f, 1.0f);
            if (itemStack.isEmpty()) player.setStackInHand(hand, new ItemStack(YippeeItems.MYSTICAL_SAP_BOTTLE));
            else if (!player.getInventory().insertStack(new ItemStack(YippeeItems.MYSTICAL_SAP_BOTTLE))) player.dropItem(new ItemStack(YippeeItems.MYSTICAL_SAP_BOTTLE), false);
            world.setBlockState(pos, YippeeBlocks.RESTING_MYSTICAL_OAK_LOG.getDefaultState().with(FACING, world.getBlockState(pos).get(FACING)));
            world.emitGameEvent(player, GameEvent.FLUID_PICKUP, pos);
            return ActionResult.SUCCESS;
        }
        if (itemStack.getItem() instanceof AxeItem) {
            if (!player.getAbilities().creativeMode) itemStack.damage(1, player, p -> p.sendToolBreakStatus(hand));
            if (player instanceof ServerPlayerEntity) Criteria.ITEM_USED_ON_BLOCK.trigger((ServerPlayerEntity)player, pos, itemStack);
            world.playSound(player, pos, SoundEvents.ITEM_AXE_STRIP, SoundCategory.BLOCKS, 1.0f, 1.0f);
            world.emitGameEvent(GameEvent.BLOCK_CHANGE, pos, GameEvent.Emitter.of(player, state));
            world.setBlockState(pos, YippeeBlocks.STRIPPED_MYSTICAL_OAK_LOG.getDefaultState().with(FACING, world.getBlockState(pos).get(FACING)));
            return ActionResult.success(world.isClient);
        }
        return super.onUse(state, world, pos, player, hand, hit);
    }
}
