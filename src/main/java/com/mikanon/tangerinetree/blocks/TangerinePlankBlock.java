package com.mikanon.tangerinetree.blocks;

import com.mikanon.tangerinetree.TangerineTree;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.common.util.ForgeDirection;

public class TangerinePlankBlock extends Block {
    public TangerinePlankBlock(Material material) {
        super(material);
        this.setHardness(3.0F);
        this.setResistance(2.0F);
        this.setStepSound(soundTypeWood);
        this.setCreativeTab(TangerineTree.tab);
    }

    @Override
    public Block setBlockName(String p_149663_1_) {
        return super.setBlockName(p_149663_1_);
    }

    @Override
    public boolean isFlammable(IBlockAccess world, int x, int y, int z, ForgeDirection face) {
        return true;
    }

    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister register) {
        this.blockIcon = register.registerIcon(TangerineTree.MODID + ":" + this.getUnlocalizedName().substring(5));
    }
}
