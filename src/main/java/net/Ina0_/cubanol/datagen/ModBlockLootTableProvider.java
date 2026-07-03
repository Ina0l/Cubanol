package net.Ina0_.cubanol.datagen;

import net.Ina0_.cubanol.block.ModBlocks;
import net.Ina0_.cubanol.block.custom.AgaveFlowerBlock;
import net.Ina0_.cubanol.item.ModItems;
import net.minecraft.advancements.critereon.StatePropertiesPredicate;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.ApplyBonusCount;
import net.minecraft.world.level.storage.loot.predicates.LootItemBlockStatePropertyCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import org.jetbrains.annotations.NotNull;

import java.util.Set;

public class ModBlockLootTableProvider extends BlockLootSubProvider {
    protected ModBlockLootTableProvider(HolderLookup.Provider registries) {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags(), registries);
    }

    @Override
    protected void generate() {
        dropSelf(ModBlocks.OAK_TABLE.get());
        dropSelf(ModBlocks.SPRUCE_TABLE.get());
        dropSelf(ModBlocks.BIRCH_TABLE.get());
        dropSelf(ModBlocks.JUNGLE_TABLE.get());
        dropSelf(ModBlocks.ACACIA_TABLE.get());
        dropSelf(ModBlocks.DARK_OAK_TABLE.get());
        dropSelf(ModBlocks.MANGROVE_TABLE.get());
        dropSelf(ModBlocks.CHERRY_TABLE.get());
        dropSelf(ModBlocks.CRIMSON_TABLE.get());
        dropSelf(ModBlocks.WARPED_TABLE.get());
        dropSelf(ModBlocks.BAMBOO_TABLE.get());
        dropSelf(ModBlocks.FAKE_WINE_BOTTLE.get());
        dropSelf(ModBlocks.AGAVE_CROP.get());

        LootItemCondition.Builder lootItemCondition = LootItemBlockStatePropertyCondition.hasBlockStateProperties(ModBlocks.AGAVE_FLOWER.get())
                .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(AgaveFlowerBlock.AGE, ((AgaveFlowerBlock) ModBlocks.AGAVE_FLOWER.get()).getMaxAge()));
        HolderLookup.RegistryLookup<Enchantment> registryLookup = this.registries.lookupOrThrow(Registries.ENCHANTMENT);
        this.add(
                ModBlocks.AGAVE_FLOWER.get(),
                this.applyExplosionDecay(
                        ModBlocks.AGAVE_FLOWER,
                        LootTable.lootTable()
                                .withPool(LootPool.lootPool().add(
                                                LootItem.lootTableItem(ModItems.AGAVE_SEEDS).apply(ApplyBonusCount.addBonusBinomialDistributionCount(
                                                        registryLookup.getOrThrow(Enchantments.FORTUNE),
                                                        0.5714286F,
                                                        3
                                                ))
                                        ).when(lootItemCondition))
                )
        );
    }

    @Override
    protected @NotNull Iterable<Block> getKnownBlocks() {
        return ModBlocks.BLOCKS.getEntries().stream().map(Holder::value)::iterator;
    }
}
