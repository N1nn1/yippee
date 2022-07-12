package com.ninni.yippee.item;

import com.ninni.yippee.sound.YippeeSoundEvents;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.stat.Stats;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class TBHItem extends Item {
    public TBHItem(Settings settings) { super(settings); }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        world.playSoundFromEntity(user, user, YippeeSoundEvents.ITEM_CREATURE_YIPPEE, SoundCategory.MASTER, 1.5F, 1.0F);
        user.getItemCooldownManager().set(this, 8);
        user.incrementStat(Stats.USED.getOrCreateStat(this));
        Vec3d vec3d = user.getBoundingBox().getCenter();
        if (world instanceof ServerWorld serverWorld) serverWorld.spawnParticles(ParticleTypes.ENTITY_EFFECT, vec3d.x, vec3d.y - 0.15, vec3d.z, 100, 0, 0, 0, 1.75D);
        return TypedActionResult.success(user.getStackInHand(hand));
    }
}
