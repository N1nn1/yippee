package com.ninni.yippee.util;

import com.ninni.yippee.Yippee;
import net.minecraft.util.Identifier;

public interface YippeePacketIdentifiers {
    Identifier FLATTEN = create("flatten");

    static Identifier create(String id) {
        return new Identifier(Yippee.MOD_ID, id);
    }
}
