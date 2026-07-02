package net.Ina0_.cubanol.block;

import net.Ina0_.cubanol.Cubanol;
import net.Ina0_.cubanol.block.custom.FakeBottle;
import net.Ina0_.cubanol.block.custom.Table;
import net.Ina0_.cubanol.item.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;


public class ModBlocks {
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(Cubanol.MOD_ID);


    public static final DeferredBlock<Block> TABLE = registerBlock("table", () -> new Table(
            BlockBehaviour.Properties.of()
                    .mapColor(Blocks.OAK_LOG.defaultMapColor())
                    .sound(SoundType.WOOD)
                    .isRedstoneConductor(ModBlocks::never)
                    .forceSolidOn()
                    .ignitedByLava()
                    .isViewBlocking(ModBlocks::never)
    ));

    public static final DeferredBlock<Block> FAKE_WINE_BOTTLE = registerBlock("fake_wine_bottle", () -> new FakeBottle(
            BlockBehaviour.Properties.of()
                    .sound(SoundType.GLASS)
                    .forceSolidOn()
                    .isRedstoneConductor(ModBlocks::never)
                    .forceSolidOn()
                    .offsetType(BlockBehaviour.OffsetType.XZ)
                    .dynamicShape()
                    .instabreak()
                    .strength(1.0F),
            () -> {
                VoxelShape base = Block.box(6.0, 0.0, 6.0, 9.0, 10.0, 9.0);
                VoxelShape neck = Block.box(7.0, 10.0, 7.0, 8.0, 15.0, 8.0);
                return Shapes.or(base, neck);
            }
    ));



    private static boolean never(BlockState state, BlockGetter blockGetter, BlockPos pos){
        return false;
    }


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
}
