package net.Ina0_.cubanol.datagen;

import net.Ina0_.cubanol.Cubanol;
import net.Ina0_.cubanol.block.ModBlocks;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredBlock;

public class ModBlockStateProvider extends BlockStateProvider {
    public ModBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, Cubanol.MOD_ID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        simpleBlockWithItemFromExistingModelFile(ModBlocks.OAK_TABLE);
        simpleBlockWithItemFromExistingModelFile(ModBlocks.SPRUCE_TABLE);
        simpleBlockWithItemFromExistingModelFile(ModBlocks.BIRCH_TABLE);
        simpleBlockWithItemFromExistingModelFile(ModBlocks.JUNGLE_TABLE);
        simpleBlockWithItemFromExistingModelFile(ModBlocks.ACACIA_TABLE);
        simpleBlockWithItemFromExistingModelFile(ModBlocks.DARK_OAK_TABLE);
        simpleBlockWithItemFromExistingModelFile(ModBlocks.MANGROVE_TABLE);
        simpleBlockWithItemFromExistingModelFile(ModBlocks.CHERRY_TABLE);
        simpleBlockWithItemFromExistingModelFile(ModBlocks.CRIMSON_TABLE);
        simpleBlockWithItemFromExistingModelFile(ModBlocks.WARPED_TABLE);
        simpleBlockWithItemFromExistingModelFile(ModBlocks.BAMBOO_TABLE);

        horizontalDirectionalBlockWithItemFromExistingModelFile(ModBlocks.FAKE_WINE_BOTTLE);
    }

    private void simpleBlockWithItem(DeferredBlock<?> deferredBlock){
        simpleBlockWithItem(deferredBlock.get(), cubeAll(deferredBlock.get()));
    }

    private void simpleBlockWithItemFromExistingModelFile(DeferredBlock<?> deferredBlock){
        simpleBlockWithItem(deferredBlock.get(), models().getExistingFile(deferredBlock.getId()));
    }

    private void horizontalDirectionalBlockWithItemFromExistingModelFile(DeferredBlock<?> deferredBlock){
        horizontalBlock(deferredBlock.get(), models().getExistingFile(deferredBlock.getId()), 0);
        simpleBlockItem(deferredBlock.get(), models().getExistingFile(deferredBlock.getId()));
    }
}
