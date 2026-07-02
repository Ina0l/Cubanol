package net.Ina0_.cubanol.datagen;

import net.Ina0_.cubanol.block.ModBlocks;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.level.block.Block;

import java.util.Set;

public class ModBlockLootTableProvider extends BlockLootSubProvider {
    protected ModBlockLootTableProvider(HolderLookup.Provider registries) {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags(), registries);
    }

    @Override
    protected void generate() {
        dropSelf(ModBlocks.OAK_TABLE.get());
        dropSelf(ModBlocks.SPRUCE_TABLE.get());
        dropSelf(ModBlocks.BIRCH_TABLE.get());
        dropSelf(ModBlocks.JUNGLE_TABLE.get());
        dropSelf(ModBlocks.ACACIA_TABLE.get());
        dropSelf(ModBlocks.DARK_OAK_TABLE.get());
        dropSelf(ModBlocks.MANGROVE_TABLE.get());
        dropSelf(ModBlocks.CHERRY_TABLE.get());
        dropSelf(ModBlocks.CRIMSON_TABLE.get());
        dropSelf(ModBlocks.WARPED_TABLE.get());
        dropSelf(ModBlocks.BAMBOO_TABLE.get());
        dropSelf(ModBlocks.FAKE_WINE_BOTTLE.get());
    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return ModBlocks.BLOCKS.getEntries().stream().map(Holder::value)::iterator;
    }
}
