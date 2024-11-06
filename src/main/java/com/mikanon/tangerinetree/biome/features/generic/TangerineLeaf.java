package com.mikanon.tangerinetree.biome.features.generic;

import com.mikanon.tangerinetree.TangerineTree;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockLeaves;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

import java.util.List;
import java.util.Random;

public class TangerineLeaf extends BlockLeaves {

    private static final String[][] leafTypes = new String[][] {
            {"leaves_tangerine", "leaves_tangerine_fruit", "leaves_tangerine_flower"},
            {"leaves_tangerine_opaque", "leaves_tangerine_fruit_opaque", "leaves_tangerine_flower_opaque"}
    };
    private static final String[] leaves = new String[]{"tangerine"};
    private int RANDOM_SEASON;

    public TangerineLeaf(){
        this.rollLeaves();
    }

    public void rollLeaves(){
        this.RANDOM_SEASON = (int) (Math.random() * leafTypes[0].length);
    }

    protected void func_150124_c(World world, int x, int y, int z, int side, int meta) {
        if (world.rand.nextInt(5) == 2) {
            this.dropBlockAsItem(world, x, y, z, new ItemStack(TangerineTree.tangerineFruit, 1, 0));
        } else if ((side & 3) == 1 && world.rand.nextInt(meta) == 0) {
            this.dropBlockAsItem(world, x, y, z, new ItemStack(TangerineTree.tangerineSapling, 1, 0));
        }
    }

    @Override
    public Block setBlockName(String name) {
        return super.setBlockName(name);
    }

    @Override
    public int colorMultiplier(IBlockAccess world, int x, int y, int z) {
        return 0xffffff;
    }

	@Override
	public int damageDropped(int meta) {
		return 0;
	}

    @Override
    public int getDamageValue(World world, int x, int y, int z) {
        return world.getBlockMetadata(x, y, z) & 3;
    }

    @SideOnly(Side.CLIENT)
    public void getSubBlocks(Item item, CreativeTabs tab, List list) {
        for (int i = 0; i < leaves.length; i++) {
            list.add(new ItemStack(item, 1, i));
        }
    }

    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister register) {
        for (int i = 0; i < leafTypes.length; ++i) {
            this.field_150129_M[i] = new IIcon[leafTypes[i].length];
            for (int j = 0; j < leafTypes[i].length; ++j) {
                this.field_150129_M[i][j] = register.registerIcon(TangerineTree.MODID + ":" + leafTypes[i][j]);
            }
        }
    }

    @Override
    public Item getItemDropped(int meta, Random random, int fortune) {
        return Item.getItemFromBlock(TangerineTree.tangerineSapling);
    }

    @Override
    public IIcon getIcon(int side, int meta) {

        this.setGraphicsLevel(Minecraft.getMinecraft().gameSettings.fancyGraphics);

        if (side == 1) {
            return this.field_150129_M[this.field_150127_b][0];
        }
		switch (meta){
			case 0:
				return this.field_150129_M[this.field_150127_b][0];
			case 1:
				return this.field_150129_M[this.field_150127_b][RANDOM_SEASON];
			case 2:
				return this.field_150129_M[this.field_150127_b][RANDOM_SEASON];
		}
        return this.field_150129_M[this.field_150127_b][0];
    }

    @Override
    public boolean isFlammable(IBlockAccess world, int x, int y, int z, ForgeDirection face) {
        return true;
    }

    @Override
    public boolean isOpaqueCube() {
        return !Minecraft.getMinecraft().gameSettings.fancyGraphics;
    }

    @Override
    public void setGraphicsLevel(boolean fancy) {
        this.field_150121_P = !fancy;
        this.field_150127_b = fancy ? 0 : 1;
    }

    @Override
    public boolean renderAsNormalBlock() {
        return super.renderAsNormalBlock();
    }

    @Override
    public String[] func_150125_e() {
        return leaves;
    }

    @Override
    public int getRenderBlockPass() {
        return super.getRenderBlockPass();
    }

    @Override
    public boolean shouldSideBeRendered(IBlockAccess world, int x, int y, int z, int side) {
        return !this.field_150121_P || super.shouldSideBeRendered(world, x, y, z, side);
    }
}

