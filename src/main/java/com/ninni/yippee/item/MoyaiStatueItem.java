package com.ninni.yippee.item;

import com.ninni.yippee.sound.YippeeSoundEvents;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.sound.SoundCategory;
import net.minecraft.stat.Stats;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import java.util.Random;

public class MoyaiStatueItem extends Item {

    public MoyaiStatueItem(Settings settings) { super(settings); }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        if (world.isClient) {
            world.playSound(user, user.getBlockPos(), YippeeSoundEvents.ITEM_MOYAI_BOOM, SoundCategory.MASTER, 1.5F, 1.0F);
            user.getItemCooldownManager().set(this, 15);
            user.incrementStat(Stats.USED.getOrCreateStat(this));
            return TypedActionResult.success(user.getStackInHand(hand), world.isClient());
        }
        return null;
    }
}
