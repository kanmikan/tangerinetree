package com.mikanon.tangerinetree.biome.features.generic;

import com.mikanon.tangerinetree.TangerineTree;
import net.minecraft.block.Block;
import net.minecraft.block.BlockSapling;
import net.minecraft.init.Blocks;
import net.minecraft.util.Direction;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraftforge.common.util.ForgeDirection;

import java.util.Random;

public class WorldGenCustomTree extends WorldGenAbstractTree {

    private final int minTreeHeight;
    private final boolean vinesGrow;
    private final int metaWood;
    private final int metaLeaves;

    private final Block wood;
    private final Block leaves;

    public WorldGenCustomTree(Block wood, Block leaves, int metaWood, int metaLeaves) {
        this(wood, leaves, metaWood, metaLeaves, false, 4, 6, false);
    }

    public WorldGenCustomTree(Block wood, Block leaves, int metaWood, int metaLeaves, boolean doBlockNotify, int minTreeHeight, int randomTreeHeight, boolean vinesGrow) {
        super(doBlockNotify);
        this.wood = wood;
        this.leaves = leaves;
        this.minTreeHeight = minTreeHeight;
        this.metaWood = metaWood;
        this.metaLeaves = metaLeaves;
        this.vinesGrow = vinesGrow;
    }

    public boolean generate(World world, Random random, int x, int y, int z) {
		int height = random.nextInt(3) + this.minTreeHeight;
		boolean flag = true;

		if (y >= 1 && y + height + 1 <= 256) {
			byte b0;
			int k1;
			Block block;

			for (int i1 = y; i1 <= y + 1 + height; ++i1) {
				b0 = 1;
				if (i1 == y) {
					b0 = 0;
				}
				if (i1 >= y + 1 + height - 2) {
					b0 = 2;
				}
				for (int j1 = x - b0; j1 <= x + b0 && flag; ++j1) {
					for (k1 = z - b0; k1 <= z + b0 && flag; ++k1) {
						if (i1 >= 0 && i1 < 256) {
							block = world.getBlock(j1, i1, k1);
							if (!this.isReplaceable(world, j1, i1, k1)) {
								flag = false;
							}
						} else {
							flag = false;
						}
					}
				}
			}

			if (!flag) {
				return false;
			} else {
				Block soilBlock = world.getBlock(x, y - 1, z);
				boolean isSoil = soilBlock.canSustainPlant(world, x, y - 1, z, ForgeDirection.UP, (BlockSapling) TangerineTree.tangerineSapling);
				if (isSoil && y < 256 - height - 1) {
					soilBlock.onPlantGrow(world, x, y - 1, z, x, y, z);
					b0 = 3;

					for (k1 = y - b0 + height; k1 <= y + height; ++k1) {
						int relativeHeight = k1 - (y + height);
						int leafRadius = 1 - relativeHeight / 2;

						for (int i2 = x - leafRadius; i2 <= x + leafRadius; ++i2) {
							int dx = i2 - x;
							for (int j2 = z - leafRadius; j2 <= z + leafRadius; ++j2) {
								int dz = j2 - z;

								if (Math.abs(dx) != leafRadius || Math.abs(dz) != leafRadius || random.nextInt(2) != 0 && relativeHeight != 0) {
									Block leafBlock = world.getBlock(i2, k1, j2);
									if (leafBlock.isAir(world, i2, k1, j2) || leafBlock.isLeaves(world, i2, k1, j2)) {
										int meta = (relativeHeight >= -1) ? 0 : (random.nextBoolean() ? 1 : 2);  // Top es 0, bottom random entre 1 (fruit) y 2 (flower)
										this.setBlockAndNotifyAdequately(world, i2, k1, j2, leaves, meta);
									}
								}
							}
						}
					}

					for (k1 = 0; k1 < height; ++k1) {
						block = world.getBlock(x, y + k1, z);
						if (block.isAir(world, x, y + k1, z) || block.isLeaves(world, x, y + k1, z)) {
							this.setBlockAndNotifyAdequately(world, x, y + k1, z, wood, this.metaWood);
						}
					}

					return true;
				} else {
					return false;
				}
			}
		} else {
			return false;
		}
	}

	/*
    private void growVines(World p_76529_1_, int p_76529_2_, int p_76529_3_, int p_76529_4_, int p_76529_5_) {
        this.setBlockAndNotifyAdequately(p_76529_1_, p_76529_2_, p_76529_3_, p_76529_4_, Blocks.vine, p_76529_5_);
        int i1 = 4;

        while (true) {
            --p_76529_3_;

            if (!p_76529_1_.getBlock(p_76529_2_, p_76529_3_, p_76529_4_).isAir(p_76529_1_, p_76529_2_, p_76529_3_, p_76529_4_) || i1 <= 0) {
                return;
            }

            this.setBlockAndNotifyAdequately(p_76529_1_, p_76529_2_, p_76529_3_, p_76529_4_, Blocks.vine, p_76529_5_);
            --i1;
        }
    }
    */

}
