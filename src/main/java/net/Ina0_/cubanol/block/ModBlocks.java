package net.Ina0_.cubanol.block;

import net.Ina0_.cubanol.Cubanol;
import net.Ina0_.cubanol.block.custom.*;
import net.Ina0_.cubanol.block.custom.crops.*;
import net.Ina0_.cubanol.fluids.ModFluids;
import net.Ina0_.cubanol.block.properties.ModBlockSetType;
import net.Ina0_.cubanol.block.properties.ModWoodTypes;
import net.Ina0_.cubanol.item.ModItems;
import net.Ina0_.cubanol.worldgen.tree.ModTreeGrowers;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import net.minecraft.world.level.storage.loot.LootParams;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import java.util.List;
import java.util.function.Supplier;


public class ModBlocks {
    private static final BlockBehaviour.StatePredicate never = (state, blockGetter, pos) -> false;


    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(Cubanol.MOD_ID);

    public static final DeferredBlock<TableBlock> OAK_TABLE = registerBlock("oak_table", () -> new TableBlock(
            BlockBehaviour.Properties.of()
                    .mapColor(Blocks.OAK_PLANKS.defaultMapColor())
                    .sound(SoundType.WOOD)
                    .isRedstoneConductor(never)
                    .forceSolidOn()
                    .ignitedByLava()
                    .isViewBlocking(never)
                    .strength(1.0F)
                    .pushReaction(PushReaction.IGNORE)
    ));

    public static final DeferredBlock<TableBlock> SPRUCE_TABLE = registerBlock("spruce_table", () -> new TableBlock(
            BlockBehaviour.Properties.of()
                    .mapColor(Blocks.SPRUCE_PLANKS.defaultMapColor())
                    .sound(SoundType.WOOD)
                    .isRedstoneConductor(never)
                    .forceSolidOn()
                    .ignitedByLava()
                    .isViewBlocking(never)
                    .strength(1.0F)
                    .pushReaction(PushReaction.IGNORE)
    ));

    public static final DeferredBlock<TableBlock> BIRCH_TABLE = registerBlock("birch_table", () -> new TableBlock(
            BlockBehaviour.Properties.of()
                    .mapColor(Blocks.BIRCH_PLANKS.defaultMapColor())
                    .sound(SoundType.WOOD)
                    .isRedstoneConductor(never)
                    .forceSolidOn()
                    .ignitedByLava()
                    .isViewBlocking(never)
                    .strength(1.0F)
                    .pushReaction(PushReaction.IGNORE)
    ));

    public static final DeferredBlock<TableBlock> JUNGLE_TABLE = registerBlock("jungle_table", () -> new TableBlock(
            BlockBehaviour.Properties.of()
                    .mapColor(Blocks.JUNGLE_PLANKS.defaultMapColor())
                    .sound(SoundType.WOOD)
                    .isRedstoneConductor(never)
                    .forceSolidOn()
                    .ignitedByLava()
                    .isViewBlocking(never)
                    .strength(1.0F)
                    .pushReaction(PushReaction.IGNORE)
    ));

    public static final DeferredBlock<TableBlock> ACACIA_TABLE = registerBlock("acacia_table", () -> new TableBlock(
            BlockBehaviour.Properties.of()
                    .mapColor(Blocks.ACACIA_PLANKS.defaultMapColor())
                    .sound(SoundType.WOOD)
                    .isRedstoneConductor(never)
                    .forceSolidOn()
                    .ignitedByLava()
                    .isViewBlocking(never)
                    .strength(1.0F)
                    .pushReaction(PushReaction.IGNORE)
    ));

    public static final DeferredBlock<TableBlock> DARK_OAK_TABLE = registerBlock("dark_oak_table", () -> new TableBlock(
            BlockBehaviour.Properties.of()
                    .mapColor(Blocks.DARK_OAK_PLANKS.defaultMapColor())
                    .sound(SoundType.WOOD)
                    .isRedstoneConductor(never)
                    .forceSolidOn()
                    .ignitedByLava()
                    .isViewBlocking(never)
                    .strength(1.0F)
                    .pushReaction(PushReaction.IGNORE)
    ));

