package com.mikanon.tangerinetree.biome.features.generic;

import com.mikanon.tangerinetree.TangerineTree;
import com.mikanon.tangerinetree.items.TangerineFruit;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.BlockLeaves;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import java.util.List;

public class CustomLeaf extends BlockLeaves {

    //texturas para tipo de renderer
    public static final String[][] leafTypes = new String[][] {
            {"leaves_tangerine"},
            {"leaves_tangerine_opaque"}
    };
    public static String[] leaves = new String[]{"tangerine"};

    protected void func_150124_c(World world, int x, int y, int z, int side, int meta) {

        if (world.rand.nextInt(5) == 2) {
            this.dropBlockAsItem(world, x, y, z, new ItemStack(TangerineTree.tangerineFruit, 1, 0));
        } else if ((side & 3) == 1 && world.rand.nextInt(meta) == 0){
            this.dropBlockAsItem(world, x, y, z, new ItemStack(TangerineTree.tangerineSapling, 1, 0));
        }
    }

    @Override
    public int colorMultiplier(IBlockAccess p_149720_1_, int p_149720_2_, int p_149720_3_, int p_149720_4_) {
        return 0xffffff;
    }

    public int damageDropped(int i) {
        return super.damageDropped(i) + 4;
    }

    public int getDamageValue(World world, int x, int y, int z) {
        return world.getBlockMetadata(x, y, z) & 3;
    }

    @SideOnly(Side.CLIENT)
    public void getSubBlocks(Item item, CreativeTabs tab, List list) {
        for (int i=0; i<leaves.length; i++){
            list.add(new ItemStack(item, 1, i));
        }
    }

    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister register) {
        for (int i = 0; i < leafTypes.length; ++i){
            this.field_150129_M[i] = new IIcon[leafTypes[i].length];
            for (int j = 0; j < leafTypes[i].length; ++j){
                this.field_150129_M[i][j] = register.registerIcon(TangerineTree.MODID + ":" + leafTypes[i][j]);
            }
        }
    }

    @Override
    public IIcon getIcon(int side, int meta) {
        this.setGraphicsLevel(Minecraft.getMinecraft().gameSettings.fancyGraphics);
        return (meta & 3) == 1 ? this.field_150129_M[this.field_150127_b][1] : this.field_150129_M[this.field_150127_b][0];
    }

    @Override
    public boolean isOpaqueCube() {
        return !this.field_150121_P;
    }

    @Override
    public boolean renderAsNormalBlock() {
        return false;
    }

    @Override
    public String[] func_150125_e() {
        return leaves;
    }
}
