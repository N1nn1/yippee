package com.ninni.yippee.world;

import com.ninni.yippee.Yippee;
import com.ninni.yippee.mixin.TreeDecoratorTypeAccessor;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.treedecorator.TreeDecoratorType;

public class YippeeTreeDecorators {

    public static void init() { }

    public static final TreeDecoratorType<MysticalTreeLogDecorator> MYSTICAL_TREE_LOG = TreeDecoratorTypeAccessor.callRegister(new Identifier(Yippee.MOD_ID, "mystical_tree_log").toString(), MysticalTreeLogDecorator.CODEC);

}