    public static final DeferredBlock<TableBlock> MANGROVE_TABLE = registerBlock("mangrove_table", () -> new TableBlock(
            BlockBehaviour.Properties.of()
                    .mapColor(Blocks.MANGROVE_PLANKS.defaultMapColor())
                    .sound(SoundType.WOOD)
                    .isRedstoneConductor(never)
                    .forceSolidOn()
                    .ignitedByLava()
                    .isViewBlocking(never)
                    .strength(1.0F)
                    .pushReaction(PushReaction.IGNORE)
    ));

    public static final DeferredBlock<TableBlock> CHERRY_TABLE = registerBlock("cherry_table", () -> new TableBlock(
            BlockBehaviour.Properties.of()
                    .mapColor(Blocks.CHERRY_PLANKS.defaultMapColor())
                    .sound(SoundType.WOOD)
                    .isRedstoneConductor(never)
                    .forceSolidOn()
                    .ignitedByLava()
                    .isViewBlocking(never)
                    .strength(1.0F)
                    .pushReaction(PushReaction.IGNORE)
    ));


    public static final DeferredBlock<TableBlock> CRIMSON_TABLE = registerBlock("crimson_table", () -> new TableBlock(
            BlockBehaviour.Properties.of()
                    .mapColor(Blocks.CRIMSON_PLANKS.defaultMapColor())
                    .sound(SoundType.NETHER_WOOD)
                    .isRedstoneConductor(never)
                    .forceSolidOn()
                    .isViewBlocking(never)
                    .strength(1.0F)
                    .pushReaction(PushReaction.IGNORE)
    ));

    public static final DeferredBlock<TableBlock> WARPED_TABLE = registerBlock("warped_table", () -> new TableBlock(
            BlockBehaviour.Properties.of()
                    .mapColor(Blocks.WARPED_PLANKS.defaultMapColor())
                    .sound(SoundType.NETHER_WOOD)
                    .isRedstoneConductor(never)
                    .forceSolidOn()
                    .isViewBlocking(never)
                    .strength(1.0F)
                    .pushReaction(PushReaction.IGNORE)
    ));

    public static final DeferredBlock<TableBlock> BAMBOO_TABLE = registerBlock("bamboo_table", () -> new TableBlock(
            BlockBehaviour.Properties.of()
                    .mapColor(Blocks.BAMBOO_PLANKS.defaultMapColor())
                    .sound(SoundType.BAMBOO_WOOD)
                    .isRedstoneConductor(never)
                    .forceSolidOn()
                    .ignitedByLava()
                    .isViewBlocking(never)
                    .strength(1.0F)
                    .pushReaction(PushReaction.IGNORE)
    ));


    public static final DeferredBlock<BottleBlock> FAKE_WINE_BOTTLE = registerBlock("fake_wine_bottle", () -> new BottleBlock(
            BlockBehaviour.Properties.of()
                    .sound(SoundType.GLASS)
                    .forceSolidOn()
                    .isRedstoneConductor(never)
                    .forceSolidOn()
                    .offsetType(BlockBehaviour.OffsetType.XZ)
                    .dynamicShape()
                    .instabreak()
                    .strength(0.5F),
            () -> {
                VoxelShape base = Block.box(6.0, 0.0, 6.0, 9.0, 10.0, 9.0);
                VoxelShape neck = Block.box(7.0, 10.0, 7.0, 8.0, 15.0, 8.0);
                return Shapes.or(base, neck);
            }
    ));

    public static final DeferredBlock<AgaveCropBlock> AGAVE_CROP = BLOCKS.register("agave_crop", () -> new AgaveCropBlock(
            BlockBehaviour.Properties.of()
                    .mapColor(MapColor.PLANT)
                    .noCollission()
                    .randomTicks()
                    .instabreak()
                    .sound(SoundType.CROP)
                    .pushReaction(PushReaction.DESTROY)
                    .isRedstoneConductor(never)
    ));

    public static final DeferredBlock<AgaveStemBlock> AGAVE_STEM = BLOCKS.register("agave_stem", () -> new AgaveStemBlock(
            BlockBehaviour.Properties.of()
                    .mapColor(MapColor.PLANT)
                    .noCollission()
                    .randomTicks()
                    .instabreak()
                    .sound(SoundType.CROP)
                    .pushReaction(PushReaction.DESTROY)
                    .noLootTable()
                    .isRedstoneConductor(never)
    ));

