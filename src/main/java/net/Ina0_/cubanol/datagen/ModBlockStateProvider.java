package net.Ina0_.cubanol.datagen;

import com.mojang.datafixers.util.Pair;
import net.Ina0_.cubanol.Cubanol;
import net.Ina0_.cubanol.block.ModBlocks;
import net.Ina0_.cubanol.block.custom.*;
import net.minecraft.core.Direction;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.LeavesBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.Property;
import net.neoforged.neoforge.client.model.generators.*;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredBlock;

import javax.annotation.Nullable;
import java.util.Arrays;
import java.util.function.Function;
import java.util.stream.Collectors;

public class ModBlockStateProvider extends BlockStateProvider {
    public ModBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, Cubanol.MOD_ID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        ResourceLocation tableParent = ResourceLocation.fromNamespaceAndPath(Cubanol.MOD_ID, "block/table");
        modelFromParent(ModBlocks.OAK_TABLE, tableParent, blockTexture(Blocks.OAK_PLANKS));
        modelFromParent(ModBlocks.SPRUCE_TABLE, tableParent, blockTexture(Blocks.SPRUCE_PLANKS));
        modelFromParent(ModBlocks.BIRCH_TABLE, tableParent, blockTexture(Blocks.BIRCH_PLANKS));
        modelFromParent(ModBlocks.JUNGLE_TABLE, tableParent, blockTexture(Blocks.JUNGLE_PLANKS));
        modelFromParent(ModBlocks.ACACIA_TABLE, tableParent, blockTexture(Blocks.ACACIA_PLANKS));
        modelFromParent(ModBlocks.DARK_OAK_TABLE, tableParent, blockTexture(Blocks.DARK_OAK_PLANKS));
        modelFromParent(ModBlocks.MANGROVE_TABLE, tableParent, blockTexture(Blocks.MANGROVE_PLANKS));
        modelFromParent(ModBlocks.CHERRY_TABLE, tableParent, blockTexture(Blocks.CHERRY_PLANKS));
        modelFromParent(ModBlocks.CRIMSON_TABLE, tableParent, blockTexture(Blocks.CRIMSON_PLANKS));
        modelFromParent(ModBlocks.WARPED_TABLE, tableParent, blockTexture(Blocks.WARPED_PLANKS));
        modelFromParent(ModBlocks.BAMBOO_TABLE, tableParent, blockTexture(Blocks.BAMBOO_PLANKS));

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

        Function<BlockState, Boolean> isExistingFileForRiceCrop = state -> !state.getValue(RiceCropBlock.SUPPORTING) || state.getValue(RiceCropBlock.AGE)==((RiceCropBlock)state.getBlock()).getMaxAge();
        blockBasedOnBlockStates(
                ModBlocks.RICE_CROP.get(),
                "rice_crop",
                "rice_crop",
                pair -> models().crop(pair.getFirst(), pair.getSecond()).renderType("cutout"),
                isExistingFileForRiceCrop,
                null,
                RiceCropBlock.AGE,
                RiceCropBlock.SUPPORTING
        );
        crop(ModBlocks.RICE_PANICLES.get(), "rice_panicles", "rice_panicles", false);

        crop(ModBlocks.STAR_ANISE_CROP.get(), "star_anise_crop", "star_anise_crop", true);

        logBlock(ModBlocks.APPLE_TREE_LOG.get());
        logBlock(ModBlocks.STRIPPED_APPLE_TREE_LOG.get());
        simpleBlockWithItem(ModBlocks.APPLE_TREE_WOOD.get(), models().cubeAll(name(ModBlocks.APPLE_TREE_WOOD.get()), blockTexture(ModBlocks.APPLE_TREE_LOG.get())));
        simpleBlockWithItem(ModBlocks.STRIPPED_APPLE_TREE_WOOD.get(), models().cubeAll(name(ModBlocks.STRIPPED_APPLE_TREE_WOOD.get()), blockTexture(ModBlocks.STRIPPED_APPLE_TREE_LOG.get())));

