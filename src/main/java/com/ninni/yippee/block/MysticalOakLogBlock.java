package com.ninni.yippee.block;

import com.ninni.yippee.init.YippeeBlocks;
import com.ninni.yippee.init.YippeeItems;
import com.ninni.yippee.init.YippeeSoundEvents;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.BlockHitResult;

public class MysticalOakLogBlock extends AbstractMysticalLogBlock {

    public MysticalOakLogBlock(Properties settings) {
        super(settings);
    }

    @Override
    public InteractionResult use(BlockState state, Level world, BlockPos pos, Player player, InteractionHand hand, BlockHitResult p_60508_) {
        ItemStack itemStack = player.getItemInHand(hand);
        if (itemStack.is(Items.GLASS_BOTTLE)) {
            if (!player.getAbilities().instabuild) itemStack.shrink(1);
            world.playSound(player, player.getX(), player.getY(), player.getZ(), SoundEvents.BOTTLE_FILL, SoundSource.NEUTRAL, 1.0f, 1.0f);
            if (itemStack.isEmpty()) player.setItemInHand(hand, new ItemStack(YippeeItems.MYSTICAL_SAP_BOTTLE.get()));
            else if (!player.getInventory().add(new ItemStack(YippeeItems.MYSTICAL_SAP_BOTTLE.get()))) player.drop(new ItemStack(YippeeItems.MYSTICAL_SAP_BOTTLE.get()), false);
            world.setBlockAndUpdate(pos, YippeeBlocks.RESTING_MYSTICAL_OAK_LOG.get().defaultBlockState().setValue(FACING, world.getBlockState(pos).getValue(FACING)));
            world.gameEvent(player, GameEvent.FLUID_PICKUP, pos);
            return InteractionResult.SUCCESS;
        }
        if (itemStack.getItem() instanceof AxeItem) {
            if (!player.getAbilities().instabuild) itemStack.hurtAndBreak(1, player, p -> p.broadcastBreakEvent(hand));
            if (player instanceof ServerPlayer serverPlayer) CriteriaTriggers.ITEM_USED_ON_BLOCK.trigger(serverPlayer, pos, itemStack);
            world.playSound(player, pos, SoundEvents.AXE_STRIP, SoundSource.BLOCKS, 1.0f, 1.0f);
            world.gameEvent(GameEvent.BLOCK_CHANGE, pos, GameEvent.Context.of(player, state));
            world.setBlockAndUpdate(pos, YippeeBlocks.STRIPPED_MYSTICAL_OAK_LOG.get().defaultBlockState().setValue(FACING, world.getBlockState(pos).getValue(FACING)));
            return InteractionResult.sidedSuccess(world.isClientSide);
        }
        return super.use(state, world, pos, player, hand, p_60508_);
    }

}