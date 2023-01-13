package com.mikanon.tangerinetree.ui;

import com.mikanon.tangerinetree.TangerineTree;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class TangerineTab extends CreativeTabs {
    public TangerineTab() {
        super(TangerineTree.MODID);
    }

    @SideOnly(Side.CLIENT)
    public Item getTabIconItem() {
        return Item.getItemFromBlock(TangerineTree.tangerineSapling);
    }
}
