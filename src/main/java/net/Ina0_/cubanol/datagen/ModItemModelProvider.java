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
import net.neoforged.neoforge.registries.DeferredItem;

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

        basicItemFromAbsolutePath(ModBlocks.APPLE_SAPLING.asItem(), modLoc("block/apple_sapling"));

        withExistingParent(ModBlocks.APPLE_BUTTON.asItem(), mcLoc("block/button_inventory"), modLoc("block/" + getPath(ModBlocks.APPLE_PLANKS.get())));
        withExistingParent(ModBlocks.APPLE_FENCE.asItem(), mcLoc("block/fence_inventory"), modLoc("block/" + getPath(ModBlocks.APPLE_PLANKS.get())));

        basicItem(ModBlocks.APPLE_DOOR.asItem());

        basicItem(ModItems.APPLE_SEEDS.get());

        basicItem(ModItems.APPLE_SIGN.get());
        basicItem(ModItems.APPLE_HANGING_SIGN.get());

        basicItem(ModItems.ORANGE.get());

        basicItemFromAbsolutePath(ModBlocks.ORANGE_SAPLING.asItem(), modLoc("block/orange_sapling"));

        withExistingParent(ModBlocks.ORANGE_BUTTON.asItem(), mcLoc("block/button_inventory"), modLoc("block/" + getPath(ModBlocks.ORANGE_PLANKS.get())));
        withExistingParent(ModBlocks.ORANGE_FENCE.asItem(), mcLoc("block/fence_inventory"), modLoc("block/" + getPath(ModBlocks.ORANGE_PLANKS.get())));

        basicItem(ModBlocks.ORANGE_DOOR.asItem());

        basicItem(ModItems.ORANGE_SEEDS.get());

        basicItem(ModItems.ORANGE_SIGN.get());
        basicItem(ModItems.ORANGE_HANGING_SIGN.get());
    }

    private void withExistingParent(Item item, ResourceLocation parent, ResourceLocation texture, String key){
        withExistingParent(getPath(item), parent)
                .texture(key, texture);
    }

    private void withExistingParent(Item item, ResourceLocation parent, ResourceLocation texture){
        withExistingParent(item, parent, texture, "texture");
    }

    private void basicItemFromAbsolutePath(Item item, ResourceLocation textureLocation){
        withExistingParent(item, mcLoc("item/generated"), textureLocation, "layer0");
    }

    private String getPath(Item item){
        return Objects.requireNonNull(BuiltInRegistries.ITEM.getKey(item)).getPath();
    }
    private String getPath(Block block){
        return Objects.requireNonNull(BuiltInRegistries.BLOCK.getKey(block)).getPath();
    }
}
