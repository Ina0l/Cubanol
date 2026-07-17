package net.Ina0_.cubanol.item;

import net.Ina0_.cubanol.Cubanol;
import net.Ina0_.cubanol.block.ModBlocks;
import net.Ina0_.cubanol.item.custom.AgaveSyrupItem;
import net.Ina0_.cubanol.item.custom.GrapeSeedsItem;
import net.Ina0_.cubanol.item.custom.WireItem;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemNameBlockItem;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

//this is there to access the vanilla examples
import net.minecraft.world.item.Items;

public class ModItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(Cubanol.MOD_ID);

    public static final DeferredItem<Item> BLACK_GRAPE = ITEMS.register("black_grape", () -> new Item(
            new Item.Properties().food(
                    (new FoodProperties.Builder()).nutrition(2).saturationModifier(0.1F).build()
            )
    ));

    public static final DeferredItem<Item> WHITE_GRAPE = ITEMS.register("white_grape", () -> new Item(
            new Item.Properties().food(
                    (new FoodProperties.Builder()).nutrition(2).saturationModifier(0.1F).build()
            )
    ));

    public static final DeferredItem<Item> STAR_ANISE = ITEMS.register("star_anise", () -> new Item(
            new Item.Properties()
    ));

    public static final DeferredItem<ItemNameBlockItem> AGAVE_SEEDS = ITEMS.register("agave_seeds", () -> new ItemNameBlockItem(
            ModBlocks.AGAVE_CROP.get(),
            new Item.Properties()
    ));

    public static final DeferredItem<AgaveSyrupItem> AGAVE_SYRUP = ITEMS.register("agave_syrup", () -> new AgaveSyrupItem(
            new Item.Properties()
                    .food(new FoodProperties.Builder().nutrition(6).saturationModifier(0.1F).build())
    ));

    public static final DeferredItem<Item> WIRE = ITEMS.register("wire", () -> new WireItem(
            new Item.Properties()
    ));

    public static final DeferredItem<GrapeSeedsItem> BLACK_GRAPE_SEEDS = ITEMS.register("black_grape_seeds", () -> new GrapeSeedsItem(
            new Item.Properties(),
            false
    ));

    public static final DeferredItem<GrapeSeedsItem> WHITE_GRAPE_SEEDS = ITEMS.register("white_grape_seeds", () -> new GrapeSeedsItem(
            new Item.Properties(),
            true
    ));

    public static final DeferredItem<ItemNameBlockItem> RICE = ITEMS.register("rice", () -> new ItemNameBlockItem(
            ModBlocks.RICE_CROP.get(),
            new Item.Properties()
    ));

    public static final DeferredItem<Item> RICE_PANICLE = ITEMS.register("rice_panicle", () -> new Item(
            new Item.Properties()
    ));

    public static void register(IEventBus event_bus){
        ITEMS.register(event_bus);
    }
}
