package net.Ina0_.cubanol.block;

import net.Ina0_.cubanol.Cubanol;
import net.Ina0_.cubanol.block.custom.*;
import net.Ina0_.cubanol.item.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
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

import javax.annotation.Nullable;
import java.util.List;
import java.util.function.Supplier;


public class ModBlocks {
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(Cubanol.MOD_ID);


    public static final DeferredBlock<Block> OAK_TABLE = registerBlock("oak_table", () -> new TableBlock(
            BlockBehaviour.Properties.of()
                    .mapColor(Blocks.OAK_PLANKS.defaultMapColor())
                    .sound(SoundType.WOOD)
                    .isRedstoneConductor(ModBlocks::never)
                    .forceSolidOn()
                    .ignitedByLava()
                    .isViewBlocking(ModBlocks::never)
                    .strength(1.0F)
    ));

    public static final DeferredBlock<Block> SPRUCE_TABLE = registerBlock("spruce_table", () -> new TableBlock(
            BlockBehaviour.Properties.of()
                    .mapColor(Blocks.SPRUCE_PLANKS.defaultMapColor())
                    .sound(SoundType.WOOD)
                    .isRedstoneConductor(ModBlocks::never)
                    .forceSolidOn()
                    .ignitedByLava()
                    .isViewBlocking(ModBlocks::never)
                    .strength(1.0F)
    ));

    public static final DeferredBlock<Block> BIRCH_TABLE = registerBlock("birch_table", () -> new TableBlock(
            BlockBehaviour.Properties.of()
                    .mapColor(Blocks.BIRCH_PLANKS.defaultMapColor())
                    .sound(SoundType.WOOD)
                    .isRedstoneConductor(ModBlocks::never)
                    .forceSolidOn()
                    .ignitedByLava()
                    .isViewBlocking(ModBlocks::never)
                    .strength(1.0F)
    ));

    public static final DeferredBlock<Block> JUNGLE_TABLE = registerBlock("jungle_table", () -> new TableBlock(
            BlockBehaviour.Properties.of()
                    .mapColor(Blocks.JUNGLE_PLANKS.defaultMapColor())
                    .sound(SoundType.WOOD)
                    .isRedstoneConductor(ModBlocks::never)
                    .forceSolidOn()
                    .ignitedByLava()
                    .isViewBlocking(ModBlocks::never)
                    .strength(1.0F)
    ));

    public static final DeferredBlock<Block> ACACIA_TABLE = registerBlock("acacia_table", () -> new TableBlock(
            BlockBehaviour.Properties.of()
                    .mapColor(Blocks.ACACIA_PLANKS.defaultMapColor())
                    .sound(SoundType.WOOD)
                    .isRedstoneConductor(ModBlocks::never)
                    .forceSolidOn()
                    .ignitedByLava()
                    .isViewBlocking(ModBlocks::never)
                    .strength(1.0F)
    ));

    public static final DeferredBlock<Block> DARK_OAK_TABLE = registerBlock("dark_oak_table", () -> new TableBlock(
            BlockBehaviour.Properties.of()
                    .mapColor(Blocks.DARK_OAK_PLANKS.defaultMapColor())
                    .sound(SoundType.WOOD)
                    .isRedstoneConductor(ModBlocks::never)
                    .forceSolidOn()
                    .ignitedByLava()
                    .isViewBlocking(ModBlocks::never)
                    .strength(1.0F)
    ));

    public static final DeferredBlock<Block> MANGROVE_TABLE = registerBlock("mangrove_table", () -> new TableBlock(
            BlockBehaviour.Properties.of()
                    .mapColor(Blocks.MANGROVE_PLANKS.defaultMapColor())
                    .sound(SoundType.WOOD)
                    .isRedstoneConductor(ModBlocks::never)
                    .forceSolidOn()
                    .ignitedByLava()
                    .isViewBlocking(ModBlocks::never)
                    .strength(1.0F)
    ));

    public static final DeferredBlock<Block> CHERRY_TABLE = registerBlock("cherry_table", () -> new TableBlock(
            BlockBehaviour.Properties.of()
                    .mapColor(Blocks.CHERRY_PLANKS.defaultMapColor())
                    .sound(SoundType.WOOD)
                    .isRedstoneConductor(ModBlocks::never)
                    .forceSolidOn()
                    .ignitedByLava()
                    .isViewBlocking(ModBlocks::never)
                    .strength(1.0F)
    ));

    public static final DeferredBlock<Block> CRIMSON_TABLE = registerBlock("crimson_table", () -> new TableBlock(
            BlockBehaviour.Properties.of()
                    .mapColor(Blocks.CRIMSON_PLANKS.defaultMapColor())
                    .sound(SoundType.NETHER_WOOD)
                    .isRedstoneConductor(ModBlocks::never)
                    .forceSolidOn()
                    .isViewBlocking(ModBlocks::never)
                    .strength(1.0F)
    ));

    public static final DeferredBlock<Block> WARPED_TABLE = registerBlock("warped_table", () -> new TableBlock(
            BlockBehaviour.Properties.of()
                    .mapColor(Blocks.WARPED_PLANKS.defaultMapColor())
                    .sound(SoundType.NETHER_WOOD)
                    .isRedstoneConductor(ModBlocks::never)
                    .forceSolidOn()
                    .isViewBlocking(ModBlocks::never)
                    .strength(1.0F)
    ));

