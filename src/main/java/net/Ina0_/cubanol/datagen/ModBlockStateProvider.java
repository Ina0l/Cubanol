package net.Ina0_.cubanol.datagen;

import com.mojang.datafixers.util.Pair;
import net.Ina0_.cubanol.Cubanol;
import net.Ina0_.cubanol.block.ModBlocks;
import net.Ina0_.cubanol.block.custom.*;
import net.minecraft.core.Direction;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.Property;
import net.neoforged.neoforge.client.model.generators.*;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredBlock;

import javax.annotation.Nullable;
import java.util.function.Function;

public class ModBlockStateProvider extends BlockStateProvider {
    private final ResourceLocation NULL = modLoc("block/null");

    public ModBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, Cubanol.MOD_ID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        ResourceLocation tableParent = modLoc("block/table");
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
        modelFromParent(ModBlocks.APPLE_TABLE, tableParent, blockTexture(ModBlocks.APPLE_PLANKS.get()));
        modelFromParent(ModBlocks.ORANGE_TABLE, tableParent, blockTexture(ModBlocks.ORANGE_PLANKS.get()));

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
        simpleBlockItem(ModBlocks.CROP_SUPPORT.get(), models().getExistingFile(modLoc("block/crop_support")));

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

        logBlock(ModBlocks.APPLE_LOG.get());
        logBlock(ModBlocks.STRIPPED_APPLE_LOG.get());
        simpleBlockWithItem(ModBlocks.APPLE_WOOD.get(), models().cubeAll(name(ModBlocks.APPLE_WOOD.get()), blockTexture(ModBlocks.APPLE_LOG.get())));
        simpleBlockWithItem(ModBlocks.STRIPPED_APPLE_WOOD.get(), models().cubeAll(name(ModBlocks.STRIPPED_APPLE_WOOD.get()), blockTexture(ModBlocks.STRIPPED_APPLE_LOG.get())));

        blockItem(ModBlocks.APPLE_LOG);
        blockItem(ModBlocks.STRIPPED_APPLE_LOG);

        leavesBlock(ModBlocks.APPLE_LEAVES);
        crossBlock(ModBlocks.APPLE_SAPLING);

        simpleBlockWithItem(ModBlocks.APPLE_PLANKS);

        blockBasedOnBlockStates(
                ModBlocks.GROWING_APPLE_LEAVES.get(),
                "growing_apple_leaves",
                "growing_apple_leaves",
                pair -> models().withExistingParent(pair.getFirst(), modLoc("block/overlayered_block"))
                        .texture("overlay", pair.getSecond())
                        .texture("underlay", blockTexture(ModBlocks.APPLE_LEAVES.get())),
                null,
                null,
                GrowingAppleLeavesBlock.AGE
        );
        blockItem(ModBlocks.GROWING_APPLE_LEAVES, "_age8");

        ResourceLocation appleTreePlanks = blockTexture(ModBlocks.APPLE_PLANKS.get());
        stairsBlock(ModBlocks.APPLE_STAIR.get(), appleTreePlanks);
        slabBlock(ModBlocks.APPLE_SLAB.get(), appleTreePlanks, appleTreePlanks);
        buttonBlock(ModBlocks.APPLE_BUTTON.get(), appleTreePlanks);
        pressurePlateBlock(ModBlocks.APPLE_PRESSURE_PLATE.get(), appleTreePlanks);
        trapdoorBlockWithRenderType(ModBlocks.APPLE_TRAPDOOR.get(), modLoc("block/apple_trapdoor"), true, "cutout");
        doorBlockWithRenderType(ModBlocks.APPLE_DOOR.get(), modLoc("block/apple_door_bottom"), modLoc("block/apple_door_top"), "cutout");
        fenceBlock(ModBlocks.APPLE_FENCE.get(), appleTreePlanks);
        fenceGateBlock(ModBlocks.APPLE_FENCE_GATE.get(), appleTreePlanks);

        blockItem(ModBlocks.APPLE_STAIR);
        blockItem(ModBlocks.APPLE_SLAB);
        blockItem(ModBlocks.APPLE_BUTTON);
        blockItem(ModBlocks.APPLE_PRESSURE_PLATE);
        blockItem(ModBlocks.APPLE_TRAPDOOR, "_bottom");
        blockItem(ModBlocks.APPLE_DOOR);
        blockItem(ModBlocks.APPLE_FENCE);
        blockItem(ModBlocks.APPLE_FENCE_GATE);

        crop(ModBlocks.APPLE_SAPLING_CROP.get(), "apple_sapling_crop", "apple_sapling_crop", true);

        this.signBlock(ModBlocks.APPLE_SIGN.get(), ModBlocks.APPLE_WALL_SIGN.get(), modLoc("block/apple_planks"));
        this.hangingSignBlock(ModBlocks.APPLE_HANGING_SIGN.get(), ModBlocks.APPLE_WALL_HANGING_SIGN.get(), modLoc("block/stripped_apple_log"));


