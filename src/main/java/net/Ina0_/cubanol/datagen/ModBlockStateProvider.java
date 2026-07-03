package net.Ina0_.cubanol.datagen;

import net.Ina0_.cubanol.Cubanol;
import net.Ina0_.cubanol.block.ModBlocks;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.client.model.generators.ConfiguredModel;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredBlock;

import java.util.function.Function;

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

        crop((CropBlock) ModBlocks.AGAVE_CROP.get(), "agave_crop_stage", "agave_crop_stage", true);
        crop((CropBlock) ModBlocks.AGAVE_STEM.get(), "agave_stem_stage", "agave_stem_stage", true);
        crop((CropBlock) ModBlocks.AGAVE_FLOWER.get(), "agave_flower_stage", "agave_flower_stage", true);
    }

    public void crop(CropBlock block, String modelName, String textureName, Boolean isModelCrossShaped){
        Function<BlockState, ConfiguredModel[]> function = state -> {
            ConfiguredModel[] configuredModels = new ConfiguredModel[1];
            if(!isModelCrossShaped) {
                configuredModels[0] = new ConfiguredModel(models().crop(
                        modelName + block.getAge(state),
                        ResourceLocation.fromNamespaceAndPath(Cubanol.MOD_ID, "block/" + textureName + block.getAge(state))
                ).renderType("cutout"));
            } else {
                configuredModels[0] = new ConfiguredModel(models().cross(
                        modelName + block.getAge(state),
                        ResourceLocation.fromNamespaceAndPath(Cubanol.MOD_ID, "block/" + textureName + block.getAge(state))
                ).renderType("cutout"));
            }
            return configuredModels;
        };

        getVariantBuilder(block).forAllStates(function);
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