    public static final DeferredBlock<Block> BAMBOO_TABLE = registerBlock("bamboo_table", () -> new TableBlock(
            BlockBehaviour.Properties.of()
                    .mapColor(Blocks.BAMBOO_PLANKS.defaultMapColor())
                    .sound(SoundType.BAMBOO_WOOD)
                    .isRedstoneConductor(ModBlocks::never)
                    .forceSolidOn()
                    .ignitedByLava()
                    .isViewBlocking(ModBlocks::never)
                    .strength(1.0F)
    ));


    public static final DeferredBlock<Block> FAKE_WINE_BOTTLE = registerBlock("fake_wine_bottle", () -> new FakeBottleBlock(
            BlockBehaviour.Properties.of()
                    .sound(SoundType.GLASS)
                    .forceSolidOn()
                    .isRedstoneConductor(ModBlocks::never)
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

    public static final DeferredBlock<Block> AGAVE_CROP = BLOCKS.register("agave_crop", () -> new AgaveCropBlock(
            BlockBehaviour.Properties.of()
                    .mapColor(MapColor.PLANT)
                    .noCollission()
                    .randomTicks()
                    .instabreak()
                    .sound(SoundType.CROP)
                    .pushReaction(PushReaction.DESTROY)
                    .isRedstoneConductor(ModBlocks::never)
    ));

    public static final DeferredBlock<Block> AGAVE_STEM = BLOCKS.register("agave_stem", () -> new AgaveStemBlock(
            BlockBehaviour.Properties.of()
                    .mapColor(MapColor.PLANT)
                    .noCollission()
                    .randomTicks()
                    .instabreak()
                    .sound(SoundType.CROP)
                    .pushReaction(PushReaction.DESTROY)
                    .noLootTable()
                    .isRedstoneConductor(ModBlocks::never)
    ));

    public static final DeferredBlock<Block> AGAVE_FLOWER = BLOCKS.register("agave_flower", () -> new AgaveFlowerBlock(
            BlockBehaviour.Properties.of()
                    .mapColor(MapColor.PLANT)
                    .noCollission()
                    .randomTicks()
                    .instabreak()
                    .sound(SoundType.CROP)
                    .pushReaction(PushReaction.DESTROY)
                    .isRedstoneConductor(ModBlocks::never)
    ));


    public static final DeferredBlock<Block> CROP_SUPPORT = registerBlock("crop_support", () -> new CropSupportBlock(
            BlockBehaviour.Properties.of()
                    .mapColor(MapColor.WOOD)
                    .noCollission()
                    .instabreak()
                    .sound(SoundType.WOOD)
                    .pushReaction(PushReaction.DESTROY)
                    .isViewBlocking(ModBlocks::never)
                    .isRedstoneConductor(ModBlocks::never)
                    .strength(0.1f)
    ));

    public static final DeferredBlock<Block> GRAPE_CROP = registerBlock("grape_crop", () -> new GrapeCropBlock(
            BlockBehaviour.Properties.of()
                    .mapColor(MapColor.WOOD)
                    .sound(SoundType.WOOD)
                    .noOcclusion()
                    .dynamicShape()
                    .pushReaction(PushReaction.DESTROY)
                    .isViewBlocking(ModBlocks::never)
                    .isRedstoneConductor(ModBlocks::never)
                    .strength(0.5f)
    ));


    /**
     * Used instead of the Block::never used in vanilla BlockProperties, which is private
     * @return false
     */
    private static boolean never(BlockState state, BlockGetter blockGetter, BlockPos pos){
        return false;
    }


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

    public static void register(IEventBus event_bus){
        BLOCKS.register(event_bus);
    }


    /**
     *
     * @return the number of drops
     */
    public static Integer dropItemsFromState(ServerLevel level, BlockState state, BlockPos pos, @Nullable Player player){
        if(player!=null && player.hasInfiniteMaterials()){
            return 0;
        }
        List<ItemStack> drops = state.getDrops(
                new LootParams.Builder(level)
                        .withOptionalParameter(LootContextParams.TOOL, player!=null? player.getMainHandItem(): ItemStack.EMPTY)
                        .withParameter(LootContextParams.ORIGIN, Vec3.atCenterOf(pos))
        );
        for(ItemStack stack: drops){
            level.addFreshEntity(new ItemEntity(level, pos.getX(), pos.getY(), pos.getZ(), stack));
        }
        return drops.size();
    }

    /**
     *
     * @return the number of drops
     */
    public static Integer collectOrDropItemsFromState(ServerLevel level, Player player, BlockState state, BlockPos pos){
        List<ItemStack> drops = state.getDrops(new LootParams.Builder(level).withParameter(LootContextParams.TOOL, player.getMainHandItem()).withParameter(LootContextParams.ORIGIN, Vec3.atCenterOf(pos)));
        return collectOrDropItemsFromState(level, player, pos, drops);
    }

    public static Integer collectOrDropItemsFromState(ServerLevel level, Player player, BlockPos pos, List<ItemStack> drops){
        if(player.hasInfiniteMaterials()){
            return 0;
        }
        for(ItemStack stack: drops){
            player.getInventory().add(stack);
            if(stack.isEmpty()){
                continue;
            }
            level.addFreshEntity(new ItemEntity(level, pos.getX(), pos.getY(), pos.getZ(), stack));
        }
        return drops.size();
    }

    public static Double getLocalizedRandom(BlockPos pos){
        int sum = pos.getX() * 228479 + pos.getY() * 780287 + pos.getZ() * 2470777;
        sum = sum ^ (sum >> 3) * 6610;
        sum = sum ^ (sum << 14) * 40366;
        sum = sum ^ (sum >> 1) * 71033;
        return Math.abs(sum / Math.pow(2, 31));
    }
}