    public static final DeferredBlock<AgaveFlowerBlock> AGAVE_FLOWER = BLOCKS.register("agave_flower", () -> new AgaveFlowerBlock(
            BlockBehaviour.Properties.of()
                    .mapColor(MapColor.PLANT)
                    .noCollission()
                    .randomTicks()
                    .instabreak()
                    .sound(SoundType.CROP)
                    .pushReaction(PushReaction.DESTROY)
                    .isRedstoneConductor(never)
    ));


    public static final DeferredBlock<CropSupportBlock> CROP_SUPPORT = registerBlock("crop_support", () -> new CropSupportBlock(
            BlockBehaviour.Properties.of()
                    .mapColor(MapColor.WOOD)
                    .noCollission()
                    .instabreak()
                    .sound(SoundType.WOOD)
                    .pushReaction(PushReaction.DESTROY)
                    .isViewBlocking(never)
                    .isRedstoneConductor(never)
                    .strength(0.1f)
    ));

    public static final DeferredBlock<GrapeCropBlock> GRAPE_CROP = BLOCKS.register("grape_crop", () -> new GrapeCropBlock(
            BlockBehaviour.Properties.of()
                    .mapColor(MapColor.WOOD)
                    .sound(SoundType.WOOD)
                    .noOcclusion()
                    .dynamicShape()
                    .pushReaction(PushReaction.DESTROY)
                    .isViewBlocking(never)
                    .isRedstoneConductor(never)
                    .strength(0.5f)
    ));

    public static final DeferredBlock<RiceCropBlock> RICE_CROP = BLOCKS.register("rice_crop", () -> new RiceCropBlock(
            BlockBehaviour.Properties.of()
                    .mapColor(MapColor.PLANT)
                    .noCollission()
                    .randomTicks()
                    .instabreak()
                    .sound(SoundType.CROP)
                    .pushReaction(PushReaction.DESTROY)
                    .isRedstoneConductor(never)
    ));

    public static final DeferredBlock<RicePaniclesBlock> RICE_PANICLES = BLOCKS.register("rice_panicles", () -> new RicePaniclesBlock(
            BlockBehaviour.Properties.of()
                    .mapColor(MapColor.PLANT)
                    .noCollission()
                    .randomTicks()
                    .instabreak()
                    .sound(SoundType.CROP)
                    .pushReaction(PushReaction.DESTROY)
                    .isRedstoneConductor(never)
    ));

    public static final DeferredBlock<StarAniseCropBlock> STAR_ANISE_CROP = BLOCKS.register("star_anise_crop", () -> new StarAniseCropBlock(
            BlockBehaviour.Properties.of()
                    .mapColor(MapColor.PLANT)
                    .noCollission()
                    .randomTicks()
                    .instabreak()
                    .sound(SoundType.CROP)
                    .pushReaction(PushReaction.DESTROY)
                    .isRedstoneConductor(never)
    ));

