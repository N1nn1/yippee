package com.ninni.yippee.item;

import net.minecraft.item.MusicDiscItem;
import net.minecraft.sound.SoundEvent;

public class PublicMusicDiscItem extends MusicDiscItem {
    public PublicMusicDiscItem(int comparatorOutput, SoundEvent sound, Settings settings, int lengthInSeconds) {
        super(comparatorOutput, sound, settings, lengthInSeconds);
    }
}
