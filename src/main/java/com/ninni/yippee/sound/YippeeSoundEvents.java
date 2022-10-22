package com.ninni.yippee.sound;

import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import static com.ninni.yippee.Yippee.*;

public interface YippeeSoundEvents {


    SoundEvent ITEM_CREATURE_YIPPEE = tbh("yippee");
    private static SoundEvent tbh(String type) { return createItemSound("tbh", type); }

    SoundEvent ITEM_MOYAI_BOOM = moyai("boom");
    private static SoundEvent moyai(String type) { return createItemSound("moyai", type); }

    SoundEvent BLOCK_CUSHION_PRESSED = whoopee_cushion("pressed");
    SoundEvent BLOCK_CUSHION_UNPRESSED = whoopee_cushion("unpressed");
    private static SoundEvent whoopee_cushion(String type) { return createBlockSound("whoopee_cushion", type); }

    SoundEvent BLOCK_TACO_BELL_RING = taco_bell("ring");
    private static SoundEvent taco_bell(String type) { return createBlockSound("taco_bell", type); }

    SoundEvent BLOCK_WEIGHT_FALL = weight("fall");
    private static SoundEvent weight(String type) { return createBlockSound("weight", type); }

    SoundEvent ITEM_RECORD_SCRATCH1 = damaged_disc("scratch");
    private static SoundEvent damaged_disc(String type) { return createItemSound("damaged_disc", type); }
    SoundEvent ITEM_RECORD_SCRATCH2 = broken_disc("scratch");
    private static SoundEvent broken_disc(String type) { return createItemSound("broken_disc", type); }

    SoundEvent BLOCK_MYSTICAL_WOOD_SPEAK = mysticalTree("speak");
    SoundEvent BLOCK_MYSTICAL_WOOD_COLLECT = mysticalTree("collect");
    SoundEvent BLOCK_MYSTICAL_WOOD_BREAK = mysticalTree("break");
    SoundEvent BLOCK_MYSTICAL_WOOD_HIT = mysticalTree("hit");
    SoundEvent BLOCK_MYSTICAL_WOOD_STEP = mysticalTree("step");
    SoundEvent BLOCK_MYSTICAL_WOOD_FALL = mysticalTree("fall");
    SoundEvent BLOCK_MYSTICAL_WOOD_PLACE = mysticalTree("place");
    private static SoundEvent mysticalTree(String type) { return createBlockSound("mystical_tree", type); }

    private static SoundEvent register(String id) {
        Identifier identifier = new Identifier(MOD_ID, id);
        return Registry.register(Registry.SOUND_EVENT, identifier, new SoundEvent(identifier));
    }

    private static SoundEvent createItemSound(String item, String id) { return register("item." + item + "." + id); }
    private static SoundEvent createBlockSound(String block, String id) { return register("block." + block + "." + id); }
}
