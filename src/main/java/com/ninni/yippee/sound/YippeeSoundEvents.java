package com.ninni.yippee.sound;

import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import static com.ninni.yippee.Yippee.*;

public interface YippeeSoundEvents {


    SoundEvent ITEM_CREATURE_YIPPEE   = creature("yippee");
    private static SoundEvent creature(String type) {
        return createItemSound("creature", type);
    }

    private static SoundEvent register(String id) {
        Identifier identifier = new Identifier(MOD_ID, id);
        return Registry.register(Registry.SOUND_EVENT, identifier, new SoundEvent(identifier));
    }

    private static SoundEvent createItemSound(String item, String id) { return register("item." + item + "." + id); }
}