    public static final DeferredBlock<FlammableRotatedPillarBlock> APPLE_LOG = registerBlock("apple_log", () -> new FlammableRotatedPillarBlock(
            BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LOG)
    ));
    public static final DeferredBlock<Block> APPLE_WOOD = registerBlock("apple_wood", () -> new Block(
            BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_WOOD)
    ));
    public static final DeferredBlock<FlammableRotatedPillarBlock> STRIPPED_APPLE_LOG = registerBlock("stripped_apple_log", () -> new FlammableRotatedPillarBlock(
            BlockBehaviour.Properties.ofFullCopy(Blocks.STRIPPED_OAK_LOG)
    ));
    public static final DeferredBlock<Block> STRIPPED_APPLE_WOOD = registerBlock("stripped_apple_wood", () -> new Block(
            BlockBehaviour.Properties.ofFullCopy(Blocks.STRIPPED_OAK_WOOD)
    ));
    public static final DeferredBlock<LeavesBlock> APPLE_LEAVES = registerBlock("apple_leaves", () -> new LeavesBlock(
            BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LEAVES)
    ));
    public static final DeferredBlock<GrowingAppleLeavesBlock> GROWING_APPLE_LEAVES = registerBlock("growing_apple_leaves", () -> new GrowingAppleLeavesBlock(
            BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LEAVES)
    ));

    public static final DeferredBlock<SaplingBlock> APPLE_SAPLING = registerBlock("apple_sapling", () -> new SaplingBlock(
            ModTreeGrowers.APPLE_TREE,
            BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_SAPLING)
    ));

    public static final DeferredBlock<SaplingCropBlock> APPLE_SAPLING_CROP = BLOCKS.register("apple_sapling_crop", () -> new SaplingCropBlock(
            BlockBehaviour.Properties.of()
                    .mapColor(MapColor.PLANT)
                    .noCollission()
                    .randomTicks()
                    .instabreak()
                    .sound(SoundType.CROP)
                    .pushReaction(PushReaction.DESTROY)
                    .isRedstoneConductor(never),
            APPLE_SAPLING,
            () -> Items.APPLE
    ));

    public static final DeferredBlock<Block> APPLE_PLANKS = registerBlock("apple_planks", () -> new Block(
            BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS)
    ));

    public static final DeferredBlock<SlabBlock> APPLE_SLAB = registerBlock("apple_slab", () -> new SlabBlock(
            BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_SLAB)
    ));
    public static final DeferredBlock<StairBlock> APPLE_STAIR = registerBlock("apple_stair", () -> new StairBlock(
            APPLE_PLANKS.get().defaultBlockState(),
            BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_STAIRS)
    ));
    public static final DeferredBlock<ButtonBlock> APPLE_BUTTON = registerBlock("apple_button", () -> new ButtonBlock(
            ModBlockSetType.APPLE, 30, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_BUTTON)
    ));
    public static final DeferredBlock<PressurePlateBlock> APPLE_PRESSURE_PLATE = registerBlock("apple_pressure_plate", () -> new PressurePlateBlock(
            ModBlockSetType.APPLE, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PRESSURE_PLATE)
    ));
    public static final DeferredBlock<TrapDoorBlock> APPLE_TRAPDOOR = registerBlock("apple_trapdoor", () -> new TrapDoorBlock(
            ModBlockSetType.APPLE, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_TRAPDOOR)
    ));
    public static final DeferredBlock<DoorBlock> APPLE_DOOR = registerBlock("apple_door", () -> new DoorBlock(
            ModBlockSetType.APPLE, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_TRAPDOOR)
    ));
    public static final DeferredBlock<FenceBlock> APPLE_FENCE = registerBlock("apple_fence", () -> new FenceBlock(
            BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_FENCE)
    ));
    public static final DeferredBlock<FenceGateBlock> APPLE_FENCE_GATE = registerBlock("apple_fence_gate", () -> new FenceGateBlock(
            ModWoodTypes.APPLE,
            BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_FENCE_GATE)
    ));

    public static final DeferredBlock<TableBlock> APPLE_TABLE = registerBlock("apple_table", () -> new TableBlock(
            BlockBehaviour.Properties.of()
                    .mapColor(ModBlocks.APPLE_PLANKS.get().defaultMapColor())
                    .sound(SoundType.WOOD)
                    .isRedstoneConductor(never)
                    .forceSolidOn()
                    .ignitedByLava()
                    .isViewBlocking(never)
                    .strength(1.0F)
                    .pushReaction(PushReaction.IGNORE)
    ));

    public static final DeferredBlock<StandingSignBlock> APPLE_SIGN = BLOCKS.register("apple_sign", () -> new StandingSignBlock(
            ModWoodTypes.APPLE,
            BlockBehaviour.Properties.of()
                    .mapColor(APPLE_LOG.get().defaultMapColor())
                    .forceSolidOn()
                    .instrument(NoteBlockInstrument.BASS)
                    .noCollission()
                    .strength(1.0F)
                    .ignitedByLava()
    ));
    public static final DeferredBlock<WallSignBlock> APPLE_WALL_SIGN = BLOCKS.register("apple_wall_sign", () -> new WallSignBlock(
            ModWoodTypes.APPLE,
            BlockBehaviour.Properties.of()
                    .mapColor(MapColor.WOOD)
                    .forceSolidOn()
                    .instrument(NoteBlockInstrument.BASS)
                    .noCollission()
                    .strength(1.0F)
                    .lootFrom(APPLE_SIGN)
                    .ignitedByLava()
    ));
    public static final DeferredBlock<CeilingHangingSignBlock> APPLE_HANGING_SIGN = BLOCKS.register("apple_hanging_sign", () -> new CeilingHangingSignBlock(
            ModWoodTypes.APPLE,
            BlockBehaviour.Properties.of()
                    .mapColor(APPLE_LOG.get().defaultMapColor())
                    .forceSolidOn()
                    .instrument(NoteBlockInstrument.BASS)
                    .noCollission()
                    .strength(1.0F)
                    .ignitedByLava()
    ));
    public static final DeferredBlock<WallHangingSignBlock> APPLE_WALL_HANGING_SIGN = BLOCKS.register("apple_wall_hanging_sign", () -> new WallHangingSignBlock(
            ModWoodTypes.APPLE,
            BlockBehaviour.Properties.of()
                    .mapColor(APPLE_LOG.get().defaultMapColor())
                    .forceSolidOn()
                    .instrument(NoteBlockInstrument.BASS)
                    .noCollission()
                    .strength(1.0F)
                    .ignitedByLava()
                    .lootFrom(APPLE_HANGING_SIGN)
    ));


    public static final DeferredBlock<FlammableRotatedPillarBlock> ORANGE_LOG = registerBlock("orange_log", () -> new FlammableRotatedPillarBlock(
            BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LOG)
    ));
    public static final DeferredBlock<Block> ORANGE_WOOD = registerBlock("orange_wood", () -> new Block(
            BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_WOOD)
    ));
    public static final DeferredBlock<FlammableRotatedPillarBlock> STRIPPED_ORANGE_LOG = registerBlock("stripped_orange_log", () -> new FlammableRotatedPillarBlock(
            BlockBehaviour.Properties.ofFullCopy(Blocks.STRIPPED_OAK_LOG)
    ));
    public static final DeferredBlock<Block> STRIPPED_ORANGE_WOOD = registerBlock("stripped_orange_wood", () -> new Block(
            BlockBehaviour.Properties.ofFullCopy(Blocks.STRIPPED_OAK_WOOD)
    ));
    public static final DeferredBlock<LeavesBlock> ORANGE_LEAVES = registerBlock("orange_leaves", () -> new LeavesBlock(
            BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LEAVES)
    ));
    public static final DeferredBlock<GrowingOrangeLeavesBlock> GROWING_ORANGE_LEAVES = registerBlock("growing_orange_leaves", () -> new GrowingOrangeLeavesBlock(
            BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LEAVES)
    ));

    public static final DeferredBlock<SaplingBlock> ORANGE_SAPLING = registerBlock("orange_sapling", () -> new SaplingBlock(
            ModTreeGrowers.ORANGE_TREE,
            BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_SAPLING)
    ));

    public static final DeferredBlock<SaplingCropBlock> ORANGE_SAPLING_CROP = BLOCKS.register("orange_sapling_crop", () -> new SaplingCropBlock(
            BlockBehaviour.Properties.of()
                    .mapColor(MapColor.PLANT)
                    .noCollission()
                    .randomTicks()
                    .instabreak()
                    .sound(SoundType.CROP)
                    .pushReaction(PushReaction.DESTROY)
                    .isRedstoneConductor(never),
            ORANGE_SAPLING,
            ModItems.ORANGE
    ));

    public static final DeferredBlock<Block> ORANGE_PLANKS = registerBlock("orange_planks", () -> new Block(
            BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS)
    ));

    public static final DeferredBlock<SlabBlock> ORANGE_SLAB = registerBlock("orange_slab", () -> new SlabBlock(
            BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_SLAB)
    ));
    public static final DeferredBlock<StairBlock> ORANGE_STAIR = registerBlock("orange_stair", () -> new StairBlock(
            ORANGE_PLANKS.get().defaultBlockState(),
            BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_STAIRS)
    ));
    public static final DeferredBlock<ButtonBlock> ORANGE_BUTTON = registerBlock("orange_button", () -> new ButtonBlock(
            ModBlockSetType.ORANGE, 30, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_BUTTON)
    ));
    public static final DeferredBlock<PressurePlateBlock> ORANGE_PRESSURE_PLATE = registerBlock("orange_pressure_plate", () -> new PressurePlateBlock(
            ModBlockSetType.ORANGE, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PRESSURE_PLATE)
    ));
    public static final DeferredBlock<TrapDoorBlock> ORANGE_TRAPDOOR = registerBlock("orange_trapdoor", () -> new TrapDoorBlock(
            ModBlockSetType.ORANGE, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_TRAPDOOR)
    ));
    public static final DeferredBlock<DoorBlock> ORANGE_DOOR = registerBlock("orange_door", () -> new DoorBlock(
            ModBlockSetType.ORANGE, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_TRAPDOOR)
    ));
    public static final DeferredBlock<FenceBlock> ORANGE_FENCE = registerBlock("orange_fence", () -> new FenceBlock(
            BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_FENCE)
    ));
    public static final DeferredBlock<FenceGateBlock> ORANGE_FENCE_GATE = registerBlock("orange_fence_gate", () -> new FenceGateBlock(
            ModWoodTypes.ORANGE,
            BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_FENCE_GATE)
    ));

    public static final DeferredBlock<TableBlock> ORANGE_TABLE = registerBlock("orange_table", () -> new TableBlock(
            BlockBehaviour.Properties.of()
                    .mapColor(ModBlocks.ORANGE_PLANKS.get().defaultMapColor())
                    .sound(SoundType.WOOD)
                    .isRedstoneConductor(never)
                    .forceSolidOn()
                    .ignitedByLava()
                    .isViewBlocking(never)
                    .strength(1.0F)
                    .pushReaction(PushReaction.IGNORE)
    ));

    public static final DeferredBlock<StandingSignBlock> ORANGE_SIGN = BLOCKS.register("orange_sign", () -> new StandingSignBlock(
            ModWoodTypes.ORANGE,
            BlockBehaviour.Properties.of()
                    .mapColor(ORANGE_LOG.get().defaultMapColor())
                    .forceSolidOn()
                    .instrument(NoteBlockInstrument.BASS)
                    .noCollission()
                    .strength(1.0F)
                    .ignitedByLava()
    ));
    public static final DeferredBlock<WallSignBlock> ORANGE_WALL_SIGN = BLOCKS.register("orange_wall_sign", () -> new WallSignBlock(
            ModWoodTypes.ORANGE,
            BlockBehaviour.Properties.of()
                    .mapColor(MapColor.WOOD)
                    .forceSolidOn()
                    .instrument(NoteBlockInstrument.BASS)
                    .noCollission()
                    .strength(1.0F)
                    .lootFrom(ORANGE_SIGN)
                    .ignitedByLava()
    ));
    public static final DeferredBlock<CeilingHangingSignBlock> ORANGE_HANGING_SIGN = BLOCKS.register("orange_hanging_sign", () -> new CeilingHangingSignBlock(
            ModWoodTypes.ORANGE,
            BlockBehaviour.Properties.of()
                    .mapColor(ORANGE_LOG.get().defaultMapColor())
                    .forceSolidOn()
                    .instrument(NoteBlockInstrument.BASS)
                    .noCollission()
                    .strength(1.0F)
                    .ignitedByLava()
    ));
    public static final DeferredBlock<WallHangingSignBlock> ORANGE_WALL_HANGING_SIGN = BLOCKS.register("orange_wall_hanging_sign", () -> new WallHangingSignBlock(
            ModWoodTypes.ORANGE,
            BlockBehaviour.Properties.of()
                    .mapColor(ORANGE_LOG.get().defaultMapColor())
                    .forceSolidOn()
                    .instrument(NoteBlockInstrument.BASS)
                    .noCollission()
                    .strength(1.0F)
                    .ignitedByLava()
                    .lootFrom(ORANGE_HANGING_SIGN)
    ));

    public static final DeferredBlock<CaskBlock> CASK = BLOCKS.register("cask", () -> new CaskBlock(
            BlockBehaviour.Properties.ofFullCopy(Blocks.BARREL).noLootTable().noOcclusion()
    ));

    public static final DeferredBlock<LiquidBlock> DESTEMMED_BLACK_GRAPE = BLOCKS.register("destemmed_black_grape", () -> new LiquidBlock(
            ModFluids.DESTEMMED_BLACK_GRAPE.get(),
            BlockBehaviour.Properties.of()
                    .replaceable()
                    .noCollission()
                    .strength(100.0F)
                    .pushReaction(PushReaction.DESTROY)
                    .noLootTable()
                    .liquid()
                    .sound(SoundType.EMPTY)
    ));

    public static final DeferredBlock<LiquidBlock> TROD_BLACK_GRAPE = BLOCKS.register("trod_black_grape", () -> new LiquidBlock(
            ModFluids.TROD_BLACK_GRAPE.get(),
            BlockBehaviour.Properties.of()
                    .replaceable()
                    .noCollission()
                    .strength(100.0F)
                    .pushReaction(PushReaction.DESTROY)
                    .noLootTable()
                    .liquid()
                    .sound(SoundType.EMPTY)
    ));


    /**
     * Allows to register both the block and the BlockItem in one go
     * @param name the id of the block
     * @param blockSupplier a supplier returning the block
     */
    private static <T extends Block> DeferredBlock<T> registerBlock(String name, Supplier<T> blockSupplier){
        DeferredBlock<T> toReturn = BLOCKS.register(name, blockSupplier);
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    private static <T extends Block> void registerBlockItem(String name, DeferredBlock<T> block){
        ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }

    public static void register(IEventBus eventBus){
        BLOCKS.register(eventBus);
    }


    public static void dropItemsFromState(ServerLevel level, BlockState state, BlockPos pos, @Nullable Player player){
        if(player!=null && player.hasInfiniteMaterials()){
            return;
        }
        List<ItemStack> drops = state.getDrops(
                new LootParams.Builder(level)
                        .withOptionalParameter(LootContextParams.TOOL, player!=null? player.getMainHandItem(): ItemStack.EMPTY)
                        .withParameter(LootContextParams.ORIGIN, Vec3.atCenterOf(pos))
        );
        dropItems(level, pos, drops);
    }

    public static void dropItems(ServerLevel level, BlockPos pos, List<ItemStack> drops){
        for(ItemStack stack: drops){
            level.addFreshEntity(new ItemEntity(level, pos.getX(), pos.getY(), pos.getZ(), stack));
        }
    }

    public static void collectOrDropItemsFromState(ServerLevel level, Player player, BlockState state, BlockPos pos){
        List<ItemStack> drops = state.getDrops(new LootParams.Builder(level).withParameter(LootContextParams.TOOL, player.getMainHandItem()).withParameter(LootContextParams.ORIGIN, Vec3.atCenterOf(pos)));
        collectOrDropItems(level, player, pos, drops);
    }

    public static void collectOrDropItems(ServerLevel level, Player player, BlockPos pos, List<ItemStack> drops){
        if(player.hasInfiniteMaterials()){
            return;
        }
        for(ItemStack stack: drops){
            player.getInventory().add(stack);
            if(stack.isEmpty()){
                continue;
            }
            level.addFreshEntity(new ItemEntity(level, pos.getX(), pos.getY(), pos.getZ(), stack));
        }
    }

    public static @NotNull Direction getNeighborDirection(@NotNull BlockPos pos, @NotNull BlockPos neighborPos) {
        Direction neighborDirection = null;
        for (Direction direction : Direction.values()) {
            if (pos.getX() + direction.getStepX() == neighborPos.getX()){
                if(pos.getY() + direction.getStepY() == neighborPos.getY()) {
                    if(pos.getZ() + direction.getStepZ() == neighborPos.getZ()){
                        neighborDirection = direction;
                    }
                }
            }
        }
        if (neighborDirection == null) {
            throw new NullPointerException("neighborDirection is null");
        }
        return neighborDirection;
    }

    public static Double getLocalizedRandom(BlockPos pos){
        int sum = pos.getX() * 228479 + pos.getY() * 780287 + pos.getZ() * 2470777;
        sum = sum ^ (sum >> 3) * 6610;
        sum = sum ^ (sum << 14) * 40366;
        sum = sum ^ (sum >> 1) * 71033;
        return Math.abs(sum / Math.pow(2, 31));
    }
}
