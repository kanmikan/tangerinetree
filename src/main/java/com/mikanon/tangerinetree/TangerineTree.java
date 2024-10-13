package com.mikanon.tangerinetree;

import com.mikanon.tangerinetree.biome.features.generic.CustomLeaf;
import com.mikanon.tangerinetree.biome.features.generic.CustomLog;
import com.mikanon.tangerinetree.biome.features.generic.CustomSapling;
import com.mikanon.tangerinetree.blocks.TangerinePlankBlock;
import com.mikanon.tangerinetree.blocks.TangerineSlab;
import com.mikanon.tangerinetree.blocks.TangerineStairs;
import com.mikanon.tangerinetree.items.*;
import com.mikanon.tangerinetree.ui.TangerineTab;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;

import net.minecraft.block.Block;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import net.minecraft.block.BlockSlab;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;


@Mod(modid = TangerineTree.MODID, version = TangerineTree.VERSION)
public class TangerineTree {

    public static final String MODID = "tangerinetree";
    public static final String VERSION = "1.2";

    public static TangerineTab tab;
    public static Block tangerineLog;
    public static Block tangerineLeaf;
    public static Block tangerineSapling;
    public static Block tangerinePlank;
    public static BlockSlab tangerineSlab, tangerineDoubleSlab;
    public static Block tangerineStairs;

    public static Item tangerineFruit;

    @EventHandler
    public void PreInit(FMLPreInitializationEvent event){

        //Inicializacion
        tab = new TangerineTab();
        tangerineLog = new CustomLog().setBlockName("log").setCreativeTab(tab);
        tangerineLeaf = new CustomLeaf().setBlockName("leaf").setCreativeTab(tab);
        tangerineSapling = new CustomSapling().setBlockName("sapling").setCreativeTab(tab);
        tangerineFruit = new TangerineFruit().setUnlocalizedName("tangerine_fruit");
        tangerinePlank = new TangerinePlankBlock(Material.wood).setBlockName("tangerine_plank");
        tangerineSlab = (BlockSlab) new TangerineSlab(false).setBlockName("tangerine_slab");
        tangerineDoubleSlab = (BlockSlab) new TangerineSlab(true).setBlockName("tangerine_double_slab");
        tangerineStairs = new TangerineStairs().setBlockName("tangerine_stairs");

        //Registro
        GameRegistry.registerBlock(tangerineLog, TangerineLogBlock.class, tangerineLog.getUnlocalizedName().substring(5));
        GameRegistry.registerBlock(tangerineLeaf, TangerineLeafBlock.class, tangerineLeaf.getUnlocalizedName().substring(5));
        GameRegistry.registerBlock(tangerineSapling, TangerineSaplingBlock.class, tangerineSapling.getUnlocalizedName().substring(5));
        GameRegistry.registerItem(tangerineFruit, "tangerine_fruit");
        GameRegistry.registerBlock(tangerinePlank, "tangerine_plank");
        GameRegistry.registerBlock(tangerineSlab, ItemTangerineSlab.class, "tangerine_slab");
        GameRegistry.registerBlock(tangerineDoubleSlab, ItemTangerineSlab.class, "tangerine_double_slab");
        GameRegistry.registerBlock(tangerineStairs, "tangerine_stairs");

    }

    @EventHandler
    public void init(FMLInitializationEvent event){
        GameRegistry.addShapelessRecipe(new ItemStack(tangerinePlank, 4), new ItemStack(tangerineLog, 1));

        GameRegistry.addRecipe(new ItemStack(tangerineStairs, 4),
                "X  ",
                "XX ",
                "XXX",
                'X', new ItemStack(tangerinePlank));

        GameRegistry.addRecipe(new ItemStack(tangerineSlab, 6),
                "XXX",
                'X', new ItemStack(tangerinePlank));

        GameRegistry.addRecipe(new ItemStack(Blocks.crafting_table, 1),
                "XX",
                "XX",
                'X', new ItemStack(tangerinePlank));

        GameRegistry.addRecipe(new ItemStack(Items.stick, 4),
                "X",
                "X",
                'X', new ItemStack(tangerinePlank));

        GameRegistry.addRecipe(new ItemStack(Blocks.chest, 1),
                "XXX",
                "X X",
                "XXX",
                'X', new ItemStack(tangerinePlank));

        GameRegistry.addRecipe(new ItemStack(Items.wooden_sword, 1),
                "X",
                "X",
                "S",
                'X', new ItemStack(tangerinePlank),
                'S', new ItemStack(Items.stick));

        GameRegistry.addRecipe(new ItemStack(Items.wooden_pickaxe, 1),
                "XXX",
                " S ",
                " S ",
                'X', new ItemStack(tangerinePlank),
                'S', new ItemStack(Items.stick));

        GameRegistry.addRecipe(new ItemStack(Items.wooden_axe, 1),
                "XX",
                "XS",
                " S",
                'X', new ItemStack(tangerinePlank),
                'S', new ItemStack(Items.stick));

        GameRegistry.addRecipe(new ItemStack(Items.wooden_shovel, 1),
                "X",
                "S",
                "S",
                'X', new ItemStack(tangerinePlank),
                'S', new ItemStack(Items.stick));

        GameRegistry.addRecipe(new ItemStack(Items.wooden_hoe, 1),
                "XX",
                " S",
                " S",
                'X', new ItemStack(tangerinePlank),
                'S', new ItemStack(Items.stick));

    }
    
    @EventHandler
    public void PostInit(FMLPostInitializationEvent event){
        //registro de flamabilidad
        Blocks.fire.setFireInfo(tangerinePlank, 5, 20);
        Blocks.fire.setFireInfo(tangerineLog, 5, 5);
        Blocks.fire.setFireInfo(tangerineLeaf, 5, 60);
        Blocks.fire.setFireInfo(tangerineSlab, 5, 20);
        Blocks.fire.setFireInfo(tangerineStairs, 5, 20);

    }
    
}
