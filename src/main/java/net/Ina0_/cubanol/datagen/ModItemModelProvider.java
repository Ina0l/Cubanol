package net.Ina0_.cubanol.datagen;

import net.Ina0_.cubanol.Cubanol;
import net.Ina0_.cubanol.block.ModBlocks;
import net.Ina0_.cubanol.item.ModItems;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.client.model.generators.ItemModelBuilder;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

import java.util.Objects;

public class ModItemModelProvider extends ItemModelProvider {
    public ModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, Cubanol.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        basicItem(ModItems.BLACK_GRAPE.get());
        basicItem(ModItems.WHITE_GRAPE.get());
        basicItem(ModItems.STAR_ANISE.get());
        basicItem(ModItems.AGAVE_SEEDS.get());
        basicItem(ModItems.AGAVE_SYRUP.get());
        basicItem(ModItems.WIRE.get());
        basicItem(ModItems.BLACK_GRAPE_SEEDS.get());
        basicItem(ModItems.WHITE_GRAPE_SEEDS.get());
        basicItem(ModItems.RICE.get());
        basicItem(ModItems.RICE_PANICLE.get());

        basicItemFromAbsolutePath(ModBlocks.APPLE_TREE_SAPLING.asItem(), ResourceLocation.fromNamespaceAndPath(Cubanol.MOD_ID, "block/apple_tree_sapling"));

        withExistingParent(ModBlocks.APPLE_TREE_BUTTON.asItem(), mcLoc("block/button_inventory"), modLoc("block/" + getPath(ModBlocks.APPLE_TREE_PLANKS.get())));
        withExistingParent(ModBlocks.APPLE_TREE_FENCE.asItem(), mcLoc("block/fence_inventory"), modLoc("block/" + getPath(ModBlocks.APPLE_TREE_PLANKS.get())));

        basicItem(ModBlocks.APPLE_TREE_DOOR.asItem());

        basicItem(ModItems.APPLE_SEEDS.get());
    }

    private ItemModelBuilder withExistingParent(Item item, ResourceLocation parent, ResourceLocation texture, String key){
        return withExistingParent(getPath(item), parent)
                .texture(key, texture);
    }

    private ItemModelBuilder withExistingParent(Item item, ResourceLocation parent, ResourceLocation texture){
        return withExistingParent(item, parent, texture, "texture");
    }

    private ItemModelBuilder basicItemFromAbsolutePath(Item item, ResourceLocation textureLocation){
        return withExistingParent(item, mcLoc("item/generated"), textureLocation, "layer0");
    }

    private String getPath(Item item){
        return Objects.requireNonNull(BuiltInRegistries.ITEM.getKey(item)).getPath();
    }
    private String getPath(Block block){
        return Objects.requireNonNull(BuiltInRegistries.BLOCK.getKey(block)).getPath();
    }
}
