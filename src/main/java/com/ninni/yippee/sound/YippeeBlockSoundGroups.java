package com.ninni.yippee.sound;

import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.sound.SoundEvents;

public class YippeeBlockSoundGroups {

    public static final BlockSoundGroup WEIGHT = new BlockSoundGroup(
            1.0F, 0.5F,

            SoundEvents.BLOCK_ANVIL_BREAK,
            SoundEvents.BLOCK_ANVIL_STEP,
            SoundEvents.BLOCK_ANVIL_PLACE,
            SoundEvents.BLOCK_ANVIL_HIT,
            SoundEvents.BLOCK_ANVIL_FALL
    );

    public static final BlockSoundGroup MYSTICAL_WOOD = new BlockSoundGroup(
            1F, 1F,

            YippeeSoundEvents.BLOCK_MYSTICAL_WOOD_BREAK,
            YippeeSoundEvents.BLOCK_MYSTICAL_WOOD_STEP,
            YippeeSoundEvents.BLOCK_MYSTICAL_WOOD_PLACE,
            YippeeSoundEvents.BLOCK_MYSTICAL_WOOD_HIT,
            YippeeSoundEvents.BLOCK_MYSTICAL_WOOD_FALL
    );
}
