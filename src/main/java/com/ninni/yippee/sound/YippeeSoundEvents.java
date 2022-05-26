package com.ninni.yippee.sound;

import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import static com.ninni.yippee.Yippee.*;

public interface YippeeSoundEvents {


    SoundEvent ITEM_CREATURE_YIPPEE   = creature("yippee");
    private static SoundEvent creature(String type) { return createItemSound("creature", type); }

    SoundEvent ITEM_MOYAI_BOOM   = moyai("boom");
    private static SoundEvent moyai(String type) { return createItemSound("moyai", type); }

    SoundEvent BLOCK_CUSHION_PRESSED   = whoopee_cushion("pressed");
    SoundEvent BLOCK_CUSHION_UNPRESSED   = whoopee_cushion("unpressed");
    private static SoundEvent whoopee_cushion(String type) { return createBlockSound("whoopee_cushion", type); }


    private static SoundEvent register(String id) {
        Identifier identifier = new Identifier(MOD_ID, id);
        return Registry.register(Registry.SOUND_EVENT, identifier, new SoundEvent(identifier));
    }

    private static SoundEvent createItemSound(String item, String id) { return register("item." + item + "." + id); }
    private static SoundEvent createBlockSound(String block, String id) { return register("block." + block + "." + id); }
}