        blockItem(ModBlocks.APPLE_TREE_LOG);
        blockItem(ModBlocks.STRIPPED_APPLE_TREE_LOG);

        leavesBlock(ModBlocks.APPLE_TREE_LEAVES);
        crossBlock(ModBlocks.APPLE_TREE_SAPLING);

        simpleBlockWithItem(ModBlocks.APPLE_TREE_PLANKS);

        blockBasedOnBlockStates(
                ModBlocks.GROWING_APPLE_TREE_LEAVES.get(),
                "growing_apple_tree_leaves",
                "growing_apple_tree_leaves_overlay",
                pair -> models().withExistingParent(pair.getFirst(), ResourceLocation.fromNamespaceAndPath(Cubanol.MOD_ID, "block/overlayered_block"))
                        .texture(
                                "underlay",
                                ResourceLocation.fromNamespaceAndPath(
                                        pair.getSecond().getNamespace(),
                                        Arrays.stream(pair.getSecond().getPath().split("_"))
                                                .filter(string -> !string.equals("overlay"))
                                                .collect(Collectors.joining("_"))
                                )
                        )
                        .texture("overlay", pair.getSecond()),
                null,
                null,
                GrowingAppleTreeLeavesBlock.AGE
        );
        blockItem(ModBlocks.GROWING_APPLE_TREE_LEAVES, "_age8");
    }

    private void crop(CropBlock block, String modelName, String textureName, Boolean isModelCrossShaped){
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

    private void crossBlock(DeferredBlock<? extends Block> block){
        simpleBlock(block.get(), models().cross(name(block.get()), blockTexture(block.get())).renderType("cutout"));
    }

    private void leavesBlock(DeferredBlock<? extends LeavesBlock> block){
        modelFromParent(block, ResourceLocation.withDefaultNamespace("block/leaves"), blockTexture(block.get()), "all");
        blockItem(block);
    }

    private void blockBasedOnBlockStates(Block block, String modelName, String textureName, Function<Pair<String, ResourceLocation>, ModelFile> modelType, @Nullable Function<BlockState, Boolean> isExistingFile, @Nullable Function<BlockState, BlockState> getReplacingState, Property<?>... properties){
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

    private void simpleBlockWithItem(DeferredBlock<? extends Block> deferredBlock){
        simpleBlockWithItem(deferredBlock.get(), cubeAll(deferredBlock.get()));
    }

    private void simpleBlockWithItemFromExistingModelFile(DeferredBlock<? extends Block> deferredBlock){
        simpleBlockWithItem(deferredBlock.get(), models().getExistingFile(deferredBlock.getId()));
    }

    private void horizontalDirectionalBlockWithItemFromExistingModelFile(DeferredBlock<? extends Block> deferredBlock){
        horizontalBlock(deferredBlock.get(), models().getExistingFile(deferredBlock.getId()), 0);
        simpleBlockItem(deferredBlock.get(), models().getExistingFile(deferredBlock.getId()));
    }

    private <T extends Block> void modelFromParent(DeferredBlock<T> block, ResourceLocation parent, ResourceLocation texture){
        simpleBlockWithItem(block.get(), models().singleTexture(block.getRegisteredName(), parent, texture));
    }

    private <T extends Block> void modelFromParent(DeferredBlock<T> block, ResourceLocation parent, ResourceLocation texture, String textureKey){
        simpleBlockWithItem(block.get(), models().singleTexture(block.getRegisteredName(), parent, textureKey, texture));
    }

    private void blockItem(DeferredBlock<? extends Block> deferredBlock) {
        simpleBlockItem(deferredBlock.get(), new ModelFile.UncheckedModelFile("cubanol:block/" + deferredBlock.getId().getPath()));
    }

    private void blockItem(DeferredBlock<? extends Block> deferredBlock, String appendix) {
        simpleBlockItem(deferredBlock.get(), new ModelFile.UncheckedModelFile("cubanol:block/" + deferredBlock.getId().getPath() + appendix));
    }

    private String name(Block block) {
        return BuiltInRegistries.BLOCK.getKey(block).getPath();
    }
}
