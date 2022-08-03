package com.ninni.yippee.util;

import com.ninni.yippee.init.YippeeItems;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public class YippeeCreativeModeTab extends CreativeModeTab {

    public YippeeCreativeModeTab(String label) {
        super(label);
    }

    @Override
    public ItemStack makeIcon() {
        return new ItemStack(YippeeItems.YIPPEE.get());
    }
}