        logBlock(ModBlocks.ORANGE_LOG.get());
        logBlock(ModBlocks.STRIPPED_ORANGE_LOG.get());
        simpleBlockWithItem(ModBlocks.ORANGE_WOOD.get(), models().cubeAll(name(ModBlocks.ORANGE_WOOD.get()), blockTexture(ModBlocks.ORANGE_LOG.get())));
        simpleBlockWithItem(ModBlocks.STRIPPED_ORANGE_WOOD.get(), models().cubeAll(name(ModBlocks.STRIPPED_ORANGE_WOOD.get()), blockTexture(ModBlocks.STRIPPED_ORANGE_LOG.get())));

        blockItem(ModBlocks.ORANGE_LOG);
        blockItem(ModBlocks.STRIPPED_ORANGE_LOG);

        leavesBlock(ModBlocks.ORANGE_LEAVES);
        crossBlock(ModBlocks.ORANGE_SAPLING);

        simpleBlockWithItem(ModBlocks.ORANGE_PLANKS);

        blockBasedOnBlockStates(
                ModBlocks.GROWING_ORANGE_LEAVES.get(),
                "growing_orange_leaves",
                "growing_orange_leaves",
                pair -> models().withExistingParent(pair.getFirst(), modLoc("block/overlayered_block"))
                        .texture("overlay", pair.getSecond())
                        .texture("underlay", blockTexture(ModBlocks.ORANGE_LEAVES.get())),
                null,
                null,
                GrowingOrangeLeavesBlock.AGE
        );
        blockItem(ModBlocks.GROWING_ORANGE_LEAVES, "_age8");

        ResourceLocation orangeTreePlanks = blockTexture(ModBlocks.ORANGE_PLANKS.get());
        stairsBlock(ModBlocks.ORANGE_STAIR.get(), orangeTreePlanks);
        slabBlock(ModBlocks.ORANGE_SLAB.get(), orangeTreePlanks, orangeTreePlanks);
        buttonBlock(ModBlocks.ORANGE_BUTTON.get(), orangeTreePlanks);
        pressurePlateBlock(ModBlocks.ORANGE_PRESSURE_PLATE.get(), orangeTreePlanks);
        trapdoorBlock(ModBlocks.ORANGE_TRAPDOOR.get(), modLoc("block/orange_trapdoor"), true);
        doorBlock(ModBlocks.ORANGE_DOOR.get(), modLoc("block/orange_door_bottom"), modLoc("block/orange_door_top"));
        fenceBlock(ModBlocks.ORANGE_FENCE.get(), orangeTreePlanks);
        fenceGateBlock(ModBlocks.ORANGE_FENCE_GATE.get(), orangeTreePlanks);

        blockItem(ModBlocks.ORANGE_STAIR);
        blockItem(ModBlocks.ORANGE_SLAB);
        blockItem(ModBlocks.ORANGE_BUTTON);
        blockItem(ModBlocks.ORANGE_PRESSURE_PLATE);
        blockItem(ModBlocks.ORANGE_TRAPDOOR, "_bottom");
        blockItem(ModBlocks.ORANGE_DOOR);
        blockItem(ModBlocks.ORANGE_FENCE);
        blockItem(ModBlocks.ORANGE_FENCE_GATE);

        crop(ModBlocks.ORANGE_SAPLING_CROP.get(), "orange_sapling_crop", "orange_sapling_crop", true);

        this.signBlock(ModBlocks.ORANGE_SIGN.get(), ModBlocks.ORANGE_WALL_SIGN.get(), modLoc("block/orange_planks"));
        this.hangingSignBlock(ModBlocks.ORANGE_HANGING_SIGN.get(), ModBlocks.ORANGE_WALL_HANGING_SIGN.get(), modLoc("block/stripped_orange_log"));

        this.simpleBlockFromExistingModelFile(ModBlocks.CASK);
    }

    private <T extends CropBlock> void crop(T block, String modelName, String textureName, Boolean isModelCrossShaped){
        Function<BlockState, ConfiguredModel[]> function = state -> {
            ConfiguredModel[] configuredModels = new ConfiguredModel[1];
            if(!isModelCrossShaped) {
                configuredModels[0] = new ConfiguredModel(models().crop(
                        modelName + "_age" + block.getAge(state),
                        modLoc("block/" + textureName + "_age" + block.getAge(state))
                ).renderType("cutout"));
            } else {
                configuredModels[0] = new ConfiguredModel(models().cross(
                        modelName + "_age" + block.getAge(state),
                        modLoc("block/" + textureName + "_age" + block.getAge(state))
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
            ResourceLocation resourceLocation = finalIsExistingFile.apply(state)? modLoc(filePath): NULL;
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
        simpleBlockFromExistingModelFile(deferredBlock);
        simpleBlockItemFromExistingModelFile(deferredBlock);
    }

    private void simpleBlockFromExistingModelFile(DeferredBlock<? extends Block> deferredBlock){
        simpleBlock(deferredBlock.get(), models().getExistingFile(deferredBlock.getId()));
    }

    private void simpleBlockItemFromExistingModelFile(DeferredBlock<? extends Block> deferredBlock){
        simpleBlockItem(deferredBlock.get(), models().getExistingFile(deferredBlock.getId()));
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
