package net.Ina0_.cubanol.datagen;

import net.Ina0_.cubanol.Cubanol;
import net.Ina0_.cubanol.block.ModBlocks;
import net.Ina0_.cubanol.item.ModItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.neoforge.common.conditions.IConditionBuilder;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends RecipeProvider implements IConditionBuilder {
    public ModRecipeProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider) {
        super(output, lookupProvider);
    }

    @Override
    protected void buildRecipes(@NotNull RecipeOutput recipeOutput) {
        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, ModBlocks.OAK_TABLE)
                .pattern("PPP")
                .pattern("S S")
                .pattern("S S")
                .define('P', Blocks.OAK_PLANKS)
                .define('S', Items.STICK)
                .unlockedBy("has_oak_planks", has(Blocks.OAK_PLANKS))
                .save(recipeOutput);
        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, ModBlocks.SPRUCE_TABLE)
                .pattern("PPP")
                .pattern("S S")
                .pattern("S S")
                .define('P', Blocks.SPRUCE_PLANKS)
                .define('S', Items.STICK)
                .unlockedBy("has_spruce_planks", has(Blocks.SPRUCE_PLANKS))
                .save(recipeOutput);
        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, ModBlocks.BIRCH_TABLE)
                .pattern("PPP")
                .pattern("S S")
                .pattern("S S")
                .define('P', Blocks.BIRCH_PLANKS)
                .define('S', Items.STICK)
                .unlockedBy("has_birch_planks", has(Blocks.BIRCH_PLANKS))
                .save(recipeOutput);
        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, ModBlocks.JUNGLE_TABLE)
                .pattern("PPP")
                .pattern("S S")
                .pattern("S S")
                .define('P', Blocks.JUNGLE_PLANKS)
                .define('S', Items.STICK)
                .unlockedBy("has_jungle_planks", has(Blocks.JUNGLE_PLANKS))
                .save(recipeOutput);
        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, ModBlocks.ACACIA_TABLE)
                .pattern("PPP")
                .pattern("S S")
                .pattern("S S")
                .define('P', Blocks.ACACIA_PLANKS)
                .define('S', Items.STICK)
                .unlockedBy("has_acacia_planks", has(Blocks.ACACIA_PLANKS))
                .save(recipeOutput);
        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, ModBlocks.DARK_OAK_TABLE)
                .pattern("PPP")
                .pattern("S S")
                .pattern("S S")
                .define('P', Blocks.DARK_OAK_PLANKS)
                .define('S', Items.STICK)
                .unlockedBy("has_dark_oak_planks", has(Blocks.DARK_OAK_PLANKS))
                .save(recipeOutput);
        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, ModBlocks.MANGROVE_TABLE)
                .pattern("PPP")
                .pattern("S S")
                .pattern("S S")
                .define('P', Blocks.MANGROVE_PLANKS)
                .define('S', Items.STICK)
                .unlockedBy("has_mangrove_planks", has(Blocks.MANGROVE_PLANKS))
                .save(recipeOutput);
        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, ModBlocks.CHERRY_TABLE)
                .pattern("PPP")
                .pattern("S S")
                .pattern("S S")
                .define('P', Blocks.CHERRY_PLANKS)
                .define('S', Items.STICK)
                .unlockedBy("has_cherry_planks", has(Blocks.CHERRY_PLANKS))
                .save(recipeOutput);
        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, ModBlocks.CRIMSON_TABLE)
                .pattern("PPP")
                .pattern("S S")
                .pattern("S S")
                .define('P', Blocks.CRIMSON_PLANKS)
                .define('S', Items.STICK)
                .unlockedBy("has_crimson_planks", has(Blocks.CRIMSON_PLANKS))
                .save(recipeOutput);
        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, ModBlocks.WARPED_TABLE)
                .pattern("PPP")
                .pattern("S S")
                .pattern("S S")
                .define('P', Blocks.WARPED_PLANKS)
                .define('S', Items.STICK)
                .unlockedBy("has_warped_planks", has(Blocks.WARPED_PLANKS))
                .save(recipeOutput);
        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, ModBlocks.BAMBOO_TABLE)
                .pattern("PPP")
                .pattern("S S")
                .pattern("S S")
                .define('P', Blocks.BAMBOO_PLANKS)
                .define('S', Items.STICK)
                .unlockedBy("has_bamboo_planks", has(Blocks.BAMBOO_PLANKS))
                .save(recipeOutput);


        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.CROP_SUPPORT)
                .pattern("S")
                .pattern("S")
                .pattern("S")
                .define('S', Items.STICK)
                .unlockedBy("has_stick", has(Items.STICK))
                .save(recipeOutput);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.WIRE)
                .pattern(" I ")
                .pattern("ISI")
                .pattern(" I ")
                .define('I', Items.IRON_NUGGET)
                .define('S', Items.STRING)
                .unlockedBy("has_iron_nugget", has(Items.IRON_NUGGET))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.APPLE_WOOD, 3)
                .pattern("##")
                .pattern("##")
                .define('#', ModBlocks.APPLE_LOG)
                .unlockedBy("has_apple_log", has(ModBlocks.APPLE_LOG))
                .save(recipeOutput);
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.STRIPPED_APPLE_WOOD, 3)
                .pattern("##")
                .pattern("##")
                .define('#', ModBlocks.STRIPPED_APPLE_LOG)
                .unlockedBy("has_stripped_apple_log", has(ModBlocks.STRIPPED_APPLE_LOG))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.ORANGE_WOOD, 3)
                .pattern("##")
                .pattern("##")
                .define('#', ModBlocks.ORANGE_LOG)
                .unlockedBy("has_orange_log", has(ModBlocks.ORANGE_LOG))
                .save(recipeOutput);
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.STRIPPED_ORANGE_WOOD, 3)
                .pattern("##")
                .pattern("##")
                .define('#', ModBlocks.STRIPPED_ORANGE_LOG)
                .unlockedBy("has_stripped_orange_log", has(ModBlocks.STRIPPED_ORANGE_LOG))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.CASK)
                .pattern("#S#")
                .pattern("IBI")
                .pattern("#S#")
                .define('#', ItemTags.PLANKS)
                .define('S', ItemTags.WOODEN_SLABS)
                .define('I', Items.IRON_INGOT)
                .define('B', Items.BUCKET)
                .unlockedBy("has_bucket", has(Items.BUCKET))
                .save(recipeOutput);


        ShapelessRecipeBuilder.shapeless(RecipeCategory.DECORATIONS, ModBlocks.FAKE_WINE_BOTTLE)
                .requires(Items.GLASS_BOTTLE)
                .unlockedBy("has_glass_bottle", has(Items.GLASS_BOTTLE))
                .save(recipeOutput);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.DECORATIONS, Items.GLASS_BOTTLE)
                .requires(ModBlocks.FAKE_WINE_BOTTLE)
                .unlockedBy("has_fake_wine_bottle", has(ModBlocks.FAKE_WINE_BOTTLE))
                .save(recipeOutput);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, ModItems.BLACK_GRAPE_SEEDS)
                .requires(ModItems.BLACK_GRAPE)
                .unlockedBy("has_black_grape", has(ModItems.BLACK_GRAPE))
                .save(recipeOutput);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, ModItems.WHITE_GRAPE_SEEDS)
                .requires(ModItems.WHITE_GRAPE)
                .unlockedBy("has_white_grape", has(ModItems.WHITE_GRAPE))
                .save(recipeOutput);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, ModItems.RICE)
                .requires(ModItems.RICE_PANICLE)
                .unlockedBy("has_rice_panicle", has(ModItems.RICE_PANICLE))
                .save(recipeOutput);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, ModBlocks.APPLE_PLANKS)
                .requires(ModBlocks.APPLE_LOG)
                .unlockedBy("has_apple_log", has(ModBlocks.APPLE_LOG))
                .save(recipeOutput, ResourceLocation.fromNamespaceAndPath(Cubanol.MOD_ID, "apple_planks_from_log"));
        ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, ModBlocks.APPLE_PLANKS)
                .requires(ModBlocks.APPLE_WOOD)
                .unlockedBy("has_apple_wood", has(ModBlocks.APPLE_WOOD))
                .save(recipeOutput, ResourceLocation.fromNamespaceAndPath(Cubanol.MOD_ID, "apple_planks_from_wood"));
        ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, ModBlocks.APPLE_PLANKS)
                .requires(ModBlocks.STRIPPED_APPLE_LOG)
                .unlockedBy("has_stripped_apple_log", has(ModBlocks.STRIPPED_APPLE_LOG))
                .save(recipeOutput, ResourceLocation.fromNamespaceAndPath(Cubanol.MOD_ID, "apple_planks_from_stripped_log"));
        ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, ModBlocks.APPLE_PLANKS)
                .requires(ModBlocks.STRIPPED_APPLE_WOOD)
                .unlockedBy("has_stripped_apple_wood", has(ModBlocks.STRIPPED_APPLE_WOOD))
                .save(recipeOutput, ResourceLocation.fromNamespaceAndPath(Cubanol.MOD_ID, "apple_planks_from_stripped_wood"));

        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, ModItems.APPLE_SEEDS, 2)
                .requires(Items.APPLE)
                .unlockedBy("has_apple", has(Items.APPLE))
                .save(recipeOutput);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, ModItems.ORANGE_SEEDS, 2)
                .requires(ModItems.ORANGE)
                .unlockedBy("has_orange", has(ModItems.ORANGE))
                .save(recipeOutput);


        stairBuilder(ModBlocks.APPLE_STAIR, Ingredient.of(ModBlocks.APPLE_PLANKS))
                .group("apple")
                .unlockedBy("has_apple_planks", has(ModBlocks.APPLE_PLANKS))
                .save(recipeOutput);
        slab(recipeOutput, RecipeCategory.BUILDING_BLOCKS, ModBlocks.APPLE_SLAB, ModBlocks.APPLE_PLANKS);
        buttonBuilder(ModBlocks.APPLE_BUTTON, Ingredient.of(ModBlocks.APPLE_PLANKS))
                .group("apple")
                .unlockedBy("has_apple_planks", has(ModBlocks.APPLE_PLANKS))
                .save(recipeOutput);
        pressurePlate(recipeOutput, ModBlocks.APPLE_PRESSURE_PLATE, ModBlocks.APPLE_PLANKS);
        trapdoorBuilder(ModBlocks.APPLE_TRAPDOOR, Ingredient.of(ModBlocks.APPLE_PLANKS))
                .group("apple")
                .unlockedBy("has_apple_planks", has(ModBlocks.APPLE_PLANKS))
                .save(recipeOutput);
        doorBuilder(ModBlocks.APPLE_DOOR, Ingredient.of(ModBlocks.APPLE_PLANKS))
                .group("apple")
                .unlockedBy("has_apple_planks", has(ModBlocks.APPLE_PLANKS))
                .save(recipeOutput);
        fenceBuilder(ModBlocks.APPLE_FENCE, Ingredient.of(ModBlocks.APPLE_PLANKS))
                .group("apple")
                .unlockedBy("has_apple_planks", has(ModBlocks.APPLE_PLANKS))
                .save(recipeOutput);
        fenceGateBuilder(ModBlocks.APPLE_FENCE_GATE, Ingredient.of(ModBlocks.APPLE_PLANKS))
                .group("apple")
                .unlockedBy("has_apple_planks", has(ModBlocks.APPLE_PLANKS))
                .save(recipeOutput);
        signBuilder(ModItems.APPLE_SIGN, Ingredient.of(ModBlocks.APPLE_PLANKS))
                .group("apple")
                .unlockedBy("has_apple_planks", has(ModBlocks.APPLE_PLANKS))
                .save(recipeOutput);
        hangingSign(recipeOutput, ModItems.APPLE_HANGING_SIGN, ModBlocks.STRIPPED_APPLE_LOG);


        stairBuilder(ModBlocks.ORANGE_STAIR, Ingredient.of(ModBlocks.ORANGE_PLANKS))
                .group("orange")
                .unlockedBy("has_orange_planks", has(ModBlocks.ORANGE_PLANKS))
                .save(recipeOutput);
        slab(recipeOutput, RecipeCategory.BUILDING_BLOCKS, ModBlocks.ORANGE_SLAB, ModBlocks.ORANGE_PLANKS);
        buttonBuilder(ModBlocks.ORANGE_BUTTON, Ingredient.of(ModBlocks.ORANGE_PLANKS))
                .group("orange")
                .unlockedBy("has_orange_planks", has(ModBlocks.ORANGE_PLANKS))
                .save(recipeOutput);
        pressurePlate(recipeOutput, ModBlocks.ORANGE_PRESSURE_PLATE, ModBlocks.ORANGE_PLANKS);
        trapdoorBuilder(ModBlocks.ORANGE_TRAPDOOR, Ingredient.of(ModBlocks.ORANGE_PLANKS))
                .group("orange")
                .unlockedBy("has_orange_planks", has(ModBlocks.ORANGE_PLANKS))
                .save(recipeOutput);
        doorBuilder(ModBlocks.ORANGE_DOOR, Ingredient.of(ModBlocks.ORANGE_PLANKS))
                .group("orange")
                .unlockedBy("has_orange_planks", has(ModBlocks.ORANGE_PLANKS))
                .save(recipeOutput);
        fenceBuilder(ModBlocks.ORANGE_FENCE, Ingredient.of(ModBlocks.ORANGE_PLANKS))
                .group("orange")
                .unlockedBy("has_orange_planks", has(ModBlocks.ORANGE_PLANKS))
                .save(recipeOutput);
        fenceGateBuilder(ModBlocks.ORANGE_FENCE_GATE, Ingredient.of(ModBlocks.ORANGE_PLANKS))
                .group("orange")
                .unlockedBy("has_orange_planks", has(ModBlocks.ORANGE_PLANKS))
                .save(recipeOutput);
        signBuilder(ModItems.ORANGE_SIGN, Ingredient.of(ModBlocks.ORANGE_PLANKS))
                .group("orange")
                .unlockedBy("has_orange_planks", has(ModBlocks.ORANGE_PLANKS))
                .save(recipeOutput);
        hangingSign(recipeOutput, ModItems.ORANGE_HANGING_SIGN, ModBlocks.STRIPPED_ORANGE_LOG);
    }
}
