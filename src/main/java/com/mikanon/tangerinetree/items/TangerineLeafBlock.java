package com.mikanon.tangerinetree.items;

import com.mikanon.tangerinetree.TangerineTree;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class TangerineLeafBlock extends ItemBlock {

    public static final String[] leaves = new String[]{"tangerine"};

    public TangerineLeafBlock(Block block) {
        super(block);
        this.setHasSubtypes(true);
    }

    public String getUnlocalizedName(ItemStack stack){
        int i = stack.getItemDamage();
        if (i < 0 || i >= leaves.length){
            i = 0;
        }
        return super.getUnlocalizedName() + "." + leaves[i];
    }

    public int getMetadata(int meta){
        return meta;
    }

}
