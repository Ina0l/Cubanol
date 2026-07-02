package net.Ina0_.cubanol.datagen;

import net.Ina0_.cubanol.block.ModBlocks;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.world.item.Items;
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


        ShapelessRecipeBuilder.shapeless(RecipeCategory.DECORATIONS, ModBlocks.FAKE_WINE_BOTTLE)
                .requires(Items.GLASS_BOTTLE)
                .unlockedBy("has_glass_bottle", has(Items.GLASS_BOTTLE))
                .save(recipeOutput);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.DECORATIONS, Items.GLASS_BOTTLE)
                .requires(ModBlocks.FAKE_WINE_BOTTLE)
                .unlockedBy("has_fake_wine_bottle", has(ModBlocks.FAKE_WINE_BOTTLE))
                .save(recipeOutput);
    }
}
