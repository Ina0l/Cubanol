package net.Ina0_.cubanol.block.entity;

import net.Ina0_.cubanol.Cubanol;
import net.Ina0_.cubanol.block.ModBlocks;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

@SuppressWarnings("DataFlowIssue")
public class ModBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(BuiltInRegistries.BLOCK_ENTITY_TYPE, Cubanol.MOD_ID);


    public static final Supplier<BlockEntityType<TableBlockEntity>> TABLE_BLOCK_ENTITY =
            BLOCK_ENTITIES.register("table_block_entity", () -> BlockEntityType.Builder.of(
                    TableBlockEntity::new,
                    ModBlocks.OAK_TABLE.get(),
                    ModBlocks.SPRUCE_TABLE.get(),
                    ModBlocks.BIRCH_TABLE.get(),
                    ModBlocks.JUNGLE_TABLE.get(),
                    ModBlocks.ACACIA_TABLE.get(),
                    ModBlocks.DARK_OAK_TABLE.get(),
                    ModBlocks.MANGROVE_TABLE.get(),
                    ModBlocks.CHERRY_TABLE.get(),
                    ModBlocks.CRIMSON_TABLE.get(),
                    ModBlocks.WARPED_TABLE.get(),
                    ModBlocks.BAMBOO_TABLE.get(),
                    ModBlocks.APPLE_TABLE.get(),
                    ModBlocks.ORANGE_TABLE.get()
            ).build(null));


    public static void register(IEventBus eventBus){
        BLOCK_ENTITIES.register(eventBus);
    }
}
