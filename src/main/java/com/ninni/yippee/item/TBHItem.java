package com.ninni.yippee.item;

import com.ninni.yippee.init.YippeeSoundEvents;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

public class TBHItem extends Item {

    public TBHItem(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level world, Player user, InteractionHand hand) {
        if (world.isClientSide()) {
            world.playSound(user, user, YippeeSoundEvents.ITEM_CREATURE_YIPPEE.get(), SoundSource.MASTER, 1.5F, 1.0F);
            user.getCooldowns().addCooldown(this, 8);
            user.awardStat(Stats.ITEM_USED.get(this));
            Vec3 vec3d = user.getBoundingBox().getCenter();
            if (world instanceof ServerLevel serverWorld) serverWorld.sendParticles(ParticleTypes.ENTITY_EFFECT, vec3d.x, vec3d.y - 0.15, vec3d.z, 100, 0, 0, 0, 1.75D);
            return InteractionResultHolder.sidedSuccess(user.getItemInHand(hand), world.isClientSide());
        }
        return null;
    }
}
