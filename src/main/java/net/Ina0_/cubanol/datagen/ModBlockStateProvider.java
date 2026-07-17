package net.Ina0_.cubanol.datagen;

import com.mojang.datafixers.util.Pair;
import net.Ina0_.cubanol.Cubanol;
import net.Ina0_.cubanol.block.ModBlocks;
import net.Ina0_.cubanol.block.custom.*;
import net.minecraft.core.Direction;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.Property;
import net.neoforged.neoforge.client.model.generators.*;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredBlock;

import javax.annotation.Nullable;
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
                null,
                null,
                AgaveCropBlock.AGE,
                AgaveCropBlock.DRIED
        );
        blockBasedOnBlockStates(
                ModBlocks.AGAVE_STEM.get(),
                "agave_stem",
                "agave_stem",
                pair -> models().cross(pair.getFirst(), pair.getSecond()).renderType("cutout"),
                null,
                null,
                AgaveStemBlock.AGE,
                AgaveStemBlock.DRIED
        );
        blockBasedOnBlockStates(
                ModBlocks.AGAVE_FLOWER.get(),
                "agave_flower",
                "agave_flower",
                pair -> models().cross(pair.getFirst(), pair.getSecond()).renderType("cutout"),
                null,
                null,
                AgaveFlowerBlock.AGE,
                AgaveFlowerBlock.DRIED
        );

        blockBasedOnBlockStates(
                ModBlocks.CROP_SUPPORT.get(),
                "crop_support",
                "crop_support",
                pair -> models().getExistingFile(pair.getSecond()),
                null,
                null,
                CropSupportBlock.NORTH,
                CropSupportBlock.SOUTH,
                CropSupportBlock.EAST,
                CropSupportBlock.WEST
        );
        simpleBlockItem(ModBlocks.CROP_SUPPORT.get(), models().getExistingFile(ResourceLocation.fromNamespaceAndPath(Cubanol.MOD_ID, "block/crop_support")));

        Function<BlockState, Boolean> isExistingFileForGrapeCrop = state -> (
                    (state.getValue(GrapeCropBlock.NORTH) && state.getValue(GrapeCropBlock.VINE_HANGING_SIDE) == Direction.NORTH) ||
                    (state.getValue(GrapeCropBlock.SOUTH) && state.getValue(GrapeCropBlock.VINE_HANGING_SIDE) == Direction.SOUTH) ||
                    (state.getValue(GrapeCropBlock.EAST) && state.getValue(GrapeCropBlock.VINE_HANGING_SIDE) == Direction.EAST) ||
                    (state.getValue(GrapeCropBlock.WEST) && state.getValue(GrapeCropBlock.VINE_HANGING_SIDE) == Direction.WEST)
                );
        Function<BlockState, BlockState> getReplacingStateForGrapeCrop = state -> {
            if(state.getValue(GrapeCropBlock.WHITE) && state.getValue(GrapeCropBlock.AGE) <= 8){
                return state.setValue(GrapeCropBlock.WHITE, false);
            }
            return state;
        };
        blockBasedOnBlockStates(
                ModBlocks.GRAPE_CROP.get(),
                "grape_crop",
                "grape_crop",
                pair -> models().getExistingFile(pair.getSecond()),
                isExistingFileForGrapeCrop,
                getReplacingStateForGrapeCrop,
                GrapeCropBlock.AGE,
                GrapeCropBlock.VINE_HANGING_SIDE,
                GrapeCropBlock.NORTH,
                GrapeCropBlock.SOUTH,
                GrapeCropBlock.EAST,
                GrapeCropBlock.WEST,
                GrapeCropBlock.WHITE
        );
    }

    public void crop(CropBlock block, String modelName, String textureName, Boolean isModelCrossShaped){
        Function<BlockState, ConfiguredModel[]> function = state -> {
            ConfiguredModel[] configuredModels = new ConfiguredModel[1];
            if(!isModelCrossShaped) {
                configuredModels[0] = new ConfiguredModel(models().crop(
                        modelName + "_age" + block.getAge(state),
                        ResourceLocation.fromNamespaceAndPath(Cubanol.MOD_ID, "block/" + textureName + "_age" + block.getAge(state))
                ).renderType("cutout"));
            } else {
                configuredModels[0] = new ConfiguredModel(models().cross(
                        modelName + "_age" + block.getAge(state),
                        ResourceLocation.fromNamespaceAndPath(Cubanol.MOD_ID, "block/" + textureName + "_age" + block.getAge(state))
                ).renderType("cutout"));
            }
            return configuredModels;
        };

        getVariantBuilder(block).forAllStates(function);
    }

    public void blockBasedOnBlockStates(Block block, String modelName, String textureName, Function<Pair<String, ResourceLocation>, ModelFile> modelType, @Nullable Function<BlockState, Boolean> isExistingFile, @Nullable Function<BlockState, BlockState> getReplacingState, Property<?>... properties){
        if(isExistingFile==null){
            isExistingFile = state -> true;
        }
        if(getReplacingState==null){
            getReplacingState = state -> state;
        }
        Function<BlockState, Boolean> finalIsExistingFile = isExistingFile;
        Function<BlockState, BlockState> finalGetReplacingState = getReplacingState;
        Function<BlockState, ConfiguredModel[]> function = state -> {
            state = finalGetReplacingState.apply(state);
            ConfiguredModel[] configuredModels = new ConfiguredModel[1];
            StringBuilder blockStatesPropertiesValues = new StringBuilder();
            for(Property<?> property: properties){
                if(!(property instanceof BooleanProperty)){
                    blockStatesPropertiesValues.append("_");
                    blockStatesPropertiesValues.append(property.getName());
                    blockStatesPropertiesValues.append(state.getValue(property));
                } else {
                    if(state.getValue((BooleanProperty) property)) {
                        blockStatesPropertiesValues.append("_");
                        blockStatesPropertiesValues.append(property.getName());
                    }
                }
            }
            String filePath = "block/" + textureName + blockStatesPropertiesValues;
            ResourceLocation resourceLocation = ResourceLocation.fromNamespaceAndPath(
                    Cubanol.MOD_ID,
                    finalIsExistingFile.apply(state)? filePath: "block/null"
            );
            configuredModels[0] = new ConfiguredModel(modelType.apply(
                    Pair.of(
                            modelName + blockStatesPropertiesValues,
                            resourceLocation
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
