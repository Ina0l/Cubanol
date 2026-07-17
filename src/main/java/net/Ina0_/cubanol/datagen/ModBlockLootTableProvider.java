package net.Ina0_.cubanol.datagen;

import net.Ina0_.cubanol.block.ModBlocks;
import net.Ina0_.cubanol.block.custom.AgaveFlowerBlock;
import net.Ina0_.cubanol.block.custom.CropSupportBlock;
import net.Ina0_.cubanol.block.custom.GrapeCropBlock;
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
        HolderLookup.RegistryLookup<Enchantment> enchantmentRegistryLookup = this.registries.lookupOrThrow(Registries.ENCHANTMENT);

        this.dropSelf(ModBlocks.OAK_TABLE.get());
        this.dropSelf(ModBlocks.SPRUCE_TABLE.get());
        this.dropSelf(ModBlocks.BIRCH_TABLE.get());
        this.dropSelf(ModBlocks.JUNGLE_TABLE.get());
        this.dropSelf(ModBlocks.ACACIA_TABLE.get());
        this.dropSelf(ModBlocks.DARK_OAK_TABLE.get());
        this.dropSelf(ModBlocks.MANGROVE_TABLE.get());
        this.dropSelf(ModBlocks.CHERRY_TABLE.get());
        this.dropSelf(ModBlocks.CRIMSON_TABLE.get());
        this.dropSelf(ModBlocks.WARPED_TABLE.get());
        this.dropSelf(ModBlocks.BAMBOO_TABLE.get());
        this.dropSelf(ModBlocks.FAKE_WINE_BOTTLE.get());
        this.dropSelf(ModBlocks.AGAVE_CROP.get());

        LootItemCondition.Builder lootItemConditionForAgaveFlower = LootItemBlockStatePropertyCondition.hasBlockStateProperties(ModBlocks.AGAVE_FLOWER.get())
                .setProperties(
                        StatePropertiesPredicate.Builder.properties()
                                .hasProperty(AgaveFlowerBlock.AGE, ModBlocks.AGAVE_FLOWER.get().getMaxAge())
                                .hasProperty(AgaveFlowerBlock.CUT, false)
                );
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
                                        ).when(lootItemConditionForAgaveFlower))
                )
        );

        LootItemCondition.Builder lootItemConditionForCropSupportNORTH = LootItemBlockStatePropertyCondition.hasBlockStateProperties(ModBlocks.CROP_SUPPORT.get())
                .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(CropSupportBlock.NORTH, true));
        LootItemCondition.Builder lootItemConditionForCropSupportSOUTH = LootItemBlockStatePropertyCondition.hasBlockStateProperties(ModBlocks.CROP_SUPPORT.get())
                .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(CropSupportBlock.SOUTH, true));
        LootItemCondition.Builder lootItemConditionForCropSupportEAST = LootItemBlockStatePropertyCondition.hasBlockStateProperties(ModBlocks.CROP_SUPPORT.get())
                .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(CropSupportBlock.EAST, true));
        LootItemCondition.Builder lootItemConditionForCropSupportWEST = LootItemBlockStatePropertyCondition.hasBlockStateProperties(ModBlocks.CROP_SUPPORT.get())
                .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(CropSupportBlock.WEST, true));
        this.add(
                ModBlocks.CROP_SUPPORT.get(),
                this.applyExplosionDecay(
                        ModBlocks.CROP_SUPPORT,
                        LootTable.lootTable()
                                .withPool(LootPool.lootPool().add(
                                        LootItem.lootTableItem(ModBlocks.CROP_SUPPORT)
                                ))
                                .withPool(LootPool.lootPool().add(
                                        LootItem.lootTableItem(ModItems.WIRE)
                                ).when(lootItemConditionForCropSupportNORTH))
                                .withPool(LootPool.lootPool().add(
                                        LootItem.lootTableItem(ModItems.WIRE)
                                ).when(lootItemConditionForCropSupportSOUTH))
                                .withPool(LootPool.lootPool().add(
                                        LootItem.lootTableItem(ModItems.WIRE)
                                ).when(lootItemConditionForCropSupportEAST))
                                .withPool(LootPool.lootPool().add(
                                        LootItem.lootTableItem(ModItems.WIRE)
                                ).when(lootItemConditionForCropSupportWEST))
                )
        );


        LootItemCondition.Builder lootItemConditionForGrapeCrop = LootItemBlockStatePropertyCondition
                .hasBlockStateProperties(ModBlocks.GRAPE_CROP.get())
                .setProperties(
                        StatePropertiesPredicate.Builder.properties()
                                .hasProperty(GrapeCropBlock.AGE, ModBlocks.GRAPE_CROP.get().getMaxAge()));
        LootItemCondition.Builder lootItemConditionForWhiteGrape = LootItemBlockStatePropertyCondition
                .hasBlockStateProperties(ModBlocks.GRAPE_CROP.get())
                .setProperties(
                        StatePropertiesPredicate.Builder.properties()
                                .hasProperty(GrapeCropBlock.WHITE, true)
                );
        LootItemCondition.Builder lootItemConditionForBlackGrape = LootItemBlockStatePropertyCondition
                .hasBlockStateProperties(ModBlocks.GRAPE_CROP.get())
                .setProperties(
                        StatePropertiesPredicate.Builder.properties()
                                .hasProperty(GrapeCropBlock.WHITE, false)
                );
        this.add(
                ModBlocks.GRAPE_CROP.get(),
                this.applyExplosionDecay(
                        ModItems.BLACK_GRAPE,
                        LootTable.lootTable()
                                .withPool(LootPool.lootPool()
                                        .add(LootItem.lootTableItem(ModItems.BLACK_GRAPE)
                                                .when(lootItemConditionForGrapeCrop)
                                                .otherwise(LootItem.lootTableItem(ModItems.BLACK_GRAPE_SEEDS)))
                                        .when(lootItemConditionForBlackGrape)
                                )
                                .withPool(LootPool.lootPool()
                                        .add(LootItem.lootTableItem(ModItems.BLACK_GRAPE_SEEDS)
                                                .apply(ApplyBonusCount.addBonusBinomialDistributionCount(
                                                        enchantmentRegistryLookup.getOrThrow(Enchantments.FORTUNE),0.5714286F, 3
                                                ))
                                        )
                                        .when(lootItemConditionForBlackGrape)
                                        .when(lootItemConditionForGrapeCrop)
                                )
                                .withPool(LootPool.lootPool()
                                        .add(LootItem.lootTableItem(ModItems.WHITE_GRAPE)
                                                .when(lootItemConditionForGrapeCrop)
                                                .otherwise(LootItem.lootTableItem(ModItems.WHITE_GRAPE_SEEDS))
                                        )
                                        .when(lootItemConditionForWhiteGrape)
                                )
                                .withPool(LootPool.lootPool()
                                        .add(LootItem.lootTableItem(ModItems.WHITE_GRAPE_SEEDS)
                                                .apply(ApplyBonusCount.addBonusBinomialDistributionCount(
                                                        enchantmentRegistryLookup.getOrThrow(Enchantments.FORTUNE),0.5714286F,3
                                                )))
                                        .when(lootItemConditionForWhiteGrape)
                                        .when(lootItemConditionForGrapeCrop)
                                )
                )
        );
    }

    @Override
    protected @NotNull Iterable<Block> getKnownBlocks() {
        return ModBlocks.BLOCKS.getEntries().stream().map(Holder::value)::iterator;
    }
}
