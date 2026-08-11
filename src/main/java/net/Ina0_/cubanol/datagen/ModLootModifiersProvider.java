package net.Ina0_.cubanol.datagen;

import net.Ina0_.cubanol.Cubanol;
import net.Ina0_.cubanol.lootmodifiers.NoAppleLootModifier;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.neoforged.neoforge.common.data.GlobalLootModifierProvider;
import net.neoforged.neoforge.common.loot.LootTableIdCondition;

import java.util.concurrent.CompletableFuture;

public class ModLootModifiersProvider extends GlobalLootModifierProvider {
    public ModLootModifiersProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries, Cubanol.MOD_ID);
    }

    @Override
    protected void start() {
        this.add(
                "no_apple_loot_modifier",
                new NoAppleLootModifier(new LootItemCondition[]{
                        LootTableIdCondition.builder(ResourceLocation.withDefaultNamespace("blocks/oak_leaves"))
                                .or(
                                        LootTableIdCondition.builder(ResourceLocation.withDefaultNamespace("blocks/dark_oak_leaves"))
                                )
                                .build()
                })
        );
    }
}
