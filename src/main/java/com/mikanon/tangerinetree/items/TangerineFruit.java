package com.mikanon.tangerinetree.items;

import com.mikanon.tangerinetree.TangerineTree;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFood;

public class TangerineFruit extends ItemFood {

    public TangerineFruit(){
        super(3, 0.3F, false);
    }

    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister iconRegister){
        this.itemIcon = iconRegister.registerIcon(TangerineTree.MODID + ":" + this.getUnlocalizedName().substring(5));
    }

}
