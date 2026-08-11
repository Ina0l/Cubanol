package net.Ina0_.cubanol.item;

import net.Ina0_.cubanol.Cubanol;
import net.Ina0_.cubanol.block.ModBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModCreativeModTabs {

    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, Cubanol.MOD_ID);

    public static final Supplier<CreativeModeTab> CROPS_TAB = CREATIVE_MODE_TABS.register(
            "crops_tab",
            () -> CreativeModeTab.builder()
                    .icon(() -> new ItemStack(ModItems.BLACK_GRAPE.get()))
                    .title(Component.translatable("creative_mode_tab.cubanol.crops_tab"))
                    .displayItems((parameters, output) -> {
                        output.accept(ModItems.BLACK_GRAPE);
                        output.accept(ModItems.WHITE_GRAPE);
                        output.accept(ModItems.BLACK_GRAPE_SEEDS);
                        output.accept(ModItems.WHITE_GRAPE_SEEDS);

                        output.accept(ModBlocks.CROP_SUPPORT);
                        output.accept(ModItems.WIRE);

                        output.accept(ModItems.AGAVE_SYRUP);
                        output.accept(ModItems.AGAVE_SEEDS);

                        output.accept(ModItems.RICE_PANICLE);
                        output.accept(ModItems.RICE);

                        output.accept(ModItems.STAR_ANISE);

                        output.accept(ModBlocks.GROWING_APPLE_TREE_LEAVES);
                        output.accept(ModBlocks.APPLE_TREE_SAPLING);

                        output.accept(ModItems.APPLE_SEEDS);
                    })
                    .build()
    );

    public static final Supplier<CreativeModeTab> DECORATIONS_TAB = CREATIVE_MODE_TABS.register(
            "decorations_tab",
            () -> CreativeModeTab.builder()
                    .icon(() -> new ItemStack(ModBlocks.OAK_TABLE.asItem()))
                    .title(Component.translatable("creative_mode_tab.cubanol.decorations_tab"))
                    .withTabsBefore(ResourceLocation.fromNamespaceAndPath(Cubanol.MOD_ID, "crops_tab"))
                    .displayItems(((parameters, output) -> {
                        output.accept(ModBlocks.APPLE_TREE_LOG);
                        output.accept(ModBlocks.APPLE_TREE_WOOD);
                        output.accept(ModBlocks.STRIPPED_APPLE_TREE_LOG);
                        output.accept(ModBlocks.STRIPPED_APPLE_TREE_WOOD);
                        output.accept(ModBlocks.APPLE_TREE_LEAVES);
                        output.accept(ModBlocks.APPLE_TREE_PLANKS);

                        output.accept(ModBlocks.APPLE_TREE_STAIR);
                        output.accept(ModBlocks.APPLE_TREE_SLAB);
                        output.accept(ModBlocks.APPLE_TREE_BUTTON);
                        output.accept(ModBlocks.APPLE_TREE_PRESSURE_PLATE);
                        output.accept(ModBlocks.APPLE_TREE_TRAPDOOR);
                        output.accept(ModBlocks.APPLE_TREE_DOOR);
                        output.accept(ModBlocks.APPLE_TREE_FENCE);
                        output.accept(ModBlocks.APPLE_TREE_FENCE_GATE);
                        output.accept(ModBlocks.APPLE_TREE_SIGN);

                        output.accept(ModBlocks.OAK_TABLE);
                        output.accept(ModBlocks.SPRUCE_TABLE);
                        output.accept(ModBlocks.BIRCH_TABLE);
                        output.accept(ModBlocks.JUNGLE_TABLE);
                        output.accept(ModBlocks.ACACIA_TABLE);
                        output.accept(ModBlocks.DARK_OAK_TABLE);
                        output.accept(ModBlocks.MANGROVE_TABLE);
                        output.accept(ModBlocks.CHERRY_TABLE);
                        output.accept(ModBlocks.BAMBOO_TABLE);
                        output.accept(ModBlocks.CRIMSON_TABLE);
                        output.accept(ModBlocks.WARPED_TABLE);
                        output.accept(ModBlocks.APPLE_TREE_TABLE);

                        output.accept(ModBlocks.FAKE_WINE_BOTTLE);
                    }))
                    .build()
    );

    public static void register(IEventBus eventBus){
        CREATIVE_MODE_TABS.register(eventBus);
    }

}
