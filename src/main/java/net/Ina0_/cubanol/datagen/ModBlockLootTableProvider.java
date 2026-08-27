package net.Ina0_.cubanol.datagen;

import net.Ina0_.cubanol.block.ModBlocks;
import net.Ina0_.cubanol.block.custom.crops.*;
import net.Ina0_.cubanol.item.ModItems;
import net.minecraft.advancements.critereon.StatePropertiesPredicate;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.ApplyBonusCount;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.predicates.BonusLevelTableCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemBlockStatePropertyCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;

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
        this.dropSelf(ModBlocks.APPLE_TABLE.get());
        this.dropSelf(ModBlocks.ORANGE_TABLE.get());

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

        LootItemCondition.Builder lootItemConditionForCropSupport1 = LootItemBlockStatePropertyCondition.hasBlockStateProperties(ModBlocks.CROP_SUPPORT.get())
                .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(CropSupportBlock.WIRE_COUNT, 1));
        LootItemCondition.Builder lootItemConditionForCropSupport2 = LootItemBlockStatePropertyCondition.hasBlockStateProperties(ModBlocks.CROP_SUPPORT.get())
                .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(CropSupportBlock.WIRE_COUNT, 2));
        LootItemCondition.Builder lootItemConditionForCropSupport3 = LootItemBlockStatePropertyCondition.hasBlockStateProperties(ModBlocks.CROP_SUPPORT.get())
                .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(CropSupportBlock.WIRE_COUNT, 3));
        LootItemCondition.Builder lootItemConditionForCropSupport4 = LootItemBlockStatePropertyCondition.hasBlockStateProperties(ModBlocks.CROP_SUPPORT.get())
                .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(CropSupportBlock.WIRE_COUNT, 4));

        this.add(
                ModBlocks.CROP_SUPPORT.get(),
                this.applyExplosionDecay(
                        ModBlocks.CROP_SUPPORT,
                        LootTable.lootTable()
                                .withPool(LootPool.lootPool().add(
                                        LootItem.lootTableItem(ModBlocks.CROP_SUPPORT)
                                ))
                                .withPool(LootPool.lootPool().add(
                                        (
                                                LootItem.lootTableItem(ModItems.WIRE)
                                                        .apply(SetItemCountFunction.setCount(ConstantValue.exactly(4)))
                                        ).when(lootItemConditionForCropSupport4).otherwise(
                                                (
                                                        LootItem.lootTableItem(ModItems.WIRE)
                                                                .apply(SetItemCountFunction.setCount(ConstantValue.exactly(3)))
                                                ).when(lootItemConditionForCropSupport3).otherwise(
                                                        (
                                                                LootItem.lootTableItem(ModItems.WIRE)
                                                                        .apply(SetItemCountFunction.setCount(ConstantValue.exactly(2)))
                                                        ).when(lootItemConditionForCropSupport2).otherwise(
                                                                LootItem.lootTableItem(ModItems.WIRE).when(lootItemConditionForCropSupport1)
                                                        )
                                                )
                                        )
                                ))
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

        this.dropOther(ModBlocks.RICE_CROP.get(), ModItems.RICE.get());
        LootItemCondition.Builder lootItemConditionForRice = LootItemBlockStatePropertyCondition
                .hasBlockStateProperties(ModBlocks.RICE_PANICLES.get())
                .setProperties(
                        StatePropertiesPredicate.Builder.properties()
                                .hasProperty(RicePaniclesBlock.AGE, ModBlocks.RICE_PANICLES.get().getMaxAge())
                );
        this.add(
                ModBlocks.RICE_PANICLES.get(),
                this.applyExplosionDecay(
                        ModBlocks.RICE_PANICLES.get(),
                        LootTable.lootTable()
                                .withPool(LootPool.lootPool()
                                        .add(LootItem.lootTableItem(ModItems.RICE_PANICLE)
                                                .apply(ApplyBonusCount.addBonusBinomialDistributionCount(
                                                        enchantmentRegistryLookup.getOrThrow(Enchantments.FORTUNE), 0.5714286F, 0
                                                ))
                                        )
                                        .when(lootItemConditionForRice)
                                )
                )
        );

        LootItemCondition.Builder lootItemConditionForStarAnise = LootItemBlockStatePropertyCondition
                .hasBlockStateProperties(ModBlocks.STAR_ANISE_CROP.get())
                .setProperties(
                        StatePropertiesPredicate.Builder.properties()
                                .hasProperty(StarAniseCropBlock.AGE, ModBlocks.STAR_ANISE_CROP.get().getMaxAge())
                );
        this.add(
                ModBlocks.STAR_ANISE_CROP.get(),
                this.applyExplosionDecay(
                        ModBlocks.STAR_ANISE_CROP.get(),
                        LootTable.lootTable()
                                .withPool(LootPool.lootPool().add(
                                        (
                                            LootItem.lootTableItem(ModItems.STAR_ANISE).apply(
                                                    SetItemCountFunction.setCount(UniformGenerator.between(1, 3))
                                            )
                                        ).when(lootItemConditionForStarAnise).otherwise(LootItem.lootTableItem(ModItems.STAR_ANISE)))
                                )
                )
        );

        this.dropSelf(ModBlocks.APPLE_LOG.get());
        this.dropSelf(ModBlocks.APPLE_WOOD.get());
        this.dropSelf(ModBlocks.STRIPPED_APPLE_LOG.get());
        this.dropSelf(ModBlocks.STRIPPED_APPLE_WOOD.get());

        this.add(
                ModBlocks.APPLE_LEAVES.get(),
                this.applyExplosionDecay(
                        ModBlocks.APPLE_LEAVES.get(),
                        LootTable.lootTable()
                                .withPool(
                                        LootPool.lootPool()
                                                .when(HAS_SHEARS.or(hasSilkTouch()))
                                                .add(LootItem.lootTableItem(ModBlocks.APPLE_LEAVES))
                                )
                                .withPool(
                                        LootPool.lootPool()
                                                .when(HAS_SHEARS.or(hasSilkTouch()).invert())
                                                .add(
                                                        LootItem.lootTableItem(Items.STICK)
                                                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 2.0F)))
                                                                .when(
                                                                        BonusLevelTableCondition.bonusLevelFlatChance(
                                                                                enchantmentRegistryLookup.getOrThrow(Enchantments.FORTUNE),
                                                                                0.02F, 0.022222223F, 0.025F, 0.033333335F, 0.1F
                                                                        )
                                                                )
                                                )
                                )
                )
        );

        LootItemCondition.Builder lootItemConditionForAppleTreeLeaves = LootItemBlockStatePropertyCondition
                .hasBlockStateProperties(ModBlocks.GROWING_APPLE_LEAVES.get())
                .setProperties(
                        StatePropertiesPredicate.Builder.properties()
                                .hasProperty(ModBlocks.GROWING_APPLE_LEAVES.get().getAgeProperty(), ModBlocks.GROWING_APPLE_LEAVES.get().getMaxAge())
                );
        this.add(
                ModBlocks.GROWING_APPLE_LEAVES.get(),
                this.applyExplosionDecay(
                        ModBlocks.GROWING_APPLE_LEAVES.get(),
                        LootTable.lootTable()
                                .withPool(
                                        LootPool.lootPool()
                                                .when(HAS_SHEARS.or(hasSilkTouch()))
                                                .add(LootItem.lootTableItem(ModBlocks.APPLE_LEAVES))
                                )
                                .withPool(
                                        LootPool.lootPool()
                                                .when(HAS_SHEARS.or(hasSilkTouch()).invert())
                                                .add(
                                                        LootItem.lootTableItem(Items.STICK)
                                                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 2.0F)))
                                                                .when(
                                                                        BonusLevelTableCondition.bonusLevelFlatChance(
                                                                            enchantmentRegistryLookup.getOrThrow(Enchantments.FORTUNE),
                                                                            0.02F, 0.022222223F, 0.025F, 0.033333335F, 0.1F
                                                                        )
                                                                )
                                                )
                                )
                                .withPool(
                                        LootPool.lootPool().add(
                                                LootItem.lootTableItem(Items.APPLE)
                                                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0f, 2.0f)))
                                        ).when(lootItemConditionForAppleTreeLeaves)
                                )
                )
        );

        this.dropSelf(ModBlocks.APPLE_SAPLING.get());

        this.dropSelf(ModBlocks.APPLE_PLANKS.get());

        this.dropSelf(ModBlocks.APPLE_STAIR.get());
        this.add(ModBlocks.APPLE_SLAB.get(), this.createSlabItemTable(ModBlocks.APPLE_SLAB.get()));
        this.dropSelf(ModBlocks.APPLE_BUTTON.get());
        this.dropSelf(ModBlocks.APPLE_PRESSURE_PLATE.get());
        this.dropSelf(ModBlocks.APPLE_TRAPDOOR.get());
        this.add(ModBlocks.APPLE_DOOR.get(), this.createDoorTable(ModBlocks.APPLE_DOOR.get()));
        this.dropSelf(ModBlocks.APPLE_FENCE.get());
        this.dropSelf(ModBlocks.APPLE_FENCE_GATE.get());

        this.dropOther(ModBlocks.APPLE_SAPLING_CROP.get(), ModItems.APPLE_SEEDS);

        this.dropSelf(ModBlocks.APPLE_SIGN.get());
        this.dropSelf(ModBlocks.APPLE_WALL_SIGN.get());
        this.dropSelf(ModBlocks.APPLE_HANGING_SIGN.get());
        this.dropSelf(ModBlocks.APPLE_WALL_HANGING_SIGN.get());


        this.dropSelf(ModBlocks.ORANGE_LOG.get());
        this.dropSelf(ModBlocks.ORANGE_WOOD.get());
        this.dropSelf(ModBlocks.STRIPPED_ORANGE_LOG.get());
        this.dropSelf(ModBlocks.STRIPPED_ORANGE_WOOD.get());

        this.add(
                ModBlocks.ORANGE_LEAVES.get(),
                this.applyExplosionDecay(
                        ModBlocks.ORANGE_LEAVES.get(),
                        LootTable.lootTable()
                                .withPool(
                                        LootPool.lootPool()
                                                .when(HAS_SHEARS.or(hasSilkTouch()))
                                                .add(LootItem.lootTableItem(ModBlocks.ORANGE_LEAVES))
                                )
                                .withPool(
                                        LootPool.lootPool()
                                                .when(HAS_SHEARS.or(hasSilkTouch()).invert())
                                                .add(
                                                        LootItem.lootTableItem(Items.STICK)
                                                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 2.0F)))
                                                                .when(
                                                                        BonusLevelTableCondition.bonusLevelFlatChance(
                                                                                enchantmentRegistryLookup.getOrThrow(Enchantments.FORTUNE),
                                                                                0.02F, 0.022222223F, 0.025F, 0.033333335F, 0.1F
                                                                        )
                                                                )
                                                )
                                )
                )
        );

        LootItemCondition.Builder lootItemConditionForOrangeTreeLeaves = LootItemBlockStatePropertyCondition
                .hasBlockStateProperties(ModBlocks.GROWING_ORANGE_LEAVES.get())
                .setProperties(
                        StatePropertiesPredicate.Builder.properties()
                                .hasProperty(ModBlocks.GROWING_ORANGE_LEAVES.get().getAgeProperty(), ModBlocks.GROWING_ORANGE_LEAVES.get().getMaxAge())
                );
        this.add(
                ModBlocks.GROWING_ORANGE_LEAVES.get(),
                this.applyExplosionDecay(
                        ModBlocks.GROWING_ORANGE_LEAVES.get(),
                        LootTable.lootTable()
                                .withPool(
                                        LootPool.lootPool()
                                                .when(HAS_SHEARS.or(hasSilkTouch()))
                                                .add(LootItem.lootTableItem(ModBlocks.ORANGE_LEAVES))
                                )
                                .withPool(
                                        LootPool.lootPool()
                                                .when(HAS_SHEARS.or(hasSilkTouch()).invert())
                                                .add(
                                                        LootItem.lootTableItem(Items.STICK)
                                                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 2.0F)))
                                                                .when(
                                                                        BonusLevelTableCondition.bonusLevelFlatChance(
                                                                            enchantmentRegistryLookup.getOrThrow(Enchantments.FORTUNE),
                                                                            0.02F, 0.022222223F, 0.025F, 0.033333335F, 0.1F
                                                                        )
                                                                )
                                                )
                                )
                                .withPool(
                                        LootPool.lootPool().add(
                                                LootItem.lootTableItem(ModItems.ORANGE)
                                                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0f, 2.0f)))
                                        ).when(lootItemConditionForOrangeTreeLeaves)
                                )
                )
        );

        this.dropSelf(ModBlocks.ORANGE_SAPLING.get());

        this.dropSelf(ModBlocks.ORANGE_PLANKS.get());

        this.dropSelf(ModBlocks.ORANGE_STAIR.get());
        this.add(ModBlocks.ORANGE_SLAB.get(), this.createSlabItemTable(ModBlocks.ORANGE_SLAB.get()));
        this.dropSelf(ModBlocks.ORANGE_BUTTON.get());
        this.dropSelf(ModBlocks.ORANGE_PRESSURE_PLATE.get());
        this.dropSelf(ModBlocks.ORANGE_TRAPDOOR.get());
        this.add(ModBlocks.ORANGE_DOOR.get(), this.createDoorTable(ModBlocks.ORANGE_DOOR.get()));
        this.dropSelf(ModBlocks.ORANGE_FENCE.get());
        this.dropSelf(ModBlocks.ORANGE_FENCE_GATE.get());

        this.dropOther(ModBlocks.ORANGE_SAPLING_CROP.get(), ModItems.ORANGE_SEEDS);

        this.dropSelf(ModBlocks.ORANGE_SIGN.get());
        this.dropSelf(ModBlocks.ORANGE_WALL_SIGN.get());
        this.dropSelf(ModBlocks.ORANGE_HANGING_SIGN.get());
        this.dropSelf(ModBlocks.ORANGE_WALL_HANGING_SIGN.get());
    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return ModBlocks.BLOCKS.getEntries().stream().map(Holder::value)::iterator;
    }
}
