package net.Ina0_.cubanol.datagen;

import net.Ina0_.cubanol.block.ModBlocks;
import net.Ina0_.cubanol.item.ModItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.conditions.TrueCondition;
import net.neoforged.neoforge.common.data.DataMapProvider;
import net.neoforged.neoforge.registries.datamaps.builtin.Compostable;
import net.neoforged.neoforge.registries.datamaps.builtin.NeoForgeDataMaps;
import net.neoforged.neoforge.registries.datamaps.builtin.Strippable;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.CompletableFuture;

public class ModDataMapsProvider extends DataMapProvider {
    protected ModDataMapsProvider(PackOutput packOutput, CompletableFuture<HolderLookup.Provider> lookupProvider) {
        super(packOutput, lookupProvider);
    }

    @Override
    protected void gather(HolderLookup.@NotNull Provider provider) {
        this.builder(NeoForgeDataMaps.COMPOSTABLES)
                .add(ModItems.AGAVE_SEEDS.getId(), new Compostable(0.5f), false)
                .add(ModItems.STAR_ANISE.getId(), new Compostable(0.3f), false)
                .add(ModItems.BLACK_GRAPE.getId(), new Compostable(0.3f), false)
                .add(ModItems.WHITE_GRAPE.getId(), new Compostable(0.3f), false)
                .add(ModItems.BLACK_GRAPE_SEEDS.getId(), new Compostable(0.3f), false)
                .add(ModItems.WHITE_GRAPE_SEEDS.getId(), new Compostable(0.3f), false);

        this.builder(NeoForgeDataMaps.STRIPPABLES)
                .add(ModBlocks.APPLE_TREE_LOG, new Strippable(ModBlocks.STRIPPED_APPLE_TREE_LOG.get()), false, TrueCondition.INSTANCE)
                .add(ModBlocks.APPLE_TREE_WOOD, new Strippable(ModBlocks.STRIPPED_APPLE_TREE_WOOD.get()), false, TrueCondition.INSTANCE);
    }
}
