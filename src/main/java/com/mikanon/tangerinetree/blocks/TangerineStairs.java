package com.mikanon.tangerinetree.blocks;

import com.mikanon.tangerinetree.TangerineTree;
import net.minecraft.block.BlockStairs;

public class TangerineStairs extends BlockStairs {
    public TangerineStairs() {
        super(TangerineTree.tangerinePlank, 0);
        this.setHardness(3.0F);
        this.setResistance(2.0F);
        this.setStepSound(soundTypeWood);
        this.setCreativeTab(TangerineTree.tab);
    }
}
