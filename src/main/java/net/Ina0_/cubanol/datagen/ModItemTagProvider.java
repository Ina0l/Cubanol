package net.Ina0_.cubanol.datagen;

import net.Ina0_.cubanol.Cubanol;
import net.Ina0_.cubanol.block.ModBlocks;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModItemTagProvider extends ItemTagsProvider {

    public ModItemTagProvider(
            PackOutput output,
            CompletableFuture<HolderLookup.Provider> lookupProvider,
            CompletableFuture<TagLookup<Block>> blockTags,
            @Nullable ExistingFileHelper existingFileHelper
    ) {
        super(output, lookupProvider, blockTags, Cubanol.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.@NotNull Provider provider) {
        tag(ItemTags.LOGS_THAT_BURN)
                .add(ModBlocks.APPLE_TREE_LOG.asItem())
                .add(ModBlocks.STRIPPED_APPLE_TREE_LOG.asItem())
                .add(ModBlocks.APPLE_TREE_WOOD.asItem())
                .add(ModBlocks.STRIPPED_APPLE_TREE_WOOD.asItem());

        tag(ItemTags.PLANKS)
                .add(ModBlocks.APPLE_TREE_PLANKS.asItem());
    }
}
