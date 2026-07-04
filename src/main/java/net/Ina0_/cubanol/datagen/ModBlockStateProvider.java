package net.Ina0_.cubanol.datagen;

import com.mojang.datafixers.util.Pair;
import net.Ina0_.cubanol.Cubanol;
import net.Ina0_.cubanol.block.ModBlocks;
import net.Ina0_.cubanol.block.custom.AgaveCropBlock;
import net.Ina0_.cubanol.block.custom.AgaveFlowerBlock;
import net.Ina0_.cubanol.block.custom.AgaveStemBlock;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.Property;
import net.neoforged.neoforge.client.model.generators.*;
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


        blockBasedOnBlockStates(
                ModBlocks.AGAVE_CROP.get(),
                "agave_crop",
                "agave_crop",
                pair -> models().cross(pair.getFirst(), pair.getSecond()).renderType("cutout"),
                AgaveCropBlock.AGE,
                AgaveCropBlock.DRIED
        );
        blockBasedOnBlockStates(
                ModBlocks.AGAVE_STEM.get(),
                "agave_stem",
                "agave_stem",
                pair -> models().cross(pair.getFirst(), pair.getSecond()).renderType("cutout"),
                AgaveStemBlock.AGE,
                AgaveStemBlock.DRIED
        );
        blockBasedOnBlockStates(
                ModBlocks.AGAVE_FLOWER.get(),
                "agave_flower",
                "agave_flower",
                pair -> models().cross(pair.getFirst(), pair.getSecond()).renderType("cutout"),
                AgaveFlowerBlock.AGE,
                AgaveFlowerBlock.DRIED
        );
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

    public void blockBasedOnBlockStates(Block block, String modelName, String textureName, Function<Pair<String, ResourceLocation>, ModelFile> modelType, Property<?>... properties){
        Function<BlockState, ConfiguredModel[]> function = state -> {
            ConfiguredModel[] configuredModels = new ConfiguredModel[1];
            StringBuilder blockStatesPropertiesValues = new StringBuilder();
            for(Property<?> property: properties){
                blockStatesPropertiesValues.append("_");
                blockStatesPropertiesValues.append(property.getName());
                blockStatesPropertiesValues.append(state.getValue(property));
            }
            configuredModels[0] = new ConfiguredModel(modelType.apply(
                    Pair.of(
                            modelName + String.join("_", blockStatesPropertiesValues.toString()),
                            ResourceLocation.fromNamespaceAndPath(Cubanol.MOD_ID, "block/" + textureName + blockStatesPropertiesValues)
                    ))
            );
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
