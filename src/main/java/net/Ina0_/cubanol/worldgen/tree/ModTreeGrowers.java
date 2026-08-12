package net.Ina0_.cubanol.worldgen.tree;

import net.Ina0_.cubanol.Cubanol;
import net.Ina0_.cubanol.worldgen.ModConfiguredFeatures;
import net.minecraft.world.level.block.grower.TreeGrower;

import java.util.Optional;

public class ModTreeGrowers {

    public static final TreeGrower APPLE_TREE = new TreeGrower(
            Cubanol.MOD_ID + "apple_tree",
            Optional.empty(),
            Optional.of(ModConfiguredFeatures.APPLE_TREE_KEY),
            Optional.empty()
    );

    public static final TreeGrower ORANGE_TREE = new TreeGrower(
            Cubanol.MOD_ID + "orange_tree",
            Optional.empty(),
            Optional.of(ModConfiguredFeatures.ORANGE_TREE_KEY),
            Optional.empty()
    );

}
