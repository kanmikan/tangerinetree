package com.mikanon.tangerinetree.blocks;

import com.mikanon.tangerinetree.TangerineTree;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockSlab;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

import java.util.List;
import java.util.Random;

public class TangerineSlab extends BlockSlab {
    private IIcon baseIcon;

    public TangerineSlab(boolean isHalf) {
        super(isHalf, TangerineTree.tangerinePlank.getMaterial());
        this.setHardness(3.0F);
        this.setResistance(2.0F);
        this.setStepSound(soundTypeWood);
        this.useNeighborBrightness = true;
        this.setCreativeTab(TangerineTree.tab);
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void getSubBlocks(Item item, CreativeTabs tabs, List list) {
        if (item != Item.getItemFromBlock(TangerineTree.tangerineDoubleSlab)) {
            list.add(new ItemStack(item, 1, 0));
        }
    }

    @SideOnly(Side.CLIENT)
    private static boolean isBlockSingleSlab(Block block) {
        return block == TangerineTree.tangerineSlab;
    }

    @SideOnly(Side.CLIENT)
    @Override
    public Item getItem(World world, int x, int y, int z) {
        return isBlockSingleSlab(this) ? Item.getItemFromBlock(this) : Item.getItemFromBlock(TangerineTree.tangerineSlab);
    }

    @Override
    public Item getItemDropped(int a, Random random, int b) {
        return Item.getItemFromBlock(TangerineTree.tangerineSlab);
    }

    @Override
    protected ItemStack createStackedBlock(int p_149644_1_) {
        return new ItemStack(TangerineTree.tangerineSlab, 2, 0);
    }

    @Override
    public String func_150002_b(int i) {
        return this.getUnlocalizedName();
    }

    @Override
    public IIcon getIcon(int side, int meta) {
        //int index = meta & 7;
        if (this.field_150004_a && (meta & 2) != 0) { side = 1;}
        if (side == 1) {
            return this.baseIcon;
        } else {
            return (side == 0) ? this.baseIcon : this.blockIcon;
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister registry) {
        this.blockIcon = registry.registerIcon(TangerineTree.MODID + ":tangerine_slab");
        this.baseIcon = registry.registerIcon(TangerineTree.MODID + ":tangerine_plank");
    }
}
