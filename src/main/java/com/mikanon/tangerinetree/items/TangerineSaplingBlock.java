package com.mikanon.tangerinetree.items;

import com.mikanon.tangerinetree.TangerineTree;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class TangerineSaplingBlock extends ItemBlock {

    public static final String[] saplings = new String[]{"tangerine"};

    public TangerineSaplingBlock(Block block) {
        super(block);
        this.setHasSubtypes(true);
    }

    public String getUnlocalizedName(ItemStack stack){
        int i = stack.getItemDamage();
        if (i < 0 || i >= saplings.length){
            i = 0;
        }
        return super.getUnlocalizedName() + "." + saplings[i];
    }

    public int getMetadata(int meta){
        return meta;
    }


}
