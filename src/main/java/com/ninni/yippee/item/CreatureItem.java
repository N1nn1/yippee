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

public class CreatureItem extends Item {

    public CreatureItem(Settings settings) { super(settings); }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        if (world.isClient) {
            world.playSound(user, user.getBlockPos(), YippeeSoundEvents.ITEM_CREATURE_YIPPEE, SoundCategory.MASTER, 1.5F, 1.0F);
            user.getItemCooldownManager().set(this, 8);
            user.incrementStat(Stats.USED.getOrCreateStat(this));
            Vec3d vec3d = user.getBoundingBox().getCenter();
            Random random = world.getRandom();
            for (int i = 0; i < 100; ++i) {
                double velX = random.nextGaussian() * 1.75;
                double velY = random.nextGaussian() * 1.75;
                double velZ = random.nextGaussian() * 1.75;
                world.addParticle(ParticleTypes.ENTITY_EFFECT, vec3d.x, vec3d.y - 0.15, vec3d.z, velX, velY, velZ);
            }
            return TypedActionResult.success(user.getStackInHand(hand), world.isClient());
        }
        return null;
    }
}
