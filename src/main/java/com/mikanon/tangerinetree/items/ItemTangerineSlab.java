package com.mikanon.tangerinetree.items;

import com.mikanon.tangerinetree.TangerineTree;
import net.minecraft.block.Block;
import net.minecraft.item.ItemSlab;

public class ItemTangerineSlab extends ItemSlab {
    public ItemTangerineSlab(Block block) {
        super(block, TangerineTree.tangerineSlab, TangerineTree.tangerineDoubleSlab, block == TangerineTree.tangerineDoubleSlab);
    }
}
