package com.mikanon.tangerinetree.biome.features.generic;

import com.mikanon.tangerinetree.TangerineTree;
import com.mikanon.tangerinetree.biome.features.generic.WorldGenCustomTree;
import cpw.mods.fml.common.IWorldGenerator;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.IChunkProvider;

import java.util.Random;

public class TangerineTreeWorldGenerator implements IWorldGenerator {

    public TangerineTreeWorldGenerator() {}

    @Override
    public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider) {
        if (world.provider.dimensionId == 0) {
            if (random.nextInt(10) == 0) {
                int x = chunkX * 16 + random.nextInt(16);
                int z = chunkZ * 16 + random.nextInt(16);
                int y = world.getHeightValue(x, z);

                BiomeGenBase biome = world.getBiomeGenForCoords(x, z);
                if (biome.biomeName.equals("Forest") || biome.biomeName.equals("Plains") || biome.biomeName.equals("Birch Forest")) {
                    Block ground = world.getBlock(x, y - 1, z);
                    if (ground == Blocks.grass || ground == Blocks.dirt) {
                        //si todo se cumple, generar
                        new WorldGenCustomTree(TangerineTree.tangerineLog, TangerineTree.tangerineLeaf, 0, 0).generate(world, random, x, y, z);
                    }
                }
            }
        }
    }


}
