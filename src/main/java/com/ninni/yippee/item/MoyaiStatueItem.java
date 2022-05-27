package com.ninni.yippee.item;

import com.ninni.yippee.init.YippeeSoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class MoyaiStatueItem extends Item {

    public MoyaiStatueItem(Properties settings) { super(settings); }

    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {
        if (pLevel.isClientSide()) {
            pLevel.playSound(pPlayer, pPlayer.blockPosition(), YippeeSoundEvents.ITEM_MOYAI_BOOM.get(), SoundSource.MASTER, 0.75F, 1.0F);
            pPlayer.getCooldowns().addCooldown(this, 15);
            pPlayer.awardStat(Stats.ITEM_USED.get(this));
            return InteractionResultHolder.sidedSuccess(pPlayer.getItemInHand(pUsedHand), pLevel.isClientSide());
        }
        return null;
    }

}
