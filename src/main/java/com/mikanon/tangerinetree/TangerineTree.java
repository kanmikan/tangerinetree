package com.mikanon.tangerinetree;

import com.mikanon.tangerinetree.biome.features.generic.CustomLeaf;
import com.mikanon.tangerinetree.biome.features.generic.CustomLog;
import com.mikanon.tangerinetree.biome.features.generic.CustomSapling;
import com.mikanon.tangerinetree.blocks.TangerinePlankBlock;
import com.mikanon.tangerinetree.items.TangerineFruit;
import com.mikanon.tangerinetree.items.TangerineLeafBlock;
import com.mikanon.tangerinetree.items.TangerineLogBlock;
import com.mikanon.tangerinetree.items.TangerineSaplingBlock;
import com.mikanon.tangerinetree.ui.TangerineTab;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;

import net.minecraft.block.Block;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;


@Mod(modid = TangerineTree.MODID, version = TangerineTree.VERSION)
public class TangerineTree {

    public static final String MODID = "tangerinetree";
    public static final String VERSION = "1.0";

    public static TangerineTab tab;
    public static Block tangerineLog;
    public static Block tangerineLeaf;
    public static Block tangerineSapling;
    public static Block tangerinePlank;

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

        //Registro
        GameRegistry.registerBlock(tangerineLog, TangerineLogBlock.class, tangerineLog.getUnlocalizedName().substring(5));
        GameRegistry.registerBlock(tangerineLeaf, TangerineLeafBlock.class, tangerineLeaf.getUnlocalizedName().substring(5));
        GameRegistry.registerBlock(tangerineSapling, TangerineSaplingBlock.class, tangerineSapling.getUnlocalizedName().substring(5));
        GameRegistry.registerItem(tangerineFruit, "tangerine_fruit");
        GameRegistry.registerBlock(tangerinePlank, "tangerine_plank");

    }

    @EventHandler
    public void init(FMLInitializationEvent event){

        GameRegistry.addShapelessRecipe(new ItemStack(tangerinePlank, 4), new ItemStack(tangerineLog, 1));

    }
    
    @EventHandler
    public void PostInit(FMLPostInitializationEvent event){
        //registro de flamabilidad
        Blocks.fire.setFireInfo(tangerinePlank, 5, 20);
        Blocks.fire.setFireInfo(tangerineLog, 5, 5);
        Blocks.fire.setFireInfo(tangerineLeaf, 5, 60);
    }
    
}
