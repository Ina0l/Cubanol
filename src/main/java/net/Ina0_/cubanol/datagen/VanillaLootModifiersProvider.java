package net.Ina0_.cubanol.datagen;

import net.Ina0_.cubanol.lootmodifiers.NoAppleLootModifier;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.neoforged.neoforge.common.data.GlobalLootModifierProvider;

import java.util.concurrent.CompletableFuture;

public class VanillaLootModifiersProvider extends GlobalLootModifierProvider {
    public VanillaLootModifiersProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries, "minecraft");
    }

    @Override
    protected void start() {
        this.add(
                "oak_leaves",
                new NoAppleLootModifier(new LootItemCondition[]{})
        );
        this.add(
                "dark_oak_leaves",
                new NoAppleLootModifier(new LootItemCondition[]{})
        );
    }
}
