package net.Ina0_.cubanol.datagen;

import net.Ina0_.cubanol.Cubanol;
import net.Ina0_.cubanol.block.ModBlocks;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagProvider extends BlockTagsProvider {
    public ModBlockTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, Cubanol.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.@NotNull Provider provider) {
        tag(BlockTags.MINEABLE_WITH_PICKAXE)
                .add(ModBlocks.FAKE_WINE_BOTTLE.get());

        tag(BlockTags.MINEABLE_WITH_AXE)
                .add(ModBlocks.OAK_TABLE.get())
                .add(ModBlocks.SPRUCE_TABLE.get())
                .add(ModBlocks.BIRCH_TABLE.get())
                .add(ModBlocks.JUNGLE_TABLE.get())
                .add(ModBlocks.ACACIA_TABLE.get())
                .add(ModBlocks.DARK_OAK_TABLE.get())
                .add(ModBlocks.MANGROVE_TABLE.get())
                .add(ModBlocks.CHERRY_TABLE.get())
                .add(ModBlocks.CRIMSON_TABLE.get())
                .add(ModBlocks.WARPED_TABLE.get())
                .add(ModBlocks.BAMBOO_TABLE.get())
                .add(ModBlocks.APPLE_TREE_TABLE.get())

                .add(ModBlocks.CROP_SUPPORT.get())
                .add(ModBlocks.GRAPE_CROP.get())

                .add(ModBlocks.APPLE_TREE_LOG.get())
                .add(ModBlocks.APPLE_TREE_WOOD.get())
                .add(ModBlocks.STRIPPED_APPLE_TREE_LOG.get())
                .add(ModBlocks.STRIPPED_APPLE_TREE_WOOD.get())
                .add(ModBlocks.APPLE_TREE_PLANKS.get())

                .add(ModBlocks.APPLE_TREE_STAIR.get())
                .add(ModBlocks.APPLE_TREE_SLAB.get())
                .add(ModBlocks.APPLE_TREE_BUTTON.get())
                .add(ModBlocks.APPLE_TREE_PRESSURE_PLATE.get())
                .add(ModBlocks.APPLE_TREE_TRAPDOOR.get())
                .add(ModBlocks.APPLE_TREE_DOOR.get())
                .add(ModBlocks.APPLE_TREE_FENCE.get())
                .add(ModBlocks.APPLE_TREE_FENCE_GATE.get());

        tag(BlockTags.MINEABLE_WITH_HOE)
                .add(ModBlocks.APPLE_TREE_LEAVES.get())
                .add(ModBlocks.GROWING_APPLE_TREE_LEAVES.get());

        tag(BlockTags.MAINTAINS_FARMLAND)
                .add(Blocks.DIRT);

        tag(BlockTags.LOGS_THAT_BURN)
                .add(ModBlocks.APPLE_TREE_LOG.get())
                .add(ModBlocks.STRIPPED_APPLE_TREE_LOG.get())
                .add(ModBlocks.APPLE_TREE_WOOD.get())
                .add(ModBlocks.STRIPPED_APPLE_TREE_WOOD.get());

        tag(BlockTags.WOODEN_FENCES)
                .add(ModBlocks.APPLE_TREE_FENCE.get());

        tag(BlockTags.FENCE_GATES)
                .add(ModBlocks.APPLE_TREE_FENCE_GATE.get());

        tag(BlockTags.STANDING_SIGNS)
                .add(ModBlocks.APPLE_TREE_SIGN.get());

        tag(BlockTags.WALL_SIGNS)
                .add(ModBlocks.APPLE_TREE_WALL_SIGN.get());
    }
}
