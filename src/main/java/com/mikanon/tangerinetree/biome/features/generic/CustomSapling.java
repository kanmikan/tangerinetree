package com.mikanon.tangerinetree.biome.features.generic;

import com.mikanon.tangerinetree.TangerineTree;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockSapling;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.*;

import java.util.List;
import java.util.Random;

//CLASE GENERICA COPIADA DE LOS MAPPINGS DE FORGE
public class CustomSapling extends BlockSapling {

    public static final String[] saplings = new String[] {"tangerine"};
    private static final IIcon[] iconLenght = new IIcon[saplings.length];

    public CustomSapling(){
        float f = 0.4F;
        this.setBlockBounds(0.5F - f, 0.0F, 0.5F - f, 0.5F + f, f * 2.0F, 0.5F + f);
        this.setCreativeTab(TangerineTree.tab);
    }

    public void updateTick(World world, int x, int y, int z, Random random){
        if (!world.isRemote){
            super.updateTick(world, x, y, z, random);
            if (world.getBlockLightValue(x, y + 1, z) >= 9 && random.nextInt(7) == 0){
                this.func_149879_c(world, x, y, z, random);
            }
        }
    }

    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int side, int meta){
        meta &= 7;
        return iconLenght[MathHelper.clamp_int(meta, 0, 5)];
    }

    public void func_149879_c(World world, int x, int y, int z, Random random){
        int l = world.getBlockMetadata(x, y, z);
        if ((l & 8) == 0){
            world.setBlockMetadataWithNotify(x, y, z, l | 8, 4);
        } else {
            this.func_149878_d(world, x, y, z, random);
        }
    }

    //SPAWN DEL ARBOL DESDE EL SAPLING
    @Override
    public void func_149878_d(World world, int x, int y, int z, Random random) {
        if (!net.minecraftforge.event.terraingen.TerrainGen.saplingGrowTree(world, random, x, y, z)) return;

        int metadata = world.getBlockMetadata(x, y, z) & 7;
        int i1 = 0, j1 = 0;
        boolean flag = false;

        //ignorar las demas variedades.
        Object object = random.nextInt(10) == 0 ? new WorldGenBigTree(true) : new WorldGenTrees(true);
        switch (metadata) {
            case 0:
                object = new WorldGenCustomTree(TangerineTree.tangerineLog, TangerineTree.tangerineLeaf, 0, 0, false, 4, 5, false);
                break;
            default:
                break;
        }

        Block block = Blocks.air;
        if (flag){
            world.setBlock(x + i1, y, z + j1, block, 0, 4);
            world.setBlock(x + i1 + 1, y, z + j1, block, 0, 4);
            world.setBlock(x + i1, y, z + j1 + 1, block, 0, 4);
            world.setBlock(x + i1 + 1, y, z + j1 + 1, block, 0, 4);
        } else {
            world.setBlock(x, y, z, block, 0, 4);
        }

        if (!((WorldGenerator)object).generate(world, random, x + i1, y, z + j1)) {
            if (flag) {
                world.setBlock(x + i1, y, z + j1, this, metadata, 4);
                world.setBlock(x + i1 + 1, y, z + j1, this, metadata, 4);
                world.setBlock(x + i1, y, z + j1 + 1, this, metadata, 4);
                world.setBlock(x + i1 + 1, y, z + j1 + 1, this, metadata, 4);
            } else {
                world.setBlock(x, y, z, this, metadata, 4);
            }
        }
    }

    public boolean func_149880_a(World world, int x, int y, int z, int p_149880_5_) {
        return world.getBlock(x, y, z) == this && (world.getBlockMetadata(x, y, z) & 7) == p_149880_5_;
    }

    public int damageDropped(int p_149692_1_) {
        return MathHelper.clamp_int(p_149692_1_ & 7, 0, 5);
    }

    @SideOnly(Side.CLIENT)
    public void getSubBlocks(Item item, CreativeTabs tabs, List list) {
        for (int i=0; i<saplings.length; i++){
            list.add(new ItemStack(item, 1, i));
        }
    }

    @Override
    public Block setBlockName(String p_149663_1_) {
        return super.setBlockName(p_149663_1_);
    }

    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister register) {
        for (int i = 0; i < iconLenght.length; ++i) {
            iconLenght[i] = register.registerIcon(TangerineTree.MODID + ":" + this.getUnlocalizedName().substring(5) + "_" + saplings[i]);
        }
    }

    public boolean func_149851_a(World world, int x, int y, int z, boolean p_149851_5_) {
        return true;
    }

    public boolean func_149852_a(World world, Random random, int p_149852_3_, int p_149852_4_, int p_149852_5_) {
        return (double)world.rand.nextFloat() < 0.45D;
    }

    public void func_149853_b(World world, Random random, int p_149853_3_, int p_149853_4_, int p_149853_5_) {
        this.func_149879_c(world, p_149853_3_, p_149853_4_, p_149853_5_, random);
    }

}
