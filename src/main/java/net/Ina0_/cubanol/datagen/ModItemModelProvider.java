package net.Ina0_.cubanol.datagen;

import net.Ina0_.cubanol.Cubanol;
import net.Ina0_.cubanol.item.ModItems;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

public class ModItemModelProvider extends ItemModelProvider {
    public ModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, Cubanol.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        basicItem(ModItems.GRAPE.get());
        basicItem(ModItems.ANISE.get());
        basicItem(ModItems.AGAVE_SEEDS.get());
        basicItem(ModItems.AGAVE_SYRUP.get());
        basicItem(ModItems.WIRE.get());
        basicItem(ModItems.GRAPE_SEEDS.get());
    }
}
