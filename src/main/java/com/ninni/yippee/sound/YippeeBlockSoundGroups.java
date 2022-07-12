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
}
